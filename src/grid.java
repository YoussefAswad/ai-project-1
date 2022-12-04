import java.util.Random;
import java.util.stream.IntStream;

public class grid {

    cell[][] grid;
    int m;
    int n;
    agent agent;

    public grid() {
        Random rand = new Random();
        this.m = rand.nextInt(5, 16);
        this.n = rand.nextInt(5, 16);
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
                containsShip = IntStream.of(shipPositions).anyMatch(x -> x == curr);
            } while (containsShip);
            shipPositions[i] = pos;
        }

        for (int i = 0; i < stationPositions.length; i++) {
            do {
                pos = rand.nextInt(0, m * n);
                int curr = pos;
                containsShip = IntStream.of(shipPositions).anyMatch(x -> x == curr);
                containsStation = IntStream.of(stationPositions).anyMatch(x -> x == curr);
            } while (containsShip && containsStation);
            stationPositions[i] = pos;
        }
        System.out.println("Size: " + m + "*" + n);
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                this.addCell(new cell(i, j));
            }
        }
        System.out.println("Size: " + this.grid.length + "*" + this.grid[0].length);

        for (int i : shipPositions) {
            this.addCell(new ship((int) Math.floor(i / n), i % n));

        }

        for (int i : stationPositions) {
            this.addCell(new station((int) Math.floor(i / n), i % n));
        }
        do {
            pos = rand.nextInt(0, m * n);
            int curr = pos;
            containsShip = IntStream.of(shipPositions).anyMatch(x -> x == curr);
            containsStation = IntStream.of(stationPositions).anyMatch(x -> x == curr);
        } while (containsShip && containsStation);
        this.agent = new agent((int) Math.floor(pos / n), pos % n);
    }

    public void addCell(cell c) {
        this.grid[c.getX()][c.getY()] = c;
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        // String s = "";
        // for (cell[] row : this.grid) {
        // for (cell cell : row) {
        // s = s + cell + "\t";
        // }
        // s = s + "\n";
        // }
        String string = this.m + "," + this.n + ";" + this.agent + ";";
        String stations = "";
        String ships = "";
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (this.grid[i][j] instanceof station) {
                    if (!stations.equals(""))
                        stations = stations + ",";
                    stations = stations + this.grid[i][j];
                }
                if (this.grid[i][j] instanceof ship) {
                    if (!ships.equals(""))
                        ships = ships + ",";
                    ships = ships + this.grid[i][j];
                }
            }

        }
        string = string + stations + ";" + ships;
        return string;
    }

    public static void main(String[] args) {
        grid grid = new grid();
        System.out.println(grid);
    }

}