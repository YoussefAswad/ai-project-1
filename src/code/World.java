package code;

import java.io.Serializable;
import java.io.SyncFailedException;
import java.util.*;
import java.util.stream.IntStream;

public class World implements Comparable, Cloneable, Serializable {

    HashMap<Cell, Integer> ships;
    HashMap<Cell, Integer> wrecks;
    HashMap<Cell, Integer> stations;

    int m;
    int n;
    int shipNum;
    int wreckNum;
    int stationNum;
    int deaths;
    Agent agent;

    public World() {
        this.shipNum = 0;
        this.deaths = 0;
        this.wreckNum = 0;
        this.stationNum = 0;
        Random rand = new Random();
        this.m = rand.nextInt(5, 16);
        this.n = rand.nextInt(5, 16);
        // this.grid = new Cell[n][m];
        this.ships = new HashMap<>();
        this.wrecks = new HashMap<>();
        this.stations = new HashMap<>();

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
        // for (int i = 0; i < n; i++) {
        // for (int j = 0; j < m; j++) {
        // this.addCell(new Cell(i, j));
        // }
        // }

        for (int i : shipPositions) {
            this.addCell(new Cell((int) Math.floor(i / m), i % m),(new Random()).nextInt(100) + 1,"ship");

        }

        for (int i : stationPositions) {
            this.addCell(new Cell((int) Math.floor(i / m), i % m),0,"station");
        }
        do {
            pos = rand.nextInt(0, m * n);
            int curr = pos;
            containsShip = IntStream.of(shipPositions).anyMatch(x -> x == curr);
            containsStation = IntStream.of(stationPositions).anyMatch(x -> x == curr);
        } while (containsShip && containsStation);
        this.agent = new Agent((int) Math.floor(pos / n), pos % n);
    }

    public World(String input) {
        this.shipNum = 0;
        this.wreckNum = 0;
        this.deaths = 0;
        this.stationNum = 0;
        this.ships = new HashMap<>();
        this.wrecks = new HashMap<>();
        this.stations = new HashMap<>();
        String[] gridElements = input.split(";");
        String[] gridSize = gridElements[0].split(",");
        this.m = Integer.parseInt(gridSize[0]);
        this.n = Integer.parseInt(gridSize[1]);
//        for (int i = 0; i < this.grid.length; i++) {
//            for (int j = 0; j < this.grid[i].length; j++) {
//                this.addCell(new Cell(i, j));
//            }
//        }
        String[] agentLoc = gridElements[2].split(",");
        this.agent = new Agent(Integer.parseInt(agentLoc[0]), Integer.parseInt(agentLoc[1]),
                Integer.parseInt(gridElements[1]));
        String[] stations = gridElements[3].split(",");
        for (int i = 0; i < stations.length; i += 2) {
            this.addCell(new Cell(Integer.parseInt(stations[i]), Integer.parseInt(stations[i + 1])),0,"station");
        }
        String[] ships = gridElements[4].split(",");
        for (int i = 0; i < ships.length; i += 3) {

            this.addCell(new Cell(Integer.parseInt(ships[i]), Integer.parseInt(ships[i + 1])),
                    (Integer.parseInt(ships[i + 2])),"ship");

        }
    }

    public void addCell(Cell c, int o,String type) {
        if (type.equals("wreck")) {
            this.wreckNum++;
            this.wrecks.put(c,  o);
        } else if (type.equals("ship")) {
            this.shipNum++;
            this.ships.put(c, o);

        } else if (type.equals("station")) {
            this.stationNum++;
            this.stations.put(c, o);
        }
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }

    public boolean worldEnd() {
        if (wreckNum == 0 && shipNum == 0)
            return true;
        return false;
    }

    public boolean validateOperator(String operator) {
        switch (operator) {
            case "up":
                if (agent.getX() >= 1)
                    return true;
                else
                    return false;
            case "down":
                if (agent.getX() <= n - 2)
                    return true;
                else
                    return false;
            case "right":
                if (agent.getY() <= m - 2)
                    return true;
                else
                    return false;
            case "left":
                if (agent.getY() >= 1)
                    return true;
                else
                    return false;
            case "pickup":
                if (this.ships.containsKey(new Cell(agent.x, agent.y)))
                    return true;
                else
                    return false;
            case "drop":
                if (this.stations.containsKey(new Cell(agent.x, agent.y)))
                    return true;
                else
                    return false;
            case "retrieve":
                if (this.wrecks.containsKey(new Cell(agent.x, agent.y)))
                    return true;
                else
                    return false;
            default:
                return false;
        }
    }

    public void execOperator(String operator) {
        switch (operator) {
            case "up":
                agent.up();
                this.update();
                return;
            case "down":
                agent.down();
                this.update();
                return;
            case "right":
                agent.right();
                this.update();
                return;
            case "left":
                agent.left();
                this.update();
                return;
            case "pickup":
                agent.pickup(ships);
                this.update();
                return;
            case "drop":
                agent.drop(stations);
                this.update();
                return;
            case "retrieve":
                agent.retrieve(wrecks);
                this.update();
        }
    }

    public void update() {
//        for (Cell[] col : this.grid) {
//            for (Cell cell : col) {
//                if (cell instanceof Ship) {
//                    ((Ship) cell).setPassengerCount(((Ship) cell).getPassengerCount() - 1);
//                    this.deaths = this.deaths + 1;
//                    if (((Ship) cell).isEmpty()) {
//                        this.addCell(new Wreck(cell.getX(), cell.y));
//                        this.shipNum--;
//                    }
//                } else if (cell instanceof Wreck) {
//                    ((Wreck) cell).setBlackbox(((Wreck) cell).getBlackbox() - 1);
//                    if (((Wreck) cell).getBlackbox() <= 0)
//                        this.addCell(new Cell(cell.getX(), cell.getY()));
//                    this.wreckNum--;
//                }
//            }
//        }
        Iterator<Map.Entry<Cell,Integer>> iter = ships.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Cell,Integer> entry = iter.next();
            ships.replace(entry.getKey(),entry.getValue()-1);
            deaths++;
            if (entry.getValue() <= 0){
                this.addCell(entry.getKey(),20,"wreck");
                this.ships.remove(entry.getKey());
                shipNum--;
            }
        }
        Iterator<Map.Entry<Cell,Integer>> iter2 = wrecks.entrySet().iterator();
        while (iter2.hasNext()) {
            Map.Entry<Cell,Integer> entry = iter2.next();

            wrecks.replace(entry.getKey(),entry.getValue()-1);
            deaths++;
            if (entry.getValue() <= 0){
                this.wrecks.remove(entry.getKey());
                wreckNum--;
            }
        }
    }

    @Override
    public World clone() throws CloneNotSupportedException {
        World newWorld = (World) super.clone();
        newWorld.agent = this.agent.clone();
        newWorld.ships = (HashMap<Cell, Integer>) this.ships.clone();
        newWorld.stations = (HashMap<Cell, Integer>) newWorld.stations.clone();
        newWorld.wrecks = (HashMap<Cell, Integer>) newWorld.wrecks.clone();
//        newWorld.ships = new HashMap<Cell, Ship>(this.ships);;
//        newWorld.wrecks = new HashMap<Cell, Ship>(this.wrecks);;
//        newWorld.stations = new HashMap<Cell, Station>(this.stations);
//        newWorld.ships = (HashMap<Cell,Ship>)newWorld.ships.clone();
//        newWorld.wrecks = (HashMap<Cell,Wreck>)newWorld.wrecks.clone();
//        newWorld.stations = (HashMap<Cell, Station>)newWorld.stations.clone();
//        HashMap<Cell,Integer> newShips = new HashMap<>();
//        HashMap<Cell,Integer> newStations = new HashMap<>();
//        HashMap<Cell,Integer> newWrecks = new HashMap<>();
//        for(Map.Entry<Cell, Integer> e : this.ships.entrySet()){
//            newShips.put(e.getKey(), (Ship) e.getValue().clone());
//        }
//
//        for(Map.Entry<Cell, Wreck> e : this.wrecks.entrySet()){
//            newWrecks.put(e.getKey(), (Wreck) e.getValue().clone());
//        }
//        for(Map.Entry<Cell, Station> e : this.stations.entrySet()){
//            newStations.put(e.getKey(), (Station) e.getValue().clone());
//        }

////        for (int i = 0; i < n; i++) {
////            for (int j = 0; j < m; j++) {
////                newGrid[i][j] = this.grid[i][j].clone();
////            }
////        }\
//
//        Iterator<Map.Entry<Cell,Station>> iter1 = stations.entrySet().iterator();
//        while (iter1.hasNext()) {
//            Map.Entry<Cell,Station> entry = iter1.next();
//            newStations.put(entry.getKey(),entry.getValue().clone());
//        }
//        Iterator<Map.Entry<Cell,Wreck>> iter2 = wrecks.entrySet().iterator();
//        while (iter2.hasNext()) {
//            Map.Entry<Cell,Wreck> entry = iter2.next();
//            newWrecks.put(entry.getKey(),entry.getValue().clone());
//        }
//        Iterator<Map.Entry<Cell,Ship>> iter3 = ships.entrySet().iterator();
//        while (iter3.hasNext()) {
//            Map.Entry<Cell,Ship> entry = iter3.next();
//            newShips.put(entry.getKey(),entry.getValue().clone());
//        }
//        newWorld.wrecks = newWrecks;
//        newWorld.ships = newShips;
//        newWorld.stations = newStations;

        return newWorld;
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
//        String string = this.m + "," + this.n + ";" + this.agent + ";";
//        String stations = "";
//        String ships = "";
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if (this.grid[i][j] instanceof Station) {
//                    if (!stations.equals(""))
//                        stations = stations + ",";
//                    stations = stations + this.grid[i][j];
//                }
//                if (this.grid[i][j] instanceof Ship) {
//                    if (!ships.equals(""))
//                        ships = ships + ",";
//                    ships = ships + this.grid[i][j];
//                }
//            }
//
//        }
        String string = this.m + "," + this.n + ";" + this.agent + ";";
        String stations = "";
        String ships = "";
        Iterator<Map.Entry<Cell,Integer>> iter1 = this.stations.entrySet().iterator();
        while (iter1.hasNext()) {
            Map.Entry<Cell,Integer> entry = iter1.next();
            if (!stations.equals(""))
                stations = stations + ",";
            stations = stations + entry.getKey();
        }
        Iterator<Map.Entry<Cell,Integer>> iter2 = this.ships.entrySet().iterator();
        while (iter2.hasNext()) {
            Map.Entry<Cell,Integer> entry = iter2.next();
            if (!ships.equals(""))
                ships = ships + ",";
            ships = ships + entry.getKey()+","+entry.getValue();

        }
        string = string + stations + ";" + ships + ";";
        return string;
    }

    @Override
    public int compareTo(Object o) {
        if (this.toString().equals(o.toString()))
            return 0;
        else
            return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (getClass() != o.getClass())
            return false;
        World w = (World) o;
        Iterator<Map.Entry<Cell,Integer>> iter1 = stations.entrySet().iterator();
        while (iter1.hasNext()) {
            Map.Entry<Cell,Integer> entry = iter1.next();
            if(w.stations.containsKey(entry.getKey())){
                if (!w.stations.get(entry.getKey()).equals(entry.getValue()))
                    return false;
            }
            else {
                return false;
            }
        }
        Iterator<Map.Entry<Cell,Integer>> iter2 = wrecks.entrySet().iterator();
        while (iter2.hasNext()) {
            Map.Entry<Cell,Integer> entry = iter2.next();
            if(w.wrecks.containsKey(entry.getKey())){
                if (!w.wrecks.get(entry.getKey()).equals(entry.getValue()))
                    return false;
            }
            else {
                return false;
            }
        }
        Iterator<Map.Entry<Cell,Integer>> iter3 = ships.entrySet().iterator();
        while (iter3.hasNext()) {
            Map.Entry<Cell,Integer> entry = iter3.next();
            if(w.ships.containsKey(entry.getKey())){
                if (!w.ships.get(entry.getKey()).equals(entry.getValue()))
                    return false;
            }
            else {
                return false;
            }
        }
        return (this.m == w.m) && (this.n == w.n) && (this.shipNum == w.shipNum)
                && (this.wreckNum == w.wreckNum) && (this.stationNum == w.stationNum) && (this.agent.equals(w.agent));
    }

    public static void main(String[] args) {
        String testa0 = "5,6;50;0,1;0,4,3,3;1,1,90;";
        World world = new World(testa0);
        TreeNode tn = new TreeNode(world,"left");
        try {
            TreeNode tn2 = tn.clone();
            System.out.println(tn.hashCode());
            System.out.println(tn2.hashCode());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}