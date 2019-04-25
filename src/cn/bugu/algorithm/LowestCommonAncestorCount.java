package cn.bugu.algorithm;

public class LowestCommonAncestorCount {
    private int count = 0;
    /**
     * @param root: The root of the binary search tree.
     * @param A and B: two nodes in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        TreeNode result = helper(root, A, B);
        if (A == B) {
            return result;
        } else {
            return (count == 2) ? result : null;
        }
    }

    private TreeNode helper(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) return null;

        TreeNode lNode = helper(root.left, A, B);
        TreeNode rNode = helper(root.right, A, B);
        // root is the LCA of A and B
        if (lNode != null && rNode != null) return root;
        // root node is A/B(including the case below)
        if (root == A || root == B) {
            count++;
            return root;
        }
        // return lNode/rNode if root is not LCA
        return (lNode != null) ? lNode : rNode;
    }

    /**
     * Definition of TreeNode:
     */
    class TreeNode {
        public int val;
        public LowestCommonAncestorCount.TreeNode left, right;

        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
}
