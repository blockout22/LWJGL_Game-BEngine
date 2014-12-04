package client;


public class Input {

	public static void update(Game game) {
		keyboard(game);
		mouse(game);
	}
	
	private static void keyboard(Game game) {
//		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
//			game.cam.moveForward(0.01f);
//		}
//
//		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
//			game.cam.moveBack(0.01f);
//		}
//
//		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
//			game.cam.moveLeft(0.01f);
//		}
//
//		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
//			game.cam.moveRight(0.01f);
//		}
//
//		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
//			game.cam.moveDown(0.01f);
//		}
//
//		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
//			game.cam.moveUp(0.01f);
//		}
//		
//		if(Keyboard.isKeyDown(Keyboard.KEY_R))
//		{
//			System.out.println("Rotating");
//			game.cam.rotate(1f, 1f);
//		}
//
//		while (Keyboard.next()) {
//			if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
//				TexturedMesh mesh = OBJLoader.loadObjModel("models/stall.obj", "images/stall.png", new Vector3f(-game.cam.getCameraPos().x, -game.cam.getCameraPos().y, -game.cam.getCameraPos().z));
//				game.meshList.add(mesh);
//			}
//			
//			if(Keyboard.isKeyDown(Keyboard.KEY_B))
//			{
//				System.out.println("Pressed B!¬!!!!!!");
//				Game.getBrowser().showWindow();
//			}
//			
//			if(Keyboard.isKeyDown(Keyboard.KEY_P))
//			{
//				Game.getMusic().playSound();
//			}
//			
//			if(Keyboard.isKeyDown(Keyboard.KEY_L))
//			{
//				Game.getMusic().stop();
//			}
//			
//			if(Keyboard.isKeyDown(Keyboard.KEY_UP))
//			{
//				float currentVol = Game.getMusic().getVolume();
//				Game.getMusic().setVolume(currentVol += 1.1f);
//			}
//			
//			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
//			{
//				float currentVol = Game.getMusic().getVolume();
//				Game.getMusic().setVolume(currentVol -= 1.1f);
//			}
//		}
	}

	private static void mouse(Game game) {
	}

}
