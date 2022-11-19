package window.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import config.Config;
import lombok.AllArgsConstructor;
import window.frame.MainFrameSingleton;

@AllArgsConstructor
public class TaskButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	String key;
	int days;
	boolean repeats;

	private final JLabel repeat_label = new JLabel();
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
		
		repeat_label.setText((repeats) ? "R" : "");
		repeat_label.setLocation(5, 0);
		repeat_label.setSize(30,25);
		repeat_label.setFont(new Font("Verdana", Font.BOLD, 12));
		add(repeat_label);
		
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
					
						boolean repeats;
						if (Config.properties.getProperty(key + "_repeating") == null)
							repeats = true;
						else
							repeats = Config.properties.getProperty(key + "_repeating").equals("true");
						
						if (repeats) {
							Config.properties.put(key+"_last_date", LocalDate.now().toString());
						} else {
							Config.properties.remove(key+"_days");
							Config.properties.remove(key+"_last_date");
							try {
								Config.properties.remove(key+"_repeating");
							} catch (Exception e2) {}	
						}

					Config.save();
					MainFrameSingleton.getInstance().loadPanel(new MainPanel());
				}
			});
		}
	}
}
