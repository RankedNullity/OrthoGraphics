package graphics;


import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GraphicsMain {	
	public static void main(String[] args) {
		JFrame mainDisplay = new JFrame();
		mainDisplay.setSize(1440, 720);
		
		JPanel container = new JPanel(); 
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		Scene3D s = new Scene3D(3); 
		Scene3D s2 = new Scene3D(3);
		container.add(s);
		container.add(s2);
		mainDisplay.add(container);
		mainDisplay.setTitle("Project BigCube");
		mainDisplay.setVisible(true);
		mainDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
