package window.frame;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static Point point = new Point();
	public static MainFrame window;
	private JPanel panel = new JPanel();
	
	public MainFrame() {
		setup_frame();
	}

	public MainFrame(Point location) {
		setup_frame();
		this.setLocation(location);
	}
	
	public void loadPanel(JPanel panel) {
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
		this.setSize(0x171,0x200);
		this.setResizable(false);
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
                MainFrameSingleton.getInstance().setLocation(p.x + e.getX() - point.x, p.y + e.getY() - point.y);
            }
        });
	}
}
