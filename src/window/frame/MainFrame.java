package window.frame;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JFrame;

import window.panel.MainPanel;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainFrame() {
		setup_frame_settings();
		this.setType(Type.UTILITY);
		this.add(new MainPanel(this));
		this.setVisible(true);
	}

	public MainFrame(Point location) {
		setup_frame_settings();
		this.setType(Type.UTILITY);
		this.setLocation(location);
		this.add(new MainPanel(this));
		this.setVisible(true);
	}
	
	private void setup_frame_settings() {
		this.setTitle("re-todo");
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(false);
		this.setSize(0x171,0x200);
		this.setResizable(true);
	}
}
