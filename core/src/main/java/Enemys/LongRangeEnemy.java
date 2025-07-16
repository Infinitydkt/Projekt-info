package Enemys;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import ObjectClasses.Enemy;
import ObjectClasses.Player;
import io.github.mayas_fall.OwnClasses.AdNmLv1R1;
import io.github.mayas_fall.OwnClasses.Levels.Room;
import Projectiles.Projectile;
import Projectiles.RangedEnemyProjectiles;

public class LongRangeEnemy extends Enemy{
	Texture LongRangeEnemy;
	Texture LongRangeEnemyShooting;
	float AttackCooldown;
	ArrayList<Projectile> Projectiles;
	
	public LongRangeEnemy(float x, int Health,  Player p, Room r) {
		super(x, AdNmLv1R1.darterYpos, AdNmLv1R1.darterXSize, AdNmLv1R1.darterYSize, Health, new Rectangle(), r);
		
		LongRangeEnemy=new Texture("Seliniassets/Blower.png");
		Projectiles=new ArrayList<Projectile>();
	}

	@Override
	public Texture getCorrectTexture() {
		if(AttackCooldown>=AdNmLv1R1.darterShootingCooldown) {
			AttackCooldown-=AdNmLv1R1.darterShootingCooldown;
			Shoot();
			System.out.println("Shooting");
		}
		return LongRangeEnemy;
	}

	private void Shoot() {
		RangedEnemyProjectiles P=new RangedEnemyProjectiles(xPos+AdNmLv1R1.dartershootingOffsetX,yPos+=AdNmLv1R1.dartershootingOffsetY,room);
		Projectiles.add(P);
		room.AddProjectile(P);
	}

	@Override
	public void Movement(float worldWidth, float delta) {
		AttackCooldown-=delta;
		for(Projectile P:Projectiles) {
			if(P.getX()<0||P.getX()>worldWidth) {
				room.RemoveProjectile(P);
				Projectiles.remove(P);
			}
		}
		//static. don't ask why
	}
	

}
