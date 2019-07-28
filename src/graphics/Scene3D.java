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
	private static final double[] ORIGIN = new double[] {0,0,0};
	private static final double[] DEFAULT_CAMERA_LOC = new double[] {0, 0, 10};
	
	
	// Panel Properties
	private int screenWidth;
	private int FOV;
	private static int FPS = 60;
	
	// Scene Properties 
	private double radius, phi, theta;  //Spherical coordinates for the camera location.
	private Plane3D viewPlane;
	public static final double cubeSpacing = 0.2;
	
	private IList<Polygon3D> polys = new DoubleLinkedList<>();
	
	private Cube abstractCube; 
	private boolean updated;
	
	/**
	 * Returns the location of the camera in cartesian coordinates. 
	 * @return
	 */
	private Vector3D getCameraLoc() {
		return new Vector3D(radius * Math.sin(phi) * Math.cos(theta), radius * Math.sin(phi) * Math.sin(theta), radius * Math.cos(phi));
	}
	
	  
	public void paintComponent(Graphics g) {
		if (updated) { 
			calculateDrawables();
		}
		g.setColor(new Color(140, 180, 180));
		g.fillRect(0, 0, screenWidth, screenWidth);
		//g.drawLine(0, 0, 100, 100);
		this.repaint();
	}
	
	private void calculateDrawables() {
		// TODO Auto-generated method stub
		
	}


	public Scene3D(int size) {
		radius = 10;
		phi = 0;
		theta = 0; 
		updated = false; 
		abstractCube = new FullStickerCube(size);
		
	}
	
	public void addPolygon(Polygon3D p) {
		// Add p to the collection of polygons. Currently this is a list 
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
		//Generates cubes in around 0,0.
	}
	
	public Vector3D getRotatation() {
		return null;
		
	}
	
}
