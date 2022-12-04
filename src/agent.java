import java.util.Random;

public class agent{
    int x;
    int y;
    int capacity;
     
    public agent(int x, int y){
        this.x = x;
        this.y = y;
        Random rand = new Random();
        this.capacity = rand.nextInt(30, 101);
    }

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
