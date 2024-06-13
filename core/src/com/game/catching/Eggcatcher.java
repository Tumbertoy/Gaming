package com.game.catching;

import static com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable.draw;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;

import org.w3c.dom.ranges.Range;

import java.util.Random;

public class Eggcatcher extends ApplicationAdapter {
	int gamestate = 0;
	int score = 0;
	SpriteBatch batch;
	Texture background;
	Texture wolf;
	long timeSpawnLastEnemy, timeSpawnEnemyInterval = 1500;

	Texture gameOver;
	Texture imgEggTexture;
	TextureRegion imgEgg;
	int rotation;
	float startX;
	float startY;
	Array<Egg> eggs = new Array<>();


	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background.png");
		imgEggTexture = new Texture("egg.png");
		imgEgg = new TextureRegion(imgEggTexture);
		gameOver = new Texture("gameover.png");
		rotation = 0;
		startX=360;
		startY=450;




    }

	@Override
	public void render () {

		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if (gamestate == 1) {
			spawnEggs();


			for (int i=0; i<eggs.size; i++) {
				eggs.get(i).move();
				if (eggs.get(i).eggOnGround()){
					eggs.removeIndex(i);
				}
			}


			///batch.draw(egg, 360,450 );   ///яичко левое нижнее начало
			///batch.draw(egg, 585, 354);   ///яичко левое нижнее конец

//			batch.draw( regionEgg, startX, startY,0, 0, 58, 58, 1, 1, rotation, false);
//			rotation--;
//			startX = (float) (startX+0.7);
//			startY = (float) (startY-0.35);

			//отрисовка
			for (Egg e: eggs) {
				batch.draw(imgEgg,  e.getX(), e.getY(), 29, 29, 58, 58, 1, 1, e.angle);
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

	void spawnEggs(){

		if(TimeUtils.millis() > timeSpawnLastEnemy+timeSpawnEnemyInterval){
			timeSpawnLastEnemy = TimeUtils.millis();
			eggs.add(new Egg(360, 445));
			eggs.add(new Egg(1810, 450));

			eggs.add(new Egg(360, 677));
			eggs.add(new Egg(1810, 680));


	}
}
}





