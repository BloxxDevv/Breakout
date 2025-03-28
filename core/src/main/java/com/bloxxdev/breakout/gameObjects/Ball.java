package com.bloxxdev.breakout.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.bloxxdev.breakout.screens.Breakout;

public class Ball implements GameObject {

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public static final int SIZE = 10;

    public static final int SPEED = 8;

    private ShapeRenderer renderer;

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

        renderer = new ShapeRenderer();
    }

    public void setMovement(int direction, boolean value) {
        this.movement[direction] = value;
    }

    private void move(){
        if (movement[LEFT]) {
            if (x-SPEED >= 0) {
                x-=SPEED;
            }else {
                int dist = x;
                x -= dist - (SPEED - dist);

                movement[LEFT] = false;
                movement[RIGHT] = true;
            }
        }else if (movement[RIGHT]) {
            if (x+SPEED <= Gdx.graphics.getWidth() - SIZE) {
                x+=SPEED;
            }else{
                int dist = Gdx.graphics.getWidth() - x;
                x += dist - (SPEED - dist);

                movement[RIGHT] = false;
                movement[LEFT] = true;
            }
        }

        if (movement[UP]) {
            if (y+SPEED <= Gdx.graphics.getHeight()-SIZE) {
                y+=SPEED;
            }else{
                int dist = Gdx.graphics.getHeight() - y;
                y += dist - (SPEED - dist);

                movement[UP] = false;
                movement[DOWN] = true;
            }
        }

        if (movement[DOWN]) {
            if (y-SPEED >= SIZE) {
                y-=SPEED;
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
        renderer.begin(ShapeType.Filled);
        renderer.rect(x, y, SIZE, SIZE);
        renderer.end();
    }

    @Override
    public void dispose() {
        
    }
    
}
