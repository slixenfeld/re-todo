package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import config.Config;

public class MainPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	JButton config_button;
	
	int row_x = 25;
	int row_y = 50;
	
	JFrame parent;
	
	public MainPanel(JFrame parent) {
		this.parent = parent;
		
		Config.load();
		
		this.setSize(parent.getSize());
		this.setLayout(null);
		
		setup_ui_components();
	}
	
	private void setup_ui_components() {
		config_button = new JButton("configure..");
		config_button.setSize(80,25);
		config_button.setLocation(this.getWidth()-config_button.getWidth()-20,20);
		config_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ConfigFrame();
			}
		});
		this.add(config_button);
		
		for( Entry<Object, Object> entry : Config.properties.entrySet() ) {
			show_row( (String)entry.getKey(), (String)entry.getValue(), row_x, row_y);
		}
	}
	
	private void show_row(String key, String value, int x, int y) {
		
		String name = (key.contains("_days")) ? key.substring(0, key.length()-5 ) : "" ;
	
		if (!name.isEmpty()) {
					
			boolean button_disabled = checkTodoExpired(key, name);
			
			JButton task_button = new JButton(name);
			task_button.setSize(300,25);
			task_button.setLocation(x, y);
			if (button_disabled)
				task_button.setEnabled(false);
			else {
				task_button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Config.properties.put(name+"_last_date", LocalDate.now().toString());
						System.out.println("date set");
						Config.save();
						
						parent.dispose();
						new MainFrame();
					}
				});
			}
			this.add(task_button);
			
			row_y += 25;
		}
	}

	private boolean checkTodoExpired(String key, String name) {
		String date_string = (String) Config.properties.get(name+"_last_date");
		LocalDate date_after_days = LocalDate.parse(date_string)
				.plusDays(Integer.parseInt((String)Config.properties.get(key)));
		if (date_after_days.compareTo(LocalDate.now()) > 0) {
			return true;
		}
		return false;
	}
}
