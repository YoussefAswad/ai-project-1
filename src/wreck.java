public class wreck extends cell {

    int blackbox;

    public wreck(int x, int y) {
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