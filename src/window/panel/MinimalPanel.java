package window.panel;

import java.time.LocalDate;
import java.util.Map.Entry;

import config.Config;
import window.frame.MainFrame;
import window.frame.MainFrameSingleton;
import window.ui.TaskButton;
import window.ui.WindowType;

public class MinimalPanel extends AbstractPanel {

	private static final long serialVersionUID = 1L;

	int row_x = 5;
	int row_y = 0;

	public MinimalPanel this_obj = this;

	public MinimalPanel() {

		MainFrame.currentWindowType = WindowType.MINIMAL;

		Config.load();
		this.setSize(0x171, 0x200);
		setupUIComponents();
	}

	@Override
	public void setupUIComponents() {
		super.setupUIComponents();

		addTaskButtons();
	}

	public void addTaskButtons() {
		for (Entry<Object, Object> entry : Config.properties.entrySet())
			addTaskButton((String) entry.getKey(), (String) entry.getValue(), row_x, 35 + row_y);
	}

	private void addTaskButton(String property, String value, int x, int y) {

		String key = (property.contains("_days")) ? property.substring(0, property.length() - 5)
				: "";

		if (!key.isEmpty()) {

			if (Config.get(key + "_category").equals("")
					|| Config.get("category_show_in_minimal_" + Config.get(key + "_category"))
							.equals("true")) {

				if (getExpiredTime(property, key) <= 0) {

					boolean repeats;

					if (!Config.exists(key + "_repeating"))
						repeats = true;
					else
						repeats = Config.properties.getProperty(key + "_repeating").equals("true");

					TaskButton task_button = new TaskButton(WindowType.MINIMAL, key,
							getExpiredTime(property, key), repeats);
					task_button.setDefaults();
					task_button.setLocation(x, y);
					this.add(task_button);

					row_y += 30;
					MainFrameSingleton.getInstance().setSize(this.getWidth(), 35 + row_y);
				}
			}
		}
	}

	/**
	 * returns the days until the task expires.
	 * 
	 * @param key
	 * @param name
	 * @return days until expiration. (if the TODO is more than 5 years in the
	 *         future returns - 999999999)
	 */
	private int getExpiredTime(String key, String name) {
		String date_string = (String) Config.properties.get(name + "_last_date");
		LocalDate dateAfterDays = LocalDate.parse(date_string)
				.plusDays(Integer.parseInt((String) Config.properties.get(key)));

		if (dateAfterDays.getYear() == LocalDate.now().getYear()) {
			return dateAfterDays.getDayOfYear() - LocalDate.now().getDayOfYear();
		} else if (dateAfterDays.getYear() == LocalDate.now().getYear() + 1) {
			return (dateAfterDays.getDayOfYear() + (365 - LocalDate.now().getDayOfYear()));
		} else if (dateAfterDays.getYear() == LocalDate.now().getYear() + 2) {
			return (365 * 1)
					+ (dateAfterDays.getDayOfYear() + (365 - LocalDate.now().getDayOfYear()));
		} else if (dateAfterDays.getYear() == LocalDate.now().getYear() + 3) {
			return (365 * 2)
					+ (dateAfterDays.getDayOfYear() + (365 - LocalDate.now().getDayOfYear()));
		} else if (dateAfterDays.getYear() == LocalDate.now().getYear() + 4) {
			return (365 * 3)
					+ (dateAfterDays.getDayOfYear() + (365 - LocalDate.now().getDayOfYear()));
		} else if (dateAfterDays.getYear() == LocalDate.now().getYear() + 5) {
			return (365 * 4)
					+ (dateAfterDays.getDayOfYear() + (365 - LocalDate.now().getDayOfYear()));
		}
		return -999999999;
	}

}