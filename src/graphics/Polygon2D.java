package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

/**
 * Polygon2D class for graphics rendering. Represents a polygon that is drawable by Graphics. 
 * @author Jaron Wang
 *
 */
public class Polygon2D {
	private Polygon p;
	private Color c;
	public Polygon2D(double[] x, double[] y, Color c) {
		p = new Polygon(); 
 
		p.npoints = x.length;
	}
	
	/**
	 * Draws the Polygon2D using g.
	 * @param g Graphics
	 */
	public void drawPolygon(Graphics g) {
		Color current = g.getColor();
		g.setColor(c);
		g.drawPolygon(p);
		g.setColor(current);
	}
	
	/**
	 * Updates this object with the given x and y coordinates. 
	 * This action is cheaper than constructing a new Polygon2D as the size of the polygon is 
	 * garunteed to be the same. 
	 * @param x array of x-coordinates
	 * @param y array of y-coordinates
	 */
	public void updatePolygon(double[] x, double[] y) {
		if (x.length != y.length) {
			throw new IllegalArgumentException();
		}
		p.reset();
		p.npoints = x.length;
		// Updates the points to draw on the screen by simply rounding. 
		for (int i = 0; i < x.length; i++) {
			p.xpoints[i] = (int) x[i];
			p.ypoints[i] = (int) y[i];
		}
	}
}
