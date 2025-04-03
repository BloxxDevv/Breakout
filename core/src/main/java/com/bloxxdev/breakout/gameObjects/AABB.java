package com.bloxxdev.breakout.gameObjects;

public class AABB {

    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int BOTTOM = 2;
    public static final int TOP = 3;

    private int left;
    private int right;
    private int bottom;
    private int top;

    private int width;
    private int height;

    public AABB(int x, int y, int width, int height){
        this.left = x;
        this.right = x+width-1;
        this.bottom = y;
        this.top = y+height-1;
        this.width = width;
        this.height = height;
    }

    public void setPos(int x, int y){
        this.left = x;
        this.right = x+width-1;
        this.bottom = y;
        this.top = y+height-1;
    }

    public AABB cloneMove(int x, int y){
        return new AABB(left+x, bottom+y, width, height);
    }

    public boolean intersects(AABB aabb){
        return (right >= aabb.left && 
                left <= aabb.right && 
                top >= aabb.bottom && 
                bottom <= aabb.top);
    }

    public int[] getCorners(){
        return new int[]{left, right, top, bottom};
    }
}
