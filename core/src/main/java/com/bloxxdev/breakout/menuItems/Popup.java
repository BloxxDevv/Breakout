package com.bloxxdev.breakout.menuItems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.bloxxdev.breakout.Main;
import com.bloxxdev.breakout.util.Textures;

public class Popup {

    private int x;
    private int y;

    private int width;
    private int height;

    private ShapeRenderer renderer;

    private boolean shouldLoop;
    private boolean shouldRender;

    private EventExecutor eventExecutor;
    private Color background;

    private ButtonFont font = new ButtonFont(6, 8, 1, 4);

    String popupMessage;

    Button yesButton;
    Button noButton;

    public Popup(int x, int y, int width, int height, Color popupColor, String popupMessage, EventExecutor eventExecutor){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.eventExecutor = eventExecutor;
        this.background = popupColor;

        this.popupMessage = popupMessage;

        renderer = new ShapeRenderer();
    }

    public void show(){
        shouldLoop = true;
        shouldRender = true;

        yesButton = new Button(Textures.SMALL_BUTTON_TEXTURE, Textures.SMALL_BUTTON_TEXTURE_HOVER, null, x+20, y+20, "Yes", new EventExecutor() {
            @Override
            public void execute() {
                hide();
                eventExecutor.execute();
            }
        });
        yesButton.setLocked(false);

        noButton = new Button(Textures.SMALL_BUTTON_TEXTURE, Textures.SMALL_BUTTON_TEXTURE_HOVER, null, x+width-Textures.SMALL_BUTTON_TEXTURE.getWidth()-20, y+20, "No", new EventExecutor() {
           @Override
           public void execute() {
               hide();
           } 
        });

        noButton.setLocked(false);
    }
    
    public void tick(){
        if (shouldLoop) {
            yesButton.tick();
            noButton.tick();
        }
    }

    public void render(){
        if (shouldRender) {
            Gdx.gl20.glEnable(GL20.GL_BLEND);

            renderer.begin(ShapeType.Filled);

            renderer.setColor(background);
            renderer.rect(x, y, width, height);

            renderer.end();

            Gdx.gl20.glDisable(GL20.GL_BLEND);

            drawMessage();

            yesButton.render();
            noButton.render();
        }
    }

    public void hide(){
        dispose();
        Main.mainMenu.isPopupActive = false;
    }

    public void dispose(){
        shouldLoop = false;
        shouldRender = false;
    }

    public void drawMessage(){
        font.setFontColor(new Color(0.9F, 0.0F, 0.0F, 1.0F));
        font.drawString(popupMessage, x + width/2 - font.getStringWidth(popupMessage)/2, y+height-font.getStringHeight(popupMessage)-10);
    }
}
