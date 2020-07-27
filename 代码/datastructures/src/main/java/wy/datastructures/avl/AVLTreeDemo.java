package wy.datastructures.avl;

/**
 * AVL树
 */
public class AVLTreeDemo {

    public static void main(String[] args) {
        AVLTreeDemo demo = new AVLTreeDemo();
        TreeNode root = new TreeNode(0);
        for (int i = 1; i < 11; i++) {
            root = demo.put(root, i);
        }
        root.infixOrder();
    }

    /**
     * AVL树插入
     * @param root
     * @param key
     * @return
     */
    public TreeNode put(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(key);
        }
        if (root.value > key) {
            root.left = put(root.left, key);
        } else if (root.value < key) {
            root.right = put(root.right, key);
        } else {
            root.value = key;
        }
        updateHeight(root);
        return rebalance(root, key);
    }

    public TreeNode rebalance(TreeNode node, int key) {
        int balancedFactor = getBalancedFactor(node);
        if (balancedFactor > 1 && node.left.value > key) { //LL型
            return rightRotation(node);
        } else if (balancedFactor < -1 && node.right.value < key) { //RR型
            return leftRotation(node);
        } else if (balancedFactor > 1 && node.left.value < key) { //LR型
            node.left = leftRotation(node.left);
            return rightRotation(node);
        } else if (balancedFactor < -1 && node.right.value > key) { //RL型
            node.right = rightRotation(node.right);
            return leftRotation(node);
        }
        return node;
    }

    public TreeNode leftRotation(TreeNode node) {
        TreeNode y = node.right;
        node.right = y.left;
        y.left = node;
        updateHeight(node);
        updateHeight(y);
        return y;
    }

    public TreeNode rightRotation(TreeNode node) {
        TreeNode y = node.left;
        node.left = y.right;
        y.right = node;
        updateHeight(node);
        updateHeight(y);
        return y;
    }

    public void updateHeight(TreeNode node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    public int getBalancedFactor(TreeNode node) {
        if (node != null) {
            return height(node.left) - height(node.right);
        }
        return 0;
    }

    public int height(TreeNode node) {
        if (node == null) return 0;
        return node.height;
    }

}

class TreeNode {
    int value;
    int height;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
        this.height = 1;
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                '}';
    }
}