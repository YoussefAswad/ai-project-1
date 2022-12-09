package code;

import java.io.Serializable;
import java.util.Objects;

public class TreeNode implements Cloneable, Serializable {

    World world;
    String plan;
    String operator;

    public TreeNode(World world, String operator){
        this.world = world;
        //this.nodesExpanded = 0;
        this.operator = operator;
        this.plan = "";
    }

    public TreeNode(String in){
        String[] ele = in.split("!");
        this.world = new World(ele[0]);
        this.plan = ele[1];
        //this.nodesExpanded = Integer.parseInt(ele[2]);
        this.operator = ele[2];

    }

    public TreeNode(String in,String operator){
        String[] ele = in.split("!");
        this.world = new World(ele[0]);
        this.plan = ele[1];
        //this.nodesExpanded = Integer.parseInt(ele[2]);
        this.operator = operator;

    }

    public TreeNode(World world, String operator, String plan){
        this.world = world;
        //this.nodesExpanded = nodesExpanded;
        this.operator = operator;
        this.plan = plan;
    }

    @Override
    public boolean equals(Object o){
        if (getClass() != o.getClass())
            return false;
        TreeNode n = (TreeNode) o;
        return (this.world.equals(n.world)) & this.plan.equals(n.plan) & this.operator.equals(n.operator);
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
        return Objects.hash(world.toString());
    }

    @Override
    public String toString() {
        return world.toString()+"!"+plan+"!"+operator;
    }
}
