package com.bloxxdev.breakout.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bloxxdev.breakout.screens.Breakout;

public class Block implements GameObject{
    public static final int WIDTH = 40;
    public static final int HEIGHT = 24;

    private int x;
    private int y;

    private Texture blockTexture;
    private SpriteBatch spriteBatch;

    private Color color;

    private int blockType;

    private AABB hitbox;

    public Block(int x, int y, int blockType){
        blockTexture = new Texture(Gdx.files.internal("Block.png"));
        spriteBatch = new SpriteBatch();

        this.x = x;
        this.y = y;

        this.hitbox = new AABB(x, y, WIDTH, HEIGHT);

        this.blockType = blockType;

        switch (blockType) {
            case 5:
                color = Color.RED;
                break;
            case 4:
                color = Color.BLUE;
                break;
            case 3:
                color = Color.GREEN;
                break;
            case 2:
                color = Color.YELLOW;
                break;
            case 1:
                color = Color.PURPLE;
                break;
            case -1:
                color = Color.GRAY;
                break;
        }
    }

    public AABB getHitbox() {
        return hitbox;
    }

    public void damageBlock(){
        blockType--;
        if (blockType == 0) {
            Breakout.blocks.remove(this);
        }
    }

    public int getBlockType() {
        return blockType;
    }

    @Override
    public void tick() {
        switch (blockType) {
            case 5:
                color = Color.RED;
                break;
            case 4:
                color = Color.BLUE;
                break;
            case 3:
                color = Color.GREEN;
                break;
            case 2:
                color = Color.YELLOW;
                break;
            case 1:
                color = Color.PURPLE;
                break;
        }
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
