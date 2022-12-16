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

	boolean nameChangeable;
	boolean repeating = false;

	String presetName = "";
	String presetDays = "";
	String category;

	public NewTaskPanel(boolean nameChangeable, String task_name, String category, String days,
			boolean repeats) {
		this.nameChangeable = nameChangeable;
		this.presetName = task_name;
		this.category = category;
		this.presetDays = days;
		this.repeating = repeats;

		this.setSize(0x171, 0x0A0);
		setupUIComponents();
	}

	@Override
	public void setupUIComponents() {
		super.setupUIComponents();

		JLabel nameLabel = new JLabel((nameChangeable) ? "todo name" : presetName);
		nameLabel.setSize(150, 25);
		nameLabel.setLocation(25, 0 + 0);
		this.add(nameLabel);

		JTextField taskNameField = new JTextField();
		taskNameField.setEnabled(nameChangeable);
		taskNameField.setSize(300, 25);
		taskNameField.setLocation(25, 25 + 0);
		if (!presetName.isEmpty()) {
			taskNameField.setText(presetName);
		}
		this.add(taskNameField);

		JLabel daysLabel = new JLabel("days");
		daysLabel.setSize(50, 25);
		daysLabel.setLocation(25, 50 + 0);
		this.add(daysLabel);

		JSpinner taskRepeatDays = new JSpinner();
		taskRepeatDays.setSize(125, 25);
		taskRepeatDays.setLocation(25, 75 + 0);
		if (!presetDays.isEmpty()) {
			taskRepeatDays.setValue(Integer.parseInt(presetDays));
		}
		this.add(taskRepeatDays);

		JLabel repeatingLabel = new JLabel("repeating");
		repeatingLabel.setBounds(25, 100, 120, 30);
		add(repeatingLabel);

		JCheckBox repeatingCheckbox = new JCheckBox();
		repeatingCheckbox.setBounds(100, 110, 25, 16);
		repeatingCheckbox.setSelected(repeating);
		repeatingCheckbox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				repeating = e.getStateChange() == 1;
			}
		});
		add(repeatingCheckbox);

		JLabel categoryLabel = new JLabel("category");
		categoryLabel.setBounds(180, 50, 100, 25);
		add(categoryLabel);

		JTextField categoryField = new JTextField(category);
		categoryField.setBounds(180, 70, 100, 25);
		add(categoryField);

		JButton saveButton = new JButton("Save");
		saveButton.setSize(70, 25);
		saveButton.setLocation(135, 85 + 23);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Config.properties.put("" + taskNameField.getText() + "_days",
						"" + taskRepeatDays.getValue());
				Config.properties.put("" + taskNameField.getText() + "_category",
						"" + categoryField.getText());
				if (Config.properties.get("" + taskNameField.getText() + "_last_date") == null) {
					Config.properties.put("" + taskNameField.getText() + "_last_date",
							LocalDate.now().toString());
				}
				Config.properties.put("" + taskNameField.getText() + "_repeating",
						"" + repeating);

				Config.save();

				MainFrameSingleton.getInstance().loadPanel(new MainPanel());
			}
		});
		this.add(saveButton);

		JButton cancelButton = new JButton("cancel");
		cancelButton.setSize(70, 25);
		cancelButton.setLocation(215, 85 + 23);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrameSingleton.getInstance().loadPanel(new MainPanel());
			}
		});
		this.add(cancelButton);

		JButton deleteButton = new JButton("delete");
		deleteButton.setSize(70, 25);
		deleteButton.setLocation(295, 85 + 23);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Config.properties.remove(taskNameField.getText() + "_days");
				Config.properties.remove(taskNameField.getText() + "_last_date");
				try {
					Config.properties.remove(taskNameField.getText() + "_repeating");
				} catch (Exception e2) {
				}
				;
				try {
					Config.properties.remove(taskNameField.getText() + "_category");
				} catch (Exception e2) {
				}
				;

				Config.save();
				MainFrameSingleton.getInstance().loadPanel(new MainPanel());
			}
		});
		this.add(deleteButton);

	}
}