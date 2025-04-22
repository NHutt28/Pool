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
    private Cue stick;

    public GameView(Game game) {
        this.pool = game;

        table = new ImageIcon("Resources/Pool table.png").getImage();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("The Table");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);
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
        g.drawImage(table, 0,TITLE_BAR_HEIGHT, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        pool.getWhite().draw(g);
        for (Ball b: pool.getBalls())
        {
            b.draw(g);
        }
    }
}
