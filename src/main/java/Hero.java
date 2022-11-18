import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private Position position;

    public Hero(int x, int y) {
    }
    Hero hero = new Hero(10, 10);
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
    public void draw(Screen screen){
    }

    public void setPosition(Position position) {
    }
}
