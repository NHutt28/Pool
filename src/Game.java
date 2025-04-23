import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game implements MouseListener, MouseMotionListener, ActionListener {

    private final int NUM_BALLS = 16;

    private Ball black;
    private Ball white;
    private ArrayList<Ball> balls;
    private int state;
    private Timer clock;
    private GameView window;
    private int startX, startY, releaseX, releaseY;
    private boolean moving;

    public Game() {

        window = new GameView(this);
        this.white = new Ball(300,200,10, window);
        this.balls = new ArrayList<Ball>();
        // for (int i = 1; i < 16; i++) {
        //   balls.add(new Ball(10,10,5, i, window));
        // }
        this.window.addMouseListener(this);
        this.window.addMouseMotionListener(this);
        this.state = 0;
        clock = new Timer(40, this);
        clock.start();
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

    public Ball getWhite() {
        return white;
    }

    public void setWhite(Ball white) {
        this.white = white;
    }

    // Plays the actual game - allows people to hit balls
    public void playGame()
    {

    }


    // All implemented methods
    @Override
    public void actionPerformed(ActionEvent e) {
        white.move();
        window.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        if(Math.sqrt((e.getX()-white.getX())*(e.getX()-white.getX())+((e.getY()-white.getY())*(e.getY()-white.getY()))) < white.getSize())
        {
            moving = true;
            startX = e.getX();
            startY = e.getY();
        }
        else
        {
            moving = false;
        }
        window.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(moving) {
            releaseY = e.getY();
            releaseX = e.getX();
            white.setDy(startY - releaseY);
            white.setDx(startX - releaseX);
        }


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

