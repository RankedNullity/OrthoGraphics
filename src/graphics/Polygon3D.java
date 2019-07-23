package graphics;

import java.awt.Color;

public class Polygon3D {
	private Color c;
	private double[] x, y, z;
	public Polygon3D(double[] x, double[] y, double[] z, Color c) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.c = c;
	}
}
