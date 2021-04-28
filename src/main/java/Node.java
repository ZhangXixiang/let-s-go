import java.util.LinkedList;
import java.util.Stack;

public class Node {
    char data;
    Node leftChild;
    Node rightChild;

    public Node(char data, Node leftChild, Node rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }



    // 前序 根左右
    // 前序遍历的递推公式：
    // preOrder(r) = print r->preOrder(r->left)->preOrder(r->right)

    public static void preOrder(Node root){
        System.out.print(root.data);
        if(null != root.leftChild){
            preOrder(root.leftChild);
        }
        if(null != root.rightChild) {
            preOrder(root.rightChild);
        }
    }

    // 中序 左跟右
    // 中序遍历的递推公式：
    // inOrder(r) = inOrder(r->left)->print r->inOrder(r->right)
    public static void inOrder(Node root){
        if(null != root.leftChild){
            inOrder(root.leftChild);
        }
        System.out.print(root.data);
        if(null != root.rightChild) {
            inOrder(root.rightChild);
        }
    }

    // 后序 左右根
    // 后序遍历的递推公式：
    // postOrder(r) = postOrder(r->left)->postOrder(r->right)->print r
    public static void postOrder(Node root){
        if(null != root.leftChild){
            postOrder(root.leftChild);
        }
        if(null != root.rightChild) {
            postOrder(root.rightChild);
        }
        System.out.print(root.data);
    }



    //深度优先遍历
    public static void depthFirstSearch(Node rNode) {
        Stack<Node> stack = new Stack<>();
        stack.push(rNode);
        Node node = null;
        while (!stack.isEmpty()){
            node = stack.pop();
            System.out.println(node.data);

            if(null != node.rightChild){
                stack.push(node.rightChild);
            }
            if(null != node.leftChild){
                stack.push(node.leftChild);
            }
        }



        // Stack<Node> stack = new Stack<Node>();
        // stack.push(rNode);
        // Node node = null;
        // while (!stack.empty()) {
        //     node = stack.pop();
        //     System.out.print(node.data);//遍历根结点
        //     if (node.rightChild != null) {
        //         stack.push(node.rightChild);//先将右子树压栈
        //     }
        //     if (node.leftChild != null) {
        //         stack.push(node.leftChild);//再将左子树压栈
        //     }
        // }
    }

    //广度优先遍历
    public static void breadFirstSearch(Node rNode) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(rNode);

        while (!queue.isEmpty()){
            Node node = queue.poll();
            System.out.println(node.data);
            if(node.leftChild != null){
                queue.offer(node.leftChild);
            }
            if(node.rightChild != null){
                queue.offer(node.rightChild);
            }
        }

        // LinkedList<Node> queue = new LinkedList<Node>();
        // queue.offer(rNode);
        // Node node = null;
        // while (!queue.isEmpty()) {
        //     node = queue.poll();
        //     System.out.print(node.data);//遍历根结点
        //     if (node.leftChild != null) {
        //         queue.offer(node.leftChild);//先将左子树入队
        //     }
        //     if (node.rightChild != null) {
        //         queue.offer(node.rightChild);//再将右子树入队
        //     }
        // }
    }


    public static void main(String[] args) {
        Node D = new Node('D',null,null);
        Node E = new Node('E',null,null);
        Node F = new Node('F',null,null);
        Node G = new Node('G',null,null);
        Node B = new Node('B',D,E);
        Node C = new Node('C',F,G);
        Node A = new Node('A',B,C);

        breadFirstSearch(A);
        // depthFirstSearch(A);
        // preOrder(A);
        // inOrder(A);
        // postOrder(A);

    }



}
