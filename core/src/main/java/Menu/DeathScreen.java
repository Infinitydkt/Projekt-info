package Menu;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.mayas_fall.Main;
import io.github.mayas_fall.OwnClasses.Levels.Room;
import io.github.mayas_fall.OwnClasses.Levels.Level0Rooms.Level0Room0;

public class DeathScreen implements Screen{
final Main game;
final Room lastroom;
final int deathcode;
public DeathScreen(Main game,Room lastRoom,int deathCode) {
	System.out.println("DeathScreenCreated");
	this.game=game;
	this.lastroom=lastRoom;
	this.deathcode=deathCode;
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(Color.BLACK);
		
		game.viewport.apply();
		game.spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);

		game.spriteBatch.begin();
		//draw text. Remember that x and y are in meters
		game.Font.draw(game.spriteBatch, "You Died", game.viewport.getWorldHeight()/2, game.viewport.getWorldWidth()/2);
		game.Font.draw(game.spriteBatch, "Tap to retry",game.viewport.getWorldHeight()/2, game.viewport.getWorldWidth()/2-0.5f);
		game.spriteBatch.end();

		if (Gdx.input.isTouched()) {
			Room R=null;
			Object[] Objects= {game};

			try {
				Constructor<? extends Room> Ctor = lastroom.getClass().getDeclaredConstructor(game.getClass());
				Ctor.setAccessible(true);
				R=(Room) Ctor.newInstance(Objects);
			} catch (Exception e) {
				// TODO Auto-generated catch block					
				e.printStackTrace();
				int i=1/10;
				}
			game.setScreen((Screen) R);
			
			dispose();

		}		
	}

	@Override
	public void resize(int width, int height) {
		game.viewport.update(width, height, true);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
}
