package graphics;

import javax.swing.JPanel;

import java.awt.Graphics;

import common.datastructures.concrete.*;
import common.datastructures.interfaces.*;
import cube.Cube;
import cube.FullStickerCube;
import math.linalg.lin3d.*;

public class Scene3D extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final double[] ORIGIN = new double[] {0,0,0};
	private static final double[] DEFAULT_CAMERA_LOC = new double[] {0, 0, 10};
	
	
	// Panel Properties
	private double screenWidth;
	private int FOV;
	private static int FPS = 60;
	
	// Scene Properties 
	private double[] cameraLoc;
	private double phi, theta; 
	private Plane3D viewPlane;
	public static final double cubeSpacing = 0.2;
	
	private IList<Polygon3D> polys = new DoubleLinkedList<>();
	
	private Cube abstractCube; 

	
	
	  
	public void paintComponent(Graphics g) {
		g.drawLine(0, 0, 100, 100);
	}
	
	public Scene3D(int size) {
		cameraLoc = DEFAULT_CAMERA_LOC;
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
