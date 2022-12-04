package window.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import config.Config;
import lombok.Getter;
import window.frame.MainFrame;
import window.frame.MainFrameSingleton;

@Getter
public class MainPanel extends AbstractPanel {
	
	private static final long serialVersionUID = 1L;

	
	JButton add_button;
	
	int row_x = 5;
	int row_y = 0;
	
	private List<String> categories;
	
	public MainPanel this_obj = this;
	
	public MainPanel() {
		Config.load();
		this.setSize(0x171,0x200);
		setup_ui_components();
	}
	
	@Override
	public void setup_ui_components() {
		super.setup_ui_components();
		
		add_button = new JButton(new ImageIcon(this.getClass().getResource("/resources/icons/add.png")));
		add_button.setFocusable(false);
		add_button.setSize(35,34);
		add_button.setLocation(1,1);
		add_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrameSingleton.getInstance().loadPanel(new NewTaskPanel("", "", "", false));
			}
		});
		this.add(add_button);

		loadCategories();
		addTabButtons();
		addTaskButtons();
	}

	private void loadCategories() {
		
		categories = new ArrayList<>();	
		categories.add("");
		
		Config.properties.entrySet().stream()
			.filter(entry -> entry.getKey().toString().contains("_category"))
			.forEach(entry -> {
				if(!categories.contains(entry.getValue()))
					categories.add((String)entry.getValue()); 
			});
	}
	
	private void addTabButtons() {
		int index = 0;
		int width = ((0x171 ) / categories.size());

		for (String category : categories) {
			addTabButton(category, 1+ (index * width), 35 , width -2 , 25);
			index++;
		}
	}
	
	private void addTabButton(String name, int x, int y, int w, int h) {
		TabButton tabButton = new TabButton(name);
		tabButton.setDefaults();
		tabButton.setBounds(x, y, w, h);
		this.add(tabButton);
	}
	
	public void addTaskButtons() {
		Config.properties.entrySet().stream().forEach(
			entry -> addTaskButton( (String)entry.getKey(), (String)entry.getValue(), row_x, 65 + row_y));
	}
	
	private void addTaskButton(String key, String value, int x, int y) {
		
		String short_key = (key.contains("_days")) ? key.substring(0, key.length()-5 ) : "" ;
	
		
		if (!short_key.isEmpty()) {
			
			String category = "";
			if (Config.properties.getProperty(short_key + "_category") != null)
				category = Config.properties.getProperty(short_key + "_category");
			
			if (category.equals(MainFrame.currentTab))
			{
				boolean repeats;
				
				
				if (Config.properties.getProperty(short_key + "_repeating") == null)
					repeats = true;
				else
					repeats = Config.properties.getProperty(short_key + "_repeating").equals("true");
	
				TaskButton task_button = new TaskButton(short_key, getExpiredTime(key, short_key), repeats);
				task_button.setDefaults();
				task_button.setLocation(x, y);
				this.add(task_button);
			
				row_y += 30;
				MainFrameSingleton.getInstance().setSize(this.getWidth(), 65 + row_y);
			}
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
