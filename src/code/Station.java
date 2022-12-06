package code;

public class Station extends Cell {

    public Station(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return this.x + "," + this.y;
    }
}
