package org.rdan.patterns;

import java.util.LinkedList;
import java.util.List;

import org.rdan.bullets.Bullet;

public class StraightLinePattern extends DanmakuPattern {
	public StraightLinePattern(Bullet bulletType, int x0, int y0, double angle, int count){
		this.populateBullets(bulletType, x0, y0, angle, count);
	}
	
	private void populateBullets(Bullet bullettype, int x0, int y0, double angle, int count){
		List<Bullet> bullets = new LinkedList<Bullet>();
		int n = 0;
		
		int width = bullettype.getImage().getWidth();
		int height = bullettype.getImage().getHeight();
		
		while(n < count){
			Bullet curr = bullettype.createNew();
			curr.setX(x0 + (width * n));
			curr.setY(y0 - (height * n));
			bullets.add(curr);
			
			n++;
		}
	}
}
