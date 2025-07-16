package ObjectClasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import io.github.mayas_fall.OwnClasses.AdjustableNumbers;
import io.github.mayas_fall.OwnClasses.Levels.Room;
import io.github.mayas_fall.OwnClasses.Levels.Level0Rooms.Level0Room0;
import io.github.mayas_fall.OwnClasses.Levels.Level0Rooms.Level0Room1;

public class DoorOpen extends Rätsel{
	public DoorOpen(Room r) {
		super(AdjustableNumbers.doorPositionX, AdjustableNumbers.doorPositionY, AdjustableNumbers.doorSizeX, AdjustableNumbers.doorSizeY, new Rectangle(AdjustableNumbers.doorPositionX, AdjustableNumbers.doorPositionY, AdjustableNumbers.doorSizeX, AdjustableNumbers.doorSizeY),new Texture("Seliniassets/DoorOpen.png"), r);
		System.out.println("DoorOpenCreated");
		isBlocking=false;
	}

	@Override
	public void inRange() {
		room.ShowText("Press E to enter Door");
	}

	@Override
	public void ePressed() {
		System.out.println("doorENTERED");
		room.changeRoom(new Level0Room1(room.game));
		
	}

	@Override
	public Texture getCorrectTexture() {
		Level0Room0 R=(Level0Room0) room;
		return texture;
	}

}
