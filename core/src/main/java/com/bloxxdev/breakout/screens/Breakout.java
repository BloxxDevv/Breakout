package com.bloxxdev.breakout.screens;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.bloxxdev.breakout.Main;
import com.bloxxdev.breakout.gameObjects.Ball;
import com.bloxxdev.breakout.gameObjects.Block;
import com.bloxxdev.breakout.gameObjects.GameObject;
import com.bloxxdev.breakout.gameObjects.Paddle;
import com.bloxxdev.breakout.menuItems.Button;
import com.bloxxdev.breakout.menuItems.EventExecutor;
import com.bloxxdev.breakout.util.FileHandler;
import com.bloxxdev.breakout.util.Textures;

public class Breakout extends ScreenAdapter{

    public static GameObject paddle;

    GameObject ball;

    private boolean dead = false;
    private boolean paused = true;

    public ArrayList<Block> blocks = new ArrayList<>();

    private boolean shouldLoop = false;
    private boolean shouldRender = false;

    public static int level = (int) FileHandler.readFile(Main.instance.getPlayerData());

    boolean escLock = false;

    ShapeRenderer renderer;

    Button pauseCloseButton;

    Button restartBtn;
    Button menuBtn;
    
    private int currentLevel;

    public Breakout(File data, int level){
        this.currentLevel = level;
        try{
            BufferedReader br = new BufferedReader(new FileReader(data));
            while (br.ready()) {           
                String blockData = br.readLine().trim();

                if (blockData.startsWith("#") || blockData.isBlank()){
                    continue;
                }

                int x = Integer.parseInt(blockData.split(";")[0]);
                int y = Integer.parseInt(blockData.split(";")[1]);
                String color = blockData.split(";")[2];

                int type = 0;

                switch (color) {
                    case "RED":
                        type = 5;
                        break;
                    case "BLUE":
                        type = 4;
                        break;
                    case "GREEN":
                        type = 3;
                        break;
                    case "YELLOW":
                        type = 2;
                        break;
                    case "PURPLE":
                        type = 1;
                        break;
                    case "GRAY":
                        type = -1;
                        break;
                }

                blocks.add(new Block(x, Gdx.graphics.getHeight() - Block.HEIGHT - y, type));
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        this.renderer = new ShapeRenderer();
    }

    private void checkKeys(){
        if (shouldLoop) {
            if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                ((Paddle) paddle).setMovement(Paddle.LEFT, true);
                if (paused) {
                    paused = false;
                    ((Ball) ball).setMovement(Ball.LEFT, true);
                    ((Ball) ball).setMovement(Ball.UP, true);
                }
            }else{
                ((Paddle) paddle).setMovement(Paddle.LEFT, false);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                ((Paddle) paddle).setMovement(Paddle.RIGHT, true);
                if (paused) {
                    paused = false;
                    ((Ball) ball).setMovement(Ball.RIGHT, true);
                    ((Ball) ball).setMovement(Ball.UP, true);
                }
            }else{
                ((Paddle) paddle).setMovement(Paddle.RIGHT, false);
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && !escLock) {
            paused = !paused;
            shouldLoop = !shouldLoop;
            escLock = true;
            drawPauseMenu = !drawPauseMenu;
        }

        if (!Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            escLock = false;
        }
    }

    private boolean drawPauseMenu = false;

    @Override
    public void show() {        
        pauseCloseButton = new Button(Textures.CLOSE_TEXTURE, Textures.CLOSE_TEXTURE_HOVER, null, 550, 500, "", new EventExecutor() {
           @Override
           public void execute() {
            paused = !paused;
            shouldLoop = !shouldLoop;
            drawPauseMenu = !drawPauseMenu;
           } 
        });

        restartBtn = new Button(Textures.WIDE_BUTTON_TEXTURE, Textures.WIDE_BUTTON_TEXTURE_HOVER, null, Gdx.graphics.getWidth()/2-Textures.WIDE_BUTTON_TEXTURE.getWidth()/2, 300, "Restart", new EventExecutor() {
            @Override
            public void execute() {
                shouldRender = false;
                Main.breakout = new Breakout(Main.levels[level-1], level);
                Main.breakout.show();
                hide();
                System.gc();
            }
        });

        menuBtn = new Button(Textures.WIDE_BUTTON_TEXTURE, Textures.WIDE_BUTTON_TEXTURE_HOVER, null, Gdx.graphics.getWidth()/2-Textures.WIDE_BUTTON_TEXTURE.getWidth()/2, 150, "Main menu", new EventExecutor() {
            @Override
            public void execute() {
                shouldRender = false;
                Main.breakout = null;
                hide();
                LevelMenu.shouldRender = true;
                System.gc();
            }
        });

        pauseCloseButton.setLocked(false);
        restartBtn.setLocked(false);
        menuBtn.setLocked(false);

        Gdx.gl.glClearColor(20/255.0F, 20/255.0F, 100/255.0F, 0);

        paddle = new Paddle(Gdx.graphics.getWidth()/2 - Paddle.PADDLE_WIDTH/2, 0);
        ball = new Ball(Gdx.graphics.getWidth()/2 - Ball.SIZE/2, Paddle.PADDLE_HEIGHT);
        shouldLoop = true;
        shouldRender = true;
    }

    private boolean won(){
        for (Block block : blocks) {
            if (block.getBlockType() > 0) {
                return false;
            }
        }

        blocks = new ArrayList<>();

        return true;
    }

    public void winAnimation(){
        shouldLoop = false;
        if (level < 5 && currentLevel == level) {
            FileHandler.writeFile(Main.instance.getPlayerData(), ++level);   
        }
        hide();
        Main.breakout = null;
        LevelMenu.shouldRender = true;
        paused = true;
    }

    public void deathAnimation(){
        dead = false;
        Main.breakout.shouldLoop = false;
        Main.breakout.paused = true;
        hide();
        LevelMenu.shouldRender = true;
    }

    public void die(){
        dead = true;
    }

    int timer = 0;
    float scale = 0.1F;

    public void tick(){
        if (won() && !LevelMenu.shouldRender) {
            if (timer < 300) {
                scale += 0.015F;
                timer++;
            }else{
                winAnimation();
            }
            return;
        }

        if (dead) {
            if (timer < 300) {
                scale += 0.015F;
                timer++;
            }else{
                deathAnimation();
            }
            return;
        }

        checkKeys();

        if (!dead && shouldLoop && !paused){
            paddle.tick();
            ball.tick();
            for (Block b : blocks) {
                b.tick();
            }
        }

        if (drawPauseMenu) {
            pauseCloseButton.tick();
            restartBtn.tick();
            menuBtn.tick();
        }
    }

    @Override
    public void pause() {
        if (!drawPauseMenu) {
            paused = !paused;
            shouldLoop = !shouldLoop;
            drawPauseMenu = !drawPauseMenu;
        }
    }

    public static final int PAUSE_MENU_WIDTH = 400;
    public static final int PAUSE_MENU_HEIGHT = 500;

    SpriteBatch batch = new SpriteBatch();

    @Override
    public void render(float delta) {
        if (won()) {
            batch.begin();
            batch.draw(new TextureRegion(Textures.LEVEL_COMPLETED), Gdx.graphics.getWidth()/2 - (Textures.LEVEL_COMPLETED.getWidth()*scale)/2, Gdx.graphics.getHeight()/2 - (Textures.LEVEL_COMPLETED.getHeight()*scale)/2, 0, 0, Textures.LEVEL_COMPLETED.getWidth(), Textures.LEVEL_COMPLETED.getHeight(), scale, scale, 0.0F);
            batch.end();
        }else if (dead) {
            batch.begin();
            batch.draw(new TextureRegion(Textures.GAME_OVER), Gdx.graphics.getWidth()/2 - (Textures.GAME_OVER.getWidth()*scale)/2, Gdx.graphics.getHeight()/2 - (Textures.GAME_OVER.getHeight()*scale)/2, 0, 0, Textures.GAME_OVER.getWidth(), Textures.GAME_OVER.getHeight(), scale, scale, 0.0F);
            batch.end();
        }else{

            if (shouldRender) {
                paddle.render();
                ball.render();
                for (Block b : blocks) {
                    b.render();
                }

                if (drawPauseMenu) {
                    Gdx.gl20.glEnable(GL20.GL_BLEND);

                    renderer.begin(ShapeType.Filled);

                    renderer.setColor(0.3F, 0.3F, 0.3F, 0.6F);
                    renderer.rect(Gdx.graphics.getWidth()/2-PAUSE_MENU_WIDTH/2, Gdx.graphics.getHeight()/2-PAUSE_MENU_HEIGHT/2, PAUSE_MENU_WIDTH, PAUSE_MENU_HEIGHT);

                    renderer.end();

                    Gdx.gl20.glDisable(GL20.GL_BLEND);

                    pauseCloseButton.render();
                    restartBtn.render();
                    menuBtn.render();

                }
            }

        }
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        shouldLoop = false;
        shouldRender = false;
        paddle.dispose();
        ball.dispose();
        for (Block b : blocks) {
            b.dispose();
        }
    }

}
