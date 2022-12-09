package window.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import lombok.AllArgsConstructor;
import window.frame.MainFrame;
import window.frame.MainFrameSingleton;
import window.panel.MainPanel;

@AllArgsConstructor
public class TabButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	
	String name;

	public void setDefaults() {

		this.setLayout(null);
		this.setSize(100,25);
		this.setEnabled(true);
		this.setText(name);
		
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.currentTab = name;
				MainFrameSingleton.getInstance().loadPanel(new MainPanel());
			}
		});
	}
}
