//Example of custom game object
import java.awt.*;

public class Ship extends GameObject {
    public Ship(Game game) {
        super(game);
    }
    @Override
    public void update(){
        super.update();
        if(game.pressedKeys.contains(37)){
            position.x -= 10;
        }
        if(game.pressedKeys.contains(39)){
            position.x += 10;
        }
        position.x = game.mousePositionListener().x;
        position.y = game.mousePositionListener().y;
    }
    @Override
    public void draw(Graphics g) {
        super.draw(g);

        int x = position.x;
        int y = position.y;

        g.setColor(Color.RED);
        g.drawLine(x-25, y, x+25, y);
        g.drawLine(x-25, y, x, y-70);
        g.drawLine(x+25, y, x, y-70);
    }
}