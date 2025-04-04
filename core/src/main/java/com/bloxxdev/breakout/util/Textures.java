package com.bloxxdev.breakout.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Textures {

    public static final Texture CLOSE_TEXTURE = new Texture(Gdx.files.internal("PopupCloseButton.png"));
    public static final Texture CLOSE_TEXTURE_HOVER = new Texture(Gdx.files.internal("PopupCloseButtonHover.png"));

    public static final Texture WIDE_BUTTON_TEXTURE = new Texture(Gdx.files.internal("EmptyButton.png"));
    public static final Texture WIDE_BUTTON_TEXTURE_HOVER = new Texture(Gdx.files.internal("EmptyButtonHover.png"));
    
    public static final Texture MAP_TEXTURE = new Texture(Gdx.files.internal("Map.png"));

    public static final Texture LEVEL_BUTTON = new Texture(Gdx.files.internal("LevelButton.png"));
    public static final Texture LEVEL_BUTTON_HOVER = new Texture(Gdx.files.internal("LevelButtonHover.png"));
    public static final Texture LEVEL_BUTTON_LOCKED = new Texture(Gdx.files.internal("LevelButtonLocked.png"));

    public static final Texture NUMBER_FONT_TEXTURE = new Texture(Gdx.files.internal("Numbers.png"));

    public static final Texture PADDLE_TEXTURE = new Texture(Gdx.files.internal("Paddle.png"));
    public static final Texture BALL_TEXTXURE = new Texture(Gdx.files.internal("Ball.png"));
    public static final Texture BLOCK_TEXTXURE = new Texture(Gdx.files.internal("Block.png"));
}
