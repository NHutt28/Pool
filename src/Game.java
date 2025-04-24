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
        this.white = new Ball(250,280,12, window);
        this.balls = new ArrayList<Ball>();

        for (int i = 1; i < 16; i++) {
            balls.add(new Ball(0,0,12, i, window));
        }

        for(int i = 1; i < 6; i++)
        {
            for(int j = 0; j < i; j++)
            {
                balls.get(i*(i-1) / 2 + j).setX(620 + (int) Math.sqrt(3) *(i * 16));
                balls.get(i*(i-1) / 2 + j).setY(280 - (i*10) + (j * 20));
            }

        }

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

    public int getReleaseX() {
        return releaseX;
    }

    public int getReleaseY() {
        return releaseY;
    }


    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
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
        for(Ball b: balls)
        {
            b.move();
        }
        window.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        releaseX = white.getX();
        releaseY = white.getY();
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
        moving = false;

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if(moving)
        {
            releaseY = e.getY();
            releaseX = e.getX();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}

