import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static sun.swing.MenuItemLayoutHelper.max;

/* This class implements the problems available on: http://cslibrary.stanford.edu/110/BinaryTrees.html */

public class BinaryTree {
    public static class Node{
        private Node left;
        private Node right;
        private int value;

        public Node(int value){
            this.value = value;
        }

        private void insert(int value){
            if(value <= this.value){
                if(this.left == null){
                    this.left = new Node(value);
                } else{
                    this.left.insert(value);
                }
            } else{
                if(this.right == null){
                    this.right = new Node(value);
                } else{
                    this.right.insert(value);
                }
            }
        }

        public int getSize(){
            int size = 1;

            if(this.left != null){
                size += this.left.getSize();
            }

            if(this.right != null){
                size += this.right.getSize();
            }

            return size;
        }

        public int maxDepth(Node node){
            if(node == null){
                return 0;
            }

            if(node.left == null && node.right == null){
                return 1;
            }

            return max(maxDepth(node.left), maxDepth(node.right)) + 1;
        }

        public int minValue(){
            if(this.left == null){
                return this.value;
            }

            return this.left.minValue();
        }

        public String printInOrder(){
            String printValue = "";

            if(this.left != null){
                printValue += this.left.printInOrder();
            }

            printValue += this.value;

            if(this.right != null){
                printValue += this.right.printInOrder();
            }

            return printValue;
        }

        public String printPostOrder(){
            String printValue = "";

            if(this.left != null){
                printValue += this.left.printPostOrder();
            }

            if(this.right != null){
                printValue += this.right.printPostOrder();
            }

            printValue += this.value;

            return printValue;
        }

        public Boolean hasPathSum(int initialValue, int sum){
            int value = initialValue + this.value;

            Boolean leftResult = false;
            Boolean rightResult = false;

            if(this.left != null){
                leftResult = this.left.hasPathSum(value, sum);
            }

            if(this.right != null){
                rightResult = this.right.hasPathSum(value, sum);
            }

            if(this.left == null && this.right == null){
                return sum == value;
            }

            return leftResult || rightResult;
        }

        public void printPaths(String path){
            String resultPath = path + " " + this.value;

            if(this.left != null){
                this.left.printPaths(resultPath);
            }

            if(this.right != null){
                this.right.printPaths(resultPath);
            }

            if(this.left == null && this.right == null){
                System.out.println(resultPath);
            }
        }

        public void swapNodes(){
            if(this.left == null && this.right == null){
                return;
            }

            Node tmp = this.left;
            this.left = this.right;
            this.right = tmp;

            if(this.left != null){
                this.left.swapNodes();
            }

            if(this.right != null){
                this.right.swapNodes();
            }
        }

        public void doubleTree(){
            if(this.left == null){
                this.left = new Node(this.value);
            } else{
                Node tmp = this.left;
                this.left = new Node(this.value);
                this.left.left = tmp;

                this.left.left.doubleTree();
            }

            if(this.right != null){
                this.right.doubleTree();
            }
        }

        public Boolean sameTree(Node tree1, Node tree2){
            if(tree1 == null && tree2 == null){
                return true;
            } else if(tree1 != null && tree2 != null){
                return tree1.value == tree2.value
                        && sameTree(tree1.left, tree2.left)
                        && sameTree(tree1.right, tree2.right);
            }

            return false;
        }

        private Boolean isBst(int min, int max){
            Boolean isLeftOk = true;
            Boolean isRightOk = true;

            if(this.value > max || this.value < min){
                return false;
            }

            if(this.left != null){
                isLeftOk = this.left.isBst(min, this.value);
            }

            if(this.right != null){
                isRightOk = this.right.isBst(this.value + 1, max);
            }

            return isLeftOk && isRightOk;
        }

        public Boolean isBst(){
            return isBst(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        public void levelOrderTraversal(){
            Queue<Node> queue = new LinkedList<>();
            queue.add(this);

            while(!queue.isEmpty()) {
                Node node = queue.poll();
                System.out.print(node.value + " ");

                if (node.left != null) {
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
        }

        public int maxWidth(){
            Queue<Node> queue = new LinkedList<>();
            queue.add(this);
            int maxWidth = queue.size();

            while(!queue.isEmpty()){
                Node node = queue.poll();

                if(node.left != null){
                    queue.add(node.left);
                }

                if(node.right != null){
                    queue.add(node.right);
                }

                maxWidth = max(maxWidth, queue.size());
            }

            return maxWidth;
        }

        private void calculateVerticalSum(Node node, Map<Integer, Integer> columns, int position){
            int oldValue = columns.getOrDefault(position, 0);
            columns.put(position, oldValue + node.value);

            int leftPosition = position;
            int rightPosition = position;

            if(node.left != null){
                leftPosition --;
                calculateVerticalSum(node.left, columns, leftPosition);
            }

            if(node.right != null){
                rightPosition++;
                calculateVerticalSum(node.right, columns, rightPosition);
            }
        }

        public void verticalSum(){
            Map<Integer, Integer> columns = new HashMap<>();

            calculateVerticalSum(this, columns, 0);

            for(Map.Entry<Integer, Integer> column : columns.entrySet()){
                System.out.println("Column " + column.getKey() + " has the vertical sum: " + column.getValue());
            }
        }
    }

    public static void main(String[] args){
        Node tree = new Node(5);
        tree.insert(1);
        tree.insert(7);
        tree.insert(8);
        tree.insert(6);

        System.out.println("The size is: " + tree.getSize());
        System.out.println("The max depth is: " + tree.maxDepth(tree));
        System.out.println("The min value is: " + tree.minValue());
        System.out.println("The in order traversal is: " + tree.printInOrder());
        System.out.println("The post order traversal is: " + tree.printPostOrder());
        System.out.println("Is there a path sum for 18? " + tree.hasPathSum(0, 18));
        tree.printPaths("");
        tree.swapNodes();
        System.out.println("The in order traversal after swapping nodes is: " + tree.printInOrder());
        tree.doubleTree();
        System.out.println("The in order traversal after double tree is: " + tree.printInOrder());

        Node tree1 = new Node(5);
        tree1.insert(3);
        tree1.insert(1);
        tree1.insert(8);
        tree1.insert(6);
        tree1.insert(9);

        Node tree2 = new Node(5);
        tree2.insert(3);
        tree2.insert(1);
        tree2.insert(8);
        tree2.insert(7);
        tree2.insert(9);

        System.out.println("Are tree1 and tree2 equal? " + tree.sameTree(tree1, tree2));

        testBst();

        tree1.insert(10);
        tree1.insert(4);
        tree1.insert(7);
        tree1.levelOrderTraversal();

        System.out.println("\nThe largest width is: " + tree1.maxWidth());

        tree1.verticalSum();
    }

    private static void testBst() {
        Node node = new Node(5);
        Node node1 = new Node(2);
        Node node2 = new Node(7);
        Node node3 = new Node(6);
        Node node4 = new Node(1);
        Node node5 = new Node(25);
        Node node6 = new Node(0);

        node.left = node1;
        node.right = node2;
        System.out.println("is Bst? " + node.isBst());

        node.left = node3;
        node.right = node2;
        System.out.println("is Bst? " + node.isBst());

        node.left = node1;
        node.left.left = node4;
        node.right = node2;
        System.out.println("is Bst? " + node.isBst());

        node.left.right = node3;
        System.out.println("is Bst? " + node.isBst());

    }
}
