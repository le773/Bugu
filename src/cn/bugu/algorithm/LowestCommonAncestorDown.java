package cn.bugu.algorithm;

public class LowestCommonAncestorDown {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) return null;

        TreeNode lNode = lowestCommonAncestor(root.left, A, B);
        TreeNode rNode = lowestCommonAncestor(root.right, A, B);
        // root is the LCA of A and B
        if (lNode != null && rNode != null) return root;
        // root node is A/B(including the case below)
        if (root == A || root == B) return root;
        // return lNode/rNode if root is not LCA
        return (lNode != null) ? lNode : rNode;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(13);
        TreeNode node7 = new TreeNode(17);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        LowestCommonAncestorDown commonAncestorDown = new LowestCommonAncestorDown();
        TreeNode root = commonAncestorDown.lowestCommonAncestor(node1, node4, node5);
        System.out.println(root.val);
    }
}


