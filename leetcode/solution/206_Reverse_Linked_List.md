# 第206题 反转链表

## 1 题目

反转一个单链表。

示例：

```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
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
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
        	return head;
        }

        ListNode last = reverseList(head.next);

        head.next.next = head;
        head.next = null;

        return last;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点完成反转操作花费O(1)，n个结点共花费O(n)，故时间复杂度为**O(n)**；
2. 空间复杂度：递归的过程中需要压栈，共压入n层，故空间复杂度为**O(n)**。

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
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        return prev;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点完成反转操作花费O(1)，n个结点共花费O(n)，故时间复杂度为**O(n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。