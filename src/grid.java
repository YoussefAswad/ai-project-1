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
        int[] stationPositions = new int[stationNum];
        int pos;
        boolean containsShip;
        boolean containsStation;
        for (int i = 0; i < shipPositions.length; i++) {
            do {
                pos = rand.nextInt(0, m * n);
                int curr = pos;
                containsShip = IntStream.of(shipNum).anyMatch(x -> x == curr);
            } while (containsShip);
            shipPositions[i] = pos;
        }

        for (int i = 0; i < stationPositions.length; i++) {
            do {
                pos = rand.nextInt(0, m * n);
                int curr = pos;
                containsShip = IntStream.of(shipNum).anyMatch(x -> x == curr);
                containsStation = IntStream.of(shipNum).anyMatch(x -> x == curr);
            } while (containsShip && containsStation);
            stationPositions[i] = pos;
        }

    }

    public void addCell(cell c) {
        grid[c.getX()][c.getY()] = c;
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        grid grid = new grid();

    }

}