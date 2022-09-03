package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	public static Properties properties = new Properties();
	
	public static void save() {
		try {
			FileOutputStream stream = new FileOutputStream(System.getenv("APPDATA") + "/retodo.conf");
			properties.store(stream, null);
			stream.flush();
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void load() {
		try {
			FileInputStream stream = new FileInputStream(System.getenv("APPDATA") + "/retodo.conf");
			properties.load(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
