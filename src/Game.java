import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game implements MouseListener, MouseMotionListener, ActionListener {

    public static ArrayList<Ball> turnPocketedBalls;
    private final int NUM_BALLS = 16;
    private final int Radius = 12;

    private Ball white;
    private ArrayList<Ball> balls;
    private int state;
    private Timer clock;
    private GameView window;
    private int startX, startY, releaseX, releaseY;
    private boolean moving;
    private int turnCount;
    private int player1Group;
    private int player2Group;
    private int player1Pocket;
    private int player2Pocket;
    private boolean groupsAssigned;
    private boolean gameOver;
    private boolean turnInProgress;
    private final int STRIPES = 2;
    private final int SOLIDS = 1;
    public static boolean foulState;


    public Game() {
        window = new GameView(this);
        this.white = new Ball(250,280,Radius, window);
        this.balls = new ArrayList<Ball>();
        player1Group = 0;
        player2Group = 0;
        groupsAssigned = false;
        gameOver = false;
        foulState = false;
        turnInProgress = false;
        turnCount = 0;
        turnPocketedBalls = new ArrayList<Ball>();
        for (int i = 1; i < NUM_BALLS; i++) {
            balls.add(new Ball(0,0,Radius, i, window));
        }

        int count = 0;
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j <= i; j++)
            {
                if(count >= balls.size())
                {
                    break;
                }
                balls.get(count).setX(620 + i * 21);
                balls.get(count).setY(280 - (i*Radius) + (j * 2 * Radius));
                count++;
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

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public int getReleaseX() {
        return releaseX;
    }

    public int getReleaseY() {
        return releaseY;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public boolean isMoving() {
        return moving;
    }

    public boolean isFoulState() {
        return foulState;
    }

    public Ball getWhite() {
        return white;
    }

    // Plays the actual game - allows people to hit balls
    public void playGame()
    {
        if(gameOver)
        {
            gameEnd();
        }
        if (!groupsAssigned)
        {
            assignGroups();
        }
        checkWin();
        if(allBallsStopped() && turnInProgress)
        {
            evaluateTurn();
            turnInProgress = false;
        }


    }
    public void evaluateTurn()
    {
        for(Ball b: turnPocketedBalls) {
            if (b.getNumber()== 8)
            {
                gameOver = true;
            }
        }
        if (foulState || turnPocketedBalls.isEmpty())
        {
            turnCount++;
        }
        turnPocketedBalls.clear();
    }
    public boolean allBallsStopped()
    {
        for (Ball b : balls) {
            if (b.getDx() != 0 || b.getDy() != 0)
            {
                return false;
            }
        }
        return white.getDx() == 0 && white.getDy() == 0;
    }
    public void checkWin()
    {
        player1Pocket = 0;
        player2Pocket = 0;
        for (Ball b : Ball.pocketedBalls) {
            int num = b.getNumber();
            if (player1Group == SOLIDS && num >= 1 && num <= 7)
            {
                player1Pocket++;
            }
            else if (player1Group == STRIPES && num >= 9 && num <= 15)
            {
                player1Pocket++;
            }
            if (player2Group == SOLIDS && num >= 1 && num <= 7)
            {
                player2Pocket++;
            }
            else if (player2Group == STRIPES && num >= 9 && num <= 15)
            {
                player2Pocket++;
            }
        }
        if (player1Pocket == 7 && balls.get(7).isPocketed() && turnCount % 2 == 0)
        {
            gameOver = true;
            state = 3;
        }
        else if (player2Pocket == 7 && balls.get(7).isPocketed() && turnCount % 2 == 1)
        {
            gameOver = true;
            state = 2;
        }
    }
    public void gameEnd()
    {
        if(turnCount % 2 == 1)
        {
            state = 3;
        }
        else
        {
            state = 2;
        }
    }

    public void assignGroups()
    {
            if(!Ball.pocketedBalls.isEmpty())
            {
                int num = Ball.pocketedBalls.get(0).getNumber();
                if (num == 8)
                {
                    gameOver = true;
                }
                else if (num >= 1 && num <= 7)
                {
                    player1Group = SOLIDS;
                    player2Group = STRIPES;
                }
                else if (num >= 9 && num <= 15) {
                    player1Group = STRIPES;
                    player2Group = SOLIDS;
                }
            }
    }


    // All implemented methods
    @Override
    public void actionPerformed(ActionEvent e) {

        for(Ball b: balls)
        {
            white.bounceOffBall(b);
            for(int i = 0; i < balls.size(); i++ )
            {
                b.bounceOffBall(balls.get(i));
            }
        }

        for(Ball b: balls)
        {
            b.move();
        }
        white.move();
        window.repaint();
        playGame();
        window.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {


        if(this.state == 0)
        {
            state++;
            window.repaint();
        }
        else
        {
            if(foulState)
            {
                window.repaint();
                white.setX(e.getX());
                white.setY(e.getY());
                foulState = false;
            }
            else {
                releaseX = white.getX();
                releaseY = white.getY();
                if (Math.sqrt((e.getX() - white.getX()) * (e.getX() - white.getX()) + ((e.getY() - white.getY()) * (e.getY() - white.getY()))) < white.getSize()) {
                    moving = true;
                    startX = e.getX();
                    startY = e.getY();
                } else {
                    moving = false;
                }
            }
            window.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        if(moving) {
            releaseY = e.getY();
            releaseX = e.getX();
            white.setDy((startY - releaseY) / 2);
            white.setDx((startX - releaseX) / 2);
            turnInProgress = true;
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

