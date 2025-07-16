package Projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import io.github.mayas_fall.OwnClasses.AdNmLv1R1;
import io.github.mayas_fall.OwnClasses.Levels.Room;

public class RangedEnemyProjectiles extends Projectile{

	public RangedEnemyProjectiles(float xPos, float yPos, Room R) {
		super(new Texture("Seliniassets/Projectile.png"),xPos,yPos,AdNmLv1R1.darterProjectileSize,R);
		System.out.println("Projectile Created");
		xVel=-AdNmLv1R1.darterProjectileSpeed;
	}
	@Override
	public void Move(float delta) {
		xPos+=xVel*delta;;
		rectangle=new Rectangle(xPos,yPos,Size,Size);
	}

	@Override
	public Rectangle getRectangle() {
		return rectangle;
	}

}
