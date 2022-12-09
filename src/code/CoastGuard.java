package code;

public class CoastGuard {

    public static String solve(String grid, String strategy, boolean visualize){
        SearchStrategy search;
        World world = new World(grid);
        System.out.println(world.shipNum);
        String[] operators = {"up","down","left","right","pickup","drop", "retrieve"};
        switch (strategy){
            case "BF": search = new BreadthFirst(operators, world);
            break;
            default: return "";
        }

        try {
            return search.search();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
