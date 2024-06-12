package com.game.catching;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import org.w3c.dom.ranges.Range;

import java.util.Random;

public class Eggcatcher extends ApplicationAdapter {
	int gamestate = 0;
	int score = 0;
	SpriteBatch batch;
	Texture background;
	Texture wolf;
	Texture egg;
	Texture gameOver;


	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background.png");
		egg = new Texture("egg.png");
		gameOver = new Texture("gameover.png");



    }

	@Override
	public void render () {

		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if (gamestate == 1) {

			batch.draw(egg, 360,450 );   ///яичко левое нижнее начало
			batch.draw(egg, 585, 354);   ///яичко левое нижнее конец

			for  (Integer i = 0; i<361; i++){
				batch.draw(egg, 360,450, rotation,  );

			}



		} else if (gamestate==0) {
			if (Gdx.input.justTouched()) {

				gamestate = 1;


			}
		} else if (gamestate == 2) {
			batch.draw(gameOver, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
			if (Gdx.input.justTouched()) {

				gamestate = 0;


			}
		}
		batch.end();
	}
	

}
