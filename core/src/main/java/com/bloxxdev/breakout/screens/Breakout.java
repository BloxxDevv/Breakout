package com.bloxxdev.breakout.screens;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.bloxxdev.breakout.gameObjects.Ball;
import com.bloxxdev.breakout.gameObjects.Block;
import com.bloxxdev.breakout.gameObjects.GameObject;
import com.bloxxdev.breakout.gameObjects.Paddle;

public class Breakout extends ScreenAdapter{

    public static GameObject paddle;

    GameObject ball;

    public static boolean dead = false;
    public static boolean paused = true;

    public static ArrayList<Block> blocks = new ArrayList<>();

    private boolean shouldLoop = false;

    private int level = 0;
    
    public Breakout(File data, int level){
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

        this.level = level;
    }

    private void checkKeys(){
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            ((Paddle) paddle).setMovement(Paddle.LEFT, true);
            if (paused) {
                paused = false;
                ((Ball) ball).setMovement(Ball.LEFT, true);
                ((Ball) ball).setMovement(Ball.UP, true);
            }
        }else{
            ((Paddle) paddle).setMovement(Paddle.LEFT, false);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
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


    @Override
    public void show() {
        Gdx.gl.glClearColor(20/255.0F, 20/255.0F, 100/255.0F, 0);

        paddle = new Paddle(Gdx.graphics.getWidth()/2 - Paddle.PADDLE_WIDTH/2, 0);
        ball = new Ball(Gdx.graphics.getWidth()/2 - Ball.SIZE/2, Paddle.PADDLE_HEIGHT);
        shouldLoop = true;
    }

    public void tick(){
        if (!dead && shouldLoop){
            checkKeys();

            paddle.tick();
            ball.tick();
            for (Block b : blocks) {
                b.tick();
            }
        }
    }

    @Override
    public void render(float delta) {
        if (shouldLoop) {
            paddle.render();
            ball.render();
            for (Block b : blocks) {
                b.render();
            }
        }
    }

    @Override
    public void dispose() {
        shouldLoop = false;
        paddle.dispose();
        ball.dispose();
        for (Block b : blocks) {
            b.dispose();
        }
    }

}
