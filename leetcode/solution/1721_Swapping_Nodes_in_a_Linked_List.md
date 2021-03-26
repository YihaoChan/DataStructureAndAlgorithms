# 第1721题 交换链表中的节点

## 1 题目

给你链表的头节点 head 和一个整数 k 。

交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）。

示例 1：

```
输入：head = [1,2,3,4,5], k = 2
输出：[1,4,3,2,5]
```

示例 2：

```
输入：head = [7,9,6,6,7,8,3,0,9,5], k = 5
输出：[7,9,6,6,8,7,3,0,9,5]
```

示例 3：

```
输入：head = [1], k = 1
输出：[1]
```

示例 4：

```
输入：head = [1,2], k = 1
输出：[2,1]
```

示例 5：

```
输入：head = [1,2,3], k = 2
输出：[1,2,3]
```

## 2 解法

快慢指针。

```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode sentinel = new ListNode(-1, head);

        ListNode slow = sentinel;
        ListNode fast = sentinel;
        ListNode memo = sentinel;

        int step = k;
        int count = 0;

        while (k > 0) {
            fast = fast.next;
            k--;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        while (count < step) {
            memo = memo.next;
            count++;
        }

        int temp = slow.val;
        slow.val = memo.val;
        memo.val = temp;

        return sentinel.next;
    }
}
```

复杂度分析：

1. 时间复杂度：快指针走n步，慢指针走n - k步，记录正数第k位置的指针走k步，故总时间复杂度为**O(n)**；
2. 空间复杂度：仅用到常数个额外空间，故空间复杂度为**O(1)**。

