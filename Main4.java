import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Node {
    int data;   
    Node left; 
    Node right; 

    Node(int value) {
        data = value;
        left = right = null;
    }
}

class BinaryTree {
    Node root;

    //прямой порядок
    void preOrder(Node node, ArrayList<Integer> result) {
        if (node == null) return;
        result.add(node.data);      
        preOrder(node.left, result);   
        preOrder(node.right, result);  
    }

    //центрированный порядок
    void inOrder(Node node, ArrayList<Integer> result) {
        if (node == null) return;
        inOrder(node.left, result);   
        result.add(node.data);         
        inOrder(node.right, result);    
    }

    //обратный порядок
    void postOrder(Node node, ArrayList<Integer> result) {
        if (node == null) return;
        postOrder(node.left, result);   
        postOrder(node.right, result);  
        result.add(node.data);          
    }

    //поуровневый порядок
    ArrayList<ArrayList<Integer>> levelOrder(Node root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            ArrayList<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                currentLevel.add(currentNode.data);
                if (currentNode.left != null) queue.add(currentNode.left);
                if (currentNode.right != null) queue.add(currentNode.right);
            }
            result.add(currentLevel);
        }
        return result;
    }

    //метод height для вычисления высоты дерева
    int height(Node root) {
        if (root == null) return -1; 
        int leftHeight = height(root.left); 
        int rightHeight = height(root.right); 
        return Math.max(leftHeight, rightHeight) + 1;
    }

    //проверка является ли дерево полным
    boolean isFull(Node root) {
        if (root == null) return true; 
        if (root.left == null && root.right == null) return true;
        if (root.left != null && root.right != null)
            return isFull(root.left) && isFull(root.right);
        return false; 
    }

    //BST
    Node insertBST(Node root, int data) {
        if (root == null) return new Node(data);
        if (data < root.data) {
            root.left = insertBST(root.left, data);
        } else if (data > root.data) {
            root.right = insertBST(root.right, data);
        }
        return root;
    }

    //построение сбалансированного дерева из отсортированного массива
    Node sortedArrayToBST(int[] arr, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2; 
        Node root = new Node(arr[mid]); 
        root.left = sortedArrayToBST(arr, start, mid - 1);
        root.right = sortedArrayToBST(arr, mid + 1, end); 
        return root;
    }

    //Реализация базовых операций (5.) 
    void insert(int data) {
        if (root == null) {
            root = new Node(data);
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.left == null) {
                current.left = new Node(data);
                break;
            } else {
                queue.add(current.left);
            }
            if (current.right == null) {
                current.right = new Node(data);
                break;
            } else {
                queue.add(current.right);
            }
        }
    }

    //для удаления узла путем замены на самый глубокий правый узел.
    void delete(int key) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            if (root.data == key) root = null;
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node keyNode = null, lastNode = null;

        while (!queue.isEmpty()) {
            lastNode = queue.poll();
            if (lastNode.data == key) keyNode = lastNode;
            if (lastNode.left != null) queue.add(lastNode.left);
            if (lastNode.right != null) queue.add(lastNode.right);
        }

        if (keyNode != null) {
            keyNode.data = lastNode.data; 
            deleteDeepest(root, lastNode); 
        }
    }


    void deleteDeepest(Node root, Node delNode) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current == delNode) {
                current = null;
                return;
            }
            if (current.left != null) {
                if (current.left == delNode) {
                    current.left = null;
                    return;
                } else {
                    queue.add(current.left);
                }
            }
            if (current.right != null) {
                if (current.right == delNode) {
                    current.right = null;
                    return;
                } else {
                    queue.add(current.right);
                }
            }
        }
    }
}

//реализация
public class Main4 {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        //построение дерева с помощью вставки по уровню
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        
        //inOrder (центрированный порядок)
        System.out.println("Центрированный обход:");
        ArrayList<Integer> inOrderResult = new ArrayList<>();
        tree.inOrder(tree.root, inOrderResult);
        System.out.println(inOrderResult);

        //preOrder (прямой порядок)
        System.out.println("Прямой обход:");
        ArrayList<Integer> preOrderResult = new ArrayList<>();
        tree.preOrder(tree.root, preOrderResult);
        System.out.println(preOrderResult);

        //postOrder (обратный порядок)
        System.out.println("Обратный обход:");
        ArrayList<Integer> postOrderResult = new ArrayList<>();
        tree.postOrder(tree.root, postOrderResult);
        System.out.println(postOrderResult);

        //levelOrder (поуровневый порядок)
        System.out.println("Поуровневый обход:");
        ArrayList<ArrayList<Integer>> levelOrderResult = tree.levelOrder(tree.root);
        System.out.println(levelOrderResult);

        //высота дерева
        System.out.println("Высота дерева: " + tree.height(tree.root));

        //Проверка, является ли дерево полным
        System.out.println("Является ли дерево полным? " + tree.isFull(tree.root));

        //вставка по уровню (level order)
        tree.insert(9);
        System.out.println("Центрированный обход после вставки по уровню:");
        inOrderResult.clear();
        tree.inOrder(tree.root, inOrderResult);
        System.out.println(inOrderResult);

        //Удаление узла
        tree.delete(4);
        System.out.println("Центрированный обход после удаления узла со значением 4:");
        inOrderResult.clear();
        tree.inOrder(tree.root, inOrderResult);
        System.out.println(inOrderResult);
        
    }
}
