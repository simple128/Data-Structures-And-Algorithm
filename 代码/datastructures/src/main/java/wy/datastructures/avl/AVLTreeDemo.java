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
        System.out.println("插入之后：");
        root.infixOrder();
        root = demo.remove(root, 0);
        root = demo.remove(root, 2);
        System.out.println("删除之后：");
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
        return rebalance(root);
    }

    public TreeNode remove(TreeNode root, int key) {
        if (root == null) return null;

        if (root.value > key) {
            root.left = remove(root.left, key);
        } else if (root.value < key) {
            root.right = remove(root.right, key);
        } else {
            if (root.left ==null && root.right == null) {
                return null;
            } else if (root.right != null) {
                root.value = predecessor(root);
                root.right = remove(root.right, root.value);
            } else {
                root.value = successor(root);
                root.left = remove(root.left, root.value);
            }
        }
        updateHeight(root);
        return rebalance(root);
    }

    public int successor(TreeNode root) {
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }
        return root.value;
    }

    public int predecessor(TreeNode root) {
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root.value;
    }

    public TreeNode rebalance(TreeNode node) {
        int balancedFactor = getBalancedFactor(node);
        if (balancedFactor > 1 && height(node.left.left) >= height(node.left.right)) { //LL型
            return rightRotation(node);
        } else if (balancedFactor < -1 && height(node.right.left) <= height(node.right.right)) { //RR型
            return leftRotation(node);
        } else if (balancedFactor > 1 && height(node.left.left) < height(node.left.right)) { //LR型
            node.left = leftRotation(node.left);
            return rightRotation(node);
        } else if (balancedFactor < -1 && height(node.right.left) > height(node.right.right)) { //RL型
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