package org.rdan.test;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Player {
	double v_up_abs = 300;
	double v_down_abs = 200;
	double v_left_abs = 200;
	double v_right_abs = 200;
	double v_up = 300;
	double v_down = 200;
	double v_left = 170;
	double v_right = 170;
	
	double x, y;
	double theta;
	
	Image image;
	public Player(double x, double y, double theta) {
		super();
		this.x = x;
		this.y = y;
		this.theta = theta;
		try {
			image = new Image("/resources/hideousplayerart.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void startFocus(){
		if(v_up < v_up_abs){
			return;
		}
		v_up = v_up_abs / 3.5;
		v_down = v_down_abs / 3.5;
		v_left = v_left_abs / 2.5;
		v_right = v_right_abs / 2.5;
	}
	
	public void endFocus(){
		if(v_up == v_up_abs){
			return;
		}
		v_up = v_up_abs;
		v_down = v_down_abs;
		v_left = v_left_abs;
		v_right = v_right_abs;		
	}
	
	public boolean isFocused(){
		return v_up < v_up_abs;
	}
	
	public void onKey(int key, int delta){
		switch (key) {
			case Input.KEY_UP:
				y = y - (v_up * (double)delta / 1000D);
				break;
			case Input.KEY_DOWN:
				y = y + (v_down * (double)delta / 1000D);
				break;
			case Input.KEY_LEFT:
				x = x - (v_left * (double)delta / 1000D);
				break;
			case Input.KEY_RIGHT:
				x = x + (v_right * (double)delta / 1000D);
				break;
		}
	}
	
	public Image getImage(){
			return image;
	}
	
	public Shape getHitbox(){
		return new Circle((float)(this.x + image.getWidth()/2), (float)(this.y - image.getHeight()/2), 
				(float) (Math.sqrt(Math.pow(image.getWidth() / 2,2) + Math.pow(image.getHeight() / 2,2)) * 0.5));
	}
}
