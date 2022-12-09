package code;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

public class Agent implements Cloneable, Serializable {
    int x;
    int y;
    int capacity;
    int onBoard;
    int retrieved;

    public Agent(int x, int y) {
        this.x = x;
        this.y = y;
        Random rand = new Random();
        this.capacity = rand.nextInt(30, 101);
        this.onBoard = 0;
        this.retrieved = 0;
    }

    public Agent(int x, int y, int capacity) {
        this.x = x;
        this.y = y;
        this.capacity = capacity;
        this.onBoard = 0;
        this.retrieved = 0;
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

    public int getOnBoard() {
        return onBoard;
    }

    public void setOnBoard(int onBoard) {
        this.onBoard = onBoard;
    }

    public boolean isFull() {
        if (getOnBoard() == capacity) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        if (this.onBoard <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void pickup(HashMap<Cell,Integer> ships) {
        Cell c = new Cell(x,y);
            if(ships.get(new Cell(x,y)) <= this.capacity - this.onBoard){
                this.onBoard += ships.get(c);
                ships.replace(c,0);
            }
            else {
                ships.replace(c,ships.get(c)-(this.capacity - this.onBoard));
                this.onBoard = capacity;
            }
    }

    public void drop(HashMap<Cell,Integer> stations) {
        //Cell c = new Cell(x,y);
            //stations.replace(c,stations.get(c) + this.onBoard);
            setOnBoard(0);
    }

    public void retrieve(HashMap<Cell,Integer> wrecks) {
        Cell c = new Cell(x,y);
            wrecks.replace(c,0);
            this.retrieved++;
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
        return this.capacity == capacity && this.x == a.x && this.y == a.y && this.onBoard == a.onBoard && a.retrieved == this.retrieved;
    }

}
