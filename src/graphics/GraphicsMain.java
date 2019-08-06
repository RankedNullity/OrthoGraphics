package graphics;


import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import graphics.scenes.CubeScene3D;
import graphics.scenes.GraphicsDemo3D;


public class GraphicsMain {	
	public static void main(String[] args) {
		JFrame mainDisplay = new JFrame();
		
		boolean showCube = true;
		boolean showDemo = !showCube;
		
		int windowSize = 900;
		mainDisplay.setSize(windowSize + 20, windowSize + 20);
		JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0)); 
		//container.setLayout(new BoxLayout(container, FlowLayout));
		/*for(int i = 1; i < 11; i++) {
			CubeScene3D s = new CubeScene3D(i, true, windowSize);
			s.setPreferredSize(new Dimension(windowSize, windowSize));
			container.add(s);
		}*/
		
		if(showDemo) {
			GraphicsDemo3D s2 = new GraphicsDemo3D(windowSize);
			s2.setPreferredSize(new Dimension(windowSize, windowSize));
			container.add(s2);
		}
		
		
		if(showCube) {
			CubeScene3D s = new CubeScene3D(70, true, windowSize);
			s.setPreferredSize(new Dimension(windowSize, windowSize));
			container.add(s);
		}
		
		
		mainDisplay.add(container);
		mainDisplay.setTitle("Project BigCube");
		mainDisplay.setVisible(true);
		mainDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
