import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {


    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 800;
    private final int TITLE_BAR_HEIGHT = 23;
    private Game pool;
    private Image table;
    private Cue stick;

    public GameView(Game game, Cue stick) {
        this.pool = game;
        this.stick = stick;


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("The Aquarium");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    // To draw - even animation
    public void paint(Graphics g)
    {

    }

    // Redrawing the balls
    public void myPaint(Graphics g)
    {

    }
}
