package graphics;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;

import common.datastructures.concrete.*;
import common.datastructures.interfaces.*;
import cube.Cube;
import cube.FullStickerCube;
import graphics.OnlineEngine.DDDTutorial;
import math.linalg.lin3d.*;

public class Scene3D extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	// Panel Properties
	private int screenWidth;
	private static final int FPS = 60;
	private double lastRefresh;
	
	// Scene Properties 
	private double radius, phi, theta;  //Spherical coordinates for the camera location.
	private Plane3D viewPlane;
	public static final double cubeSpacing = 0.2;
	
	private IList<Polygon3D> polys;
	private IList<Polygon2D> drawables;
	private SceneCube[][][] cubes;
	
	private Cube abstractCube; 
	private boolean cameraMoved, sceneUpdated;
	
	public Scene3D(int size) {
		radius = 10;
		phi = 0;
		theta = 0; 
		cameraMoved = sceneUpdated = false; 
		abstractCube = new FullStickerCube(size);
		this.screenWidth = 720;
		polys = new ArrayList<>();
		lastRefresh = 0;
	}
	
	/**
	 * Returns the location of the camera in Cartesian coordinates. 
	 * @return
	 */
	private Vector3D getCameraLoc() {
		return new Vector3D(radius * Math.sin(phi) * Math.cos(theta), radius * Math.sin(phi) * Math.sin(theta), radius * Math.cos(phi));
	}
	
	  
	public void paintComponent(Graphics g) {
		if (cameraMoved || sceneUpdated) { 
			calculateDrawables();
		}
		
		// Sets background color
		g.setColor(new Color(140, 180, 180));
		g.fillRect(0, 0, screenWidth, screenWidth);

		//this.repaint();
	}
	
	private void calculateDrawables() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Adds the given polygon to the list of polygons in the scene. 
	 * @param p
	 */
	public void addPolygon(Polygon3D p) {
		polys.add(p);
	}
	
	/**
	 * 
	 */
	public void generateViewPlane() {
		
	}
	
	/**
	 * Take the point which is a projection onto the plane and gets the 
	 * coordinate in terms of the plane. 
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public static double[] getDrawableCoordinate(double x, double y, double z) {
		return null;
	}
	
	
	/**
	 * Generates the cubes in the scene.
	 */
	public void generateCubes(int size) {
		double offset = 0;
		if (size % 2 == 1) {
			// Draw the cubes in the centered planes. 
		}
		// Draw the cubes in each quadrant. 
	}
	
	public Vector3D getRotatation() {
		return null;
		
	}
	
}
