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
		for(int i = 0; i < x.length; i++)
			p.addPoint((int)x[i], (int)y[i]);
		p.npoints = x.length;
		this.c = c;
	}
	
	public Polygon2D(int numVertices, Color c) {
		p = new Polygon(); 
		for (int i = 0; i < numVertices; i++) {
			p.addPoint(0, 0);
		}
		p.npoints = numVertices;
		this.c = c;
		
	}
	
	/**
	 * Draws the Polygon2D using g.
	 * @param g Graphics
	 */
	public void drawPolygon(Graphics g) {
		g.setColor(c); 
		g.fillPolygon(p); 
		g.setColor(Color.black);
		g.drawPolygon(p);
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
	
	
	public void setColor(Color c) {
		this.c = c;
	}
}
