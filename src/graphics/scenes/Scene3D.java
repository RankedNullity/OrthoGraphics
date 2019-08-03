package graphics.scenes;

import javax.swing.JPanel;

import common.datastructures.interfaces.IList;
import graphics.Polygon3D;
import math.linalg.lin3d.Plane3d;

public abstract class Scene3D extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private IList<Polygon3D> polys;
	private Plane3d viewPlane;
	/**
	 * TODO: Add a variable which keeps track of which scene this is, and stop
	 * camera controls when the current scene is not the active screen. Much much
	 * later (TM)
	 */
	private final int sceneID;
	private static int activeScene;
	private static int totalScenes;
	
	public Scene3D() {
		sceneID = totalScenes++;
	}
	 
	
	public void addPolygon(Polygon3D p) {
		polys.add(p);
	}
}
