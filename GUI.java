import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

public class GUI extends JFrame implements KeyListener{

	ArrayList<Object3D> objects = new ArrayList<Object3D>();
	ArrayList<BufferedImage> frameBuffer = new ArrayList<BufferedImage>(); 
	
	Timer bufferTimer;
	Timer drawTimer;
	
	private MovementHandler movementHandler;
	
	//Arbitrary field of view that I have found looks good.
	private final double FOV = 500;
	
	public GUI(String objLoc) throws IOException {
		this.setBounds(0,0,500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		bufferTimer = new Timer(1, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				buffer();
			}
			
		});
		
		movementHandler = new MovementHandler(this);
		this.addKeyListener(this);
		drawTimer = new Timer(1, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();
			}
			
		});
		
		objects.add(OBJLoader.load(objLoc));
		
		bufferTimer.start();
		drawTimer.start();
		
		
	}
	
	public void paint(Graphics g) {
		//If the frameBuffer is not empty draw the oldest frame to the screen and remove it.
		if(frameBuffer.size() > 0) {
			g.drawImage(frameBuffer.get(0),0,0, null);
			frameBuffer.remove(0);
		}
		
	}
	
	private void buffer() {
		
		BufferedImage frame = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D g = frame.createGraphics();
		
		//Loop through all of the layers of all of the 3D objects to get all of their points.
		for(int o = 0; o < objects.size(); o++) {
			for(int f = 0; f < objects.get(o).faces.size(); f++) {
				ArrayList<Point> projectedPoints = new ArrayList<Point>();
				for(int l = 0; l < objects.get(o).faces.get(f).lines.size(); l++) {
					for(int p = 0; p < objects.get(o).faces.get(f).lines.get(l).points.size(); p++) {
						double x = objects.get(o).faces.get(f).lines.get(l).points.get(p).x;
						double y = objects.get(o).faces.get(f).lines.get(l).points.get(p).y;
						double z = objects.get(o).faces.get(f).lines.get(l).points.get(p).z;
						
						//Calculate projection using these equations:
						// 2DX = 3DX / (-3DZ / FOV) 
						// 2DY = 3DY / (-3DZ / FOV) 
						if(z > 0) {
							Point tmp = new Point((int)Math.round((x / (-z / FOV)) + this.getWidth() / 2), (int)Math.round((y / (-z / FOV)) + this.getHeight() / 2));
							projectedPoints.add(tmp);
						}
						
						
					}
				}
				
				int[] xp = new int[projectedPoints.size()];
				int[] yp = new int[projectedPoints.size()];
				
				for(int t = 0; t < projectedPoints.size(); t++) {
					xp[t] = projectedPoints.get(t).x;
					yp[t] = projectedPoints.get(t).y;
				}
				
				g.drawPolygon(new Polygon(xp, yp, projectedPoints.size()));
			}
		}
		
		//If frameBuffer is too big clear it. This may skip a few frames now and then but it will reduce latency overall. 
		if(frameBuffer.size() > 3) {
			frameBuffer = new ArrayList<BufferedImage>();
		}
		//Add the frame to the frame buffer
		frameBuffer.add(frame);
		
	}

	//Intercept user input and pass it to movementHandler.
	@Override
	public void keyPressed(KeyEvent e) {
		movementHandler.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		movementHandler.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//Do nothing
	}
	
}
