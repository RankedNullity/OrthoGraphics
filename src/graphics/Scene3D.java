package graphics;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import common.datastructures.concrete.*;
import common.datastructures.interfaces.*;
import common.misc.exceptions.NotYetImplementedException;
import cube.GameCube;
import cube.FullStickerCube;
import math.linalg.lin3d.*;

public class Scene3D extends JPanel implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Panel Properties
	private int screenWidth;
	private static final int FPS = 60;
	private double lastRefresh;
	
	// Camera Properties 
	private double radius, phi, theta;  //Spherical coordinates for the camera location.
	private Plane3D viewPlane;
	private static final int xOffSet = 100, yOffSet = 100;
	private static final double CAMERA_ROTATION_INTERVAL = 0.01;
	private boolean[] keysHeld;
	
	// Scene Properties
	private IList<Polygon3D> polys; // A list of all the 3d polygons to be rendered. Sorted by distance from camera. 
	private SceneCube[][][] magicCube; // Keeping a pointer to all the cube objects in the magic Cube. (0,0,0) is top left, (n,n,n) is bottom right
	
	private GameCube abstractCube; // Game object to keep. 
	public static final double cubeSpacing = 0.1;
	
	// Animation Variables
	private boolean animationOn;
	private static final int ANIMATION_STEPS = 100;
	
	
	public Scene3D(int size, boolean animations) {
		keysHeld = new boolean[4];
		abstractCube = new FullStickerCube(size);
		this.screenWidth = 720;
		polys = new DoubleLinkedList<>();
		lastRefresh = System.currentTimeMillis();
		this.animationOn = animations;
		
		// Generating the inital viewPlane
		viewPlane = new Plane3D(size * size + 5, 0,0, Lin3d.zBasis, Lin3d.yBasis);
		
		// Generating the rubicks cube
		generateCubes(size);
	}
	
	public Scene3D(int size) {
		this(size, true);
	}
	
	  
	public void paintComponent(Graphics g) {
		boolean cameraMoved = updateCamera();
		boolean sceneChanged = updateScene();
		
		if (cameraMoved || sceneChanged) {
			updateDrawables();
		}
		
		// Sets background color
		g.setColor(new Color(140, 180, 180));
		g.fillRect(0, 0, screenWidth, screenWidth);
		
		// Draws all the polygons in the scene. 
		for (Polygon3D p : polys) {
			p.drawPolygon(g);
		}
		

		sleepAndRefresh();
	}
	
	private void sleepAndRefresh() {
		long timeSLU = (long) (System.currentTimeMillis() - lastRefresh);

		if (timeSLU < 1000.0 / FPS) {
			try {
				Thread.sleep((long) (1000.0 / FPS - timeSLU));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		lastRefresh = System.currentTimeMillis();

		repaint();
	}
	
	private boolean updateScene() {
		// TODO Continue/Finish any animation that is happening. 
		throw new NotYetImplementedException();
	}

	/**
	 * Updates the camera based on key presses. 
	 * @return
	 */
	private boolean updateCamera() {
		boolean cameraMovement = false;
		for (int i = 0; i < keysHeld.length;i++) {
			if (keysHeld[i] && !keysHeld[(i + 2) % keysHeld.length]) {
				cameraMovement = true; 
				int direction = (i > 1) ? 1: -1;
				if (i % 2 == 0) {
					viewPlane.applyTransform(Lin3d.getRotationAroundY(direction * CAMERA_ROTATION_INTERVAL));
				} else {
					viewPlane.applyTransform(Lin3d.getRotationAroundZ(direction * CAMERA_ROTATION_INTERVAL));
				}
			}
		}
		return cameraMovement; 
	}

	private void updateDrawables() {
		IPriorityQueue<PolygonDistancePair> pq = new ArrayHeap<>();
		Vector3D cameraLoc = getCameraLoc();
		while (!polys.isEmpty()) {
			Polygon3D currentPoly = polys.remove();
			pq.insert(new PolygonDistancePair(currentPoly, currentPoly.getClosestDistance(cameraLoc)));
		}
		
		while(!pq.isEmpty()) {
			Polygon3D p = pq.removeMin().getPolygon();
			p.updateDrawable(viewPlane);
			polys.add(pq.removeMin().getPolygon());
		}
	}
	
	public Vector3D getCameraLoc() {
		return this.viewPlane.getPoint();
	}

	/**
	 * Adds the given polygon to the list of polygons in the scene. 
	 * @param p
	 */
	public void addPolygon(Polygon3D p) {
		polys.add(p);
	}

	
	/**
	 * Generates the cubes in the scene.
	 */
	public void generateCubes(int size) {
		// TODO
		double offset = 0;
		if (size % 2 == 1) {
			// Draw the cubes in the centered planes. 
		}
		// Draw the cubes in each quadrant. 
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W)
			keysHeld[0] = true;
		if (e.getKeyCode() == KeyEvent.VK_A)
			keysHeld[1] = true;
		if (e.getKeyCode() == KeyEvent.VK_S)
			keysHeld[2] = true;
		if (e.getKeyCode() == KeyEvent.VK_D)
			keysHeld[3] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W)
			keysHeld[0] = false;
		if (e.getKeyCode() == KeyEvent.VK_A)
			keysHeld[1] = false;
		if (e.getKeyCode() == KeyEvent.VK_S)
			keysHeld[2] = false;
		if (e.getKeyCode() == KeyEvent.VK_D)
			keysHeld[3] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
}
