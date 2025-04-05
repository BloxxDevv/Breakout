package com.bloxxdev.breakout.menuItems;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bloxxdev.breakout.util.Textures;

public class ButtonFont {
    
    SpriteBatch batch = new SpriteBatch();

    private int charWidth;
    private int charHeight;

    private int spacing;

    private int scaling;

    private Color fontColor = new Color(1.0F, 1.0F, 1.0F, 1.0F);

    TextureRegion[] characters = new TextureRegion[95];

    public ButtonFont(int charWidth, int charHeight, int spacing, int scaling){
        this.charWidth = charWidth;
        this.charHeight = charHeight;
        this.spacing = spacing;
        this.scaling = scaling;

        this.batch = new SpriteBatch();

        for (int i = 0; i < characters.length; i++) {
            characters[i] = new TextureRegion(Textures.FONT_MAP, (i%10) * charWidth, i/10 * charHeight, charWidth, charHeight);
        }
    }

    public int getStringWidth(String text){
        return (text.length() * charWidth + (text.length()-1) * spacing) * scaling;
    }

    public int getStringHeight(String text){
        return charHeight * scaling;
    }

    public void setFontColor(Color color){
        this.fontColor = color;
    }

    public void drawString(String text, int x, int y){
        batch.begin();

        batch.setColor(fontColor);

        for (int i = 0; i < text.length(); i++) {
            Sprite charSprite = new Sprite(characters[text.charAt(i)-32]);
            batch.draw(charSprite, x + ((i*charWidth + i*spacing) * scaling), (text.charAt(i) == 103 || text.charAt(i) == 106 || text.charAt(i) == 112 || text.charAt(i) == 113 || text.charAt(i) == 121) ? y - 3*scaling : y, 0, 0, charWidth, charHeight, scaling, scaling, 0);
        }

        batch.end();
    }

}
