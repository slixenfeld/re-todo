package window.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import config.Config;
import window.frame.MainFrameSingleton;

public class NewTaskPanel extends AbstractPanel {
	private static final long serialVersionUID = 1L;
	
	JTextField task_name_field;
	JSpinner task_repeat_days;
	
	JButton save_button;
	JButton cancel_button;
	JCheckBox repeating_checkbox;
	JLabel repeating_label;
	JLabel category_label;
	JTextField category_field;
	
	boolean repeating = false;
	
	String preset_name = "";
	String preset_days = "";
	
	String category;
	
	public NewTaskPanel(String task_name, String category, String days, boolean repeats) {
		this.preset_name = task_name;
		this.category = category;
		this.preset_days = days;
		this.repeating = repeats;
		
		this.setSize(0x171,0x0A0);
		setup_ui_components();
	}
	
	@Override
	public void setup_ui_components() {
		super.setup_ui_components();
		
		JLabel name_label = new JLabel("todo name");
		name_label.setSize(150,25);
		name_label.setLocation(25,0 +30);
		this.add(name_label);
		
		task_name_field = new JTextField();
		task_name_field.setSize(300, 25);
		task_name_field.setLocation(25,25 +30);
		if (!preset_name.isEmpty()) {
			task_name_field.setText(preset_name);
		}
		this.add(task_name_field);
		
		JLabel days_label = new JLabel("days");
		days_label.setSize(50,25);
		days_label.setLocation(25,50 +30);
		this.add(days_label);
		
		task_repeat_days = new JSpinner();
		task_repeat_days.setSize(125,25);
		task_repeat_days.setLocation(25,75 +30);
		if (!preset_days.isEmpty()) {
			task_repeat_days.setValue(Integer.parseInt(preset_days));
		}
		this.add(task_repeat_days);
			
		repeating_label = new JLabel("repeating");
		repeating_label.setBounds(25,130,120,30);
		add(repeating_label);
		
		repeating_checkbox = new JCheckBox();
		repeating_checkbox.setBounds(100,140,25,16);
		repeating_checkbox.setSelected(repeating);
		repeating_checkbox.addItemListener(new ItemListener() {
			@Override
	        public void itemStateChanged(ItemEvent e) {  
				repeating = e.getStateChange() == 1;
	         }
		});
		add(repeating_checkbox);
	
		category_label = new JLabel("category");
		category_label.setBounds(180, 80, 100,25);
		add(category_label);
		
		category_field = new JTextField(category);
		category_field.setBounds(180,100,100,25);
		add(category_field);
		
		save_button = new JButton("Save");
		save_button.setSize(70,25);
		save_button.setLocation(185,85 +43);
		save_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Config.properties.put(""+task_name_field.getText()+"_days", ""+task_repeat_days.getValue());
				Config.properties.put(""+task_name_field.getText()+"_category", ""+category_field.getText());
				if (Config.properties.get(""+task_name_field.getText()+"_last_date") == null) {
					Config.properties.put(""+task_name_field.getText()+"_last_date", LocalDate.now().toString());
				}
				Config.properties.put(""+task_name_field.getText()+"_repeating", ""+ repeating );
				
				Config.save();
				
				MainFrameSingleton.getInstance().loadPanel(new MainPanel());
			}
		});
		this.add(save_button);
		
		cancel_button = new JButton("cancel");
		cancel_button.setSize(70,25);
		cancel_button.setLocation(275,85 +43);
		cancel_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrameSingleton.getInstance().loadPanel(new ConfigPanel());
			}
		});
		this.add(cancel_button);
	}
}