package highlow.maingame.sockets;

public class Connect implements Runnable {

	@Override
	public void run() {
			try {
				ClientWindow.main(null);
			} catch (Exception e) {
				e.printStackTrace();
			}

	}

}
