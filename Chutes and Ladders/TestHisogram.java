import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class TestHisogram {
//http://stackoverflow.com/a/12520104/714968
	static String filename;
    public static void main(String[] args) {
		filename = args[0];
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TestHisogram();
            }
        });
    }

    public TestHisogram() {
		BufferedReader reader = null;
		int[] data = new int[131];
		try {
			File file = new File(filename);
			reader = new BufferedReader(new FileReader(file));
			String line     = null;
			String[] tokens = null;
			
			for (int i = 0; (line = reader.readLine()) != null; i++) {
				data[i] = Integer.parseInt(line);
				for (int j = 0; j < data[i]; j++)
					System.out.print((i+8) + ", ");
				System.out.print(", ");
			}
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ERROR: IOException.");
			e.printStackTrace();
		}
		
        JFrame frame = new JFrame("Chutes and Ladders: moves to complete");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(new Graph(data)));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    protected class Graph extends JPanel {

        protected static final int MIN_BAR_WIDTH = 4;
        private int[] data;

        public Graph(int[] data) {
            this.data = data;
            int width = (data.length * MIN_BAR_WIDTH) + 11;
            Dimension minSize = new Dimension(width, 128);
            Dimension prefSize = new Dimension(width, 256);
            setMinimumSize(minSize);
            setPreferredSize(prefSize);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (data != null) {
                int xOffset = 5;
                int yOffset = 5;
                int width = getWidth() - 1 - (xOffset * 2);
                int height = getHeight() - 1 - (yOffset * 2);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(Color.DARK_GRAY);
                g2d.drawRect(xOffset, yOffset, width, height);
                int barWidth = Math.max(MIN_BAR_WIDTH,
                        (int) Math.floor((float) width
                        / (float) data.length));
                System.out.println("width = " + width + "; size = "
                        + data.length + "; barWidth = " + barWidth);
                int maxValue = 0;
                for (int a : data) {
                    maxValue = Math.max(maxValue, a);
                }
                int xPos = xOffset;
                for (int a : data) {
                    int value = a;
                    int barHeight = Math.round(((float) a
                            / (float) maxValue) * height);
                    g2d.setColor(Color.lightGray);
                    int yPos = height + yOffset - barHeight;
                    Rectangle2D bar = new Rectangle2D.Float(
                            xPos, yPos, barWidth, barHeight);
                    g2d.fill(bar);
                    g2d.setColor(Color.darkGray);
                    g2d.draw(bar);
                    xPos += barWidth;
                }
                g2d.dispose();
            }
        }
    }
}