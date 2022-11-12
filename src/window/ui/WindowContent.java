package window.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WindowContent {
	
	public JPanel getPanel(JFrame parentFrame) {
		JPanel panel = new JPanel();
		panel.setSize(parentFrame.getSize());
		panel.setLayout(null);
		return panel;
	}
}
