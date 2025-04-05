package com.bloxxdev.breakout;

import java.io.File;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.bloxxdev.breakout.screens.Breakout;
import com.bloxxdev.breakout.screens.LevelMenu;
import com.bloxxdev.breakout.screens.MainMenu;
import com.bloxxdev.breakout.util.FileHandler;

public class Main extends ApplicationAdapter {

    public static Breakout breakout;
    public static LevelMenu levelSelector;
    public static MainMenu mainMenu;

    public static Main instance;

    private File playerData = new File(System.getenv("APPDATA") + "/bloxxdev/breakout/saves.dat");

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

        if (!playerData.exists()) {
            createNewPlayerDataFile();
        }

        Gdx.gl.glClearColor(20/255.0F, 20/255.0F, 100/255.0F, 0);

        mainMenu = new MainMenu();
        mainMenu.show();
    }

    public void createNewPlayerDataFile(){
        FileHandler.writeFile(playerData, 1);
    }

    public File getPlayerData() {
        return playerData;
    }

    public void tick(){
        mainMenu.tick();

        if (levelSelector != null) {
            levelSelector.tick();
        }

        if (breakout != null) {
            breakout.tick();
        }
    }

    @Override
    public void render() {
        tick();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainMenu.render(0);

        if (levelSelector != null) {
            levelSelector.render(0);
        }

        if (breakout != null) {
            breakout.render(0);
        }
    }

    @Override
    public void dispose() {
        mainMenu.dispose();
        if (levelSelector != null) {
            levelSelector.dispose();
        }
        if (breakout != null) {
            breakout.dispose();
        }
        System.exit(0);
    }
}
