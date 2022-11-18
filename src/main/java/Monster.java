import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element {
    public Monster(int x, int y) {
        super(x,y);
    }
    @Override
    public void draw(TextGraphics screen) {
        screen.setForegroundColor(TextColor.Factory.fromString("#993399"));
        screen.enableModifiers(SGR.BOLD);
        screen.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "§");
    }
    public Position move(Arena arena){
        Random random = new Random();
        while(true) {
            Position mov = new Position(this.getPosition().getX() + random.nextInt(3) - 1, this.getPosition().getY() + random.nextInt(3) - 1);
            if(mov.getX() > 0 && mov.getX() < arena.getWidth()-1 && mov.getY() > 0 && mov.getY() < arena.getHeight()-1)
                return mov;
        }
    }

}
