import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TreeTest {


    public static void main(String[] args) {

        TreeNode treeNode2 = new TreeNode(9, null, null);
        TreeNode treeNode4 = new TreeNode(15, null, null);
        TreeNode treeNode5 = new TreeNode(7, null, null);
        TreeNode treeNode3 = new TreeNode(20, treeNode4, treeNode5);
        TreeNode treeNode1 = new TreeNode(3, treeNode2, treeNode3);

        levelOrder(treeNode1);
    }


    /**
     * 层序遍历 输出数组形式
     *
     * @param root
     * @return
     */
    public static ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if (null == root) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> cellList = new ArrayList<Integer>();
            for (int i = 0; i < queue.size(); i++) {
                TreeNode poll = queue.poll();
                cellList.add(poll.val);
                if (null != poll.left) {
                    queue.offer(poll.left);
                }
                if (null != poll.right) {
                    queue.offer(poll.right);
                }
            }
            result.add(cellList);
        }
        return result;
    }


}

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}