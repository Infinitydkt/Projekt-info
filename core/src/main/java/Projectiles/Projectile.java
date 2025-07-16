package Projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

import io.github.mayas_fall.OwnClasses.Levels.Room;

public abstract class Projectile {
	protected Texture texture;
	protected float xPos;
	protected float yPos;
	protected float xVel;
	protected float yVel;
	protected float Size;
	protected Rectangle rectangle;
	protected Room R;
	
	public Projectile(Texture t,float x,float y, float s,Room r) {
		texture=t;
		xPos=x;
		yPos=y;
		Size=s;
		R=r;
		
	}
	
	public abstract void Move(float delta);
	public abstract Rectangle getRectangle();
	public float getX() {
		return xPos;
	}
	public float getY() {
		return yPos;
	}
	public float getSize() {
		return Size;
	}
	public Texture getTexture() {
		return texture;
	}
	public float getRotation() {
		return (float)Math.atan(yVel/xVel);
	}
}
