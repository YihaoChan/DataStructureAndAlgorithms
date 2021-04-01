# 第92题 反转链表 II

## 1 题目

给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。

示例 1：

```
输入：head = [1,2,3,4,5], left = 2, right = 4
输出：[1,4,3,2,5]
```

示例 2：

```
输入：head = [5], left = 1, right = 1
输出：[5]
```

## 2 解法

### 2.1 递归

```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) {
 *         this.val = val; 
 *		   this.next = next; 
 *     }
 * }
 */
class Solution {
    ListNode successor = null;

    public ListNode reverseBetween(ListNode head, int left, int right)
    {
        if (head == null) {
            return head;
        }

        if (left == 1) {
            return reverseN(head, right);
        }

        head.next = reverseBetween(head.next, left - 1, right - 1);

        return head;
    }

    private ListNode reverseN(ListNode head, int right) {
        if (right == 1) {
            successor = head.next;
            return head;
        }

        ListNode last = reverseN(head.next, right - 1);

        head.next.next = head;
        head.next = successor;

        return last;
    }
}
```

复杂度分析：

1. 时间复杂度：步进到left处触发反转花费O(left)，从left到right处的结点完成反转花费O(right - left)，最坏情况下需要遍历整个链表，故总时间复杂度为**O(n)**；
2. 空间复杂度：递归完成从left到right处的结点的反转花费O(right - left)的栈空间，最坏情况下需要反转整个链表，故总空间复杂度为**O(n)**。

### 2.2 迭代

```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) {
 *         this.val = val; 
 *		   this.next = next; 
 *     }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) 
    {
        ListNode sentinel = new ListNode(-1, head);

        ListNode slow = sentinel;
        ListNode fast = head;

        int step = left;

        while (step > 1) {
            slow = slow.next;
            fast = fast.next;
            step--;
        }

        ListNode prev = fast;
        ListNode curr = prev.next;
        ListNode successor = null;

        int count = 0;

        while (left + count < right) {
            successor = curr.next;
            curr.next = prev;
            prev = curr;
            curr = successor;
            count++;
        }

        fast.next = curr;
        slow.next = prev;

        return sentinel.next;
    }
}
```

复杂度分析：

1. 时间复杂度：步进到left处触发反转花费O(left)，从left到right处的结点完成反转花费O(right - left)，最坏情况下需要遍历整个链表，故总时间复杂度为**O(n)**；
2. 空间复杂度：只花费了常数个额外空间，故空间复杂度为**O(1)**。