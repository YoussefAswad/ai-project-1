package code;

import java.io.Serializable;
import java.util.Objects;

public class TreeNode implements Cloneable, Serializable {

    World world;
    String plan;
    int nodesExpanded;
    String operator;

    public TreeNode(World world, String operator){
        this.world = world;
        this.nodesExpanded = 0;
        this.operator = operator;
        this.plan = "";
    }

    public TreeNode(World world, String operator, String plan,int nodesExpanded){
        this.world = world;
        this.nodesExpanded = nodesExpanded;
        this.operator = operator;
        this.plan = plan;
    }

    @Override
    public boolean equals(Object o){
        if (getClass() != o.getClass())
            return false;
        TreeNode n = (TreeNode) o;
        return (this.world.equals(n.world)) & this.plan.equals(n.plan) & (this.nodesExpanded == n.nodesExpanded) & this.operator.equals(n.operator);
    }
    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
    @Override
    public TreeNode clone() throws CloneNotSupportedException
    {
        TreeNode newNode = (TreeNode) super.clone();
        newNode.world = this.world.clone();
        return  newNode;
    }
    @Override
    public int hashCode() {
        return Objects.hash(world.wrecks,world.ships,world.stations,world.agent.pickUps,world.agent.blackBoxes,world.agent.x,world.agent.y,world.agent.capacity,world.shipNum, world.wreckNum,world.deaths, operator, nodesExpanded);
    }
}
