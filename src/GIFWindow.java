import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class GIFWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static JPanel panel;
	private static JButton gifButton;
	private static JButton selectButton;
	private static JLabel status;
	private static JTextField frameInput;
	private static JTextField delayInput;
	private static JTextField compressInput;
	private static JTextField countdownInput;
	private static JTextField fileInput;
	
	public GIFWindow() {
		this.setTitle("GIF Maker");
		this.setSize(400, 300);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new FlowLayout());
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		
		int screenW = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenH = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(screenW / 2 - this.getWidth() / 2, screenH / 2 - this.getHeight() / 2);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		this.add(panel);
		panel.revalidate();
		
		status = new JLabel("Welcome to GIF Maker! Select a region to start.");
		status.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(status);
		status.revalidate();
		
		JPanel framePanel = new JPanel();
		framePanel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(framePanel);
		framePanel.revalidate();
		framePanel.add(new JLabel("Number of Frames:"));
		frameInput = new JTextField("50", 5);
		framePanel.add(frameInput, BorderLayout.SOUTH);
		frameInput.setVisible(true);
		frameInput.revalidate();
		panel.revalidate();
		
		JPanel delayPanel = new JPanel();
		delayPanel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(delayPanel);
		delayPanel.revalidate();
		delayPanel.add(new JLabel("Time Between Frames:"));
		delayInput = new JTextField("100", 5);
		delayPanel.add(delayInput, BorderLayout.SOUTH);
		delayPanel.add(new JLabel("ms"));
		delayInput.setVisible(true);
		delayInput.revalidate();
		panel.revalidate();
		
		JPanel compressPanel = new JPanel();
		compressPanel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(compressPanel);
		compressPanel.revalidate();
		compressPanel.add(new JLabel("Compression Factor:"));
		compressInput = new JTextField("0.5", 5);
		compressPanel.add(compressInput, BorderLayout.SOUTH);
		compressInput.setVisible(true);
		compressInput.revalidate();
		panel.revalidate();
		
		JPanel filePanel = new JPanel();
		filePanel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(filePanel);
		JLabel fileLabel = new JLabel("File Name:");
		fileInput = new JTextField("out", 7);
		JLabel extLabel = new JLabel(".gif");
		filePanel.add(fileLabel);
		filePanel.add(fileInput);
		filePanel.add(extLabel);
		filePanel.revalidate();
		
		JPanel countdownPanel = new JPanel();
		countdownPanel.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(countdownPanel);
		countdownPanel.revalidate();
		countdownPanel.add(new JLabel("Countdown Time:"));
		countdownInput = new JTextField("5", 3);
		countdownPanel.add(countdownInput, BorderLayout.SOUTH);
		countdownPanel.add(new JLabel("s"));
		countdownInput.setVisible(true);
		countdownInput.revalidate();
		panel.revalidate();
		
		selectButton = new JButton("Select Region");
		selectButton.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(selectButton);
		SelectListener sl = new SelectListener();
		selectButton.addActionListener(sl);
		selectButton.setVisible(true);
		selectButton.revalidate();
		
		gifButton = new JButton("Make GIF");
		gifButton.setAlignmentX(CENTER_ALIGNMENT);
		GIFListener gl = new GIFListener();
		gifButton.addActionListener(gl);
		gifButton.setVisible(true);
		gifButton.setEnabled(false);
		panel.add(gifButton);
		gifButton.revalidate();
		
	}
	
	public static void setEnabledSelectButton(boolean set) {
		selectButton.setEnabled(set);
	}
	
	public static void enableGifButton() {
		gifButton.setEnabled(true);
	}
	
	public static void disableGifButton() {
		gifButton.setEnabled(false);
	}
	
	public static boolean checkInputs() {
		Border defaultBorder = new JTextField().getBorder();
		boolean ok = true;
		
		if (!frameInput.getText().matches("^[0-9]+$")) {
			frameInput.setBorder(BorderFactory.createLineBorder(Color.RED));
			ok = false;
		} else frameInput.setBorder(defaultBorder);
		
		if (!delayInput.getText().matches("^[0-9]+$")) {
			delayInput.setBorder(BorderFactory.createLineBorder(Color.RED));
			ok = false;
		} else delayInput.setBorder(defaultBorder);
		
		if (!compressInput.getText().matches("^[0-9,.]+$") || getCompression() > 1) {
			compressInput.setBorder(BorderFactory.createLineBorder(Color.RED));
			ok = false;
		} else compressInput.setBorder(defaultBorder);
		
		if (!countdownInput.getText().matches("^[0-9]+$")) {
			countdownInput.setBorder(BorderFactory.createLineBorder(Color.RED));
			ok = false;
		} else countdownInput.setBorder(defaultBorder);

		return ok;
	}
	
	public static boolean checkSelectionRect() {
		return SelectWindow.selection.width != 0 && SelectWindow.selection.height != 0;
	}
	
	public static String getFileName() {
		return fileInput.getText();
	}
	
	public static int getNumFrames() {
		return Integer.parseInt(frameInput.getText());
	}
	
	public static int getDelay() {
		return Integer.parseInt(delayInput.getText());
	}
	
	public static void setStatus(String str) {
		status.setText(str);
	}
	
	public static double getCompression() {
		return Double.parseDouble(compressInput.getText());
	}
	
	public static int getCountdown() {
		return Integer.parseInt(countdownInput.getText());
	}

}
