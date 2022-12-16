package util;

public class Util {
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception e) {
			System.out.println("Error while sleeping, " + e.getMessage());
		}
	}

}
