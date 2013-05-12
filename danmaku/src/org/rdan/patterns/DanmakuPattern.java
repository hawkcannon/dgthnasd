package org.rdan.patterns;

import java.util.List;

import org.rdan.bullets.Bullet;

public abstract class DanmakuPattern {
	protected Bullet[] bullets;

	public Bullet[] getBullets(){
		return bullets;
	}
	
	public void iterate(int delta){
		for(Bullet bullet : bullets){
			bullet.step(delta);
		}
	}
	
}
