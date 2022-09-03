package launcher;

import javax.swing.UIManager;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.JMarsDarkTheme;
import window.frame.MainFrame;

public class Launch {
	public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new MaterialLookAndFeel(new JMarsDarkTheme()));
        } catch (Exception e) {
        	System.out.println("setting look and feel failed: " + e.getMessage());
        }
		new MainFrame();
	}
}
