import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;
    public Arena(int width, int height){
        this.height = height;
        this.width = width;
        hero = new Hero(10, 10);
        this.walls = createWalls();
    }
    private Position position;

    private Hero hero;
    public Position moveUp(){
        return new Position(position.getX(),position.getY()-1);
    }
    public Position moveDown(){
        return new Position(position.getX(),position.getY()+1);
    }
    public Position moveRight(){
        return new Position(position.getX()+1,position.getY());
    }
    public Position moveLeft(){
        return new Position(position.getX()-1,position.getY());
    }
    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }
    private boolean canHeroMove(Position position) {
        if ((position.getX() >= 0 && position.getX() < width) && (position.getY() >= 0 && position.getY() < height)){
            for (Wall wall : walls){
                if (wall.getPosition().equals(position)){return false;}
                else {return true;}
            }
        }
        return false;
    }
    public void draw(TextGraphics screen){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#3366 99"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(screen);
        for (Wall wall : walls)
            wall.draw(graphics);
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    private Screen screen;
    TextGraphics graphics = screen.newTextGraphics();
    private List<Wall> walls;
    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }
}
