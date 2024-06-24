package com.game.catching;

import static com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable.draw;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
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
import java.util.Vector;

public class Eggcatcher extends ApplicationAdapter {

	Texture eggCounting1, eggCounting2, eggCounting3;
	Circle circleEgg;
	Rectangle bctWolfTopLeftRectangle,bctWolfTopRightRectangle, bctWolfButtomLeftRectangle, bctWolfButtomRightRectangle;
	TextureRegion bctWolfTopLeft, bctWolfTopRight, bctWolfButtomLeft, bctWolfButtomRight;
	float imgWolfX, imgWolfY;
	OrthographicCamera camera;
	Vector3 touch;
	BitmapFont bitmapFont;
	int gamestate = 0;
	int score = 0;
	SpriteBatch batch;
	Texture background;
	Texture wolfTopLeft, wolfTopRight, wolfButtomLeft, wolfButtomRight;
	long timeSpawnLastEgg1, timeSpawnEggInterval1 =  MathUtils.random(1120,7000);
	long timeSpawnLastEgg2, timeSpawnEggInterval2 = MathUtils.random(1230,8000) ;
	long timeSpawnLastEgg3, timeSpawnEggInterval3 = MathUtils.random(1750,7700);
	long timeSpawnLastEgg4, timeSpawnEggInterval4 = MathUtils.random(2000,7800);
	Texture imgBroken_eggTexture;
	TextureRegion broken_egg;
	Texture gameOver;
	Texture imgEggTexture;
	TextureRegion imgEgg;
	int rotation;
	float startX;
	float startY;
	Array<Egg> eggs = new Array<>();
	Array<BrokenEgg> brokenEggs = new Array<>();
	Texture imgWolf;
	int counter;
	EggButton btnTopLeft, btnTopRight, btnButtomLeft, btnButtomRight;
	int[][] eggsCoordinate = {{360, 445}, {1810, 450}, {360, 677}, {1810, 680}};

	@Override
	public void create () {



        eggCounting1 = new Texture("eggCounting1.png");
		eggCounting2 = new Texture("eggCounting2.png");
		eggCounting3 = new Texture("eggCounting3.png");
		counter = 0;

		bitmapFont = new BitmapFont();
		bitmapFont.setColor(Color.WHITE);
		bitmapFont.getData().setScale(10);

		btnTopLeft = new EggButton("<",50, 400, bitmapFont);
		btnTopRight = new EggButton(">", 2050, 400, bitmapFont);
		btnButtomLeft = new EggButton("<", 50, 200, bitmapFont);
		btnButtomRight = new EggButton(">", 2050, 200, bitmapFont);

		camera = new OrthographicCamera();
		touch = new Vector3();
		camera.setToOrtho(false, 2200, 1000);

		wolfTopLeft = new Texture("wolf_left_up.png");
		wolfTopRight = new Texture("wolf_right_up.png");
		wolfButtomLeft = new Texture("wolf_left_down.png");
		wolfButtomRight = new Texture("wolf_right_down.png");

		imgWolf = wolfTopRight;

		batch = new SpriteBatch();
		background = new Texture("background.png");
		imgEggTexture = new Texture("egg.png");
		imgEgg = new TextureRegion(imgEggTexture);
		gameOver = new Texture("gameover.png");
		rotation = 0;
		startX=360;
		startY=450;
		imgBroken_eggTexture = new Texture("broken_egg.png");
		broken_egg = new TextureRegion(imgBroken_eggTexture);

		imgWolfX = 1120;
		imgWolfY = 195;

		bctWolfTopLeft = new TextureRegion(wolfTopLeft, 25, 110, 125, 35);
		bctWolfTopRight = new TextureRegion(wolfTopRight, 370, 123, 130, 35 );
		bctWolfButtomLeft = new TextureRegion(wolfButtomLeft, 0, 331, 146, 35);
		bctWolfButtomRight = new TextureRegion(wolfButtomRight, 389, 326, 107, 35);

		circleEgg = new Circle();
		bctWolfTopLeftRectangle = new Rectangle();
		bctWolfTopRightRectangle = new Rectangle();
		bctWolfButtomLeftRectangle = new Rectangle();
		bctWolfButtomRightRectangle = new Rectangle();


    }

	@Override
	public void render () {


		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if (gamestate == 1) {
			spawnEggs();




			for (int i=0; i<eggs.size; i++) {
				Egg g = eggs.get(i);
				g.move();
				if (g.eggOnGround()){
					eggs.removeIndex(i);
					spawnBrokenEggs(g);
					counter ++;

				}
				circleEgg.set(g.x + 29, g.y +29, 29);
				if((Intersector.overlaps(circleEgg, bctWolfTopLeftRectangle) && g.top)||
				  (Intersector.overlaps(circleEgg, bctWolfTopRightRectangle) && g.top)||
				  (Intersector.overlaps(circleEgg, bctWolfButtomLeftRectangle) &&  !g.top)||
				  (Intersector.overlaps(circleEgg, bctWolfButtomRightRectangle) && !g.top)){

					eggs.removeIndex(i);
					score++;
					Gdx.app.log("Score", String.valueOf(score));
				}





			}
			if(counter==1){

				batch.draw(eggCounting1, 1200, 700);
			}
			if (counter ==2){

				batch.draw(eggCounting1, 1200, 700);
				batch.draw(eggCounting2, 1300, 700);
			}
			if (counter ==3){

				batch.draw(eggCounting1, 1200, 700);
				batch.draw(eggCounting2, 1300, 700);
				batch.draw(eggCounting3, 1400, 700);
				gamestate = 2;
			}

			btnTopLeft.font.draw(batch, btnTopLeft.text, btnTopLeft.x, btnTopLeft.y);
			btnTopRight.font.draw(batch, btnTopRight.text, btnTopRight.x, btnTopRight.y);
			btnButtomLeft.font.draw(batch, btnButtomLeft.text, btnButtomLeft.x, btnButtomLeft.y);
			btnButtomRight.font.draw(batch, btnButtomRight.text, btnButtomRight.x, btnButtomRight.y);
			bitmapFont.draw(batch, String.valueOf(score), 1300, 1000);

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
			batch.draw(imgWolf, imgWolfX, imgWolfY);

			for (BrokenEgg b: brokenEggs){
				batch.draw(imgBroken_eggTexture, b.x, b.y);

			}

			if(Gdx.input.justTouched()){
				touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
				camera.unproject(touch);

				if(btnTopLeft.hit(touch.x, touch.y)){

					imgWolf = wolfTopLeft;
					imgWolfX = 570;
					imgWolfY = 160;
					bctWolfTopLeftRectangle.set(0+570, 343+160, 137, 39);
					bctWolfTopRightRectangle.set(0,0,0,0);
					bctWolfButtomLeftRectangle.set(0,0,0,0);
					bctWolfButtomRightRectangle.set(0,0,0,0);
				}

				if(btnTopRight.hit(touch.x, touch.y)){
					imgWolf = wolfTopRight;
					imgWolfX = 1120;
					imgWolfY = 195;
					bctWolfTopLeftRectangle.set(0,0,0,0);
					bctWolfTopRightRectangle.set(357+1120, 340+195, 140, 24);
					bctWolfButtomLeftRectangle.set(0,0,0,0);
					bctWolfButtomRightRectangle.set(0,0,0,0);
				}

				if(btnButtomLeft.hit(touch.x, touch.y)){
					imgWolf = wolfButtomLeft;
					imgWolfX = 570;
					imgWolfY = 160;
					bctWolfTopLeftRectangle.set(0,0,0,0);
					bctWolfTopRightRectangle.set(0,0,0,0);
					bctWolfButtomLeftRectangle.set(11+570, 132+160, 147, 15);
					bctWolfButtomRightRectangle.set(0,0,0,0);
				}

				if (btnButtomRight.hit(touch.x, touch.y)){
					imgWolf = wolfButtomRight;
					imgWolfX = 1130;
					imgWolfY = 160;
					bctWolfTopLeftRectangle.set(0, 0, 0,0);
					bctWolfTopRightRectangle.set(0, 0, 0, 0);
					bctWolfButtomLeftRectangle.set(0, 0, 0, 0);
					bctWolfButtomRightRectangle.set(366+1130, 156+160, 134, 15);
				}
			}





		} else if (gamestate==0) {
			if (Gdx.input.justTouched()) {

				gamestate = 1;


			}
		} else if (gamestate == 2) {
			batch.draw(gameOver, 900, 500);
			if (Gdx.input.justTouched()) {

				gamestate = 0;
				eggs.clear();
				brokenEggs.clear();
				counter = 0;
				score = 0;




			}
		}
		batch.end();






	}
	@Override
	public void dispose(){
		imgBroken_eggTexture.dispose();
		imgEggTexture.dispose();
		bitmapFont.dispose();
	}

	void spawnEggs() {

		if (TimeUtils.millis() > timeSpawnLastEgg1 + timeSpawnEggInterval1) {
			timeSpawnLastEgg1 = TimeUtils.millis();
			eggs.add(new Egg(360, 445));
		} else if (TimeUtils.millis() > timeSpawnLastEgg2 + timeSpawnEggInterval2) {
			timeSpawnLastEgg2 = TimeUtils.millis();
			eggs.add(new Egg(1810, 450));
		} else if (TimeUtils.millis() > timeSpawnLastEgg3+timeSpawnEggInterval3) {
			timeSpawnLastEgg3 = TimeUtils.millis();
			eggs.add(new Egg(360, 677));
		} else if (TimeUtils.millis() > timeSpawnLastEgg4+timeSpawnEggInterval4) {
			timeSpawnLastEgg4 = TimeUtils.millis();
			eggs.add(new Egg(1810, 680));
		}
		
	}


	void spawnBrokenEggs(Object o) {
		brokenEggs.add(new BrokenEgg(o.x, o.y));

	}


}





