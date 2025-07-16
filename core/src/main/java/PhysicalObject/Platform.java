package PhysicalObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import io.github.mayas_fall.OwnClasses.AdjustableNumbers;

public class Platform extends PhysicalObject{
	public Platform(float xPos, float yPos, float size) {
		
		super(new Rectangle(xPos,yPos+AdjustableNumbers.plattformBoxOffset,size,AdjustableNumbers.plattformBoxHeight),new Texture("Seliniassets/Platform1.png"),xPos,yPos,size,1);
		isBlocking=true;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Texture getCorrectTexture() {
		
		return texture;
	}


}
