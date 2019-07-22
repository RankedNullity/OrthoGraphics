package code;

import java.io.*;
import java.nio.*;
import java.lang.Math;
import javax.swing.*;
import static com.jogamp.opengl.GL4.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.util.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.texture.*;
import com.jogamp.common.nio.Buffers;
import org.joml.*;

public class Code extends JFrame implements GLEventListener
{	private GLCanvas myCanvas;
	private int renderingProgram;
	private int vao[] = new int[1];
	
	private Vector3f terLoc = new Vector3f(0.0f, 0.0f, 0.0f);
	private Vector3f cameraLoc = new Vector3f(0.5f, -0.5f, 1.5f);
	
	private float tessInner = 30.0f;
	private float tessOuter = 20.0f;
	
	// allocate variables for display() function
	private FloatBuffer vals = Buffers.newDirectFloatBuffer(16);
	private Matrix4f pMat = new Matrix4f();  // perspective matrix
	private Matrix4f vMat = new Matrix4f();  // view matrix
	private Matrix4f mMat = new Matrix4f();  // model matrix
	private Matrix4f mvpMat = new Matrix4f(); // model-view-perspective matrix
	private int mvpLoc;
	private float aspect;

	public Code()
	{	setTitle("Chapter 12 - program 1");
		setSize(600, 600);
		myCanvas = new GLCanvas();
		myCanvas.addGLEventListener(this);
		this.add(myCanvas);
		this.setVisible(true);
	}

	public void display(GLAutoDrawable drawable)
	{	GL4 gl = (GL4) GLContext.getCurrentGL();
		gl.glUseProgram(renderingProgram);
		mvpLoc = gl.glGetUniformLocation(renderingProgram, "mvp");
		
		vMat.identity().setTranslation(-cameraLoc.x(), -cameraLoc.y(), -cameraLoc.z());
		
		mMat.identity().setTranslation(terLoc.x(), terLoc.y(), terLoc.z());
		mMat.rotateX((float) Math.toRadians(35.0f));

		mvpMat.identity();
		mvpMat.mul(pMat);
		mvpMat.mul(vMat);
		mvpMat.mul(mMat);
		
		gl.glUniformMatrix4fv(mvpLoc, 1, false, mvpMat.get(vals));
	
		gl.glFrontFace(GL_CCW);

		gl.glPatchParameteri(GL_PATCH_VERTICES, 1);
		gl.glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);  // FILL or LINE
		gl.glDrawArrays(GL_PATCHES, 0, 1);
	}

	public void init(GLAutoDrawable drawable)
	{	GL4 gl = (GL4) GLContext.getCurrentGL();
		renderingProgram = Utils.createShaderProgram("code/vertShader.glsl", "code/tessCShader.glsl", "code/tessEShader.glsl", "code/fragShader.glsl");
		
		aspect = (float) myCanvas.getWidth() / (float) myCanvas.getHeight();
		pMat.identity().setPerspective((float) Math.toRadians(60.0f), aspect, 0.1f, 1000.0f);
		
		gl.glGenVertexArrays(vao.length, vao, 0);
		gl.glBindVertexArray(vao[0]);
	}

	public static void main(String[] args) { new Code(); }
	public void dispose(GLAutoDrawable drawable) {}
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
	{	aspect = (float) myCanvas.getWidth() / (float) myCanvas.getHeight();
		pMat.identity().setPerspective((float) Math.toRadians(60.0f), aspect, 0.1f, 1000.0f);
	}
}