package io.github.mayas_fall;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

import Menu.Menu;

public class Main extends Game {   
    public SpriteBatch spriteBatch;
    public FitViewport viewport;
    public BitmapFont Font;
    
    @Override
    public void create() {
    	System.out.println("Main called");
    	viewport=new FitViewport(8, 5);
    	spriteBatch=new SpriteBatch();
    	Font=new BitmapFont();
    	Font.setUseIntegerPositions(false);
		Font.getData().setScale(viewport.getWorldHeight() / Gdx.graphics.getHeight());
		this.setScreen(new Menu(this));//for normal purposes
		//this.setScreen(new Level0Room1(this));
		}

    @Override
    public void render() {
    super.render();
    }

    @Override
    public void resize(int width, int height) {
    	 viewport.update(width, height, true); // true centers the camera
    }
    
    @Override
    public void dispose() {
    	spriteBatch.dispose();
    	Font.dispose();
    	System.out.println("disposed");
    }
    public void ScreenSetzen(Screen r) {
    	this.setScreen(r);
    }

    }

