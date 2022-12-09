package code;

import java.util.*;

public class BreadthFirst extends SearchStrategy {
    // HashMap<String, Vector<Comparable<World>>> visited;
    HashSet<TreeNode> visited;
    String[] operators;
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

        Queue<TreeNode> queue = new LinkedList<>();
        for (String operator : operators) {
            if (worldRoot.validateOperator(operator))
                queue.add(new TreeNode(worldRoot.clone(), operator));
        }
        TreeNode s;
        while (!queue.isEmpty()) {
            s = queue.remove();
            s.nodesExpanded++;
            if (s.world.worldEnd()) {
                System.out.println(s.plan + ";" + s.world.deaths + ";" + s.world.agent.blackBoxes + ";" + s.nodesExpanded + ";");
                return s.plan + ";" + s.world.deaths + ";" + s.world.agent.blackBoxes + ";" + s.nodesExpanded;
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
                    vec.add(new TreeNode(s.world.clone(), operator,s.plan,s.nodesExpanded));
            }
            Iterator<TreeNode> i = vec.iterator();
            while (i.hasNext()) {
                TreeNode n = i.next();
                if (visited.add(n)) {
                    //visited.add(n.clone());
                    queue.add(n);
                }
            }
        }
        return "";
    }



    public static void main(String[] args){

        System.out.println(CoastGuard.solve("5,6;50;0,1;0,4,3,3;1,1,90;","BF",false));
    }
}