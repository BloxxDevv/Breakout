package com.bloxxdev.breakout;

import java.io.File;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.bloxxdev.breakout.screens.Breakout;
import com.bloxxdev.breakout.screens.LevelMenu;

public class Main extends ApplicationAdapter {

    public static Breakout breakout;
    public static LevelMenu levelSelector;

    public static Main instance;

    public static File[] levels = new File[]{
        new File("core/levels/level1.txt"),
        new File("core/levels/level2.txt"),
        new File("core/levels/level3.txt"),
        new File("core/levels/level4.txt"),
        new File("core/levels/level5.txt")
    };

    @Override
    public void create() {
        instance = this;

        Gdx.gl.glClearColor(20/255.0F, 20/255.0F, 100/255.0F, 0);

        levelSelector = new LevelMenu();
        levelSelector.show();
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
