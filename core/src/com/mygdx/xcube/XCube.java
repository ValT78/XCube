package com.mygdx.xcube;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
public class XCube extends Game {
	public SpriteBatch batch;
	public BitmapFont font;

	public void create(){
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this, false, 150f));    // Lance l'écran de départ.

	}


	public void render(){
		super.render();
	}

	public void dispose(){
		batch.dispose();
		font.dispose();
	}
}
