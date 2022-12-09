package window.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		this.setSize(100, 25);
		this.setEnabled(true);

		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.currentTab = name;
				MainFrameSingleton.getInstance().loadPanel(new MainPanel());
			}
		});
	}

	public void addColorPanel() {

		JPanel bgPanel = new JPanel();

		if (name.equals(MainFrame.currentTab)) {
			bgPanel.setSize(this.getWidth(), this.getHeight());
			bgPanel.setBackground(new Color(50, 50, 50, 40));
			this.add(bgPanel);
		}

		JLabel textLabel = new JLabel();
		textLabel.setText(name);
		textLabel.setLocation(10, 0);
		textLabel.setSize(this.getWidth(), this.getHeight());
		textLabel.setFont(new Font("", Font.BOLD, 16));
		add(textLabel);
	}
}
