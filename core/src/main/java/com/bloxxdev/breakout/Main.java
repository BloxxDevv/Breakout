package com.bloxxdev.breakout;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.bloxxdev.breakout.screens.Breakout;

public class Main extends ApplicationAdapter {

    Breakout breakout;

    @Override
    public void create() {
        Gdx.gl.glClearColor(20/255.0F, 20/255.0F, 100/255.0F, 0);

        breakout = new Breakout();
        breakout.show();
    }

    public void tick(){
        breakout.tick();
    }

    @Override
    public void render() {
        tick();

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        breakout.render(0);
    }

    @Override
    public void dispose() {
        breakout.dispose();
        System.exit(0);
    }
}
