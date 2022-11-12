package window.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;

import config.Config;
import lombok.Getter;
import window.frame.MainFrameSingleton;

@Getter
public class ConfigPanel extends AbstractPanel {
	private static final long serialVersionUID = 1L;

	JButton new_button;
	int row_x = 25;
	int row_y = 50;
		
	public ConfigPanel() {
		this.setSize(0x251,0x200);
		setup_ui_components();
	}
	
	@Override
	public void setup_ui_components() {
		super.setup_ui_components();
		
		Config.properties.entrySet().stream().forEach(
				entry -> add_config_row( (String)entry.getKey(), (String)entry.getValue(), row_x, row_y));
		
		new_button = new JButton("add new task...");
		new_button.setSize(200,25);
		new_button.setLocation(getWidth()/2 - new_button.getWidth()/2 ,row_y +10);
		new_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrameSingleton.getInstance().loadPanel(new NewTaskPanel("", ""));
			}
		});
		this.add(new_button);
	
	}
	
	private void add_config_row(String key, String value, int x, int y) {
		
		String name = (key.contains("_days")) ? key.substring(0, key.length()-5 ) : "" ;
	
		if (!name.isEmpty()) {

			JLabel days = new JLabel( Config.properties.getProperty(key));
			days.setSize(50,25);
			days.setLocation(row_x, row_y);
			this.add(days);
			
			JLabel task_name = new JLabel(name);
			task_name.setSize(200,25);
			task_name.setLocation(x+20, y);
			this.add(task_name);
		
			JButton edit_button = new JButton("edit");
			edit_button.setSize(70,25);
			edit_button.setLocation(getWidth()-90, row_y);
			edit_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
				MainFrameSingleton.getInstance().loadPanel(new NewTaskPanel(name, Config.properties.getProperty(key)));
				}
			});
			this.add(edit_button);
			
			JButton del_button = new JButton("del");
			del_button.setSize(70,25);
			del_button.setLocation(getWidth()-170, row_y);
			del_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Config.properties.remove(key);
					Config.properties.remove(name+"_last_date");
					Config.save();
					MainFrameSingleton.getInstance().loadPanel(new ConfigPanel());
				}
			});
			this.add(del_button);
			
			JButton reset_button = new JButton("reset");
			reset_button.setSize(70,25);
			reset_button.setLocation(getWidth()-250, row_y);
			reset_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Config.properties.put(""+name+"_last_date", LocalDate.now()
							.minusDays(1 + Integer.parseInt((String)Config.properties.getProperty(key))).toString());
					Config.save();
					MainFrameSingleton.getInstance().loadPanel(new ConfigPanel());
				}
			});
			this.add(reset_button);
			
			row_y += 30;
			MainFrameSingleton.getInstance().setSize(this.getWidth(), 70 + row_y +25);
			
		}
	}
}
