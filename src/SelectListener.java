import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		new SelectWindow();
		GIFWindow.setEnabledSelectButton(false);
	}

}
