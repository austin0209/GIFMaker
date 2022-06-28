import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class SelectWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public static Rectangle selection;
	
	private JLabel status;

	public SelectWindow() {
		setTitle("Selection");
		int screenW = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenH = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
		setSize(screenW, screenH);
		setLocation(0, 0);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setVisible(true);

		if (this.getGraphicsConfiguration().isTranslucencyCapable()) {
			setOpacity(0.5f);
		}
		
		add(new DrawingPanel(), BorderLayout.CENTER);
	}
	
	private void closeWindow() {
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
	
	private class DrawingPanel extends JPanel implements MouseMotionListener, MouseListener {

		private static final long serialVersionUID = 1L;
		Rectangle rect;
		Point anchor;

		public DrawingPanel() {
			addMouseListener(this);
			addMouseMotionListener(this);
			status = new JLabel("Click and drag selection", SwingConstants.CENTER);
			status.setFont(new Font("Serif", Font.PLAIN, 50));
			add(status);
			status.revalidate();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (rect != null) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(Color.WHITE);
				g2d.fill(rect);
				g2d.setColor(Color.BLACK);
				g2d.draw(rect);
			}
		}

		public void mousePressed(MouseEvent e) {
			anchor = e.getPoint();
			rect = new Rectangle(anchor);
		}

		public void mouseDragged(MouseEvent e) {
			rect.setBounds((int) Math.min(anchor.x, e.getX()), (int) Math.min(anchor.y, e.getY()),
					(int) Math.abs(e.getX() - anchor.x), (int) Math.abs(e.getY() - anchor.y));
			repaint();
		}

		public void mouseReleased(MouseEvent e) {
			selection = rect;
			if (GIFWindow.checkSelectionRect()) {
				GIFWindow.setStatus("Region selected!");
				GIFWindow.enableGifButton();
				GIFWindow.setEnabledSelectButton(true);
				closeWindow();
			} else {
				GIFWindow.setStatus("Invalid region!");
			}
		}

		// unused
		public void mouseMoved(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	}
	
}
