package util;

import config.Config;

public class Util {
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (Exception e) {
			System.out.println("Error while sleeping, " + e.getMessage());
		}
	}

	public static boolean confExists(String propertyName) {
		return (Config.properties.getProperty(propertyName) != null);
	}

	public static String getConf(String propertyName) {
		if (confExists(propertyName))
			return Config.properties.getProperty(propertyName);
		return "";
	}
}
