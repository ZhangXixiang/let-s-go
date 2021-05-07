
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
        if (null == head || null == head.next) {
            return head;
        }
        ListNode cur = head;
        ListNode pre = null;
        while (null != cur) {
            ListNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode detectCycle(ListNode head) {
        return null;
    }


    /**
     * 找环入口
     *
     * @param head
     * @return
     */
    public static ListNode hasCircle(ListNode head) {
        if (null == head) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (null != fast && null != fast.next) {
            slow = slow.next;
            fast = fast.next.next;
            // 第一次相遇点
            if (slow == fast) {
                ListNode slow2 = head;
                while (slow != slow2) {
                    slow = slow.next;
                    slow2 = slow2.next;
                }
                return slow;
            }
        }
        return null;
    }

    // 将两个有序的链表合并为一个新链表，要求新的链表是通过拼接两个链表的节点来生成的，且合并后新链表依然有序。
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // write code here
        if (null == l1) {
            return l2;
        }
        if (null == l2) {
            return l1;
        }

        ListNode merge = null;
        if(l1.val < l2.val){
            merge = l1;
            merge.next = mergeTwoLists(l1.next,l2);
        } else {
            merge = l2;
            merge.next = mergeTwoLists(l1,l2.next);
        }
        return merge;
    }


    public static ListNode re(ListNode root){
        if(null == root || null == root.next){
            return root;
        }

        ListNode cur = root;
        ListNode pre = null;

        while (cur != null){
            ListNode next = cur.next;
            cur.next = pre;

            pre = cur;
            cur = next;


        }

        return pre;

    }


}
