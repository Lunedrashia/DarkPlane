package logic;

import java.util.HashSet;

import javafx.scene.input.KeyCode;

public class Input {

	public static boolean shotFired = false;
	private static HashSet<KeyCode> allInput = new HashSet<KeyCode>();
	
	public static void setKeyPressed(KeyCode k) {
		if (k == KeyCode.SPACE) {
			shotFired = true;
		}
		else {
			allInput.add(k);
		}
	}
	
	public static void releaseKey(KeyCode k) {
		if (allInput.contains(k)) {
			allInput.remove(k);
		}
	}
	
	public static boolean checkKeyPressed(KeyCode k) {
		return allInput.contains(k);
	}
	
	public static void update() {
		shotFired = false;
		if (!allInput.isEmpty()) {
			for (KeyCode k: allInput) {
				System.out.print("+" + k);
			}
			System.out.println();
		}
	}
}
