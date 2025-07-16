package ObjectClasses;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import io.github.mayas_fall.OwnClasses.AdjustableNumbers;
import io.github.mayas_fall.OwnClasses.Levels.Room;
import io.github.mayas_fall.OwnClasses.Levels.Level0Rooms.Level0Room0;
import io.github.mayas_fall.OwnClasses.Levels.Level0Rooms.Level0Room1;

public class Statue extends Enemy{
	
	private Texture Statue;
	private Texture StatueDamaged;
	
	public Statue(float statuePositionX, float statuePositionY, Rectangle rechteck, Room r) {
		super(statuePositionX, statuePositionY,AdjustableNumbers.statueSizeX,AdjustableNumbers.statueSizeY, AdjustableNumbers.statueHealth, rechteck,r);
		Statue=new Texture("Seliniassets/Statue.png");
		StatueDamaged=new Texture("Seliniassets/StatueDamaged.png");
	}


	@Override
	public Texture getCorrectTexture() {
		switch(Leben) {
		case 2:
		case 1:
			return StatueDamaged;
		default:
			return Statue;
		}
	}

	@Override
	public void Movement(float worldwith,float delta) {
		// Do nothing u are a Statue Idiot
		
	}
@Override 
public boolean damage(int Damage) {
	System.out.println(Leben);
	Leben-=Damage;
	switch(Leben) {
	case 4:
		room.ShowText("The Statue looks bored");
		break;
	case 3:
		room.ShowText("The Statue looks annoyed");
		break;
	case 2: 
		room.ShowText("the Statue looks impressed");
		break;
	case 1:
		room.ShowText("the Statue now watches your movements with fear in its eyes");
		break;
	default:
		room.ShowText("the Statue Crumbles under your attack");
		if(room instanceof Level0Room0) {
			Level0Room0 L=(Level0Room0)room;
			L.StatueKilled();
		}
		return true;
		}
	

	return false;
}

}
