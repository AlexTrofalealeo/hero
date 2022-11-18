import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    public Arena(int width, int height){
        this.height = height;
        this.width = width;
        hero = new Hero(10, 10);
        this.walls = createWalls();
        this.coins = createCoins();
    }
    private Position position;

    private Hero hero;
    public Position moveUp(){
        return new Position(hero.getPosition().getX(),hero.getPosition().getY()-1);
    }
    public Position moveDown(){
        return new Position(hero.getPosition().getX(),hero.getPosition().getY()+1);
    }
    public Position moveRight(){
        return new Position(hero.getPosition().getX()+1,hero.getPosition().getY());
    }
    public Position moveLeft(){
        return new Position(hero.getPosition().getX()-1,hero.getPosition().getY());
    }
    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
        retrieveCoins();
    }
    private boolean canHeroMove(Position position) {
        if ((position.getX() >= 0 && position.getX() < width) && (position.getY() >= 0 && position.getY() < height)){
            for (Wall wall : walls){
                return !wall.getPosition().equals(position);
            }
        }
        return false;
    }
    public void draw(TextGraphics screen){
        screen.setBackgroundColor(TextColor.Factory.fromString("#000000"));
        screen.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(screen);
        for (Wall wall : walls)
            wall.draw(screen);
        for(Coin coin : coins)
            coin.draw(screen);
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    private Screen screen;
    //TextGraphics graphics = screen.newTextGraphics();
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
    private List<Coin> coins;
    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Coin coinn = new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            if(!coinn.getPosition().equals(hero.getPosition())){
                boolean yh=false;
                for(Coin coin : coins){
                    if (!coinn.getPosition().equals(coin.getPosition()))
                        yh=true;
                    else yh=false;
                }
                coins.add(coinn);
            }
        }
        return coins;
    }
    private void retrieveCoins(){
        for(Coin coin : coins){
            if(hero.getPosition().equals(coin.getPosition())) {
                coins.remove(coin);
                break;
            }
        }
    }
    private class Hero extends Element {
        private Position position;

        public Hero(int x, int y) {
            super(x, y);
        }

        public void draw(TextGraphics graphics) {
            graphics.setForegroundColor(TextColor.Factory.fromString("#ff0000"));
            graphics.enableModifiers(SGR.BOLD);
            graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "0");
        }
    }

}
