
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    public static void main(String[] agrs) {
        // test data
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        Solution.ReverseList(node1);

        System.out.println("a");
    }
}


class Solution {
    public static ListNode ReverseList(ListNode head) {

        if(null == head || null == head.next){
            return head;
        }

        ListNode cur = head;
        ListNode pre = null;

        while (null != cur){
            ListNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        return pre;

    }


}
