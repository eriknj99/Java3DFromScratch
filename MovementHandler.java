import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

public class MovementHandler {

	private GUI g;
	private Timer looper;
	
	boolean f, b, l, r, u, d = false;
	int rX, rY, rZ = 0;
	boolean rCOM = true;
	
	public MovementHandler(GUI g) {
		this.g = g;
		
		looper = new Timer(1, (ActionListener) new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loop();
			}
		});
		
		looper.start();
		
	}
	//This loop function is called by Timer looper every mS this ensures smooth movement of objects.
	private void loop() {
		if(f) {
			moveAll(0,0,-1);
		}
		if(b) {
			moveAll(0,0,1);
		}
		if(l) {
			moveAll(-1,0,0);
		}
		if(r) {
			moveAll(1,0,0);
		}
		if(u) {
			moveAll(0,-1,0);
		}
		if(d) {
			moveAll(0,1,0);
		}
		rotateAll();
	}
	
	private void rotateAll() {
		for(int i = 0; i < g.objects.size(); i++) {
			
			//if rCOM rotate objects around their center otherwise rotate around the camera (0,0,0).
			if(rCOM) {
				g.objects.get(i).rotate(rX, rY, rZ, g.objects.get(i).getCenterOfMass());
			}else {
				g.objects.get(i).rotate(rX, rY, rZ, new Point3D(0,0,0));
			}
		}
	}
	
	private void moveAll(double x, double y, double z) {
		for(int i = 0; i < g.objects.size(); i++) {
			g.objects.get(i).move(x, y, z);
		}
	}
	//Intercept user input from JFrame GUI and use it to move objects around.
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			f = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			l = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			b = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			r = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			d = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			u = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_I) {
			rX = 1;
		}
		if(e.getKeyCode() == KeyEvent.VK_K) {
			rX = -1;
		}
		if(e.getKeyCode() == KeyEvent.VK_J) {
			rY = 1;
		}
		if(e.getKeyCode() == KeyEvent.VK_L) {
			rY = -1;
		}
		if(e.getKeyCode() == KeyEvent.VK_U) {
			rZ = 1;
		}
		if(e.getKeyCode() == KeyEvent.VK_O) {
			rZ = -1;
		}

	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W) {
			f = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_A) {
			l = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_S) {
			b = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			r = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
			d = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			u = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_I) {
			if(rX == 1) {
				rX = 0;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_K) {
			if(rX == -1) {
				rX = 0;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_J) {
			if(rY == 1) {
				rY = 0;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_L) {
			if(rY == -1) {
				rY = 0;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_U) {
			if(rZ == 1) {
				rZ = 0;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_O) {
			if(rZ == -1) {
				rZ = 0;
			}
		}
	}
	
	
}
