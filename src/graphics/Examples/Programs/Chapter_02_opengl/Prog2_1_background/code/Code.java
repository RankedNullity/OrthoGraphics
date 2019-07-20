package code;

import javax.swing.*;
import static com.jogamp.opengl.GL4.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;

public class Code extends JFrame implements GLEventListener
{	private GLCanvas myCanvas;

	public Code()
	{	setTitle("Chapter 2 - program 1");
		setSize(600,400);
		setLocation(200,200);
		myCanvas = new GLCanvas();
		myCanvas.addGLEventListener(this);
		this.add(myCanvas);
		this.setVisible(true);
	}

	public void display(GLAutoDrawable drawable)
	{	GL4 gl = (GL4) GLContext.getCurrentGL();
		gl.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
		gl.glClear(GL_COLOR_BUFFER_BIT);
	}

	public static void main(String[] args)
	{ new Code();
	}

	public void init(GLAutoDrawable drawable) {}
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
	public void dispose(GLAutoDrawable drawable) {}
}