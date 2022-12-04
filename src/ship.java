import java.util.Random;

public class ship extends cell {

    int passengerCount;

    public ship(int x, int y) {
        super(x, y);
        this.passengerCount = (new Random()).nextInt(100) + 1;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }
}
