package code;

import java.io.Serializable;
import java.util.Objects;

public class Cell implements Cloneable, Serializable {
    int x;
    int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
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

    @Override
    public String toString() {
        return this.x + "," + this.y;
    }
    @Override
    public boolean equals(Object o){
        if (getClass() != o.getClass())
            return false;
        Cell c = (Cell) o;
        return c.x == this.x & c.y == this.y;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }
}
