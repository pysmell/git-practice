package designModel.组合模式;

public class Tree {

    TreeNode root = null;

    public Tree(String name) {
        root = new TreeNode(name);
    }

    public static void main(String[] args) {

        Tree tree = new Tree("A");

        TreeNode treeNodeB = new TreeNode("B");

        TreeNode treeNodeC = new TreeNode("C");

        tree.root.add(treeNodeB);

        treeNodeB.setParent(tree.root);

        treeNodeB.add(treeNodeC);

        treeNodeC.setParent(treeNodeB);

    }

}
































