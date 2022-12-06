package code;

import java.util.Random;
import java.util.stream.IntStream;

public class Grid {

    Cell[][] grid;
    int m;
    int n;
    agent agent;

    boolean timeAction;

    public Grid() {
        Random rand = new Random();
        this.m = rand.nextInt(5, 16);
        this.n = rand.nextInt(5, 16);
        this.grid = new Cell[m][n];
        int occupiedNum = rand.nextInt(2, m * n);
        int shipNum = rand.nextInt(1, occupiedNum);
        int stationNum = occupiedNum - shipNum;

        int[] shipPositions = new int[shipNum];
        int[] stationPositions = new int[stationNum];
        int pos;
        timeAction = false;
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
                this.addCell(new Cell(i, j));
            }
        }
        System.out.println("Size: " + this.grid.length + "*" + this.grid[0].length);

        for (int i : shipPositions) {
            this.addCell(new Ship((int) Math.floor(i / n), i % n));

        }

        for (int i : stationPositions) {
            this.addCell(new Station((int) Math.floor(i / n), i % n));
        }
        do {
            pos = rand.nextInt(0, m * n);
            int curr = pos;
            containsShip = IntStream.of(shipPositions).anyMatch(x -> x == curr);
            containsStation = IntStream.of(stationPositions).anyMatch(x -> x == curr);
        } while (containsShip && containsStation);
        this.agent = new agent((int) Math.floor(pos / n), pos % n);
    }

    public boolean isTimeAction() {
        return timeAction;
    }

    public void setTimeAction(boolean timeAction) {
        this.timeAction = timeAction;
    }

    public void addCell(Cell c) {
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
        // for (code.cell[] row : this.code.grid) {
        // for (code.cell code.cell : row) {
        // s = s + code.cell + "\t";
        // }
        // s = s + "\n";
        // }
        String string = this.m + "," + this.n + ";" + this.agent + ";";
        String stations = "";
        String ships = "";
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (this.grid[i][j] instanceof Station) {
                    if (!stations.equals(""))
                        stations = stations + ",";
                    stations = stations + this.grid[i][j];
                }
                if (this.grid[i][j] instanceof Ship) {
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
        Grid grid = new Grid();
        System.out.println(grid);
    }

}