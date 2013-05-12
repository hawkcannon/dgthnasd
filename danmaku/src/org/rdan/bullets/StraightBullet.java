package org.rdan.bullets;

import java.io.FileNotFoundException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class StraightBullet implements Bullet {

	protected double x;
	protected double y;
	protected double angle;
	protected double velocity;
	protected Image image;

	public StraightBullet(double x, double y, double angle, double velocity, Image image) {
		this.x = x;
		this.y = y;
		this.angle = (angle * Math.PI) / 180;
		this.velocity = velocity;
		this.image = image;
		
		this.image.setCenterOfRotation((float)this.x + (image.getWidth() / 2), (float)this.y + (image.getHeight() / 2));
	}
	
	public StraightBullet(double x, double y, double angle, double velocity, String imagepath) throws FileNotFoundException {
		this.x = x;
		this.y = y;
		this.angle = (angle * Math.PI) / 180;
		this.velocity = velocity;
		try{
			this.image = new Image(imagepath);
		} catch(Exception e){
			throw new FileNotFoundException("could not find file at " + imagepath);
		}
	}
	
	public StraightBullet(double x, double y, double angle, double velocity) throws FileNotFoundException {
		this.x = x;
		this.y = y;
		this.angle = (angle * Math.PI) / 180;
		this.velocity = velocity;
		this.image = null;
	}

	public double[] getNextXY(double delta) {
		return new double[]
				{x + (velocity * Math.cos(angle) * (delta / 1000)),
				y - (velocity * Math.sin(angle) * (delta / 1000))};
	}

	public void step(int delta) {
		this.x = x + (velocity * Math.cos(angle) * ((double)delta / 1000));
		this.y = y - (velocity * Math.sin(angle) * ((double)delta / 1000));
	}

	public double[] getCurrentXY() {
		return new double[]{x, y};
	}

	public Image getImage() {
		return this.image;
	}
	
	public Shape getHitbox() {
		int[] centerxy = this.getCenterXY();
		int x = centerxy[0];
		int y = centerxy[1];
		return new Circle(x, y, 0.9F*(float) Math.sqrt(Math.pow((image.getWidth()/2),2)+Math.pow(image.getHeight()/2, 2)));
	}

	public int[] getCenterXY() {
		return new int[]{(int) (x + (image.getWidth() / 2)), (int) (y - (image.getHeight() / 2))};
	}

	public Bullet createNew() {
		return new StraightBullet(x, y, angle * 180 / Math.PI, velocity, image);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
