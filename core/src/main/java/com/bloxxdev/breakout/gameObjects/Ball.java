package com.bloxxdev.breakout.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bloxxdev.breakout.screens.Breakout;

public class Ball implements GameObject {

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public static final int SIZE = 10;

    public static final int SPEED = 6;

    private SpriteBatch spriteBatch;

    private Texture ballTexture;

    private int x;
    private int y;

    private boolean[] movement = new boolean[]
    {
        false,
        false,
        false,
        false
    };

    public Ball(int x, int y){
        this.x = x;
        this.y = y;

        ballTexture = new Texture(Gdx.files.internal("Ball.png"));
        spriteBatch = new SpriteBatch();
    }

    public void setMovement(int direction, boolean value) {
        this.movement[direction] = value;
    }

    private boolean checkBlocks(int direction){
        boolean collided = false;

        int blockX = 0;
        int blockY = 0;

        Block[] blocksArr = new Block[Breakout.blocks.size()];

        int c = 0;

        for (Block b : Breakout.blocks) {
            if (direction == UP) {
                if (y+SIZE+SPEED >= b.getY() && y+SIZE <= b.getY()){
                    if (x <= b.getX()+Block.WIDTH && x+SIZE >= b.getX()) {
                        blockY = b.getY();
                        collided = true;
                        if (b.getBlockType() > 1){
                            b.damageBlock();
                        }else if (b.getBlockType() > -1){
                            blocksArr[c++] = b;
                        }
                    }
                }
            }
            if (direction == LEFT) {
                if (x-SPEED <= b.getX()+Block.WIDTH && x >= b.getX()+Block.HEIGHT){
                    if (y <= b.getY()+Block.HEIGHT && y+SIZE >= b.getY()) {
                        blockX = b.getX();
                        collided = true;
                        if (b.getBlockType() > 1){
                            b.damageBlock();
                        }else if (b.getBlockType() > -1){
                            blocksArr[c++] = b;
                        }
                    }
                }
            }
            if (direction == RIGHT) {
                if (x+SIZE+SPEED >= b.getX() && x+SIZE <= b.getX()) {
                    if (y <= b.getY()+Block.HEIGHT && y+SIZE >= b.getY()) {
                        blockX = b.getX();
                        collided = true;
                        if (b.getBlockType() > 1){
                            b.damageBlock();
                        }else if (b.getBlockType() > -1){
                            blocksArr[c++] = b;
                        }
                    }
                }
            }
            if (direction == DOWN) {
                if (y-SPEED <= b.getY()+Block.HEIGHT && y >= b.getY()+Block.HEIGHT) {
                    if (x <= b.getX()+Block.WIDTH && x+SIZE >= b.getX()) {
                        blockY = b.getY();
                        collided = true;
                        if (b.getBlockType() > 1){
                            b.damageBlock();
                        }else if (b.getBlockType() > -1){
                            blocksArr[c++] = b;
                        }
                    }
                }
            }
        }

        for (Block block : blocksArr) {
            Breakout.blocks.remove(block);
        }

        if (collided) {
            if (direction == UP) {
                int dist = blockY - (y+SIZE);
                y += dist - (SPEED - dist);

                movement[UP] = false;
                movement[DOWN] = true;
            }
            if (direction == LEFT) {
                int dist = x-(blockX+Block.WIDTH);
                x -= dist - (SPEED - dist);

                movement[LEFT] = false;
                movement[RIGHT] = true;
            }
            if (direction == RIGHT) {
                int dist = blockX - (x+SIZE);
                x += dist - (SPEED - dist);

                movement[RIGHT] = false;
                movement[LEFT] = true;
            }
            if (direction == DOWN) {
                int dist = y-(blockY+Block.HEIGHT);
                y-= dist - (SPEED - dist);

                movement[DOWN] = false;
                movement[UP] = true;
            }
        }

        return collided;
    }

    private void move(){
        if (movement[LEFT]) {
            if (x-SPEED >= 0) {
                if (!checkBlocks(LEFT)) {
                    x-=SPEED;
                }
            }else {
                int dist = x;
                x -= dist - (SPEED - dist);

                movement[LEFT] = false;
                movement[RIGHT] = true;
            }
        }else if (movement[RIGHT]) {
            if (x+SPEED <= Gdx.graphics.getWidth() - SIZE) {
                if (!checkBlocks(RIGHT)) {
                    x+=SPEED;
                }
            }else{
                int dist = Gdx.graphics.getWidth() - x;
                x += dist - (SPEED - dist);

                movement[RIGHT] = false;
                movement[LEFT] = true;
            }
        }

        if (movement[UP]) {
            if (y+SPEED <= Gdx.graphics.getHeight()-SIZE) {
                if (!checkBlocks(UP)) {
                    y+=SPEED;
                }
            }else{
                int dist = Gdx.graphics.getHeight() - y;
                y += dist - (SPEED - dist);

                movement[UP] = false;
                movement[DOWN] = true;
            }
        }else if (movement[DOWN]) {
            if (y-SPEED >= SIZE) {
                if (!checkBlocks(DOWN)) {
                    y-=SPEED;
                }
            }else if (x+SIZE >= ((Paddle)Breakout.paddle).getX() && x <= ((Paddle)Breakout.paddle).getX() + Paddle.PADDLE_WIDTH){
                int dist = y-SIZE;
                y-= dist - (SPEED - dist);

                movement[DOWN] = false;
                movement[UP] = true;
            }else{
                y-=SPEED;
                Breakout.dead = true;
                for (int i = 0; i < movement.length; i++) {
                    movement[i] = false;
                }
            }
        }
    }

    @Override
    public void tick() {
        move();
    }

    @Override
    public void render() {
        spriteBatch.begin();
        spriteBatch.draw(ballTexture, x, y, SIZE, SIZE);
        spriteBatch.end();
    }

    @Override
    public void dispose() {
        
    }
    
}
