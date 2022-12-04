import java.util.Random;

public class grid {

    cell[][] grid;

    public grid() {
        Random rand = new Random();
        int m = rand.nextInt(5, 16);
        int n = rand.nextInt(5, 16);
        this.grid = new cell[m][n];
        int occupiedNum = rand.nextInt(2, m * n);
        int shipNum = rand.nextInt(1, occupiedNum);
        int stationNum = occupiedNum - shipNum;
    }

    public void addCell(cell c) {
        grid[c.getX()][c.getY()] = c;
    }
}
