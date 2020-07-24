package wy.datastructures.tree;

public class BinaryTreeDemo {

    public static void main(String[] args) {

        BinaryTree tree1 = new BinaryTree(1);
        BinaryTree tree2 = new BinaryTree(3);
        BinaryTree tree3 = new BinaryTree(6);
        BinaryTree tree4 = new BinaryTree(8);
        BinaryTree tree5 = new BinaryTree(10);
        BinaryTree tree6 = new BinaryTree(14);
        BinaryTree tree7 = new BinaryTree(7);
        tree1.setLeft(tree2);
        tree1.setRight(tree3);
        tree2.setLeft(tree4);
        tree2.setRight(tree5);
        tree3.setLeft(tree6);
        //tree3.setRight(tree7);
        tree1.infixOrder();
        tree1.infixthreadedBinaryTree(tree1);
        tree1.printInfixThreadedBinaryTree(tree1);
        System.out.println(tree1);
    }



    private static class BinaryTree {
        private BinaryTree left;
        private BinaryTree right;
        private int leftType;
        private int rightType;
        private int val;

        private BinaryTree pre;

        public int getLeftType() {
            return leftType;
        }

        public void setLeftType(int leftType) {
            this.leftType = leftType;
        }

        public int getRightType() {
            return rightType;
        }

        public void setRightType(int rightType) {
            this.rightType = rightType;
        }

        public BinaryTree(int val) {
            this.val = val;
        }

        public BinaryTree getLeft() {
            return left;
        }

        public void setLeft(BinaryTree left) {
            this.left = left;
        }

        public BinaryTree getRight() {
            return right;
        }

        public void setRight(BinaryTree right) {
            this.right = right;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        /**
         * 按照线索化输出线索化二叉树
         * @param node
         */
        public void printInfixThreadedBinaryTree(BinaryTree node) {
            while (node != null) {
                //利用leftType找到最前面的一个node
                while (node.getLeftType() == 0) {
                    node = node.getLeft();
                }
                System.out.println(node);
                while (node.getRightType() == 1) {
                    node = node.getRight();
                    System.out.println(node);
                }
                node = node.getRight();
            }
        }

        public void infixthreadedBinaryTree(BinaryTree node) {
            if (node == null) {
                return;
            }
            infixthreadedBinaryTree(node.getLeft());

            if (node.getLeft() == null) {
                node.setLeft(pre);
                node.setLeftType(1);
            }
            if (pre != null && pre.getRight() == null) {
                pre.setRight(node);
                pre.setRightType(1);
            }
            pre = node;

            infixthreadedBinaryTree(node.getRight());
        }

        /**
         * 前序遍历
         */
        public void preOrder() {
            System.out.println(this);
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
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

        public void postOrder() {
            if (this.left != null) {
                this.left.postOrder();
            }
            if (this.right != null) {
                this.right.postOrder();
            }
            System.out.println(this);
        }

        @Override
        public String toString() {
            return "BinaryTree{" +
                    "val=" + val +
                    '}';
        }
    }



}
