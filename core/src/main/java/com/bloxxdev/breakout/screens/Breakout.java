package com.bloxxdev.breakout.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.bloxxdev.breakout.gameObjects.Ball;
import com.bloxxdev.breakout.gameObjects.GameObject;
import com.bloxxdev.breakout.gameObjects.Paddle;

public class Breakout extends ScreenAdapter{

    public static GameObject paddle;

    GameObject ball;

    public static boolean dead = false;
    public static boolean paused = true;
    
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
    }

    public void tick(){
        if (!dead){
            checkKeys();

            paddle.tick();
            ball.tick();
        }
    }

    @Override
    public void render(float delta) {
        paddle.render();
        ball.render();
    }

    @Override
    public void dispose() {
        paddle.dispose();
        ball.dispose();
    }

}
