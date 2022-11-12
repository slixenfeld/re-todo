package window.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import config.Config;
import lombok.Getter;
import window.frame.MainFrameSingleton;

@Getter
public class MainPanel extends AbstractPanel {
	private static final long serialVersionUID = 1L;
	
	JButton config_button;
	
	int row_x = 25;
	int row_y = 50;
	
	public MainPanel this_obj = this;
	
	public MainPanel() {
		Config.load();
		this.setSize(0x171,0x200);
		setup_ui_components();
	}
	
	@Override
	public void setup_ui_components() {
		super.setup_ui_components();
		
		config_button = new JButton(new ImageIcon(this.getClass().getResource("/res/config.png")));
		config_button.setFocusable(false);
		config_button.setSize(35,35);
		config_button.setLocation(1,1);
		config_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrameSingleton.getInstance().loadPanel(new ConfigPanel());
			}
		});
		this.add(config_button);

		
		addTaskButtons();
	}

	public void addTaskButtons() {
		Config.properties.entrySet().stream().forEach(
			entry -> addTaskButton( (String)entry.getKey(), (String)entry.getValue(), row_x, row_y));
	}
	
	private void addTaskButton(String key, String value, int x, int y) {
		
		String short_key = (key.contains("_days")) ? key.substring(0, key.length()-5 ) : "" ;
	
		if (!short_key.isEmpty()) {
					
			TaskButton task_button = new TaskButton(short_key, getExpiredTime(key, short_key));
			task_button.setDefaults();
			task_button.setLocation(x, y);
			this.add(task_button);
			
			row_y += 30;
			MainFrameSingleton.getInstance().setSize(this.getWidth(), 70 + row_y);
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

}
