package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Config {
	
	public static Properties properties = new Properties();
	
	public static Timer timer;

	public static void init( ) {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				final String default_jre = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
				File jar;
				try {
					jar = new File(Config.class.getProtectionDomain().getCodeSource().getLocation().toURI());

					final ArrayList<String> command = new ArrayList<String>();
					command.add(default_jre);
					command.add("-jar");
					command.add(jar.getPath());

					final ProcessBuilder builder = new ProcessBuilder(command);
					builder.start();
					System.exit(0);
				 } catch (URISyntaxException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, (60000*60)*3, (60*60000)*3);
	}
	
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
