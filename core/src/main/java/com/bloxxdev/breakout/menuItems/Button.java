package com.bloxxdev.breakout.menuItems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {

    Texture buttonTexture;
    Texture buttonHoverTexture;
    Texture buttonLockedTexture;
    int x;
    int y;
    String text;
    SpriteBatch spriteBatch;

    int width;
    int height;

    Texture currentTexture = buttonTexture;

    NumberFont numberFont;
    ButtonFont buttonFont;

    EventExecutor buttonAction;

    private boolean locked = true;

    BitmapFont font;

    public Button (Texture buttonTexture, Texture buttonHoverTexture, Texture buttonLockedTexture, int x, int y, String text, EventExecutor buttonAction){
        this.buttonTexture = buttonTexture;
        this.buttonHoverTexture = buttonHoverTexture;
        this.buttonLockedTexture = buttonLockedTexture;
        this.x = x;
        this.y = y;
        this.text = text;
        this.spriteBatch = new SpriteBatch();

        this.width = buttonTexture.getWidth();
        this.height = buttonTexture.getHeight();

        numberFont = new NumberFont();  
        this.buttonFont = new ButtonFont(6, 8, 1, 4);
        this.buttonAction = buttonAction;

        font = new BitmapFont();
    }

    public void tick(){
        if (locked) {
            currentTexture = buttonLockedTexture;
        }else{
            if (Gdx.input.getX() >= x && Gdx.input.getX() <= x+width && Gdx.graphics.getHeight() - Gdx.input.getY() >= y && Gdx.graphics.getHeight() - Gdx.input.getY() <= y+height) {
                currentTexture = buttonHoverTexture;
                if (Gdx.input.isTouched()) {
                    buttonAction.execute();
                }
            }else{
                currentTexture = buttonTexture;
            }
        }
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void render(){
        spriteBatch.begin();
        
        spriteBatch.draw(currentTexture, x, y);
        
        spriteBatch.end();

        if (isNumberString(text)) {
            numberFont.drawNumber(Integer.parseInt(text), (x + width/2) - (text.length()*NumberFont.FONT_WIDTH + (text.length()-1) * NumberFont.SPACING) / 2, (y + height/2) - NumberFont.FONT_HEIGHT/2, 1);
        }else{
            this.buttonFont.setFontColor(new Color(0.3F, 0.3F, 0.3F, 1.0F));
            this.buttonFont.drawString(text, x + width/2 - buttonFont.getStringWidth(text)/2, y + height/2 - buttonFont.getStringHeight(text)/2);
        }

    }

    public void dispose(){
        buttonTexture.dispose();
        buttonHoverTexture.dispose();
    }

    private boolean isNumberString(String text){
        try{
            Double.parseDouble(text);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }
    
}
