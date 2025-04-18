import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game implements MouseListener, MouseMotionListener, ActionListener {

    private final int NUM_BALLS = 16;

    private Ball black;
    private Ball white;
    private ArrayList<Ball> balls;
    private ArrayList<pocket> holes;
    private int state;
    private Timer clock;

    private final double friction = 0.98;

    private GameView window;

    public Game() {
        this.holes = new ArrayList<pocket>();
        this.white = new Ball(10,10,5, Color.WHITE);
        this.balls = new ArrayList<Ball>();
        this.state = 0;
        clock = new Timer(20, this);
        clock.start();
        window = new GameView(this);
    }

    // Getters and Setters
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }

    public ArrayList<pocket> getHoles() {
        return holes;
    }

    public void setHoles(ArrayList<pocket> holes) {
        this.holes = holes;
    }

    // Plays the actual game - allows people to hit balls
    public void playGame()
    {

    }

    public boolean checkHitPocket(Ball ball)
    {
        return false;
    }


    // All implemented methods
    @Override
    public void actionPerformed(ActionEvent e) {
        window.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

