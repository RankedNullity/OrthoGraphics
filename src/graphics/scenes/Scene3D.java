package graphics.scenes;

import java.awt.Graphics;

import javax.swing.JPanel;

import common.datastructures.concrete.DoubleLinkedList;
import common.datastructures.interfaces.IList;
import graphics.Polygon3D;
import math.linalg.lin3d.Plane3d;
import math.linalg.lin3d.Vector3d;

public abstract class Scene3D extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	
	// Scene properties
	// List of all polygons in the scene.
	protected IList<Polygon3D> polys;
	
	// Camera Properties
	protected Plane3d viewPlane;
	
	/**
	 * TODO: Add a variable which keeps track of which scene this is, and stop
	 * camera controls when the current scene is not the active screen. Much much
	 * later (TM)
	 */
	protected final int sceneID;
	protected static int activeScene;
	protected static int totalScenes;
	
	protected final int MaxFPS;
	protected double lastRefresh;
	protected double drawFPS = 0, LastFPSCheck = 0, Checks = 0;
	protected int screenWidth, screenHeight;
	protected boolean debug;
	
	public Scene3D(int screenWidth, int screenHeight, int maxFPS, boolean debug) {
		sceneID = totalScenes++;
		polys = new DoubleLinkedList<>();
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		MaxFPS = maxFPS;
		this.debug = debug;
	}
	 
	/**
	 * Adds p to the scene.
	 * @param p
	 */
	public void addPolygon(Polygon3D p) {
		polys.add(p);
	}
	
	public void paintComponent(Graphics g) {
		boolean cameraMoved = updateCamera();
		boolean sceneChanged = updateScene();

		if (cameraMoved || sceneChanged) {
			updateDrawables();
			drawBackground(g);
			render(g);
		}

		if (debug) {
			displayDebug(g);
		}
		
		g.drawRect(0,0, screenWidth - 1, screenWidth - 1);
		sleepAndRefresh();
	}
	
	/**
	 * Refreshes the display and caps the FPS. Also keeps track of the current FPS.
	 */
	protected void sleepAndRefresh() {
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
	
	/**
	 * Uses g to draw all the debug details for the scene.
	 * @param g
	 */
	abstract protected void displayDebug(Graphics g);
	
	/**
	 * Uses g to draw the background.
	 * @param g
	 */
	abstract protected void drawBackground(Graphics g);
	
	/**
	 * Generates everything in the initial scene.
	 * @param size
	 */
	abstract protected void generateScene(int size);
	
	/**
	 * Return the location of the camera in 3d.
	 * @return
	 */
	abstract public Vector3d getCameraLoc();
	
	/**
	 * Attempts to update the camera (if any are needed)
	 * @return true if camera changed. Returns false otherwise.
	 */
	abstract protected boolean updateCamera();
	
	/**
	 * Attempts to update the objects in the scene. 
	 * @return true if anything was updated. Returns false otherwise.
	 */
	abstract protected boolean updateScene();
	
	/**
	 * Method which updates the drawables for the scene.
	 */
	abstract protected void updateDrawables();
	
	/**
	 * Uses g to render the full scene from the camera's perspective.
	 * @param g
	 */
	abstract protected void render(Graphics g);
	
}
