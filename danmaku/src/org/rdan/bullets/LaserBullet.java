package org.rdan.bullets;

import java.io.FileNotFoundException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class LaserBullet extends StraightBullet implements Bullet {

	public LaserBullet(double x, double y, double angle, double velocity)
			throws SlickException, FileNotFoundException {
		super(x, y, angle, velocity);
		this.image = new Image("/resources/testlaser.png");
	}
	
	public void step(int delta){
		super.step(delta);
		this.image.setRotation((float) ((angle * 180) / Math.PI));
	}
	
	public Shape getHitbox() {
		return new Rectangle((float)x, (float)y, (float)image.getWidth(), (float)image.getHeight()).transform(Transform.createRotateTransform((float) (angle*180/Math.PI)));
	}
	
	public Bullet createNew(){
		try {
			return new LaserBullet(this.x, this.y, this.angle, this.velocity);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SlickException e) {
			e.printStackTrace();
			return null;
		}
	}
}
