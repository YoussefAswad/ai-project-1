package code;

import java.util.Random;
import java.util.stream.IntStream;

public class Grid {

    Cell[][] grid;
    int m;
    int n;
    Agent agent;

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
        this.agent = new Agent((int) Math.floor(pos / n), pos % n);
    }

    public Grid(String input){
        String[] gridElements = input.split(";");
        String[] gridSize = gridElements[0].split(",");
        this.m = Integer.parseInt(gridSize[0]);
        this.n = Integer.parseInt(gridSize[1]);
        this.grid= new Cell[this.m][this.n];
        for (int i = 0; i < this.grid.length; i++) {
            for (int j = 0; j < this.grid[i].length; j++) {
                this.addCell(new Cell(i, j));
            }
        }
        String[] agentLoc = gridElements[2].split(",");
        this.agent = new Agent(Integer.parseInt(agentLoc[0]), Integer.parseInt(agentLoc[1]),Integer.parseInt(gridElements[1]));
        String[] stations = gridElements[3].split(",");
        for (int i = 0; i < stations.length; i+=2){
            this.addCell(new Station(Integer.parseInt(stations[i]), Integer.parseInt(stations[i+1])));
        }
        String[] ships = gridElements[4].split(",");
        System.out.println(ships.length);
        for (int i = 0; i < ships.length; i+=3){

            this.addCell(new Ship(Integer.parseInt(ships[i]), Integer.parseInt(ships[i+1]),Integer.parseInt(ships[i+2])));
            System.out.println(i);


        }
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
        string = string + stations + ";" + ships +";";
        return string;
    }

    public static void main(String[] args) {
        Grid grid = new Grid();
        String test = "10,6;59;1,7;0,0,2,2,3,0,5,3;1,3,69,3,4,80,4,7,94,4,9,14,5,2,39;";
        Grid grid1 = new Grid(test);
        if (grid1.toString().equals(test)){
            System.out.println(true);
        }
        else {
            System.out.println(false);
        }
        System.out.println(grid1);
    }

}