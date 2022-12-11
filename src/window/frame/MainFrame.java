package window.frame;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

import config.Config;
import window.panel.AbstractPanel;
import window.panel.NewTaskPanel;
import window.ui.WindowType;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Point point = new Point();
	public static MainFrame window;
	private AbstractPanel panel = new AbstractPanel();

	public static String currentTab = "";
	public static WindowType currentWindowType = WindowType.MAIN;

	public MainFrame() {
		setup_frame();
	}

	public MainFrame(Point location) {
		setup_frame();
		this.setLocation(location);
	}

	public void loadPanel(AbstractPanel panel) {
		remove(this.panel);
		this.panel = panel;
		add(this.panel);
	}

	private void setup_frame() {
		setup_frame_settings();
		add_listeners();
		this.setVisible(true);
	}

	private void setup_frame_settings() {
		this.setTitle("re-todo");
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setType(Type.UTILITY);
		this.setUndecorated(true);
		this.setSize(0x171, 0x200);
		this.setResizable(true);
	}

	private void add_listeners() {
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				point.x = e.getX();
				point.y = e.getY();
			}
		});

		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Point p = MainFrameSingleton.getInstance().getLocation();
				MainFrameSingleton.getInstance().setLocation(p.x + e.getX() - point.x,
						p.y + e.getY() - point.y);
			}
		});

		this.addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				panel.resize();
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

		});
	}

	public static void editTask(String name) {

		boolean repeats;
		String category = "";

		if (Config.properties.getProperty(name + "_category") != null)
			category = Config.properties.getProperty(name + "_category");

		if (Config.properties.getProperty(name + "_repeating") == null)
			repeats = true;
		else
			repeats = Config.properties.getProperty(name + "_repeating").equals("true");

		MainFrameSingleton.getInstance().loadPanel(new NewTaskPanel(name, category,
				Config.properties.getProperty(name + "_days"), repeats));
	}
}
