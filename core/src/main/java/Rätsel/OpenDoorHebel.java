package Rätsel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import ObjectClasses.Rätsel;
import io.github.mayas_fall.OwnClasses.AdjustableNumbers;
import io.github.mayas_fall.OwnClasses.Levels.Room;
import io.github.mayas_fall.OwnClasses.Levels.Level0Rooms.Level0Room0;

public class OpenDoorHebel extends Rätsel{
	
	private boolean gezogen;
	private Texture HebelUngezogen;
	private Texture HebelGezogen;
	public OpenDoorHebel( Room r) {
		super(AdjustableNumbers.hebelUPositionX+0.3f, AdjustableNumbers.hebelUPositionY+0.5f, AdjustableNumbers.hebelUSizeX/3, AdjustableNumbers.hebelUSizeY/3, new Rectangle(AdjustableNumbers.statuePositionX+AdjustableNumbers.statueSizeX/3, AdjustableNumbers.statuePositionY+AdjustableNumbers.statueSizeY/4, AdjustableNumbers.statueSizeX/5,AdjustableNumbers.statueSizeY*0.45f), null, r);
		HebelUngezogen=new Texture("Seliniassets/HebelNeu.png");
		HebelGezogen=new Texture("Seliniassets/HebelNeuUmgelegt.png");
		isBlocking=false;
	}

	@Override
	public void inRange() {
		if(!gezogen) {
		room.ShowText("This lever invites you to pull it");
		}
	}

	@Override
	public void ePressed() {
		if(!gezogen) {
			try {
				Level0Room0 L=(Level0Room0) room;
				L.openDoor();
				room.ShowText("You pull the lever");
				room.ShowText("The Door Opens");
				gezogen=true;

			}
			catch(Exception E) {}
		}
		else {
			room.ShowText("The Lever seems Stuck in the open Position");
		}
	}

	@Override
	public Texture getCorrectTexture() {
		System.out.println(gezogen);
		if(gezogen) {
			return HebelGezogen;
		}
		return HebelUngezogen;
	}

}
