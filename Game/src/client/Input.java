package client;

import org.lwjgl.input.Keyboard;

import engine.Camera;


public class Input {
	
	public static void update(Camera cam)
	{
		keyboard(cam);
		mouse(cam);
	}

	private static void keyboard(Camera cam) {
		if(Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			cam.moveForward(0.01f);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S))
		{
			cam.moveBack(0.01f);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			cam.moveLeft(0.01f);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D))
		{
			cam.moveRight(0.01f);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
		{
			cam.moveDown(0.01f);
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		{
			cam.moveUp(0.01f);
		}
	}

	private static void mouse(Camera cam) {
	}

}
