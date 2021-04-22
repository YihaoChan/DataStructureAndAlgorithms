# 第61题 旋转链表

## 1 题目

给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。

示例 1：

```
输入：head = [1,2,3,4,5], k = 2
输出：[4,5,1,2,3]
```

示例 2：

```
输入：head = [0,1,2], k = 4
输出：[2,0,1]
```

## 2 解法

先求出链表长度，再计算出从哪个结点开始断开原链表结点之间的连接，并将尾结点与首结点相连。

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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        // 计算链表长度，同时找到用于连接链表尾结点和首结点的结点
        ListNode connect = head;
        int len = 1;

        while (connect.next != null) {
            connect = connect.next;
            len++;
        }

        // 从倒数第几个结点开始断开连接
        int cutLen = len - (k % len);
        int count = 1;
        ListNode cutNode = head;

        while (count < cutLen) {
            cutNode = cutNode.next;
            count++;
        }

        // 连接尾结点和首结点，并断开原有连接
        connect.next = head;
        head = cutNode.next;
        cutNode.next = null;

        return head;
    }
}
```

复杂度分析：

1. 时间复杂度：最坏情况下需要遍历两次链表，故时间复杂度为**O(n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。




