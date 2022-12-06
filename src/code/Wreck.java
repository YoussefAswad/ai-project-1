package code;

public class Wreck extends Cell {

    int blackbox;

    public Wreck(int x, int y) {
        super(x, y);
        this.blackbox = 20;
    }

    public int getBlackbox() {
        return blackbox;
    }

    public void setBlackbox(int blackbox) {
        this.blackbox = blackbox;
    }

}