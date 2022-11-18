import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private int x;
    private int y;

    public Hero(int x, int y) {
        this.x=x;
        this.y=y;
    }
    Hero hero = new Hero(10, 10);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public Hero moveUp(){
        return new Hero(hero.getX(),hero.getY()-1);
    }
    public Hero moveDown(){
        return new Hero(hero.getX(),hero.getY()+1);
    }
    public Hero moveRight(){
        return new Hero(hero.getX()+1,hero.getY());
    }
    public Hero moveLeft(){
        return new Hero(hero.getX()-1,hero.getY());
    }
    public void draw(Screen screen){
    }
}
