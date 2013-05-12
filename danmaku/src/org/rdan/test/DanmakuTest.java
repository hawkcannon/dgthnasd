package org.rdan.test;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.SlickException;
import org.rdan.bullets.Bullet;
import org.rdan.bullets.CurvedBullet;
import org.rdan.bullets.LaserBullet;
import org.rdan.bullets.StraightBullet;

public class DanmakuTest extends BasicGame {

	List<Bullet> bullets;
	Player player;
	int x = 0;
	
	public DanmakuTest(String title) {
		super("Danmaku Test");
		bullets = Collections.synchronizedList(new LinkedList<Bullet>());
	}

	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		for(Bullet curr : bullets){
			double[] coords = curr.getCurrentXY();
			curr.getImage().draw((float) coords[0], (float) coords[1]);
		}
		player.getImage().draw((float) player.x, (float) player.y);
		bullets.get(1).getImage().draw(player.getHitbox().getCenterX(), player.getHitbox().getCenterY());
	}

	public void init(GameContainer arg0) throws SlickException {
		
		player = new Player(700, 500, 0);
	}

	public void update(GameContainer arg0, int arg1) throws SlickException {
		int i = 0;
		List<Bullet> newbullets = new LinkedList<Bullet>();
		for (Bullet curr : bullets){
			curr.step(arg1);
			double[] xy = curr.getCurrentXY();
			double x = xy[0]; double y = xy[1];
			Image cimg = curr.getImage();
			double x1 = x + cimg.getWidth();
			double y1 = y - cimg.getHeight();
			// Garbage collection
			if(!((x < 0 || x > arg0.getWidth()) && (x1 < 0 || x1 > arg0.getWidth()) &&
				  (y < 0 || y > arg0.getHeight()) && (y1 < 0 || y1 > arg0.getHeight()))){
				newbullets.add(curr);
			}
			i++;
			if(curr.getHitbox().intersects(player.getHitbox())){
				System.err.println("player collided");
			}
		}
		bullets = newbullets;
		x++;
		Input input = arg0.getInput();
		if(input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT)){
			player.startFocus();
		} else {
			player.endFocus();
		}
		if(input.isKeyDown(Input.KEY_DOWN)){
			player.onKey(Input.KEY_DOWN, arg1);
		}
		if(input.isKeyDown(Input.KEY_UP)){
			player.onKey(Input.KEY_UP, arg1);
		}
		if(input.isKeyDown(Input.KEY_LEFT)){
			player.onKey(Input.KEY_LEFT, arg1);
		}
		if(input.isKeyDown(Input.KEY_RIGHT)){
			player.onKey(Input.KEY_RIGHT, arg1);
		}
	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new DanmakuTest("test"));
		app.setDisplayMode(800, 600, false);
		app.start();
	}

}
