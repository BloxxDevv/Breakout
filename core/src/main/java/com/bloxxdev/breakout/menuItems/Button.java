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

    NumberFont numberFont;

    EventExecutor buttonAction;

    public Button (Texture buttonTexture, Texture buttonHoverTexture, int x, int y, String text, EventExecutor buttonAction){
        this.buttonTexture = buttonTexture;
        this.buttonHoverTexture = buttonHoverTexture;
        this.x = x;
        this.y = y;
        this.text = text;
        this.spriteBatch = new SpriteBatch();

        this.width = buttonTexture.getWidth();
        this.height = buttonTexture.getHeight();

        numberFont = new NumberFont();  
        this.buttonAction = buttonAction;
    }

    public void tick(){
        if (Gdx.input.getX() >= x && Gdx.input.getX() <= x+width && Gdx.graphics.getHeight() - Gdx.input.getY() >= y && Gdx.graphics.getHeight() - Gdx.input.getY() <= y+width) {
            currentTexture = buttonHoverTexture;
            if (Gdx.input.isTouched()) {
                buttonAction.execute();
            }
        }else{
            currentTexture = buttonTexture;
        }
    }

    public void render(){
        spriteBatch.begin();
        
        spriteBatch.draw(currentTexture, x, y);

        spriteBatch.end();

        numberFont.drawNumber(Integer.parseInt(text), (x + width/2) - (text.length()*NumberFont.FONT_WIDTH + (text.length()-1) * NumberFont.SPACING) / 2, (y + height/2) - NumberFont.FONT_HEIGHT/2, 1);
    }

    public void dispose(){
        buttonTexture.dispose();
        buttonHoverTexture.dispose();
    }
    
}
