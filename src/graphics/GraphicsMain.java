package graphics;

import java.awt.Toolkit;

import javax.swing.JFrame;

import graphics.OnlineEngine.Screen;
import math.linalg.lin3d.Vector3D;

public class GraphicsMain {	
	public static void main(String[] args) {
		JFrame mainDisplay = new JFrame();
		mainDisplay.setSize(720, 720);
		Scene3D s = new Scene3D(3); 
		mainDisplay.add(s);
		
		mainDisplay.setTitle("Project BigCube");
		mainDisplay.setVisible(true);
		mainDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
