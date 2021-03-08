import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for singly-linked list.
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        // System.out.println(Solution.isPalindrome(node1));
        // System.out.println(Solution.reversed(node1));
        System.out.println(Solution.deletePosNListNode(node1, 5));
        Map<String, String> map = new HashMap<>();
        map.put("q", "s");
        System.out.println(JSONObject.toJSONString(map));
        System.out.println(map);
    }
}


class Solution {
    // 单链表反转
    public static ListNode reversed(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode pre = null;
        ListNode slow = head;
        while (null != slow) {
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }
        return pre;
    }


    // 链表中环的检测
    public static boolean testCircle(ListNode head) {
        if (null == head || null == head.next) {
            return false;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    // 两个有序的链表合并


    // 删除链表倒数第 n 个结点
    public static ListNode deletePosNListNode(ListNode head, int n) {
        /*ListNode reversed = reversed(head);
        ListNode slow = reversed;
        for(int i = 1 ; i <= n ; i ++ ){
            if(i == n) {
                slow.next = slow.next.next;
            }
        }
        return reversed(reversed);*/

       /* ListNode fast = head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //慢指针要从dummy开始，要不然删除的是头节点，就不好操作
        ListNode slow = dummy;
        while (fast != null && n-- > 0) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        if (slow.next != null) {
            slow.next = slow.next.next;
        }
        return dummy.next;*/

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && n-- > 0){
            fast = fast.next;
        }
        while (null != fast){
            fast = fast.next;
            slow = slow.next;
        }
        if (slow.next != null){
            slow.next = slow.next.next;
        }
        return head;
    }


    // 求链表的中间结点
    public static ListNode middleListNode(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode fast = head, slow = head, pre = null;
        while (null != fast && null != fast.next) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (null != fast) {
            slow = slow.next;
        }
        return slow;
    }


    // 是否回文
    public static boolean isPalindrome(ListNode head) {
        if (null == head || null == head.next) {
            return true;
        }

        ListNode fast = head, slow = head, pre = null;
        while (null != fast && null != fast.next) {
            fast = fast.next.next;
            ListNode next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }

        if (null != fast) {
            slow = slow.next;
        }

        while (null != slow) {
            if (slow.val != pre.val) {
                return false;
            }
            slow = slow.next;
            pre = pre.next;
        }
        return true;
    }













    /*{
        if (head == null || head.next == null) {
            return true;
        }

        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            if (slow.val != prev.val) {
                return false;
            }
            slow = slow.next;
            prev = prev.next;
        }

        return true;
    }*/
}