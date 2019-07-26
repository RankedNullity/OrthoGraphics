package graphics;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class GraphicsMain extends JFrame {
	static JFrame F = new GraphicsMain();
	Scene3D s = new Scene3D();
	public GraphicsMain() {
		//setUndecorated(true);
		add(s);
		setSize(1080, 720);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
	}
}
