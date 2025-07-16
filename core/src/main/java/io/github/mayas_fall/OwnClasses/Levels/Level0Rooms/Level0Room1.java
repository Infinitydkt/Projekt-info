package io.github.mayas_fall.OwnClasses.Levels.Level0Rooms;

import Enemys.LongRangeEnemy;
import Enemys.closeRangeEnemy;
import ObjectClasses.Player;
import PhysicalObject.Door;
import PhysicalObject.Platform;
import io.github.mayas_fall.Main;
import io.github.mayas_fall.OwnClasses.AdNmLv1R1;
import io.github.mayas_fall.OwnClasses.AdjustableNumbers;
import io.github.mayas_fall.OwnClasses.Levels.Room;

public class Level0Room1 extends Room{
	Door door;
	int stage=0;
	public Level0Room1(Main game) {
		super(game);
		System.out.println("Created");
		player=new Player(AdjustableNumbers.playerStartPosLevel0Room0X,AdjustableNumbers.playerStartPosLevel0Room0Y,this);
		AddPhysicalObject(new Platform(-1.5f,1,11));
		door=new Door(this);
		AddPhysicalObject(door);
		AddEnemy(new closeRangeEnemy(AdNmLv1R1.catXpos,AdNmLv1R1.catYpos,AdNmLv1R1.catXsize,AdNmLv1R1.catySize,3,player,this));
	}

	@Override
	public void gameOver(int DeathCode) {
		super.gameOver(DeathCode, this);
	}

	@Override
	public void openDoor() {
		// TODO Auto-generated method stub
		
	}
	public void catKilled() {
		if(stage==0) {
			stage=1;
			player.xPos=AdjustableNumbers.playerStartPosLevel0Room0X;
			player.yPos=AdjustableNumbers.playerStartPosLevel0Room0Y;
			AddEnemy(new LongRangeEnemy(AdNmLv1R1.catXpos,3,player,this));
			ShowText("A wild Child Appears.");
		}
	}

}
