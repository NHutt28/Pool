import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game implements MouseListener, MouseMotionListener, ActionListener {

    // Static and constants
    public static ArrayList<Ball> turnPocketedBalls;
    private final int NUM_BALLS = 16;
    private final int Radius = 12;
    public static boolean foulState;

    // Instance variables
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



    // Constructor
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

        // Puts ball in triangle
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
        // Starts animation
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
        // if 8ball gets pocketed basically
        if(gameOver)
        {
            gameEnd();
        }
        // assigns solids and stripes
        if (!groupsAssigned)
        {
            assignGroups();
        }
        checkWin();

        // Make sure each player turn count
        if(allBallsStopped() && turnInProgress)
        {
            evaluateTurn();
            turnInProgress = false;
        }


    }

    // Checks when player ends turn
    public void evaluateTurn()
    {
        for(Ball b: turnPocketedBalls) {
            // Makes sure they didn't pocket the 8 ball
            if (b.getNumber()== 8)
            {
                gameOver = true;
            }
        }
        if (foulState || turnPocketedBalls.isEmpty())
        {
            // Moves to next players turn
            turnCount++;
        }
        // clears all balls
        turnPocketedBalls.clear();
    }

    // Checks if any ball is moving
    public boolean allBallsStopped()
    {
        for (Ball b : balls) {
            if (b.getDx() != 0 || b.getDy() != 0)
            {
                return false;
            }
        }
        // If something is moving it will return false
        return white.getDx() == 0 && white.getDy() == 0;
    }

    // Checks if someone won
    public void checkWin()
    {
        player1Pocket = 0;
        player2Pocket = 0;
        for (Ball b : Ball.pocketedBalls) {
            int num = b.getNumber();
            // Adds amount of pocketed balls to each side to check
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
        // Checks if any player won
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
        // Checks who pocketed early, and therefore lost
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

        // assigns solids vs stripes based on what ball was pocketed first
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

        // Draws every ball
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
        // repaints after every ball moves
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


        // Makes sure can't move balls instruction screen
        if(this.state == 0)
        {
            state++;
            window.repaint();
        }
        else
        {
            // Click to place ball when in foul
            if(foulState)
            {
                window.repaint();
                white.setX(e.getX());
                white.setY(e.getY());
                foulState = false;
            }
            // Starts the ball movement mechanic
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

        // when mouse released causes ball to move based on distance of mouse vs ball
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

        // Continuously updates where mouse is after clicked
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

