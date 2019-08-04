package graphics.scenes;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Random;

import common.datastructures.concrete.*;
import common.datastructures.interfaces.*;
import cube.GameCube;
import graphics.Polygon3D;
import graphics.PolygonDistancePair;
import graphics.SceneCube;
import cube.FullStickerCube;
import math.linalg.LinAlg;
import math.linalg.lin3d.*;

public class FunDemo3D extends Scene3D implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * TODO: Add a variable which keeps track of which scene this is, and stop
	 * camera controls when the current scene is not the active screen. Much much
	 * later (TM)
	 */

	// Panel Properties
	private int screenWidth;
	private static final int MaxFPS = 60;
	private double lastRefresh;
	private double drawFPS = 0, LastFPSCheck = 0, Checks = 0;

	// Camera Properties
	private Plane3d viewPlane;
	private static final double CAMERA_ROTATION_INTERVAL = 0.01;
	private boolean[] keysHeld;
	private double zoom;

	// Scene Properties
	private IList<Polygon3D> polys; // A list of all the 3d polygons to be rendered. Sorted by distance from camera.
	private SceneCube[][][] magicCube; // Keeping a pointer to all the cube objects in the magic Cube. (0,0,0) is top
										// left, (n,n,n) is bottom right
	
	private IList<SceneCube> cubes;
	private IList<Integer> cubeRotations;
	
	
	private GameCube gameCube; // Game object to keep.
	public static final double cubeSpacing = 0.1;

	// Animation Variables
	private boolean animationOn;
	private static final int ANIMATION_STEPS = 100;

	public FunDemo3D(int cubeSize, boolean animations, int screenWidth) {
		super();
		zoom = (screenWidth * screenWidth) / (Math.pow(cubeSize, 3));
		keysHeld = new boolean[4];
		gameCube = new FullStickerCube(cubeSize);
		this.screenWidth = screenWidth;
		polys = new DoubleLinkedList<>();
		lastRefresh = System.currentTimeMillis();
		this.animationOn = animations;

		// Generating the initial viewPlane
		viewPlane = new Plane3d(Math.pow(cubeSize,  2), 0, 0, Lin3d.zBasis, Lin3d.yBasis);

		// Generating the rubicks cube
		viewPlane.applyTransform(Lin3d.getRotationAroundY(Math.PI / 4));
		viewPlane.applyTransform(Lin3d.getRotationAroundX(Math.PI / 4));
		//keysHeld[0] = true;
		//keysHeld[1] = true;
		
		cubes = new DoubleLinkedList<>();
		cubeRotations = new ArrayList<>();
		
		generateCubes(cubeSize);
	}
	
	public FunDemo3D(int screenWidth) {
		this(20, true, screenWidth);
	}
	

	public void paintComponent(Graphics g) {
		boolean cameraMoved = updateCamera();
		boolean sceneChanged = updateScene();

		if (cameraMoved || sceneChanged) {
			updateDrawables();
			
			// Sets background color
			g.setColor(new Color(140, 180, 180));
			g.fillRect(0, 0, screenWidth, screenWidth);
			g.setColor(Color.black);

			// Draws all the polygons in the scene.
			for (Polygon3D p : polys) {
				p.drawPolygon(g);
			}
		}


		g.drawString("FPS: " + (int) drawFPS + " (Benchmark)", 40, 40);
		g.drawString("Current Camera Loc: (" + getCameraLoc().getX() + ", " + getCameraLoc().getY() + ", "
				+ getCameraLoc().getZ() + ")", 40, 60);
		//g.drawString("Camera Radius: " + LinAlg.norm(getCameraLoc(), 2), 40, 80);
		g.drawString("Zoom: " + zoom, 40, 80);
		
		g.drawRect(0,0, screenWidth, screenWidth);
		sleepAndRefresh();
	}

	/**
	 * Refreshes at exactly 60 FPS, or up to it if 60FPS cannot be supported.
	 */
	private void sleepAndRefresh() {
		long timeSLU = (long) (System.currentTimeMillis() - lastRefresh);
		Checks++;
		if (Checks >= 15) {
			drawFPS = Checks / ((System.currentTimeMillis() - LastFPSCheck) / 1000.0);
			LastFPSCheck = System.currentTimeMillis();
			Checks = 0;
		}

		if (timeSLU < 1000.0 / MaxFPS) {
			try {
				Thread.sleep((long) (1000.0 / MaxFPS - timeSLU));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		lastRefresh = System.currentTimeMillis();

		repaint();
	}

	private boolean updateScene() {
		Iterator<SceneCube> iter = cubes.iterator();
		for (int i = 1; i <= cubeRotations.size(); i++) {
			SceneCube c = iter.next();
			int rotationValue = cubeRotations.get(i - 1);
			if (rotationValue != -1) {
				double rotationDir = ((rotationValue > 5) ? -1.0 : 1.0) / 50;
				double slowDown = 50;
				double baseSpeed = 50;
				switch(rotationValue % 6) {
					case 0:
						c.applyTransform(Lin3d.getRotationAroundX(rotationDir * (i % slowDown + baseSpeed)/ ANIMATION_STEPS));
						break;
					case 1:
						c.applyTransform(Lin3d.getRotationAroundY(rotationDir * (i % slowDown + baseSpeed)/ ANIMATION_STEPS));
						break;
					case 2:
						c.applyTransform(Lin3d.getRotationAroundZ(rotationDir *  (i % slowDown + baseSpeed)/ ANIMATION_STEPS));
						break;
					case 3:
						c.applyTransform(Lin3d.getRotationAroundX(rotationDir / 2.0 *  (i % slowDown + baseSpeed)  / ANIMATION_STEPS));
						c.applyTransform(Lin3d.getRotationAroundZ(rotationDir / 2.0 *  (i % slowDown + baseSpeed) / ANIMATION_STEPS));
						break;
					case 4:
						c.applyTransform(Lin3d.getRotationAroundX(rotationDir / 2.0 *  (i % slowDown + baseSpeed)  / ANIMATION_STEPS));
						c.applyTransform(Lin3d.getRotationAroundY(rotationDir / 2.0 *  (i % slowDown + baseSpeed) / ANIMATION_STEPS));
						break;
					case 5:
						c.applyTransform(Lin3d.getRotationAroundY(rotationDir / 2.0 *  (i % slowDown + baseSpeed) / ANIMATION_STEPS));
						c.applyTransform(Lin3d.getRotationAroundZ(rotationDir / 2.0 *  (i % slowDown + baseSpeed) / ANIMATION_STEPS));
						break;		
				}
			}
		}
		return true;
	}

	/**
	 * Updates the camera based on key presses.
	 * 
	 * @return
	 */
	private boolean updateCamera() {
		boolean cameraMovement = false;
		for (int i = 0; i < keysHeld.length; i++) {
			if (keysHeld[i] && !keysHeld[(i + 2) % keysHeld.length]) {
				cameraMovement = true;
				int direction = ((i > 1) ? -1 : 1);
				if (i % 2 == 0) {
					viewPlane.applyTransform(Lin3d.getRotationAroundY(direction * CAMERA_ROTATION_INTERVAL));
				} else {
					viewPlane.applyTransform(Lin3d.getRotationAroundZ(direction * CAMERA_ROTATION_INTERVAL));
				}
			}
		}
		return cameraMovement;
	}

	/**
	 * Sorts polys in non-decreasing order of distance from camera, and updates each
	 * drawable.
	 */
	private void updateDrawables() {
		IPriorityQueue<PolygonDistancePair> pq = new ArrayHeap<>();
		Vector3d cameraLoc = getCameraLoc();
		
		while (!polys.isEmpty()) {
			Polygon3D currentPoly = polys.remove();
			pq.insert(new PolygonDistancePair(currentPoly, currentPoly.getAverageDistance(cameraLoc)));
		}

		while (!pq.isEmpty()) {
			Polygon3D p = pq.removeMin().getPolygon();
			p.updateDrawable(viewPlane, zoom, screenWidth);
			p.calculateLighting(viewPlane);
			polys.add(p);
		}
		
		/*for (Polygon3D p : polys) {
			p.updateDrawable(viewPlane, zoom, screenWidth);
		}*/
		
	}

	/**
	 * Returns the camera location.
	 * 
	 * @return
	 */
	public Vector3d getCameraLoc() {
		return this.viewPlane.getPoint();
	}

	/**
	 * Adds the given polygon to the list of polygons in the scene.
	 * 
	 * @param p
	 */
	public void addPolygon(Polygon3D p) {
		polys.add(p);
	}

	/**
	 * Generates the cubes in the scene.
	 */
	public void generateCubes(int size) {
		double offSet = size % 2 == 1 ? -0.5: 0;
		double width = 1.0;
		int half = size / 2;
		Random r = new Random(5);
		// Draw the cubes.
		int count = 0;
		for (int x = - half; x < half + size % 2 ; x += width) {
			for (int y = - half; y < half + size % 2; y += width) {
				for (int z = - half; z < half + size % 2; z += width) {
					if (r.nextDouble() < 0.01) {
						SceneCube c = new SceneCube(this, x + offSet, y + offSet, z + offSet, width);
						cubes.add(c);
						cubeRotations.add(count++ % 12);
					}
					if ( x == y && y == z && z == 0) {
						SceneCube c = new SceneCube(this, x - width, y - width, z - width,  width * 2);
						Polygon3D[] faces = c.getFaces();
						for (int i = 0; i < faces.length; i++) {
							faces[i].setColor(Color.pink);
						}
						cubes.add(c);
						cubeRotations.add(-1);
					}
					
				}
			}
		}
		updateDrawables();
	}

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
	public void keyTyped(KeyEvent arg0) {
	}

}
