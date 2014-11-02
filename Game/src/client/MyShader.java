package client;

import org.lwjgl.util.vector.Matrix4f;

import engine.Shader;

public class MyShader extends Shader{
	
	private static String vertexShader = "vertex.glsl";
	private static String fragmentShader = "fragment.glsl";
	
	private int projectionMatrixLocation, viewMatrixLocation, modelMatrixLocation;

	public MyShader() {
		super(vertexShader, fragmentShader);
		
		bindAttribLocation(0, "in_Position");
		bindAttribLocation(1, "in_texCoords");
		linkAndValidate();
		
		projectionMatrixLocation = getUniformLocation("projectionMatrix");
		viewMatrixLocation = getUniformLocation("viewMatrix");
		modelMatrixLocation = getUniformLocation("modelMatrix");
	}
	
	public void loadProjectionMatrix(Matrix4f matrix)
	{
		this.getUniform(projectionMatrixLocation, matrix);
	}
	
	public void loadViewMatrix(Matrix4f matrix)
	{
		this.getUniform(viewMatrixLocation, matrix);
	}
	
	public void loadModelMatrix(Matrix4f matrix)
	{
		this.getUniform(modelMatrixLocation, matrix);
	}

	public int getProjectionMatrixLocation() {
		return projectionMatrixLocation;
	}

	public int getViewMatrixLocation() {
		return viewMatrixLocation;
	}

	public int getModelMatrixLocation() {
		return modelMatrixLocation;
	}
	
}
