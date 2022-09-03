package window.frame;

import javax.swing.JFrame;

import lombok.Getter;
import window.panel.ConfigPanel;
import window.panel.NewTaskPanel;

@Getter
public class NewTaskFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	public ConfigPanel parent_obj;
	
	public NewTaskFrame(ConfigPanel parent, String task_name, String days) {
		this.parent_obj = parent;
		setup_frame_settings();
		this.add(new NewTaskPanel(this, task_name, days));
	}

	private void setup_frame_settings() {
		this.setTitle("New Task");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(parent_obj.getParent_obj());
		this.setUndecorated(false);
		this.setSize(0x171,0x0A0);
		this.setResizable(true);
		this.setVisible(true);
	}
}