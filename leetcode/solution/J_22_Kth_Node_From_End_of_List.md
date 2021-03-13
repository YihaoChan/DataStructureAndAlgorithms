# 剑指 Offer 22. 链表中倒数第k个节点

## 1 题目

输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。

例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。

示例：

```
给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.
```

## 2 解法

快慢指针，让快指针先走k步。然后快指针和慢指针一起出发，当快指针为空时，慢指针即为倒数第k个结点。

```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        while (k > 0) {
            fast = fast.next;

            k--;
        }

        while (fast != null) {
            fast = fast.next;

            slow = slow.next;
        }

        return slow;
    }
}
```

复杂度分析：

1. 时间复杂度：顺序扫描花费**O(n)**；
2. 空间复杂度：只用到常数个额外空间，空间复杂度为**O(1)**。



