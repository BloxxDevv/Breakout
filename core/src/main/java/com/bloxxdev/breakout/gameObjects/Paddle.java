package com.bloxxdev.breakout.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Paddle implements GameObject{

    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    public static int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 10;

    public static final int SPEED = 8;

    private int x;
    private int y;

    private SpriteBatch spriteBatch;

    private Texture paddleTexture;

    private boolean[] movement = new boolean[]
    {
        false,
        false
    };

    public Paddle(int x, int y){
        this.x = x;
        this.y = y;

        paddleTexture = new Texture(Gdx.files.internal("Paddle.png"));
        spriteBatch = new SpriteBatch();
    }

    public int getX() {
        return x;
    }

    public void setMovement(int direction, boolean value) {
        this.movement[direction] = value;
    }

    //Move player if not at border. if movement would pass the border, set pos at border
    private void move(){
        if (movement[LEFT] != movement[RIGHT]) {
            if (movement[LEFT]) {
                if (x-SPEED >= 0) {
                    x-=SPEED;
                }else {
                    x = 0;
                }
            }else{
                if (x+SPEED <= Gdx.graphics.getWidth() - Paddle.PADDLE_WIDTH) {
                    x+=SPEED;
                }else{
                    x = Gdx.graphics.getWidth() - Paddle.PADDLE_WIDTH;
                }
            }
        }
    }

    @Override
    public void tick() {
        move();
    }

    @Override
    public void render() {
        spriteBatch.begin();

        spriteBatch.draw(paddleTexture, x, y, PADDLE_WIDTH, PADDLE_HEIGHT);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        
    }

}
