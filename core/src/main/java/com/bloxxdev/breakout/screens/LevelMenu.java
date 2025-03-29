package com.bloxxdev.breakout.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bloxxdev.breakout.menuItems.Button;

public class LevelMenu extends ScreenAdapter{
    
    Texture mapTexture;

    Texture levelButton;
    Texture levelButtonHover;

    SpriteBatch spriteBatch;
    
    Button[] buttons = new Button[5];

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        mapTexture = new Texture(Gdx.files.internal("Map.png"));
        levelButton = new Texture(Gdx.files.internal("LevelButton.png"));
        levelButtonHover = new Texture(Gdx.files.internal("LevelButtonHover.png"));

        buttons[0] = new Button(levelButton, levelButtonHover, 160, 230, "1");
        buttons[1] = new Button(levelButton, levelButtonHover, 240, 430, "2");
        buttons[2] = new Button(levelButton, levelButtonHover, 305, 270, "3");
        buttons[3] = new Button(levelButton, levelButtonHover, 510, 250, "4");
        buttons[4] = new Button(levelButton, levelButtonHover, 640, 400, "5");
    }

    public void tick(){
        for (Button button : buttons) {
            button.tick();
        }
    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();

        spriteBatch.draw(mapTexture, 0, 0);
    
        spriteBatch.end();

        for (Button button : buttons) {
            button.render();
        }
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        
    }

}
