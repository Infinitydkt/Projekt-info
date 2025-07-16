package Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.mayas_fall.Main;
import io.github.mayas_fall.OwnClasses.Levels.Level0Rooms.Level0Room0;

public class Menu implements Screen {
	final Main game;
	public Menu(Main game) {
		
    	this.game=game;
    }
	
	
    

@Override
public void render(float delta) {
	ScreenUtils.clear(Color.BLACK);
	
	game.viewport.apply();
	game.spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);

	game.spriteBatch.begin();
	//draw text. Remember that x and y are in meters
	game.Font.draw(game.spriteBatch, "Welcome to Mayas Fall!!! ", game.viewport.getWorldHeight()/2, game.viewport.getWorldWidth()/2);
	game.Font.draw(game.spriteBatch, "Tap anywhere to begin!",game.viewport.getWorldHeight()/2, game.viewport.getWorldWidth()/2-0.5f);
	game.spriteBatch.end();

	if (Gdx.input.isTouched()) {
		System.out.println("Touched");
		Screen R=new Level0Room0(game);
		System.out.println("Menue ended");
		game.setScreen(R);
		game.ScreenSetzen(R);
		dispose();

	}
}

@Override
public void show() {
	
}

@Override
public void resize(int width, int height) {
	game.viewport.update(width, height, true);
}


@Override
public void pause() {
	
}

@Override
public void resume() {
	
}

@Override
public void hide() {
	
	
}

@Override
public void dispose() {

}
}
