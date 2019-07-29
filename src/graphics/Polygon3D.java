package graphics;

import java.awt.Color;
import java.awt.Graphics;

import common.misc.exceptions.NotYetImplementedException;
import math.linalg.*;
import math.linalg.lin3d.*;

public class Polygon3D {
	private Color c;
	private Vector[] vertices;
	private Polygon2D drawable;
	
	public Polygon3D(double[] x, double[] y, double[] z, Color c) {
		for (int i = 0; i < x.length; i++) {
			vertices[i]  = new Vector3D(x[i], y[i], z[i]);
		}
		this.c = c; 
	}
	
	public Polygon3D(Vector3D[] vertices, Color c) {
		this.vertices = vertices;
		this.c = c;
	} 
	
	public Color getColor() {
		return c;
	}
	
	public void setColor(Color c) {
		this.c = c;
	}
	
	
	/**
	 * Applies the given transform to all the points on the polygon. (centered about the origin).
	 * @param transform
	 */
	public void applyTransform(Matrix transform) {
		for(int i = 0; i < vertices.length; i++) {
			vertices[i] = LinAlg.multiply(transform, vertices[i]);
		}
	}
	
	/**
	 * Returns the smallest Euclidean distance from this polygon to the point (x, y, z)
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public double getClosestDistance(double x, double y, double z) {
		return getClosestDistance(new Vector3D(x, y, z));
	}
	
	
	/**
	 * Returns the smallest distance from this polygon to point p. 
	 * @param p
	 * @return
	 */
	public double getClosestDistance(Vector3D p) {
		double distance = Double.MAX_VALUE;
		for (int i = 0; i < vertices.length; i++) {
			double distanceToVertex = LinAlg.norm(LinAlg.elementWiseSubtraction(p, vertices[i]), 2);
			if (distanceToVertex < distance) {
				distance = distanceToVertex;
			}
		}
		return distance; 
	}

	public void drawPolygon(Graphics g) {
		drawable.drawPolygon(g);
	}
	
	/**
	 * Method for updating the drawables for this object.
	 * Should be called everytime the object or camera changes, and
	 * once when initialized. 
	 * @param p View plane. 
	 */
	public void updateDrawable(Plane3D p) {
		throw new NotYetImplementedException();
	}
	
	
}
