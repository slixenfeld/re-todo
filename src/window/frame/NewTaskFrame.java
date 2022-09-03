package window.frame;

import javax.swing.JFrame;

import window.panel.ConfigPanel;
import window.panel.NewTaskPanel;

public class NewTaskFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	public ConfigPanel parent;
	
	public NewTaskFrame(ConfigPanel parent, String task_name, String days) {
		setup_frame_settings();
		this.parent = parent;
		
		this.add(new NewTaskPanel(this, task_name, days));
	}

	private void setup_frame_settings() {
		this.setTitle("New Task");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(false);
		this.setSize(0x171,0x100);
		this.setResizable(true);
		this.setVisible(true);
	}
}