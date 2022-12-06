package code;

import java.util.Random;

public class Ship extends Cell {

    int passengerCount;

    public Ship(int x, int y) {
        super(x, y);
        this.passengerCount = (new Random()).nextInt(100) + 1;
    }

    public Ship(int x, int y ,int passengerCount) {
        super(x, y);
        this.passengerCount = passengerCount;
    }
    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public boolean isEmpty(){
        if(this.passengerCount <= 0){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.x + "," + this.y + "," + this.passengerCount;
    }
}
