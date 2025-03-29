package com.bloxxdev.breakout.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Block implements GameObject{
    public static final int WIDTH = 40;
    public static final int HEIGHT = 24;

    private int x;
    private int y;

    private Texture blockTexture;
    private SpriteBatch spriteBatch;

    private Color color;

    public Block(int x, int y, Color color){
        blockTexture = new Texture(Gdx.files.internal("Block.png"));
        spriteBatch = new SpriteBatch();

        this.x = x;
        this.y = y;
        this.color = color;

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public void tick() {

    }

    @Override
    public void render() {
        spriteBatch.begin();
        spriteBatch.setColor(color);
        spriteBatch.draw(blockTexture, x, y, WIDTH, HEIGHT);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
    
}
