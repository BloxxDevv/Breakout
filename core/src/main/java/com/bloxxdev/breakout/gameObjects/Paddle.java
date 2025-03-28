package com.bloxxdev.breakout.gameObjects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Paddle implements GameObject{

    public static int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 10;

    public static final int SPEED = 5;
    public static final int SCALE = 5;

    private int x;
    private int y;

    private ShapeRenderer renderer;

    public Paddle(int x, int y){
        this.x = x;
        this.y = y;

        renderer = new ShapeRenderer();
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render() {
        renderer.begin(ShapeType.Filled);

        renderer.rect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);

        renderer.end();
    }

    @Override
    public void dispose() {
        
    }

}
