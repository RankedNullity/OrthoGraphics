package graphics.scenes;

import java.awt.Color;
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
	
	// viewplane for the scene.
	protected Plane3d viewPlane;
	/**
	 * TODO: Add a variable which keeps track of which scene this is, and stop
	 * camera controls when the current scene is not the active screen. Much much
	 * later (TM)
	 */
	protected final int sceneID;
	private static int activeScene;
	private static int totalScenes;
	
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
	 
	
	public void addPolygon(Polygon3D p) {
		polys.add(p);
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
	
	abstract protected void displayDebug(Graphics g);
	abstract protected void drawBackground(Graphics g);
	abstract protected void generateScene(int size);
	abstract public Vector3d getCameraLoc();
	abstract protected boolean updateCamera();
	abstract protected boolean updateScene();
	abstract protected void updateDrawables();
	abstract protected void render(Graphics g);
	
}
