package client;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import engine.OBJLoader;
import engine.TexturedMesh;

public class Input {

	public static void update(Game game) {
		keyboard(game);
		mouse(game);
	}

	private static void keyboard(Game game) {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			game.cam.moveForward(0.01f);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			game.cam.moveBack(0.01f);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			game.cam.moveLeft(0.01f);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			game.cam.moveRight(0.01f);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			game.cam.moveDown(0.01f);
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			game.cam.moveUp(0.01f);
		}

		while (Keyboard.next()) {
			if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
				TexturedMesh mesh = OBJLoader.loadObjModel("models/stall.obj", "images/stall.png", new Vector3f(-game.cam.getCameraPos().x, -game.cam.getCameraPos().y, -game.cam.getCameraPos().z));
				game.meshList.add(mesh);
			}
		}
	}

	private static void mouse(Game game) {
	}

}
