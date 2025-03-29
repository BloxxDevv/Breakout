package com.bloxxdev.breakout.menuItems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {

    Texture buttonTexture;
    Texture buttonHoverTexture;
    int x;
    int y;
    String text;
    SpriteBatch spriteBatch;

    int width;
    int height;

    Texture currentTexture = buttonTexture;

    public Button (Texture buttonTexture, Texture buttonHoverTexture, int x, int y, String text){
        this.buttonTexture = buttonTexture;
        this.buttonHoverTexture = buttonHoverTexture;
        this.x = x;
        this.y = y;
        this.text = text;
        this.spriteBatch = new SpriteBatch();

        this.width = buttonTexture.getWidth();
        this.height = buttonTexture.getHeight();
    }

    public void tick(){
        if (Gdx.input.getX() >= x && Gdx.input.getX() <= x+width && Gdx.graphics.getHeight() - Gdx.input.getY() >= y && Gdx.graphics.getHeight() - Gdx.input.getY() <= y+width) {
            currentTexture = buttonHoverTexture;
        }else{
            currentTexture = buttonTexture;
        }
    }

    public void render(){
        spriteBatch.begin();
        
        spriteBatch.draw(currentTexture, x, y);

        spriteBatch.end();
    }

    public void dispose(){
        buttonTexture.dispose();
        buttonHoverTexture.dispose();
    }
    
}
