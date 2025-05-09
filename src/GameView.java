import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameView extends JFrame {


    public final int WINDOW_WIDTH = 896;
    public final int WINDOW_HEIGHT = 560;
    public final int TITLE_BAR_HEIGHT = 25;
    public final int TABLE_LEFT = 60;
    public final int TABLE_RIGHT = 820;
    public final int TABLE_TOP = 90;
    public final int TABLE_BOTTOM = 470;

    private Game pool;
    private Image table;
    private Image stick;

    public GameView(Game game) {
        this.pool = game;

        table = new ImageIcon("Resources/Pool table.png").getImage();
        stick = new ImageIcon("Resources/cuestick.png").getImage();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("The Table");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(1);
    }

    // To draw - even animation
    public void paint(Graphics g)
    {
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;
        Graphics g2 = null;
        try {
            g2 = bf.getDrawGraphics();
            myPaint(g2);
        }
        finally {
            g2.dispose();
        }
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }

    // Redrawing the balls
    public void myPaint(Graphics g)
    {
        if(pool.getState() == 0)
        {
            g.setColor(new Color(100,160,100));
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.setColor(new Color(0,0,0));

            // Draws instructions
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("POOL", 400, 100);
            g.setFont(new Font("Script", Font.BOLD, 16));
            g.drawString("The classic 8 ball game", 350, 150);
            g.drawString("Instructions: Click white ball and drag back to propel ", 20, 200);
            g.drawString("it into other balls. Hit balls in pockets to make them", 460, 200);
            g.drawString("disappear.", 400, 250);

            g.drawString("Click to begin! ", 380, 400);
        }
        else if (pool.getState() == 1)
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.drawImage(table, 0, TITLE_BAR_HEIGHT, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            pool.getWhite().draw(g);
            for (Ball b : pool.getBalls()) {
                b.draw(g);
            }
            if (pool.isMoving()) {
                g.setColor(Color.red);
                g.drawLine(pool.getWhite().getX() + 1, pool.getWhite().getY() + 1, pool.getReleaseX() + 1, pool.getReleaseY() + 1);
                g.drawLine(pool.getWhite().getX(), pool.getWhite().getY(), pool.getReleaseX(), pool.getReleaseY());
                g.setColor(Color.black);
            }
            if(pool.isFoulState())
            {
                g.setFont(new Font("Arial", Font.BOLD, 40));
                g.setColor(new Color(255,0,0));
                g.drawString("Click to place ball", 300, 250);
                g.setFont(new Font("Script", Font.BOLD, 16));
            }
        }
        else if(pool.getState() == 2)
        {
            g.setColor(new Color(100,160,100));
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.setColor(new Color(0,0,0));

            // Draws instructions
            g.setFont(new Font("Arial", Font.BOLD, 40));

            if(pool.getPlayer2Group() == 2)
            {
                g.drawString("SOLIDS WIN!!", 380, 250);
            }
            else
            {
                g.drawString("STRIPES WIN!!", 380, 250);
            }
        }
        else if(pool.getState() == 3)
        {
            g.setColor(new Color(100,160,100));
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.setColor(new Color(0,0,0));

            // Draws instructions
            g.setFont(new Font("Arial", Font.BOLD, 40));

            if(pool.getPlayer1Group() == 1)
            {
                g.drawString("SOLIDS WIN!!", 380, 250);
            }
            else
            {
                g.drawString("STRIPES WIN!!", 380, 250);
            }

        }
    }
}
