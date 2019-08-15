package graphics.scenes.scenes3D;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import common.datastructures.concrete.*;
import common.datastructures.interfaces.*;
import cube.GameCube;
import graphics.ObjectDistancePair;
import graphics.sceneObjects.Cube3D;
import graphics.sceneObjects.Polygon3D;
import cube.FullStickerCube;
import math.linalg.lin3d.*;

public class RubicksScene3D extends Scene3D implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	// Camera Properties
	private static final double CAMERA_ROTATION_INTERVAL = 0.001;
	private boolean[] keysHeld;

	private Cube3D[][][] magicCube; // Keeping a pointer to all the cube objects in the magic Cube. (0,0,0) is top
										// left, (n,n,n) is bottom right

	private GameCube gameCube; // Game object to keep.

	// Animation Variables
	private boolean animationOn;
	private static final int ANIMATION_STEPS = 100;

	public RubicksScene3D(int cubeSize, boolean animations, int screenWidth) {
		super(screenWidth, screenWidth, 50, true);
		zoom = (0.5 * screenWidth) / (cubeSize);
		keysHeld = new boolean[4];
		gameCube = new FullStickerCube(cubeSize);
		
		lastRefresh = System.currentTimeMillis();
		this.animationOn = animations;

		// Generating the initial viewPlane
		viewPlane = new Plane3d(Math.pow(cubeSize,  2), 0, 0, Lin3d.zBasis, Lin3d.yBasis);

		// Generating the Rubicks cube
		generateScene(cubeSize);
		keysHeld[0] = true;
		keysHeld[1] = true;
	}

	public RubicksScene3D(int size) {
		this(size, true, 720);
	}

	protected void drawBackground(Graphics g) {
		g.setColor(new Color(140, 180, 180));
		g.fillRect(0, 0, screenWidth, screenWidth);
		g.setColor(Color.black);
	}
	
	
	protected void displayDebug(Graphics g) {
		int startX = 40, y = 40, interval = 15;
		g.drawString("FPS: " + (int) drawFPS + " (Benchmark)", startX, y);
		y += interval;
		g.drawString("Current Camera Loc: (" + getCameraLoc().getX() + ", " + getCameraLoc().getY() + ", "
				+ getCameraLoc().getZ() + ")", startX, y);
		y += interval;
		//g.drawString("Camera Radius: " + LinAlg.norm(getCameraLoc(), 2), 40, 80);
		g.drawString("Zoom: " + zoom, startX, y);
		y += interval;
		g.drawString("# of Cubes: " + sceneObjs.size(), startX, y);
	}

	protected boolean updateScene() {
		// TODO Continue/Finish any animation that is happening.
		if (animationOn) {

		} else {

		}
		return false;
	}

	/**
	 * Updates the camera based on key presses.
	 * 
	 * @return
	 */
	protected boolean updateCamera() {
		boolean cameraMovement = false;
		for (int i = 0; i < keysHeld.length; i++) {
			if (keysHeld[i] && !keysHeld[(i + 2) % keysHeld.length]) {
				cameraMovement = true;
				int direction = ((i > 1) ? -1 : 1) * gameCube.getSize();
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
	 * Returns the camera location.
	 * 
	 * @return
	 */
	public Vector3d getCameraLoc() {
		return this.viewPlane.getPoint();
	}


	/**
	 * Generates the cubes in the scene.
	 */
	public void generateScene(int size) {
		double offSet = size % 2 == 1 ? -0.5: 0;
		int width = 1;
		int half = size / 2;
		// Draw the cubes.
		for (int x = - half; x < half + size % 2 ; x += width) {
			for (int y = - half; y < half + size % 2; y += width) {
				for (int z = - half; z < half + size % 2; z += width) {
					Cube3D c = new Cube3D(x + offSet, y + offSet, z + offSet, width);
					sceneObjs.add(c);
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
