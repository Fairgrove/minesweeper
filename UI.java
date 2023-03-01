import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import java.util.*;
//import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.geom.RoundRectangle2D;

public class UI extends JFrame {
    public int numberOfCells = 200;


    public UI() {
        JFrame frame = new JFrame();
        JFrame frame2 = new JFrame();
        // Customising the main frame
        frame.setTitle("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        //frame.setLayout(new FlowLayout());
        //frame.setLocationRelativeTo(null);
        //frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
        
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout());
        scorePanel.setSize(500, 100);
        frame2.add(scorePanel);
        
        JLabel elapsedTimeLabel = new JLabel("Hello, world!");
        scorePanel.add(elapsedTimeLabel);

        JLabel remainingMines = new JLabel("Hello, world!");
        scorePanel.add(remainingMines);

        //create panel to hold cell buttons
        JPanel buttonFrame = new JPanel();
        buttonFrame.setLayout(new GridLayout(0, numberOfCells/10));
        buttonFrame.setSize(500,500);
        frame.add(buttonFrame);
        
        // creating cell buttons
        for (int i = 0; i < numberOfCells; i++) {
            JButton button = ButtonFactory.createButton(Integer.toString(i+1));
            buttonFrame.add(button);
        }
        frame.setSize(500,600);
    }
}

class ButtonFactory {
    public boolean flagged = false;
    public boolean isMineCell = false;
    public boolean revealed = false;

    public static JButton createButton(String text) {
        JButton button = new JButton(text);

        //Set the button size to 50 by 50
        button.setPreferredSize(new Dimension(50, 50));
        //button.setSize(50, 50);

        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.WHITE);

        button.setMargin(new Insets(0, 0, 0, 0));
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Add an action listener to the button
        button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                
                // Check for left click
                if (SwingUtilities.isLeftMouseButton(e)) {
                    System.out.println("Left-click detected!");
                }

                // Check for right clicks
                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Right-click detected!");
                }
            }
        });

        return button;
    }
}


// smile 
class RoundedButtonUI extends BasicButtonUI {
    private static final int ARC_WIDTH = 15;
    private static final int ARC_HEIGHT = 15;

    @Override
    protected void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setOpaque(false);
        b.setBackground(new Color(220, 220, 220));
        b.setForeground(Color.BLACK);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g;
        AbstractButton b = (AbstractButton) c;
        ButtonModel model = b.getModel();

        // Paint the button background
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (model.isArmed()) {
            g2.setColor(new Color(200, 200, 200));
        } else {
            g2.setColor(b.getBackground());
        }
        g2.fill(new RoundRectangle2D.Double(0, 0, c.getWidth(), c.getHeight(), ARC_WIDTH, ARC_HEIGHT));

        // Paint the button text
        g2.setColor(b.getForeground());
        FontMetrics fm = g2.getFontMetrics();
        String text = b.getText();
        int x = (c.getWidth() - fm.stringWidth(text)) / 2;
        int y = (c.getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(text, x, y);
    }
}
