package org.rdan.bullets;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public interface Bullet {
	
	/**
	 * Get next X,Y of this bullet relative to the upper left corner of the screen.
	 * @param delta The change in time -- in ms -- between calls
	 * @return double[]: (x, y)
	 */
	public double[] getNextXY(double delta);
	
	/**
	 * In essence, set the current X and Y to the results of getNextXY(delta). Move the bullet forward
	 *   however much it should move in [delta]ms.
	 * @param delta Time in ms to move forward.
	 */
	public void step(int delta);
	
	/**
	 * Get the current X,Y of this bullet relative to the upper left corner of the screen.
	 * @return double[]: (x, y)
	 */
	public double[] getCurrentXY();
	
	/**
	 * Return the Slick Image that should represent this Bullet.
	 * @return Image for this Bullet
	 */
	public Image getImage();
	
	/**
	 * Returns the rectangular hitbox of the bullet.
	 */
	public Shape getHitbox();
	
	/**
	 * because screw image.getCenterOfRotation
	 */
	public int[] getCenterXY();
	
	/**
	 * This is a hack meant to get around my lack of meta-programming knowledge.
	 */
	public Bullet createNew();
	
	/**
	 * GETTERS AND SETTERS BE BELOW
	 */
	public double getX();
	
	public void setX(double x);
	
	public double getY();
	
	public void setY(double y);
}
