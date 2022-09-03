package window.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
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
		task_name_field = new JTextField();
		task_name_field.setSize(200, 25);
		task_name_field.setLocation(25,25);
		if (!preset_name.isEmpty()) {
			task_name_field.setText(preset_name);
		}
		this.add(task_name_field);
		
		task_repeat_days = new JSpinner();
		task_repeat_days.setSize(125,25);
		task_repeat_days.setLocation(25,70);
		if (!preset_days.isEmpty()) {
			task_repeat_days.setValue(Integer.parseInt(preset_days));
		}
		this.add(task_repeat_days);
		
		save_button = new JButton("Save");
		save_button.setSize(100,25);
		save_button.setLocation(25,120);
		save_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Config.properties.put(""+task_name_field.getText()+"_days", ""+task_repeat_days.getValue());
				Config.properties.put(""+task_name_field.getText()+"_last_date", LocalDate.now().toString());
				Config.save();
				parent.parent.parent.dispose();
				parent.dispose();
				new ConfigFrame();
			}
		});
		this.add(save_button);
	}
}