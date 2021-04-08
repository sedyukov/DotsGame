import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class Game {
    //Game window
    private JFrame window;

    //Canvas
    private GameCanvas gameCanvas;

    //Set of game elements
    public Set<GameObject> gameObjects = new HashSet<>();

    //Set of pressed keys
    public Set<Integer> pressedKeys = new HashSet<>();

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    //This method work once at the beginning
    public void start(){
        createWindow();
        startTimer();
        initKeyListeners();
        createInitialGameObjects();
    }

    //Keys listening by integer code
    private void initKeyListeners(){
        window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                pressedKeys.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                pressedKeys.remove(e.getKeyCode());
            }
        });
    }

    //return Location of cursor
    public Vector2 mousePositionListener(){
        Point location = MouseInfo.getPointerInfo().getLocation();
        Vector2 locVector = new Vector2((int)location.getX(),(int)location.getY());
        return locVector;
    }

    //Window initialization
    public void createWindow(){
        gameCanvas = new GameCanvas(this);

        window = new JFrame("Game");
        window.setSize(500, 500);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setContentPane(gameCanvas);
        window.setVisible(true);
    }

    //Timer is used for game loop
    private void startTimer() {
        Timer timer = new Timer(50, new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                tick();
            }
        });
        timer.start();
    }

    //Runs every frame for all game objects
    private void tick(){
        for(GameObject gameObject: gameObjects){
            gameObject.update();
        }
        gameCanvas.repaint();
    }

    //Initialization of game objects and adding into objects set
    private void createInitialGameObjects() {
        //Creating ship
        Ship ship = new Ship(this);
        //Starting position x,y
        ship.position = new Vector2(40, 40);
        //Adding to game set
        gameObjects.add(ship);
    }
}