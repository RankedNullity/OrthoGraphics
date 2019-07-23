package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;


public class PolygonObj {
	private Polygon p;
	private Color c;
	public PolygonObj(double[] x, double[] y, Color c) {
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
