package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import lombok.NoArgsConstructor;
import window.frame.MainFrameSingleton;
import window.ui.MainPanel;

@NoArgsConstructor
public class Config {
	
	public static Properties properties = new Properties();
	
	private static Timer refreshTimer;
	
	public static void init() {
		try {
			refreshTimer = new Timer();
			refreshTimer.scheduleAtFixedRate(new TimerTask() {
				@Override
				public void run() {
					MainFrameSingleton.getInstance().loadPanel(new MainPanel());
				}
			}, 100, (60000 * 60));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void save() {
		try {
			FileOutputStream stream = new FileOutputStream(getConfigPath());
			properties.store(stream, null);
			stream.flush();
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void load() {
		try {
			FileInputStream stream = new FileInputStream(getConfigPath());
			properties.load(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String getConfigPath() {
		String os = (System.getProperty("os.name").toLowerCase());
		if (os.contains("win")) {
			return System.getenv("APPDATA") + "/retodo.conf";
		} else if (os.contains("nux") || os.contains("nix") || os.contains("aix") || os.contains("mac") ) {
			return "/home/" + System.getProperty("user.name") + "/.local/share/applications/retodo.conf";
		} else {
			return "/retodo.conf";
		}
	}
}
