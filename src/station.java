public class station extends cell {

    public station(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return this.x + "," + this.y;
    }
}
