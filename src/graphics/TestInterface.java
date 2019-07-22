package graphics;
import cube.*;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class TestInterface extends JFrame implements ActionListener{
	private static JFrame frame;
	private static JPanel panel;
	private static FullStickerCube cube;
	
	public static void main(String[] args) {
		TestInterface t1 = new TestInterface();
		frame = new JFrame("Cube");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel();
		
		JMenuBar menubar = new JMenuBar();
		JMenu dimensions = new JMenu("Dimension");
		JMenuItem d1 = new JMenuItem("2x2x2");
		JMenuItem d2 = new JMenuItem("3x3x3");
		JMenuItem d3 = new JMenuItem("4x4x4");
		dimensions.add(d1);
		dimensions.add(d2);
		dimensions.add(d3);
		d1.addActionListener(t1);
		d2.addActionListener(t1);
		d3.addActionListener(t1);
		menubar.add(dimensions);
		JMenu moves = new JMenu("Clockwise Moves");
		JMenuItem m1 = new JMenuItem("F");
		JMenuItem m2 = new JMenuItem("L");
		JMenuItem m3 = new JMenuItem("U");
		JMenuItem m4 = new JMenuItem("D");
		JMenuItem m5 = new JMenuItem("R");
		JMenuItem m6 = new JMenuItem("B");
		moves.add(m1);
		moves.add(m2);
		moves.add(m3);
		moves.add(m4);
		moves.add(m5);
		moves.add(m6);
		m1.addActionListener(t1);
		m2.addActionListener(t1);
		m3.addActionListener(t1);
		m4.addActionListener(t1);
		m5.addActionListener(t1);
		m6.addActionListener(t1);
		menubar.add(moves);
		
		frame.setJMenuBar(menubar);
		frame.add(panel);
		frame.setSize(1000, 1000);
		frame.setVisible(true);
	}
	
	private static class RectDraw extends JPanel {
		private int cubeDimension;
		private final int SQUARESIZE = 50;
		private int startX;
		private int startY;
		//private Color[] colors;
		private int[][][] colorArray;
//		public RectDraw() {
//			this(2);
//		}
		
		public RectDraw(FullStickerCube cube) {
			cubeDimension = cube.getSize();
			if (cubeDimension < 0) {
				throw new IllegalArgumentException("Cube dimension cannot be negative!");
			}
			colorArray = cube.getColorArray();
			
			startX = 50;
			startY = 200;
//			colors = new Color[6];
//			colors[0] = Color.orange;
//			colors[1] = Color.green;
//			colors[2] = Color.white;
//			colors[3] = Color.blue;
//			colors[4] = Color.red;
//			colors[5] = Color.yellow;
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
//			for (int i = 0; i < colorArray.length; i++) {
//				for (int j = 0; j < colorArray[0].length; j++) {
//					for (int k = 0; k < colorArray[0][0].length; k++) {
//						System.out.println(Integer.toHexString(colorArray[i][j][k]));
//					}
//				}
//			}
			drawFace(g, startX, startY, colorArray[Cube.LEFT]);
			drawFace(g, startX + cubeDimension * SQUARESIZE, startY, colorArray[Cube.FRONT]);
			drawFace(g, startX + cubeDimension * SQUARESIZE, startY + cubeDimension * SQUARESIZE, colorArray[Cube.DOWN]);
			drawFace(g, startX + cubeDimension * SQUARESIZE, startY - cubeDimension * SQUARESIZE, colorArray[Cube.UP]);
			drawFace(g, startX + 2 * cubeDimension * SQUARESIZE, startY, colorArray[Cube.RIGHT]);
			drawFace(g, startX + 3 * cubeDimension * SQUARESIZE, startY, colorArray[Cube.BACK]);
			
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(1000, 1000);
		}
		
		private void drawFace(Graphics g, int x, int y, int[][] colors) {
			/*
			 *  This is like O(n^2) but it's ok because cubeDimension likely < 5
			 * 
			 */
			
			for (int i = 0; i < colors.length; i++) {
				int x_prime = x;
				for (int j = 0; j < colors[0].length; j++) {
					String colorHexString = "#" + Integer.toHexString(colors[i][j]).toUpperCase();
					g.setColor(Color.decode(colorHexString));
					g.fillRect(x_prime, y, SQUARESIZE, SQUARESIZE);
					g.setColor(Color.black);
					g.drawRect(x_prime, y, SQUARESIZE, SQUARESIZE);
					
					x_prime += SQUARESIZE;
				}
				y += SQUARESIZE;
			}
			
		}
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.equals("2x2x2")) {
			System.out.println("yest");
			cube = new FullStickerCube(2, false);
			RectDraw newrect = new RectDraw(cube);
			updateRect(newrect);
		} else if (s.equals("3x3x3")) {
			System.out.println("yest2");
			cube = new FullStickerCube(3, false);
			RectDraw newrect = new RectDraw(cube);
			updateRect(newrect);
		} else if (s.equals("4x4x4")) {
			System.out.println("yest3");
			cube = new FullStickerCube(4, false);
			RectDraw newrect = new RectDraw(cube);
			updateRect(newrect);
		} else if (s.equals("F")) {
			cube.applyMove(Cube.FRONT, 0, true);
			updateRect(new RectDraw(cube));
		} else if (s.equals("L")) {
			cube.applyMove(Cube.LEFT, 0, true);
		} else if (s.equals("U")) {
			cube.applyMove(Cube.UP, 0, true);
		} else if (s.equals("D")) {
			cube.applyMove(Cube.DOWN, 0, true);
		} else if (s.equals("R")) {
			cube.applyMove(Cube.RIGHT, 0, true);
		} else if (s.equals("B")) {
			cube.applyMove(Cube.BACK, 0, true);
		}
		
	}
	
	private void updateRect(RectDraw r) {
		Component[] componentList = panel.getComponents();
		for (Component c : componentList) {
			if (c instanceof RectDraw) {
				panel.remove(c);
			}
		}
		panel.add(r);
		panel.revalidate();
		panel.repaint();
		
	}
}
