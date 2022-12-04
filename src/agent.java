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

}
