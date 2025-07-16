package ObjectClasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import PhysicalObject.PhysicalObject;
import io.github.mayas_fall.OwnClasses.Levels.Room;

public abstract class Rätsel extends PhysicalObject{
	protected Room room;
	public Rätsel(float x,float y,float xS,float yS,Rectangle rechteck, Texture t,Room r) {
		super(t,x,y,xS,yS);
		System.out.println("Rätsel Created");
		rectangle=rechteck;
		room=r;
	}

	public abstract void inRange();
	public abstract void ePressed();
	public abstract Texture getCorrectTexture();
	
}
