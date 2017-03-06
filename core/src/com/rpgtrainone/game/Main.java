package com.rpgtrainone.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	BitmapFont font;
	ShapeRenderer sr;
	OrthographicCamera camera;
	int width;
	int height;
	int x;
	int y;
	float speed;
	JoystickInput joystick;


	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		x = 0;
		y = 0;
		width = Gdx.app.getGraphics().getWidth();
		height = Gdx.app.getGraphics().getHeight();
		font = new BitmapFont();
		sr = new ShapeRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false,width,height);
		joystick = new JoystickInput(this);
		speed = 5f;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		sr.setProjectionMatrix(camera.combined);

		batch.begin();
		batch.draw(img, x-img.getWidth()/2, y-img.getHeight()/2);
		batch.end();

		batch.begin();
		font.getData().setScale(2);
		font.setColor(1,1,1,1);
		String inputPos = "x: " + x + " y: " + y;
		font.draw(batch, inputPos,width/2,height/2);
		batch.end();
		updatePos();
	}
	
	@Override
	public void dispose () {
	    sr.dispose();
		batch.dispose();
		img.dispose();
	}

	public void updatePos(){
	    switch(Gdx.app.getType()){
            case Desktop:
                if(Gdx.input.isTouched()){
                    int nextX = (x + Gdx.input.getX())/2;
                    int nextY = (y + (height - Gdx.input.getY()))/2;
					x = (x + nextX)/2;
					y = (y + nextY)/2;
                }
                break;
            case Android:
                Vector2 movement = joystick.getInput();
                x += movement.x * Gdx.graphics.getDeltaTime() * speed;
                y += movement.y * Gdx.graphics.getDeltaTime() * speed;
                break;
        }
    }


}
