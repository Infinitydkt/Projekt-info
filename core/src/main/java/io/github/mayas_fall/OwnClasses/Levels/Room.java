package io.github.mayas_fall.OwnClasses.Levels;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import Menu.DeathScreen;
import ObjectClasses.Enemy;
import ObjectClasses.Player;
import ObjectClasses.Rätsel;
import PhysicalObject.PhysicalObject;
import Projectiles.Projectile;
import io.github.mayas_fall.Main;
import io.github.mayas_fall.OwnClasses.AdjustableNumbers;
import ObjectClasses.Rätsel;
public abstract class Room implements Screen{
	
	private SpriteBatch batch;
	private BitmapFont font;
	private FitViewport viewport;
	
	//private int ECooldown;
	
	public Main game;
	protected Player player;
	protected Texture Background;
	
	private ArrayList<PhysicalObject> PhysicalObjects;
	private ArrayList<Enemy> Enemys;
	private ArrayList<Projectile> Projectiles;
	
	private String log1="";
	private String log2="";
	private String log3="";
	
	public  Room(Main game) {
		this.game=game;
		PhysicalObjects=new ArrayList<PhysicalObject>();
		Enemys=new ArrayList<Enemy>();
		Projectiles=new ArrayList<Projectile>();
	}

	public void AddPhysicalObject(PhysicalObject O) {
		PhysicalObjects.add(O);
	}
	public void RemovePhysicalObject(PhysicalObject p) {
		PhysicalObjects.remove(p);
	}
	public void AddEnemy(Enemy E) {
		Enemys.add(E);
	}
	public void RemoveEnemy(Enemy E) {
		Enemys.remove(E);
	}
	public void AddProjectile(Projectile P) {
		Projectiles.add(P);
	}
	public void RemoveProjectile(Projectile P) {
		Projectiles.remove(P);
	}

	@Override
	public void render(float delta) {
	//split up for readability
	input();
	logic();
	draw();
	}
	
	protected void input() {
		float delta = Gdx.graphics.getDeltaTime();
		
		float worldWidth = game.viewport.getWorldWidth();

		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.DoMovement(delta, true, worldWidth);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.DoMovement(delta, false, worldWidth);
		}
	
		if (Gdx.input.isTouched()) {
			if(player.AttackCooldown==-1) {
			player.AttackCooldown=AdjustableNumbers.AttackCoolDown;
			}
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			if(player.canJump()) {
			player.yVel=AdjustableNumbers.playerJumpheight;}
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.E)) {
			for(PhysicalObject O:PhysicalObjects) {
				if(O instanceof Rätsel&&player.rectangle.overlaps(O.getRectangle())) {
					Rätsel R=(Rätsel) O;
					R.ePressed();
					break;
				}
			}
		}
		if(player.AttackCooldown!=-1) {
			player.AttackCooldown-=delta;
		}
	}
	protected void logic() {
		float worldWidth = game.viewport.getWorldWidth();

		float delta = Gdx.graphics.getDeltaTime();
		
		player.DoPhysics(delta);
		for(Enemy E:Enemys) {
			E.Movement(worldWidth,delta);
		}
		for(PhysicalObject O:PhysicalObjects) {
			if(O instanceof Rätsel && player.rectangle.overlaps(O.getRectangle())) {
				Rätsel R=(Rätsel) O;
				R.inRange();
			}
		}
	}
	protected void draw() {
		float delta = Gdx.graphics.getDeltaTime();

		ScreenUtils.clear(Color.LIGHT_GRAY);
		game.viewport.apply();
		game.spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);
		
		game.spriteBatch.begin();
		//System.out.println(PhysicalObjects.size());
		//Draw Map
		for(PhysicalObject p:PhysicalObjects) {
			game.spriteBatch.draw(p.getCorrectTexture(), p.getXPos(), p.getYPos(), p.getXSize(), p.getYSize());
		}
		for (Projectile P:Projectiles) {
			//hoffe mal das dass stimmt
			game.spriteBatch.draw(P.getTexture(),P.getX(),P.getY(),P.getSize(),P.getSize());
		}
		//Draw Statue
		for(Enemy E:Enemys) {
		//	System.out.println(E.getCorrectTexture().toString()+E.getX()+" "+E.getY()+" "+E.getXSize()+" "+E.getYSize());
			game.spriteBatch.draw(E.getCorrectTexture(),E.getX(),E.getY(),E.getXSize(),E.getYSize());
		}
		//Draw player 
		game.spriteBatch.draw(player.GetCorrectTexture(delta), player.xPos,player.yPos,AdjustableNumbers.playerSizeX,AdjustableNumbers.playerSizeY);
		
		game.Font.draw(game.spriteBatch, log3, game.viewport.getWorldHeight()/2, AdjustableNumbers.logPositionY);
		game.Font.draw(game.spriteBatch, log2, game.viewport.getWorldHeight()/2, AdjustableNumbers.logPositionY+AdjustableNumbers.logOffset);
		game.Font.draw(game.spriteBatch, log1, game.viewport.getWorldHeight()/2, AdjustableNumbers.logPositionY+AdjustableNumbers.logOffset*2);
		
		game.spriteBatch.end();
	}
	
	
	public void attack() {
		Rectangle hitbox;
		if(player.LastDirection) {
			hitbox=new Rectangle(player.xPos+AdjustableNumbers.playerSizeX,player.yPos,AdjustableNumbers.playerAttackSizeX,AdjustableNumbers.playerAttackSizeY);	
		}else {
			hitbox=new Rectangle(player.xPos-AdjustableNumbers.playerAttackSizeX,player.yPos,AdjustableNumbers.playerAttackSizeX,AdjustableNumbers.playerAttackSizeY);
		}
		for (Enemy E:Enemys) {
		if(hitbox.overlaps(E.getRectangle()  )) {
			E.damage(1);
			break;
			//TODO not ideal since you can only damage one player but i dont know how to do it with the kill otherwise since xou have to remove it
		}
		for(Projectile P:Projectiles) {
			if(hitbox.overlaps(P.getRectangle())) {
				RemoveProjectile(P);
			break;
			}
		}
		}
		
	}
	public boolean canPlayerWalk(boolean Direction) {
		Rectangle hitbox;
		if(Direction) {
			hitbox=new Rectangle(player.xPos+AdjustableNumbers.playerSizeX-AdjustableNumbers.playerCheckOffset,player.yPos,AdjustableNumbers.playerCheckboxWalkSizeX,AdjustableNumbers.playerCheckboxWalksizeY);	
		}else {
			hitbox=new Rectangle(player.xPos-AdjustableNumbers.playerCheckboxWalkSizeX+AdjustableNumbers.playerCheckOffset,player.yPos,AdjustableNumbers.playerCheckboxWalkSizeX,AdjustableNumbers.playerCheckboxWalksizeY);
		}
		for (Enemy E:Enemys) {
			if(hitbox.overlaps(E.getRectangle())) {
				return false;}
			}
		
		for(PhysicalObject O:PhysicalObjects) {
			if(hitbox.overlaps(O.getRectangle())&&O.isBlocking()) {
				return false;
				}
		}
		return true;
	}


	
	public void gameOver(int DeathCode, Room R) {
		//move to deathscreen
		System.out.println("you died");
		game.setScreen(new DeathScreen(game, R, DeathCode));
		dispose();
	}
	public abstract void gameOver(int DeathCode);
	
	public float TestPlatformUnderPlayer(float PlayerPositionBefore){
		Rectangle CheckBox=new Rectangle(player.xPos+(AdjustableNumbers.playerSizeX-AdjustableNumbers.playerPlatformBoxSizeX)/2,player.yPos-AdjustableNumbers.playerPlatformBoxSizeY,AdjustableNumbers.playerPlatformBoxSizeX,AdjustableNumbers.playerPlatformBoxSizeY );

		for (Enemy E:Enemys) {
			if(CheckBox.overlaps(E.getRectangle())) {
				//E.damage(1);//Es ist megalustig das zu aktivieren
				if(PlayerPositionBefore>=E.getRectangle().y+E.getRectangle().height) {
				return E.getRectangle().y+E.getRectangle().height;}
			}
		}
		for(PhysicalObject O:PhysicalObjects) {
			if(CheckBox.overlaps(O.getRectangle() )&&O.isBlocking()) {
			//	System.out.println(O);
				if(PlayerPositionBefore>=O.getRectangle().y+O.getRectangle().height) {

				return O.getRectangle().y+O.getRectangle().height;}
			}
		}
		return player.yPos;
	}
	
	public void ShowText(String text) {
		if(!log3.equals(text)) {
		log1=log2;
		log2=log3;
		log3=text;}
	}
	public void changeRoom(Room r) {
		game.setScreen(r);
		dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		game.viewport.update(width, height,true);
		
	}
	@Override
	public void dispose() {		
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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

	public abstract void openDoor();
}
