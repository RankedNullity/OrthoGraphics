package graphics;

import javax.swing.JPanel;

import java.awt.Graphics;

import common.datastructures.concrete.*;
import common.datastructures.interfaces.*;

public class DisplayBox extends JPanel {
	private double[] cameraLoc;
	private double[] viewToward;
	private IList<Polygon3D> polys = new DoubleLinkedList<>();
	
	
	public void paintComponent(Graphics g) {
		g.fill3DRect(0, 0, 100, 100, true);
	}
	
	public DisplayBox() {
		
	}
	
}
