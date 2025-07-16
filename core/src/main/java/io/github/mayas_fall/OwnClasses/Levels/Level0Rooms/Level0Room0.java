/**
 * 
 */
package io.github.mayas_fall.OwnClasses.Levels.Level0Rooms;

import io.github.mayas_fall.Main;
import io.github.mayas_fall.OwnClasses.AdjustableNumbers;
import io.github.mayas_fall.OwnClasses.Levels.Room;
import ObjectClasses.Statue;
import PhysicalObject.Door;
import PhysicalObject.PhysicalObject;
import PhysicalObject.Platform;
import Rätsel.HebelUnaufgebaut;
import Rätsel.OpenDoorHebel;

import com.badlogic.gdx.math.Rectangle;

import ObjectClasses.DoorOpen;
import ObjectClasses.Player;


public class Level0Room0 extends Room{
	Statue statue;
	PhysicalObject hebel;
	Door door;
	//Game game;
	public Level0Room0(Main game) {
		super(game);
		
		player=new Player(AdjustableNumbers.playerStartPosLevel0Room0X,AdjustableNumbers.playerStartPosLevel0Room0Y,this);
		
		Rectangle StatueRectangle=new Rectangle();
		StatueRectangle.set(AdjustableNumbers.statuePositionX+AdjustableNumbers.statueSizeX/3, AdjustableNumbers.statuePositionY+AdjustableNumbers.statueSizeY/4, AdjustableNumbers.statueSizeX/5,AdjustableNumbers.statueSizeY*0.45f);
		statue=new Statue(AdjustableNumbers.statuePositionX, AdjustableNumbers.statuePositionY, StatueRectangle, this);
		AddEnemy(statue);
		door=new Door(this);
		AddPhysicalObject(new Platform(0, 1, 1.5f));
		AddPhysicalObject(new Platform(2f, 1, 1));
		AddPhysicalObject(new Platform(3.5f, 1, 2.5f));

		AddPhysicalObject(new Platform(6, 1, 2f));
		AddPhysicalObject(door);
		
		ShowText("Press A and D to walk");
		ShowText("Press SPACE to jump");
		ShowText("That statue looks kinda ugly (sorry Selini). Punch it");
		}
	
	public void Show() {
	//Called When shown maybe music?
	}

	public void StatueKilled(){
		RemoveEnemy(statue);
		hebel=new HebelUnaufgebaut(this);
		AddPhysicalObject(hebel);
	}
	public void LeverRebuildt() {
		RemovePhysicalObject(hebel);
		hebel=new OpenDoorHebel(this);
		AddPhysicalObject(hebel);
		ShowText("Hm. A lever...");
	}
	public void openDoor() {
		RemovePhysicalObject(door);
		DoorOpen D=new DoorOpen(this);
		AddPhysicalObject(D);
	}
	public void nextRoom() {
		
	}
	
	@Override
	public void gameOver(int DeathCode) {
		super.gameOver(DeathCode, this);

		
	}
	
	public Rectangle getPlayerRectangle() {
		return player.rectangle;
	}
	
	
}
