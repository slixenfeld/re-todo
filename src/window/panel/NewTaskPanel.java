package window.panel;

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

	boolean repeating = false;

	String preset_name = "";
	String preset_days = "";
	String category;

	public NewTaskPanel(String task_name, String category, String days, boolean repeats) {
		this.preset_name = task_name;
		this.category = category;
		this.preset_days = days;
		this.repeating = repeats;

		this.setSize(0x171, 0x0A0);
		setup_ui_components();
	}

	@Override
	public void setup_ui_components() {
		super.setup_ui_components();

		JLabel name_label = new JLabel("todo name");
		name_label.setSize(150, 25);
		name_label.setLocation(25, 0 + 0);
		this.add(name_label);

		JTextField task_name_field = new JTextField();
		task_name_field.setSize(300, 25);
		task_name_field.setLocation(25, 25 + 0);
		if (!preset_name.isEmpty()) {
			task_name_field.setText(preset_name);
		}
		this.add(task_name_field);

		JLabel days_label = new JLabel("days");
		days_label.setSize(50, 25);
		days_label.setLocation(25, 50 + 0);
		this.add(days_label);

		JSpinner task_repeat_days = new JSpinner();
		task_repeat_days.setSize(125, 25);
		task_repeat_days.setLocation(25, 75 + 0);
		if (!preset_days.isEmpty()) {
			task_repeat_days.setValue(Integer.parseInt(preset_days));
		}
		this.add(task_repeat_days);

		JLabel repeating_label = new JLabel("repeating");
		repeating_label.setBounds(25, 100, 120, 30);
		add(repeating_label);

		JCheckBox repeating_checkbox = new JCheckBox();
		repeating_checkbox.setBounds(100, 110, 25, 16);
		repeating_checkbox.setSelected(repeating);
		repeating_checkbox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				repeating = e.getStateChange() == 1;
			}
		});
		add(repeating_checkbox);

		JLabel category_label = new JLabel("category");
		category_label.setBounds(180, 50, 100, 25);
		add(category_label);

		JTextField category_field = new JTextField(category);
		category_field.setBounds(180, 70, 100, 25);
		add(category_field);

		JButton save_button = new JButton("Save");
		save_button.setSize(70, 25);
		save_button.setLocation(135, 85 + 23);
		save_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Config.properties.put("" + task_name_field.getText() + "_days",
						"" + task_repeat_days.getValue());
				Config.properties.put("" + task_name_field.getText() + "_category",
						"" + category_field.getText());
				if (Config.properties.get("" + task_name_field.getText() + "_last_date") == null) {
					Config.properties.put("" + task_name_field.getText() + "_last_date",
							LocalDate.now().toString());
				}
				Config.properties.put("" + task_name_field.getText() + "_repeating",
						"" + repeating);

				Config.save();

				MainFrameSingleton.getInstance().loadPanel(new MainPanel());
			}
		});
		this.add(save_button);

		JButton cancel_button = new JButton("cancel");
		cancel_button.setSize(70, 25);
		cancel_button.setLocation(215, 85 + 23);
		cancel_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrameSingleton.getInstance().loadPanel(new MainPanel());
			}
		});
		this.add(cancel_button);

		JButton delete_button = new JButton("delete");
		delete_button.setSize(70, 25);
		delete_button.setLocation(295, 85 + 23);
		delete_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Config.properties.remove(task_name_field.getText() + "_days");
				Config.properties.remove(task_name_field.getText() + "_last_date");
				try {
					Config.properties.remove(task_name_field.getText() + "_repeating");
				} catch (Exception e2) {
				}
				;
				try {
					Config.properties.remove(task_name_field.getText() + "_category");
				} catch (Exception e2) {
				}
				;

				Config.save();
				MainFrameSingleton.getInstance().loadPanel(new MainPanel());
			}
		});
		this.add(delete_button);

	}
}