package window.frame;

public class MainFrameSingleton {
	
	private static MainFrame main;
	
	private static void init() {
		main = new MainFrame();
	}
	
	public static MainFrame getInstance() {
		if (main==null) {
			init();
		}
		return main;
	}
}
