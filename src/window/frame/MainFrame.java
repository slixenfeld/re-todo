package window.frame;

import java.awt.Color;

import javax.swing.JFrame;

import window.panel.MainPanel;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainFrame() {
		setup_frame_settings();
		this.add(new MainPanel(this));
		this.setVisible(true);
	}
	
	private void setup_frame_settings() {
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setSize(0x171,0x200);
		this.setResizable(true);
	}
}
