package PhysicalObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public abstract class PhysicalObject {
	protected Rectangle rectangle;
	protected Texture texture;
	protected float xPos;
	protected float yPos;
	protected float xSize;
	protected float ySize;
	protected boolean isBlocking=true;

	public PhysicalObject(Rectangle r, Texture t, float xp, float yp, float xs, float ys) {

		System.out.println("PhysicalObject Created");
		rectangle=r;
		texture=t;
		xPos=xp;
		yPos=yp;
		xSize=xs;
		ySize=ys;
		// TODO Auto-generated constructor stub
	}
	public PhysicalObject(Texture t, float xp,float yp,float xs,float ys) {
		rectangle=new Rectangle(xp,yp,xs,ys);
		texture=t;
		xPos=xp;
		yPos=yp;
		xSize=xs;
		ySize=ys;
	}
	public float getXPos() {
		return xPos;
	}
	public float getYPos() {
		return yPos;
	}
	public float getXSize() {
		return xSize;
	}
	public float getYSize() {
		return ySize;
	}
	public boolean isBlocking() {
		return isBlocking;
	}
	public Rectangle getRectangle() {
		return rectangle;
	}
	public abstract Texture getCorrectTexture();
}
