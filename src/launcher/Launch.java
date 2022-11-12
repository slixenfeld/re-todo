package launcher;

import javax.swing.UIManager;

import config.Config;
import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;
import mdlaf.themes.MaterialLiteTheme;
import mdlaf.themes.MaterialOceanicTheme;
import window.frame.MainFrame;
import window.frame.MainFrameSingleton;
import window.ui.MainPanel;

public class Launch {
	public static void main(String args[]) {
        try {
        	if (args.length > 0) {
        		if (args[0].equalsIgnoreCase("lite")) {
        			UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialLiteTheme()));
        		} else if (args[0].equalsIgnoreCase("ocean")) {
        			UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialOceanicTheme()));
        		} else if (args[0].equalsIgnoreCase("mars")) {
        			UIManager.setLookAndFeel(new MaterialLookAndFeel(new JMarsDarkTheme()));
        		}
        	}
        	else {
        		UIManager.setLookAndFeel(new MaterialLookAndFeel(new MaterialLiteTheme()));
        	}
        } catch (Exception e) {
        	System.out.println("setting look and feel failed: " + e.getMessage());
        }
		MainFrameSingleton.getInstance().loadPanel(new MainPanel());
	}
}
