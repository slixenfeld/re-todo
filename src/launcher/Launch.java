package launcher;

import javax.swing.UIManager;

import config.Config;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialOceanicTheme;
import window.frame.MainFrame;

public class Launch {
	public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialOceanicTheme()));
        } catch (Exception e) {
        	System.out.println("setting look and feel failed: " + e.getMessage());
        }
        Config.init();
		new MainFrame();
	}
}
