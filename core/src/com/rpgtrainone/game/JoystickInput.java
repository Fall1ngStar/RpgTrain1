package com.rpgtrainone.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * JoystickInput class
 * Created by Thierry
 * 05/03/2017
 */
public class JoystickInput {

    private Vector2 initPos;
    private Vector2  actualPos;
    private Vector2 relativePos;
    private Vector2 movement;
    private boolean isInit;
    private double angle;
    private double dist;
    private final Main main;

    public JoystickInput(Main main){
        this.main = main;
        initPos = new Vector2();
        actualPos = new Vector2();
        relativePos = new Vector2();
        movement = new Vector2();
    }

    public Vector2 getInput(){
        if(Gdx.input.isTouched()){
            if(isInit){
                actualPos.x = Gdx.input.getX();
                actualPos.y = Gdx.input.getY();
                relativePos.x = actualPos.x - initPos.x;
                relativePos.y = actualPos.y - initPos.y;
                angle = Math.atan2(relativePos.y, relativePos.x);
                dist = limit(Math.sqrt(relativePos.x * relativePos.x + relativePos.y * relativePos.y),100);
                movement.x = (float)(dist * Math.cos(angle));
                movement.y = -(float)(dist * Math.sin(angle));
                render();
                return movement;
            } else {
                isInit = true;
                initPos = new Vector2(Gdx.input.getX(),Gdx.input.getY());
            }
        } else {
            isInit = false;
        }
        return Vector2.Zero;
    }

    public void render(){
        ShapeRenderer sr = main.sr;
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(1,1,1,0.3f);
        sr.circle(initPos.x, main.height - initPos.y, 30);
        sr.setColor(1,0,0,0.2f);
        sr.circle(initPos.x + movement.x,  main.height - initPos.y + movement.y, 20);
        sr.end();
    }

    private double limit(double value, double limit){
        return (limit > value)? value : limit;
    }
}
