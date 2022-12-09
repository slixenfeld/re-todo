package window.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import window.frame.MainFrameSingleton;

public class AbstractPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JButton new_button;
	
	JPanel thisPanel = this;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(21, 81, 89));
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		g.setColor(new Color(41, 164, 181));
		g.drawRect(0, 0, getWidth(), getHeight());
	}

	public void setup_ui_components() {
		this.setLayout(null);
		MainFrameSingleton.getInstance().setSize(this.getSize());
	
		JButton closeButton = new JButton("x");
		closeButton.setFocusable(false);
		closeButton.setSize(40,34);
		closeButton.setLocation(getWidth()-41, 1);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (thisPanel.getClass() == MainPanel.class) {
					System.exit(0);
				} else {
					MainFrameSingleton.getInstance().loadPanel(new MainPanel());
				}
			}
		});
		this.add(closeButton);
	}
}
