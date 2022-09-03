package window.frame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.naming.OperationNotSupportedException;
import javax.swing.JFrame;

import lombok.Getter;
import window.panel.ConfigPanel;
import window.panel.MainPanel;

@Getter
public class ConfigFrame  extends JFrame implements WindowListener{
	private static final long serialVersionUID = 1L;

	public MainPanel parent_obj;
	
	public ConfigFrame(MainPanel parent) {
		this.parent_obj = parent;
		
		setup_frame_settings();
		
		this.add(new ConfigPanel(this));
		this.addWindowListener(this);
	}

	private void setup_frame_settings() {
		this.setTitle("re-todo configuration");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(false);
		this.setSize(0x251,0x200);
		this.setLocationRelativeTo(parent_obj);
		this.setResizable(true);
		this.setVisible(true);
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		parent_obj.getParent_obj().dispose();
		new MainFrame(this.getLocation());
	}
	
	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}
}