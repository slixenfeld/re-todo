package window.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import config.Config;
import lombok.AllArgsConstructor;
import window.frame.MainFrame;

@AllArgsConstructor
public class TaskButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	JFrame parent;
	String key;
	int days;

	private final JPanel dueColorPanel = new JPanel();
	
	private void setDueColor(int days) {

		Color backgroundColor = new Color(255,255,255,40);
		if (days > 0) {
			if (days > 15) days = 15;
			backgroundColor = new Color(10,100 + (days * 10),10, 40);
		}
		else if (days < 0 ) {
			if (days < -25) days = -25;
			backgroundColor = new Color(0 + (-days * 10),0,0, 40);		
		}
		dueColorPanel.setSize(299,25);
		dueColorPanel.setBackground(backgroundColor);
		this.add(dueColorPanel);
	}
	
	public void setDefaults() {
		this.setLayout(null);
		this.setSize(300,25);
		this.setEnabled(false);
		this.setText(key + " ("+days+")");
		setDueColor(days);
		if (days <= 0) {
			this.setEnabled(true);
			this.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Config.properties.put(key+"_last_date", LocalDate.now().toString());
					Config.save();
					
					parent.dispose();
					new MainFrame();
				}
			});
		}
	}
}
