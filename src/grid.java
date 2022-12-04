import java.util.Random;
import java.util.stream.IntStream;

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

        int[] shipPositions = new int[shipNum];
        for (int i = 0; i < shipPositions.length; i++) {
            int pos = rand.nextInt(0, m * n);
            boolean contains = IntStream.of(shipNum).anyMatch(x -> x == pos);
        }
    }

    public void addCell(cell c) {
        grid[c.getX()][c.getY()] = c;
    }
}
