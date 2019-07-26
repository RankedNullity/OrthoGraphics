package graphics;

import java.awt.Toolkit;

import javax.swing.JFrame;

import graphics.OnlineEngine.Screen;

public class GraphicsMain extends JFrame {
	
	Scene3D s = new Scene3D();
	
	public GraphicsMain() {
		//setUndecorated(true);
		add(s);
		setSize(1080, 720);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		JFrame mainDisplay = new GraphicsMain();
		mainDisplay.setTitle("Project BigCube");

	}
}
