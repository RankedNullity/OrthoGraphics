package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public class Polygon2D {
	private Polygon p;
	private Color c;
	public Polygon2D(double[] x, double[] y, Color c) {
		p = new Polygon(); 
 
		p.npoints = x.length;
	}
	
	void drawPolygon(Graphics g) {
		Color current = g.getColor();
		g.setColor(c);
		g.drawPolygon(p);
		g.setColor(current);
	}
}
