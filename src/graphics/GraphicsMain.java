package graphics;

import java.awt.Toolkit;

import javax.swing.JFrame;

import graphics.OnlineEngine.Screen;

public class GraphicsMain {	
	public static void main(String[] args) {
		JFrame mainDisplay = new JFrame();
		mainDisplay.setSize(1080, 720);
		Scene3D s = new Scene3D(3); 
		mainDisplay.add(s);
		
		mainDisplay.setTitle("Project BigCube");
		mainDisplay.setVisible(true);
	}
}
