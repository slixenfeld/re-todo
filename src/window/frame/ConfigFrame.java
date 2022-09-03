package window.frame;

import javax.swing.JFrame;

import window.panel.ConfigPanel;

public class ConfigFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	public ConfigFrame() {
		setup_frame_settings();
		
		this.add(new ConfigPanel(this));
	}

	private void setup_frame_settings() {
		this.setTitle("configuration");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(false);
		this.setSize(0x171,0x200);
		this.setResizable(true);
		this.setVisible(true);
	}
}