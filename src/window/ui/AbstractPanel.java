package window.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import window.frame.MainFrameSingleton;

public class AbstractPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JButton close_button;
	JButton new_button;
	
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
		
		close_button = new JButton("x");
		close_button.setFocusable(false);
		close_button.setSize(40,25);
		close_button.setLocation(getWidth()-41, 1);
		close_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		this.add(close_button);
		
		
		if (this.getClass() != MainPanel.class) {
			new_button = new JButton("Return..");
			new_button.setSize(100,25);
			new_button.setLocation(5, 5);
			new_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					MainFrameSingleton.getInstance().loadPanel(new MainPanel());
				}
			});
			this.add(new_button);
		}
	}
}
