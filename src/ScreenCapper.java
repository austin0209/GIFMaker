import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ScreenCapper {

	private Robot robot;
	
	public BufferedImage testImage;

	public ScreenCapper() throws IOException {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		this.testImage = capScreen(new Rectangle(0, 0, 10, 10));
	}
	
	public BufferedImage capScreen(Rectangle region) throws IOException {
		BufferedImage screenFullImage = robot.createScreenCapture(region);
		return screenFullImage;
	}
	
	public static void compressImages(BufferedImage[] images) {
		for (int i = 0; i < images.length; i++) {
			// width and height after compression
			int finalWidth = Math.max((int) (images[i].getWidth() * GIFWindow.getCompression()), 1); 
			int finalHeight = Math.max((int) (images[i].getHeight() * GIFWindow.getCompression()), 1);
			images[i] = resize(images[i], finalWidth, finalHeight);
		}
	}
	
	private static BufferedImage resize(BufferedImage img, int newW, int newH) { 
	    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
}