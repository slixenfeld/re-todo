package window.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import config.Config;
import lombok.Getter;
import window.frame.ConfigFrame;
import window.frame.MainFrame;

@Getter
public class MainPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	JButton config_button;
	JButton close_button;
	
	int row_x = 25;
	int row_y = 50;
	
	public JFrame parent_obj;
	public MainPanel this_obj = this;
	
	public MainPanel(JFrame parent) {
		this.parent_obj = parent;
		
		Config.load();
		
		this.setSize(parent.getSize());
		this.setLayout(null);
		
		setup_ui_components();
	}
	
	private void setup_ui_components() {
		config_button = new JButton(new ImageIcon(this.getClass().getResource("/res/config.png")));
		config_button.setFocusable(false);
		config_button.setSize(35,35);
		config_button.setLocation(1,1);
		config_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ConfigFrame(this_obj);
				config_button.setEnabled(false);
			}
		});
		this.add(config_button);
		
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
		
		Config.properties.entrySet().stream().forEach(
			entry -> show_row( (String)entry.getKey(), (String)entry.getValue(), row_x, row_y));
	}
	
	private void show_row(String key, String value, int x, int y) {
		
		String name = (key.contains("_days")) ? key.substring(0, key.length()-5 ) : "" ;
	
		if (!name.isEmpty()) {
					
			int days_to_expiration = getExpiredTime(key, name);
			
			JButton task_button = new JButton(name);
			task_button.setSize(300,25);
			task_button.setLocation(x, y);
			task_button.setEnabled(false);
			task_button.setText(task_button.getText() + " ("+days_to_expiration+")");
			if (days_to_expiration <= 0) {
				task_button.setEnabled(true);
				task_button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Config.properties.put(name+"_last_date", LocalDate.now().toString());
						Config.save();
						
						parent_obj.dispose();
						new MainFrame();
					}
				});
			}
			this.add(task_button);
			
			row_y += 30;
	
			parent_obj.setSize(this.getWidth(), 70 + row_y);
		}
	}

	/**
	 * returns the days until the task expires.
	 * 
	 * @param key
	 * @param name
	 * @return days until expiration. (if the TODO is more than 5 years in the future returns - 999999999)
	 */
	private int getExpiredTime(String key, String name) {
		String date_string = (String) Config.properties.get(name+"_last_date");
		LocalDate date_after_days = LocalDate.parse(date_string)
				.plusDays(Integer.parseInt((String)Config.properties.get(key)));

		if (date_after_days.getYear() == LocalDate.now().getYear()) {
			return date_after_days.getDayOfYear() - LocalDate.now().getDayOfYear();
		} else if (date_after_days.getYear() == LocalDate.now().getYear()+1) {
			return (date_after_days.getDayOfYear() + (365 - LocalDate.now().getDayOfYear()));
		} else if (date_after_days.getYear() == LocalDate.now().getYear()+2) {
			return (365 * 1) + (date_after_days.getDayOfYear() + (365 - LocalDate.now().getDayOfYear()));
		} else if (date_after_days.getYear() == LocalDate.now().getYear()+3) {
			return (365 * 2) + (date_after_days.getDayOfYear() + (365 - LocalDate.now().getDayOfYear()));
		} else if (date_after_days.getYear() == LocalDate.now().getYear()+4) {
			return (365 * 3) + (date_after_days.getDayOfYear() + (365 - LocalDate.now().getDayOfYear()));
		} else if (date_after_days.getYear() == LocalDate.now().getYear()+5) {
			return (365 * 4) + (date_after_days.getDayOfYear() + (365 - LocalDate.now().getDayOfYear()));
		} 
		return -999999999;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(new Color(21, 81, 89));
		g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		g.setColor(new Color(41, 164, 181));
		g.drawRect(0, 0, getWidth(), getHeight());
		
	}
}
