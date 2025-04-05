package com.bloxxdev.breakout.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.bloxxdev.breakout.Main;
import com.bloxxdev.breakout.menuItems.Button;
import com.bloxxdev.breakout.menuItems.EventExecutor;
import com.bloxxdev.breakout.menuItems.Popup;
import com.bloxxdev.breakout.util.Textures;

public class MainMenu extends ScreenAdapter{
    
    private static final int TITLE_SCALE = 3;

    public boolean shouldRender = false;
    public boolean shouldUpdate = false;

    private SpriteBatch batch;

    private Button startButton;
    private Button resetButton;
    private Button quitButton;

    private Popup confirmAction;

    @Override
    public void show() {
        shouldUpdate = true;
        shouldRender = true;

        batch = new SpriteBatch();

        startButton = new Button(Textures.WIDE_BUTTON_TEXTURE, Textures.WIDE_BUTTON_TEXTURE_HOVER, null, Gdx.graphics.getWidth()/2 - Textures.WIDE_BUTTON_TEXTURE.getWidth()/2, Gdx.graphics.getHeight()-250, "Start", new EventExecutor() {
            @Override
            public void execute() {
                Main.levelSelector = new LevelMenu();
                Main.levelSelector.show();
                hide();
            }
        });

        startButton.setLocked(false);

        resetButton = new Button(Textures.WIDE_BUTTON_TEXTURE, Textures.WIDE_BUTTON_TEXTURE_HOVER, null, Gdx.graphics.getWidth()/2 - Textures.WIDE_BUTTON_TEXTURE.getWidth()/2, Gdx.graphics.getHeight()-350, "Reset Data", new EventExecutor() {
            @Override
            public void execute() {
                int popupWidth = 500;
                int popupHeight = 250;

                isPopupActive = true;

                Color popupBackground = new Color(0.3F, 0.3F, 0.3F, 0.8F);

                confirmAction = new Popup(Gdx.graphics.getWidth()/2 - popupWidth/2, Gdx.graphics.getHeight()/2 - popupHeight/2, popupWidth, popupHeight, popupBackground, "Delete all Data?", new EventExecutor() {
                    @Override
                    public void execute() {
                        Main.instance.createNewPlayerDataFile();
                    }
                });
                confirmAction.show();
            }
        });

        resetButton.setLocked(false);

        quitButton = new Button(Textures.WIDE_BUTTON_TEXTURE, Textures.WIDE_BUTTON_TEXTURE_HOVER, null, Gdx.graphics.getWidth()/2 - Textures.WIDE_BUTTON_TEXTURE.getWidth()/2, Gdx.graphics.getHeight()-450, "Quit", new EventExecutor() {
            @Override
            public void execute() {
                Main.instance.dispose();
            }
        });

        quitButton.setLocked(false);
    }

    public boolean isPopupActive = false;

    public void tick(){
        if (shouldUpdate) {
            
            if (!isPopupActive) {
                startButton.tick();            
                resetButton.tick();
                quitButton.tick();   
            }

            if (confirmAction != null) {
                confirmAction.tick();
            }
        }
    }

    @Override
    public void render(float delta) {
        if (shouldRender) {
            batch.begin();

            batch.draw(new TextureRegion(Textures.BREAKOUT_TITLE), Gdx.graphics.getWidth()/2 - Textures.BREAKOUT_TITLE.getWidth()*TITLE_SCALE/2, Gdx.graphics.getHeight() - 120, 0, 0, Textures.BREAKOUT_TITLE.getWidth(), Textures.BREAKOUT_TITLE.getHeight(), TITLE_SCALE, TITLE_SCALE, 0);

            batch.end();

            startButton.render();
            resetButton.render();
            quitButton.render();
            
            if (confirmAction != null) {
                confirmAction.render();
            }
        }
    }

    @Override
    public void hide() {
        shouldUpdate = false;
        shouldRender = false;
        dispose();
    }

    @Override
    public void dispose() {

    }
}
