package window.frame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import window.panel.ConfigPanel;
import window.panel.MainPanel;

public class ConfigFrame  extends JFrame implements WindowListener{
	private static final long serialVersionUID = 1L;

	public MainPanel parent;
	
	public ConfigFrame(MainPanel parent) {
		this.parent = parent;
		
		setup_frame_settings();
		
		this.add(new ConfigPanel(this));
		this.addWindowListener(this);
	}

	private void setup_frame_settings() {
		this.setTitle("configuration");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setUndecorated(false);
		this.setSize(0x201,0x200);
		this.setLocationRelativeTo(parent);
		this.setResizable(true);
		this.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		parent.parent.dispose();
		new MainFrame(this.getLocation());
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}