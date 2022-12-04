import java.util.Random;

public class agent {
    int x;
    int y;
    int capacity;
    int pickUps;
    

    public agent(int x, int y) {
        this.x = x;
        this.y = y;
        Random rand = new Random();
        this.capacity = rand.nextInt(30, 101);
        this.pickUps = 0;
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

    public int getPickUps() {
        return pickUps;
    }

    public void setPickUps(int pickUps) {
        this.pickUps = pickUps;
    }

    public boolean isFull() {
        if (getPickUps() > capacity) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        if (getPickUps() <= capacity) {
            return true;
        } else {
            return false;
        }
    }


    public void pickUp(ship ship,grid grid) {
        if ((this.x == ship.getX()) && (this.y == ship.getY())) {
            ship.setPassengerCount(ship.getPassengerCount() - 1);
            setPickUps(getPickUps() + 1);
        }
        grid.setTimeAction(true);

    }

    public void dropPassenger(station station,grid grid) {
        if ((this.x == station.getX()) && (this.y == station.getY())) {
            setPickUps(getPickUps() - 1);
        }
        grid.setTimeAction(true);

    }

    public void retrieve(wreck wreck,grid grid) {
        if ((this.x == wreck.getX()) && (this.y == wreck.getY())) {
            setPickUps(getPickUps() - 1);
        }
        grid.setTimeAction(true);

    }

    public void moveUp(grid grid) {
        if (this.x >= 1) {
            this.x = x + 1;
        }
        grid.setTimeAction(true);

    }

    public void moveDown(grid grid) {
        if(this.x <= grid.grid.length -1){
            this.x = x - 1;
        }
        grid.setTimeAction(true);


    }

    public void moveLeft(grid grid) {
        if (this.y >= 1) {
            this.y = y + 1;
        }
        grid.setTimeAction(true);

    }

    public void moveRight(grid grid) {
        if(this.x <= grid.grid[0].length -1){
            this.x = x - 1;
        }
        grid.setTimeAction(true);

    }
}
