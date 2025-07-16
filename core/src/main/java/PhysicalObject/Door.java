package PhysicalObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import ObjectClasses.Rätsel;
import io.github.mayas_fall.OwnClasses.AdjustableNumbers;
import io.github.mayas_fall.OwnClasses.Levels.Room;

public class Door extends Rätsel{

	public Door(Room room) {
		super( AdjustableNumbers.doorPositionX,AdjustableNumbers.doorPositionY,AdjustableNumbers.doorSizeX,AdjustableNumbers.doorSizeY,new Rectangle(AdjustableNumbers.doorPositionX,AdjustableNumbers.doorPositionY,AdjustableNumbers.doorSizeX,AdjustableNumbers.doorSizeY), new Texture("SeliniAssets/DoorClosed.png"),room);
		isBlocking=false;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Texture getCorrectTexture() {
		// TODO Auto-generated method stub
		return texture;
	}

	@Override
	public void inRange() {
		
	}

	@Override
	public void ePressed() {
		room.ShowText("the Door is closed and looks Solid");
	}

}
