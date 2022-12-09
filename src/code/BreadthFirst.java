package code;

import java.util.*;

public class BreadthFirst extends SearchStrategy {
    // HashMap<String, Vector<Comparable<World>>> visited;
    HashSet<String> visited;
    String[] operators;
    int nodesExpanded;
    World worldRoot;

    BreadthFirst(String[] operators, World worldRoot) {
        //this.visited = new HashMap<>();
        this.operators = operators;
        this.worldRoot = worldRoot;
        /*
         * for (String operator:
         * operators) {
         * this.visited.put(operator,new Vector<Comparable<World>>());
         * }
         */
        this.visited = new HashSet<>();
    }

    public String search() throws CloneNotSupportedException {

        Queue<String> queue = new LinkedList<>();
        for (String operator : operators) {
            if (worldRoot.validateOperator(operator))
                queue.add((new TreeNode(worldRoot.clone(), operator)).toString());
        }
        String st;
        TreeNode s;
        while (!queue.isEmpty()) {
            st = queue.remove();
            s = new TreeNode(st);
            nodesExpanded++;
            if (s.world.worldEnd()) {
                System.out.println(s.plan + ";" + s.world.deaths + ";" + s.world.agent.retrieved + ";" + nodesExpanded + ";");
                return s.plan + ";" + s.world.deaths + ";" + s.world.agent.retrieved + ";" + nodesExpanded;
            }
            s.world.execOperator(s.operator);
            if (!s.plan.equals(""))
                s.plan = s.plan + "," + s.operator;
            else
                s.plan = s.operator;
            // System.out.print(s + " ");
            Vector<TreeNode> vec = new Vector<>();
            for (String operator : operators) {
                if (s.world.validateOperator(operator))
                    vec.add(new TreeNode(s.world.clone(), operator,s.plan));
            }
            Iterator<TreeNode> i = vec.iterator();
            while (i.hasNext()) {
                TreeNode n = i.next();
                if (visited.add(n.toString())) {
                    //visited.add(n.clone());
                    queue.add(n.toString());
                }
            }
        }
        return "";
    }



    public static void main(String[] args){

        System.out.println(CoastGuard.solve("5,6;50;0,1;0,4,3,3;1,1,90;","BF",false));
    }
}