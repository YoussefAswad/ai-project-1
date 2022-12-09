package code;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public class Agent implements Cloneable, Serializable {
    int x;
    int y;
    int capacity;
    int pickUps;
    int blackBoxes;

    public Agent(int x, int y) {
        this.x = x;
        this.y = y;
        Random rand = new Random();
        this.capacity = rand.nextInt(30, 101);
        this.pickUps = 0;
        this.blackBoxes = 0;
    }

    public Agent(int x, int y, int capacity) {
        this.x = x;
        this.y = y;
        this.capacity = capacity;
        this.pickUps = 0;
        this.blackBoxes = 0;
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

    @Override
    public Agent clone() throws CloneNotSupportedException
    {
        return (Agent) super.clone();
    }

    @Override
    public String toString() {

        return this.capacity + ";" + this.x + "," + this.y;
    }

    public int getPickUps() {
        return pickUps;
    }

    public void setPickUps(int pickUps) {
        this.pickUps = pickUps;
    }

    public boolean isFull() {
        if (getPickUps() == capacity) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        if (getPickUps() <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void pickup(HashMap<Cell,Integer> ships) {
        Cell c = new Cell(x,y);
            if(ships.get(new Cell(x,y)) <= this.capacity - this.pickUps){
                this.pickUps += ships.get(c);
                ships.replace(c,0);
            }
            else {
                ships.replace(c,ships.get(c)-(this.capacity - this.pickUps));
                this.pickUps = capacity;
            }
    }

    public void drop(HashMap<Cell,Integer> stations) {
        Cell c = new Cell(x,y);
            stations.replace(c,stations.get(c) + this.pickUps);
            setPickUps(0);
    }

    public void retrieve(HashMap<Cell,Integer> wrecks) {
        Cell c = new Cell(x,y);
            wrecks.replace(c,0);
            this.blackBoxes ++;
    }

    public void up() {
            this.x = x - 1;

    }

    public void down() {
            this.x = x + 1;


    }

    public void left() {

            this.y = y - 1;


    }

    public void right() {
            this.y = y + 1;

    }
    @Override
    public boolean equals(Object o){
        if (getClass() != o.getClass())
            return false;
        Agent a = (Agent) o;
        return this.capacity == capacity && this.x == a.x && this.y == a.y && this.pickUps == a.pickUps && a.blackBoxes == this.blackBoxes;
    }

}
