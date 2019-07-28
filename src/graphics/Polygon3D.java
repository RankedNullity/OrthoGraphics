package graphics;

import java.awt.Color;

import math.linalg.*;
import math.linalg.lin3d.*;

public class Polygon3D {
	private Color c;
	private Vector[] vertices;
	
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
	
	
}
