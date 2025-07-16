
package Enemys;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import ObjectClasses.Enemy;
import ObjectClasses.Player;
import io.github.mayas_fall.OwnClasses.AdNmLv1R1;
import io.github.mayas_fall.OwnClasses.AdjustableNumbers;
import io.github.mayas_fall.OwnClasses.Levels.Room;
import io.github.mayas_fall.OwnClasses.Levels.Level0Rooms.Level0Room1;

public class closeRangeEnemy extends Enemy{
	
	Player player;
	Texture textureL;
	Texture textureR;
	boolean Direction=false;
	int movement =0;
	float xVelMarker;
	float currentwait;
	float currentspeed=AdNmLv1R1.catSpeed;

	public closeRangeEnemy(float x, float y, float xS, float yS, int Health,Player p, Room r) {
		super(x, y, xS, yS, Health,new Rectangle(x,y,xS,yS), r);
		textureL=new Texture("Seliniassets/Miezekatze000.png");
		textureR=new Texture("Seliniassets/Miezekatze001.png");
		player=p;
		}

	@Override
	public Texture getCorrectTexture() {
		if (!Direction) {
			return textureR;
		}
		return textureL;
		
	}
	@Override
	public boolean damage(int Damage) {
		currentwait=0;
		Leben--;
		switch(Leben) {
		case 3:
			room.ShowText("The Cat Looks Angrier Now");
			break;
		case 2:
			room.ShowText("The Cat Looks Sad. Maybe It Just Wanted to cuddle?");
			break;
		case 1:
			room.ShowText("The Cat is bleeding from your attacks. It is hard to watch.");
			break;
		default:
			room.ShowText("The Cat dies. It dies slowly and by your hands. It just wanted to be loved, you MONSTER");
			deathEffect();
			return true;
		}
		return false;
	} 

	@Override
	public void Movement(float worldWidth,float delta) {
		switch(movement) {
		case 0:
			Direction=false;
			if(checkPlayerInRange(AdNmLv1R1.catVisionRange)||currentwait<=0) {
				movement=2;
			}
			break;
		case 1:
			Direction=true;
			if(checkPlayerInRange(AdNmLv1R1.catVisionRange)||currentwait<=0) {
				movement=3;
			}
			break;

		case 2:
			xVelMarker=xPos;
			xPos=xPos-currentspeed*delta;
			xPos=(MathUtils.clamp(xPos, 0,worldWidth - AdjustableNumbers.playerSizeX));
			if(xPos==xVelMarker) {
				movement=4;
			}
			break;

		case 3:
			xVelMarker=xPos;
			xPos=xPos+currentspeed*delta;
			xPos=(MathUtils.clamp(xPos, 0,worldWidth - AdjustableNumbers.playerSizeX));
			if(xPos==xVelMarker) {
				movement=5;
			}
			break;

		case 4:
			
			Direction=true;
			xPos=xPos+currentspeed*delta;
			if(xPos>=AdNmLv1R1.catlimit) {
				currentwait=AdNmLv1R1.catWait;
				movement=6;
			}
			break;

		case 5:
			Direction=false;
			xPos=xPos-currentspeed*delta;
			if(xPos<=worldWidth-AdNmLv1R1.catlimit) {
				currentwait=AdNmLv1R1.catWait;
				movement=7;
			}
			break;

		case 6:
			currentwait-=delta;
			if(currentwait<=0) {
				movement=1;
			}
			break;
		case 7:
			currentwait-=delta;
			if(currentwait<=0) {
				movement=0;
			}
			
		}

		rectangle.set(xPos,yPos,xSize,ySize);
		if(checkPlayerInRange(2)) {
			currentspeed=AdNmLv1R1.catSpeedMultipl* AdNmLv1R1.catSpeed;
		}
		else {
			currentspeed=AdNmLv1R1.catSpeed;

		}
		if(checkPlayerInRange(AdNmLv1R1.catRange)) {
			room.gameOver(1);
		}
	}
	boolean checkPlayerInRange(float checkrange) {
		Rectangle R;
		if(Direction) {
			R=new Rectangle(xPos+xSize,yPos,checkrange,ySize);
		}
		else {
			R=new Rectangle(xPos-checkrange,yPos,checkrange,ySize);
		}
		return R.overlaps(player.rectangle);
	}
	@Override
	protected void deathEffect() {
		super.deathEffect();
		if(room instanceof Level0Room1) {
			Level0Room1 L=(Level0Room1) room;
			L.catKilled();
		}
	}




}
