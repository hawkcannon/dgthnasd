package org.rdan.bullets;

import java.io.FileNotFoundException;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class CurvedBullet implements Bullet {
	double x, y;
	double angle;
	double theta; //change in angle per second
	double velocity;
	Image image;
	
	public CurvedBullet(double x, double y, double angle, double theta,
			double velocity, Image image) {
		super();
		this.x = x;
		this.y = y;
		this.angle = (angle * Math.PI) / 180;
		this.theta = (theta * Math.PI) / 180;
		this.velocity = velocity;
		this.image = image;
	}
	
	public void test(){}
	
	public CurvedBullet(double x, double y, double angle, double theta,
			double velocity, String imagepath) throws FileNotFoundException {
		super();
		this.x = x;
		this.y = y;
		this.angle = (angle * Math.PI) / 180;
		this.theta = (theta * Math.PI) / 180;
		this.velocity = velocity;
		try{
			this.image = new Image(imagepath);
		} catch(SlickException e){
			throw new FileNotFoundException("couldn't find image " + imagepath);
		}
	}

	public double[] getNextXY(double delta) {
		return new double[]{
				x + (velocity * Math.cos(angle) * (delta / 1000)),
				y - (velocity * Math.sin(angle) * (delta / 1000))
		};
	}

	public void step(int delta) {
		angle = angle + (theta * ((double)delta / 1000));
		x = x + (velocity * Math.cos(angle) * ((double)delta / 1000));
		y = y - (velocity * Math.sin(angle) * ((double)delta / 1000));	
	}

	public double[] getCurrentXY() {
		return new double[]{x, y};
	}

	public Image getImage() {
		return image;
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
		return new CurvedBullet(x, y, angle * 180 / Math.PI, theta * 180 / Math.PI, velocity, image);
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

	public double getTheta() {
		return theta;
	}

	public void setTheta(double theta) {
		this.theta = theta;
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
