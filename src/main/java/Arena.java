import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Arena {
    private int width;
    private int height;
    public Arena(int width, int height){
        this.height = height;
        this.width = width;
        hero = new Hero(10, 10);
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
        return (position.getX() >= 0 && position.getX() < width) && (position.getY() >= 0 && position.getY() < height);
    }
    public void draw(TextGraphics screen){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#3366 99"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(screen);
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    private Screen screen;
    TextGraphics graphics = screen.newTextGraphics();
}
