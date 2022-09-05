package launcher;

import javax.swing.UIManager;

import config.Config;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialOceanicTheme;
import window.frame.MainFrame;

public class Launch {
	public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialLiteTheme()));
        } catch (Exception e) {
        	System.out.println("setting look and feel failed: " + e.getMessage());
        }
        Config.init();
		new MainFrame();
	}
}
