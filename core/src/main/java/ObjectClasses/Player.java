package ObjectClasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import io.github.mayas_fall.OwnClasses.AdjustableNumbers;
import io.github.mayas_fall.OwnClasses.Levels.Room;

public class Player {
	public float xPos;
	public float yPos;
	public float yVel;
	public Boolean LastDirection=true;
	public float AttackCooldown;
	public Rectangle rectangle;
	Room room;
		
	private float ImageSwitcher=0;
	private float oldXpos;
	
	Texture PlayerStanding;
	Texture PlayerLeft;
	Texture PlayerRight;
	Texture PlayerLeft2;
	Texture PlayerRight2;
	
	public Player(float x, float y,Room room) {
		xPos=x;
		yPos=y;
		this.room=room;
		
		this.rectangle=new Rectangle();
		
		PlayerStanding=new Texture("Seliniassets/Character000.png");
		PlayerLeft=new Texture("Seliniassets/Character001.png");
		PlayerLeft2=new Texture("Seliniassets/Character002.png");
		PlayerRight=new Texture("Seliniassets/Character003.png");
		PlayerRight2=new Texture("Seliniassets/Character004.png");

		
	}
	public Texture GetCorrectTexture(float delta) {
		if(AttackCooldown<0&&AttackCooldown!=-1) {
			AttackCooldown=-1;
			Attack();
		}
	
		ImageSwitcher+=delta;
		
		if(oldXpos!=xPos) {
			oldXpos=xPos;

		if(ImageSwitcher>2*AdjustableNumbers.imageSwitcher) {
			ImageSwitcher-=2*AdjustableNumbers.imageSwitcher;
		}
		
		if(LastDirection) {
			if(ImageSwitcher<AdjustableNumbers.imageSwitcher) {
				return PlayerRight;}
			else {
				return PlayerRight2;
				}
			}
		else {
			if(ImageSwitcher<AdjustableNumbers.imageSwitcher) {
				return PlayerLeft;}
			else {
				return PlayerLeft2;
				}
		}
		}
		return PlayerStanding;
		}

	void Attack(){
		room.attack();
	}
	
	public void DoPhysics(float delta) {
		yVel-=AdjustableNumbers.gravity*delta;
		yPos+=yVel;
 		if(yPos<AdjustableNumbers.lowerDeathPositionY) {
			System.out.println("Died - Maya fell");
			room.gameOver(0);
		}
 		if(yVel<0) {
 		float newPos=room.TestPlatformUnderPlayer(yPos-yVel);
 		
 		if (newPos!=yPos) {
 			yVel=0;
 		}
 		yPos=newPos;
 		}
	}
	public void DoMovement(float delta, boolean right, float worldWidth) {
		LastDirection=right;
		rectangle.set(xPos,yPos,AdjustableNumbers.playerSizeX,AdjustableNumbers.playerSizeY);
		
		if (right&&room.canPlayerWalk(right) && canJump()) {
		xPos+=AdjustableNumbers.playerSpeed * delta;
		LastDirection=true;
		
		}
		else if(room.canPlayerWalk(right)&&canJump()) {
		xPos+=-AdjustableNumbers.playerSpeed * delta;
		LastDirection=false;
		}
		else if(LastDirection&&room.canPlayerWalk(LastDirection)) {
			xPos+=AdjustableNumbers.playerSpeed*delta/2;
		}
		else if (!LastDirection&&room.canPlayerWalk(LastDirection)) {
			xPos-=AdjustableNumbers.playerSpeed*delta/2;
		}
		xPos=(MathUtils.clamp(xPos, 0,worldWidth - AdjustableNumbers.playerSizeX));

}		

		

	
	public boolean canJump() {
		if (yVel==0) {
			//kommt wenn nicht direkt gesetzt vernachlässigbar selten vor
			return true;
		}
		return false;
	}
}
