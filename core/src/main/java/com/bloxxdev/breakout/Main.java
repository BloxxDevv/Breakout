package com.bloxxdev.breakout;

import java.io.File;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.bloxxdev.breakout.screens.Breakout;
import com.bloxxdev.breakout.screens.LevelMenu;

public class Main extends ApplicationAdapter {

    Breakout breakout;
    LevelMenu levelSelector;

    File[] levels = new File[]{
        new File("core/levels/level1.txt")
    };

    @Override
    public void create() {
        Gdx.gl.glClearColor(20/255.0F, 20/255.0F, 100/255.0F, 0);

        levelSelector = new LevelMenu();
        levelSelector.show();

        //breakout = new Breakout(levels[0]);
        //breakout.show();
    }

    public void tick(){
        levelSelector.tick();

        if (breakout != null) {
            breakout.tick();
        }
    }

    @Override
    public void render() {
        tick();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        levelSelector.render(0);

        if (breakout != null) {
            breakout.render(0);
        }
    }

    @Override
    public void dispose() {
        if (breakout != null) {
            breakout.dispose();
        }
        System.exit(0);
    }
}
