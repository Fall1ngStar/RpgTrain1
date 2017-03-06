package com.rpgtrainone.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Main2 class
 * Created by Thierry
 * 06/03/2017
 */
public class Main2 extends Game {

    SpriteBatch batch;
    BitmapFont font;

    @Override
    public void create(){
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new GameScreen(this));
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose(){
        batch.dispose();
        font.dispose();
    }
}
