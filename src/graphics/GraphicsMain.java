package graphics;


import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import graphics.scenes.CubeScene3D;
import graphics.scenes.DemoScene3D;


public class GraphicsMain {	
	public static void main(String[] args) {
		JFrame mainDisplay = new JFrame();
		mainDisplay.setSize(800, 800);
		int windowSize = 800;
		JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)); 
		//container.setLayout(new BoxLayout(container, FlowLayout));
		/*for(int i = 1; i < 11; i++) {
			CubeScene3D s = new CubeScene3D(i, true, windowSize);
			s.setPreferredSize(new Dimension(windowSize, windowSize));
			container.add(s);
		}*/
		//CubeScene3D s = new CubeScene3D(3, true, windowSize);
		DemoScene3D s2 = new DemoScene3D(windowSize);
		//s.setPreferredSize(new Dimension(windowSize, windowSize));
		s2.setPreferredSize(new Dimension(windowSize, windowSize));
		
		//container.add(s);
		container.add(s2);

		
		
		mainDisplay.add(container);
		mainDisplay.setTitle("Project BigCube");
		mainDisplay.setVisible(true);
		mainDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
