package Rätsel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import ObjectClasses.Rätsel;
import io.github.mayas_fall.OwnClasses.AdjustableNumbers;
import io.github.mayas_fall.OwnClasses.Levels.Room;
import io.github.mayas_fall.OwnClasses.Levels.Level0Rooms.Level0Room0;

public class HebelUnaufgebaut extends Rätsel{

	public HebelUnaufgebaut( Room r) {
		super(AdjustableNumbers.hebelUPositionX, AdjustableNumbers.hebelUPositionY, AdjustableNumbers.hebelUSizeX, AdjustableNumbers.hebelUSizeY, new Rectangle(AdjustableNumbers.statuePositionX+AdjustableNumbers.statueSizeX/3, AdjustableNumbers.statuePositionY+AdjustableNumbers.statueSizeY/4, AdjustableNumbers.statueSizeX/5,AdjustableNumbers.statueSizeY*0.45f), new Texture("Seliniassets/HebelUnaufgebaut.png"), r);
		isBlocking=false;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inRange() {
		room.ShowText("The Lever is broken. Hold E to rebuild");
	}

	@Override
	public void ePressed() {
			try {
				Level0Room0 L=(Level0Room0) room;
				L.LeverRebuildt();
			}
			catch(Exception E) {}
		
		
	}

	@Override
	public Texture getCorrectTexture() {
		// TODO Auto-generated method stub
		return texture;
	}

}
