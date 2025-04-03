package com.bloxxdev.breakout.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bloxxdev.breakout.Main;
import com.bloxxdev.breakout.menuItems.Button;
import com.bloxxdev.breakout.menuItems.EventExecutor;

public class LevelMenu extends ScreenAdapter{
    boolean shouldRender = false;

    Texture mapTexture;

    Texture levelButton;
    Texture levelButtonHover;
    Texture levelButtonLocked;

    SpriteBatch spriteBatch;
    
    public static Button[] buttons = new Button[5];

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        mapTexture = new Texture(Gdx.files.internal("Map.png"));
        levelButton = new Texture(Gdx.files.internal("LevelButton.png"));
        levelButtonHover = new Texture(Gdx.files.internal("LevelButtonHover.png"));
        levelButtonLocked = new Texture(Gdx.files.internal("LevelButtonLocked.png"));

        buttons[0] = new Button(levelButton, levelButtonHover, levelButtonLocked, 160, 230, "1", new EventExecutor() {
            @Override
            public void execute() {
                hide();
                Main.breakout = new Breakout(Main.levels[0], 1);
                Main.breakout.show();
            }
        });
        buttons[1] = new Button(levelButton, levelButtonHover, levelButtonLocked, 240, 430, "2", new EventExecutor() {
            @Override
            public void execute() {
                hide();
                Main.breakout = new Breakout(Main.levels[1], 2);
                Main.breakout.show();
            }
        });
        buttons[2] = new Button(levelButton, levelButtonHover, levelButtonLocked, 305, 270, "3", new EventExecutor() {
            @Override
            public void execute() {
                hide();
                Main.breakout = new Breakout(Main.levels[2], 3);
                Main.breakout.show();
            }
        });
        buttons[3] = new Button(levelButton, levelButtonHover, levelButtonLocked, 510, 250, "4", new EventExecutor() {
            @Override
            public void execute() {
                hide();
                Main.breakout = new Breakout(Main.levels[3], 4);
                Main.breakout.show();
            }
        });
        buttons[4] = new Button(levelButton, levelButtonHover, levelButtonLocked, 640, 400, "5", new EventExecutor() {
            @Override
            public void execute() {
                hide();
                Main.breakout = new Breakout(Main.levels[4], 5);
                Main.breakout.show();
            }
        });
        shouldRender = true;
    }

    public void tick(){
        for (int i = 0; i < Breakout.level; i++) {
            LevelMenu.buttons[i].setLocked(false);
        }

        if (shouldRender) {
            for (Button button : buttons) {
                button.tick();
            }
        }
    }

    @Override
    public void render(float delta) {
        if (shouldRender) {
            spriteBatch.begin();

            spriteBatch.draw(mapTexture, 0, 0);
        
            spriteBatch.end();
    
            for (Button button : buttons) {
                button.render();
            }
        }
    }

    @Override
    public void hide() {
        shouldRender = false;
        dispose();
    }

    @Override
    public void dispose() {
        
    }

}
