package window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.javafx.embed.swing.Disposer;

import config.Config;

public class ConfigPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	JButton new_button;
	int row_x = 25;
	int row_y = 50;
		
	public ConfigPanel this_obj = this;
	ConfigFrame parent;
	
	public ConfigPanel(ConfigFrame parent) {
		this.setSize(parent.getSize());
		this.setLayout(null);
		this.parent = parent;
		
		setup_ui_components();
	}
	
	private void setup_ui_components() {
		new_button = new JButton("Add +");
		new_button.setSize(80,25);
		new_button.setLocation(this.getWidth()-new_button.getWidth()-20,20);
		new_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new NewTaskFrame(this_obj, "", "");
			}
		});
		this.add(new_button);
		
		for( Entry<Object, Object> entry : Config.properties.entrySet() ) {
			addConfigRow( (String)entry.getKey(), (String)entry.getValue(), row_x, row_y);
		}
	}
	
	private void addConfigRow(String key, String value, int x, int y) {
		
		String name = (key.contains("_days")) ? key.substring(0, key.length()-5 ) : "" ;
	
		if (!name.isEmpty()) {
			JLabel task_name = new JLabel(name);
			task_name.setSize(200,25);
			task_name.setLocation(x, y);
			this.add(task_name);
			
			
			JLabel days = new JLabel( Config.properties.getProperty(key));
			days.setSize(50,25);
			days.setLocation(row_x + 50, row_y);
			this.add(days);
			
			JButton edit_button = new JButton("edit");
			edit_button.setSize(100,25);
			edit_button.setLocation(row_x+125, row_y);
			edit_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new NewTaskFrame(this_obj, name, Config.properties.getProperty(key));
				}
			});
			this.add(edit_button);
			
			JButton del_button = new JButton("del");
			del_button.setSize(100,25);
			del_button.setLocation(row_x+225, row_y);
			del_button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Config.properties.remove(key);
					Config.properties.remove(name+"_last_date");
					Config.save();
					parent.dispose();
					new ConfigFrame();
				}
			});
			this.add(del_button);
			
			
			
			row_y += 25;
		}
	}
}
