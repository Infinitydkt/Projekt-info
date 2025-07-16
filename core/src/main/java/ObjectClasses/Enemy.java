  package ObjectClasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import io.github.mayas_fall.OwnClasses.Levels.Room;
import io.github.mayas_fall.OwnClasses.Levels.Level0Rooms.Level0Room1;

public abstract class Enemy {
	protected float xPos;
	protected float yPos;
	protected float xSize;
	protected float ySize;
	protected int Leben;
	protected Rectangle rectangle;
	protected Room room;
	
	public Enemy(float x,float y,int Health,Rectangle rechteck, Room r) {
		xPos=x;
		yPos=y;
		xSize=1;
		ySize=1;
		Leben=Health;
		rectangle=rechteck;
		room=r;
	}
	public Enemy(float x,float y,float xS,float yS,int Health,Rectangle rechteck, Room r) {
		xPos=x;
		yPos=y;
		xSize=xS;
		ySize=yS;
		Leben=Health;
		rectangle=rechteck;
		room=r;
	}
	public abstract Texture getCorrectTexture();
	public abstract void Movement(float worldWidth, float delta) ;
	public boolean damage(int Damage) {
		System.out.println(Leben);
		Leben-=Damage;
		if (Leben<=0) {
			deathEffect();
			return true;
		}
		return false;
	}
	public float getX() {
		return xPos;
	}
	public float getY() {
		return yPos;
	}
	public float getXSize() {
		return xSize;
	}
	public float getYSize() {
		return ySize;
	}
	public Rectangle getRectangle() {
		return rectangle;
	}
	protected void deathEffect() {
		room.RemoveEnemy(this);
	}
} 
