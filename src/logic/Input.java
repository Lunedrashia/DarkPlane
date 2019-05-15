package logic;

import java.util.HashSet;

import javafx.scene.input.KeyCode;

public class Input {

	public static KeyCode skill3Key = KeyCode.SPACE;
	
	public static boolean skill1Used = false;
	public static boolean skill2Used = false;
	public static boolean skill3Used = false;
	private static HashSet<KeyCode> allInput = new HashSet<KeyCode>();
	
	public static void setKeyPressed(KeyCode k) {
		allInput.add(k);
	}
	
	public static void releaseKey(KeyCode k) {
		allInput.remove(k);
	}
	
	public static boolean checkKeyPressed(KeyCode k) {
		return allInput.contains(k);
	}
	
	public static void update() {
		skill1Used = false;
		skill2Used = false;
		skill3Used = false;
//		if (!allInput.isEmpty()) {
//			for (KeyCode k: allInput) {
//				System.out.print("+" + k);
//			}
//			System.out.println();
//		}
	}
	
	public static void reset() {
		allInput.clear();
		skill1Used = false;
		skill2Used = false;
		skill3Used = false;
	}
}
