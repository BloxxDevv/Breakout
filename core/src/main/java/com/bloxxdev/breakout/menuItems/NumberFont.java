package com.bloxxdev.breakout.menuItems;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class NumberFont {
        public static final int FONT_WIDTH = 20;
    public static final int FONT_HEIGHT = 30;

    public static final int SPACING = 7;

    private SpriteBatch spriteBatch;

    public static TextureRegion[] numFont = new TextureRegion[]{
        new TextureRegion(new Texture("Numbers.png"), 0, 0, FONT_WIDTH, FONT_HEIGHT), //0
        new TextureRegion(new Texture("Numbers.png"), FONT_WIDTH, 0, FONT_WIDTH, FONT_HEIGHT), //1
        new TextureRegion(new Texture("Numbers.png"), FONT_WIDTH*2, 0, FONT_WIDTH, FONT_HEIGHT), //2
        new TextureRegion(new Texture("Numbers.png"), FONT_WIDTH*3, 0, FONT_WIDTH, FONT_HEIGHT), //3
        new TextureRegion(new Texture("Numbers.png"), FONT_WIDTH*4, 0, FONT_WIDTH, FONT_HEIGHT), //4
        new TextureRegion(new Texture("Numbers.png"), 0, FONT_HEIGHT, FONT_WIDTH, FONT_HEIGHT), //5
        new TextureRegion(new Texture("Numbers.png"), FONT_WIDTH, FONT_HEIGHT, FONT_WIDTH, FONT_HEIGHT), //6
        new TextureRegion(new Texture("Numbers.png"), FONT_WIDTH*2, FONT_HEIGHT, FONT_WIDTH, FONT_HEIGHT), //7
        new TextureRegion(new Texture("Numbers.png"), FONT_WIDTH*3, FONT_HEIGHT, FONT_WIDTH, FONT_HEIGHT), //8
        new TextureRegion(new Texture("Numbers.png"), FONT_WIDTH*4, FONT_HEIGHT, FONT_WIDTH, FONT_HEIGHT), //9
    };

    public NumberFont(){
        spriteBatch = new SpriteBatch();
    }

    public void drawNumber(int num, int x, int y, int scaling){
        String numString = String.valueOf(num);
        
        spriteBatch.begin();

        for (int i = 0; i < numString.length(); i++) {
            spriteBatch.draw(numFont[Integer.parseInt(String.valueOf(numString.charAt(i)))], x + i*SPACING*scaling + i*FONT_WIDTH*scaling, y, FONT_WIDTH*scaling, FONT_HEIGHT*scaling);
        }

        spriteBatch.end();
    }
}
