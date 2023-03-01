import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import java.util.*;
//import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.geom.RoundRectangle2D;

public class UI extends JFrame {
    public UI() {
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Create a label
        //JLabel label = new JLabel("Hello, world!");
        //getContentPane().add(label);

        /*
        JButton button = new JButton("Click me");
        button.setUI(new RoundedButtonUI());
        getContentPane().add(button);
        */
        
        for (int i = 0; i < 100; i++) {
            //.setAlignmentX(Component.CENTER_ALIGNMENT);
            JButton button = ButtonFactory.createButton();
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            getContentPane().add(button);
        }
        
        // Set the layout manager to remove gaps between buttons
        setLayout(new FlowLayout(5, 0,0));
        //setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
        setSize(550, 550);
        setVisible(true);
    }
}

class ButtonFactory {
    public boolean flagged = false;
    public boolean isMineCell = false;

    public static JButton createButton() {
        JButton button = new JButton();

        //Set the button size to 50 by 50
        button.setPreferredSize(new Dimension(50, 50));

        button.setBackground(Color.LIGHT_GRAY);
        button.setForeground(Color.WHITE);
        //button.setBorder(new LineBorder(Color.BLACK));

        // Set the button font
        button.setFont(new Font("Arial", Font.PLAIN, 14));

        // Add a margin to the button
        //button.setMargin(new Insets(5, 5, 5, 5));

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
