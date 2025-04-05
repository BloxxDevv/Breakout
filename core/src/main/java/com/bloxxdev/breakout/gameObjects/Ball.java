package com.bloxxdev.breakout.gameObjects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bloxxdev.breakout.Main;
import com.bloxxdev.breakout.gameObjects.phys.AABB;
import com.bloxxdev.breakout.screens.Breakout;
import com.bloxxdev.breakout.util.Textures;

public class Ball implements GameObject {

    public static final boolean INVULNERABLE = false;

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    public static final int SIZE = 10;

    public static final int SPEED = 5;  //Default = 6

    private SpriteBatch spriteBatch;

    private int x;
    private int y;

    private AABB hitbox;

    private boolean[] movement = new boolean[]
    {
        false,
        false,
        false,
        false
    };

    public Ball(int x, int y){
        this.x = x;
        this.y = y;

        this.hitbox = new AABB(x, y, SIZE, SIZE);

        spriteBatch = new SpriteBatch();
    }

    public void setMovement(int direction, boolean value) {
        this.movement[direction] = value;
    }

    int d = 0;

    int lastxdir = 0;
    int lastydir = 0;

    private void move(){
        int remainingDist = SPEED;

        while(remainingDist > 0){
            int xdir = 0;
            int ydir = 0;

            if (hitbox.getCorners()[AABB.LEFT] == 0) {
                movement[LEFT] = false;
                movement[RIGHT] = true;
            }else if (hitbox.getCorners()[AABB.RIGHT] == Gdx.graphics.getWidth()-1) {
                movement[RIGHT] = false;
                movement[LEFT] = true;
            }
            if (hitbox.getCorners()[AABB.BOTTOM] == Paddle.PADDLE_HEIGHT) {
                if ((hitbox.getCorners()[AABB.RIGHT] >= ((Paddle)Breakout.paddle).getX() && 
                    hitbox.getCorners()[AABB.LEFT] <= ((Paddle)Breakout.paddle).getX()+Paddle.PADDLE_WIDTH-1)
                    || INVULNERABLE){
                    movement[DOWN] = false;
                    movement[UP] = true;
                }else{
                    Main.breakout.die();
                }
            }else if (hitbox.getCorners()[AABB.TOP] == Gdx.graphics.getHeight()-1) {
                movement[UP] = false;
                movement[DOWN] = true;
            }

            ArrayList<Block> vertIntersect = new ArrayList<>();
            ArrayList<Block> horIntersect = new ArrayList<>();
            ArrayList<Block> bothIntersect = new ArrayList<>();

            AABB vertMoveBox = hitbox.cloneMove(0, lastydir);
            AABB horMoveBox = hitbox.cloneMove(lastxdir, 0);
            AABB bothMoveBox = hitbox.cloneMove(lastxdir, lastydir);

            for (Block b : Main.breakout.blocks) {
                if (vertMoveBox.intersects(b.getHitbox())) {
                    vertIntersect.add(b);
                }
                if (horMoveBox.intersects(b.getHitbox())) {
                    horIntersect.add(b);
                }
                if (bothMoveBox.intersects(b.getHitbox())) {
                    bothIntersect.add(b);
                }
            }

            if (bothIntersect.size() > 0 && (horIntersect.isEmpty() && vertIntersect.isEmpty())) {
                movement[LEFT] = !movement[LEFT];
                movement[RIGHT] = !movement[RIGHT];
                movement[UP] = !movement[UP];
                movement[DOWN] = !movement[DOWN];
            }else{
                if (horIntersect.size() > 0 && vertIntersect.size() < 2) {
                    movement[LEFT] = !movement[LEFT];
                    movement[RIGHT] = !movement[RIGHT];
                }
                if (vertIntersect.size() > 0 && horIntersect.size() < 2) {
                    movement[UP] = !movement[UP];
                    movement[DOWN] = !movement[DOWN];
                }
            }

            for (Block b : vertIntersect) {
                b.damageBlock();
            }
            for (Block b : horIntersect) {
                b.damageBlock();
            }

            if (movement[LEFT]) {
                xdir = -1;
            }else if (movement[RIGHT]){
                xdir = 1;
            }
            if (movement[UP]) {
                ydir = 1;
            }else if (movement[DOWN]){
                ydir = -1;
            }
            
            x+=xdir;
            y+=ydir;
            lastxdir = xdir;
            lastydir = ydir;
            hitbox.setPos(x, y);
            remainingDist--;
        }
    }

    @Override
    public void tick() {
        move();
    }

    @Override
    public void render() {
        spriteBatch.begin();
        spriteBatch.draw(Textures.BALL_TEXTXURE, x, y, SIZE, SIZE);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }

}
