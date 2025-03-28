package com.bloxxdev.breakout.gameObjects;

import java.time.format.SignStyle;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Ball implements GameObject {

    public static final int SIZE = 10;

    public static final int SPEED = 8;

    private ShapeRenderer renderer;

    private int x;
    private int y;

    public Ball(int x, int y){
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
        renderer.rect(x, y, SIZE, SIZE);
        renderer.end();
    }

    @Override
    public void dispose() {
        
    }
    
}
