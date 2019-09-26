import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.Timer;

public class GIFListener implements ActionListener {

	private Timer countdownTimer;
	private Timer makeTimer;

	public void actionPerformed(ActionEvent e) {
		if (GIFWindow.checkInputs() && GIFWindow.checkSelectionRect()) {
			GIFWindow.disableGifButton();
			CountdownListener tl = new CountdownListener();
			countdownTimer = new Timer(1000, tl);
			countdownTimer.start();
		} else {
			if (!GIFWindow.checkInputs())
				GIFWindow.setStatus("Invalid inputs! Double check all fields!");
			if (!GIFWindow.checkSelectionRect())
				GIFWindow.setStatus("Invalid selection!");
		}

	}

	private void makeGif() throws IOException, InterruptedException {
		MakeListener ml = new MakeListener();
		makeTimer = new Timer(GIFWindow.getDelay(), ml);
		makeTimer.start();
	}
	
	private void putGifTogether(BufferedImage[] images, GifSequenceWriter gifMaker) throws IOException {
		ScreenCapper.compressImages(images);
		for (int i = 0; i < images.length; i++) {
			gifMaker.writeToSequence(images[i]);
		}
		GIFWindow.setStatus("GIF Created as " + GIFWindow.getFileName() + ".gif!");
		GIFWindow.enableGifButton();
	}

	private class MakeListener implements ActionListener {

		private ScreenCapper cap;
		private int numFrames;
		private int framesMade;
		private ImageOutputStream output;
		private GifSequenceWriter gifMaker;
		private BufferedImage[] images;

		public MakeListener() throws IIOException, IOException {
			cap = new ScreenCapper();
			output = new FileImageOutputStream(new File(GIFWindow.getFileName() + ".gif"));
			numFrames = GIFWindow.getNumFrames() + 1;
			images = new BufferedImage[numFrames];
			gifMaker = new GifSequenceWriter(output, cap.testImage.getType(), GIFWindow.getDelay(), true);
			framesMade = 0;
		}

		public void actionPerformed(ActionEvent e) {
			try {
				images[framesMade] = cap.capScreen(SelectWindow.selection);
				framesMade++;
				GIFWindow.setStatus("Captured frame " + framesMade + "!");
				if (framesMade == numFrames) {
					GIFWindow.setStatus("Processing GIF...");
					makeTimer.stop();
					putGifTogether(images, gifMaker);
					gifMaker.close();
					output.close();
					for(int i = 0; i < images.length; i++) {
						// free memory
						images[i].flush();
						images[i] = null;
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class CountdownListener implements ActionListener {

		private int current;

		public CountdownListener() {
			current = GIFWindow.getCountdown();
		}

		public void actionPerformed(ActionEvent e) {
			GIFWindow.setStatus("Creating gif in " + current + "...");
			if (current-- == 0) {
				countdownTimer.stop();
				try {
					makeGif();
				} catch (IOException | InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}

	}

}
