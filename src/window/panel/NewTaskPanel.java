package window.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import config.Config;
import window.frame.ConfigFrame;
import window.frame.NewTaskFrame;

public class NewTaskPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	JTextField task_name_field;
	JSpinner task_repeat_days;
	JButton save_button;
	NewTaskFrame parent;
	
	String preset_name = "";
	String preset_days = "";
	
	public NewTaskPanel(NewTaskFrame parent, String task_name, String days) {
		this.setSize(parent.getSize());
		this.setLayout(null);
		this.parent = parent;
		this.preset_name = task_name;
		this.preset_days = days;
		
		setup_ui_components();
	}
	
	private void setup_ui_components() {
		JLabel name_label = new JLabel("todo name");
		name_label.setSize(150,25);
		name_label.setLocation(25,0);
		this.add(name_label);
		
		task_name_field = new JTextField();
		task_name_field.setSize(300, 25);
		task_name_field.setLocation(25,25);
		if (!preset_name.isEmpty()) {
			task_name_field.setText(preset_name);
		}
		this.add(task_name_field);
		
		JLabel days_label = new JLabel("days");
		days_label.setSize(50,25);
		days_label.setLocation(25,50);
		this.add(days_label);
		
		task_repeat_days = new JSpinner();
		task_repeat_days.setSize(125,25);
		task_repeat_days.setLocation(25,75);
		if (!preset_days.isEmpty()) {
			task_repeat_days.setValue(Integer.parseInt(preset_days));
		}
		this.add(task_repeat_days);
		
		save_button = new JButton("Save");
		save_button.setSize(100,25);
		save_button.setLocation(225,75);
		save_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Config.properties.put(""+task_name_field.getText()+"_days", ""+task_repeat_days.getValue());
				if (Config.properties.get(""+task_name_field.getText()+"_last_date") == null) {
					Config.properties.put(""+task_name_field.getText()+"_last_date", LocalDate.now().toString());
				}
				Config.save();
				
				parent.getParent_obj()
					.getParent_obj()
					.dispose();
				
				parent.dispose();
				
				new ConfigFrame(parent
						.getParent_obj()
						.getParent_obj()
						.getParent_obj());
			}
		});
		this.add(save_button);
	}
}