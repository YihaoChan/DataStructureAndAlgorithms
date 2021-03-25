# 第2题 两数相加

## 1 题目

给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例 1：

```
输入：l1 = [2,4,3], l2 = [5,6,4]
输出：[7,0,8]
解释：342 + 465 = 807.
```

示例 2：

```
输入：l1 = [0], l2 = [0]
输出：[0]
```

示例 3：

```
输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
输出：[8,9,9,9,0,0,0,1]
```

## 2 解法

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        // 遍历l1、l2
        ListNode ptr1 = l1;
        ListNode ptr2 = l2;

        int spill = 0; // 溢出位

        // 答案链表
        ListNode res = new ListNode(-1, null);

        // 遍历答案链表
        ListNode node = res;

        // 每位求和之和
        int sum = 0;

        while (ptr1 != null && ptr2 != null) {
            sum = ptr1.val + ptr2.val + spill;

            if (sum >= 10) {
                sum -= 10;
                spill = 1;
            } else {
                spill = 0;
            }

            node.next = new ListNode(sum, null);
            node = node.next;

            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        // l1位数比l2多
        while (ptr1 != null && ptr2 == null) {
            sum = ptr1.val + spill;

            if (sum >= 10) {
                sum -= 10;
                spill = 1;
            } else {
                spill = 0;
            }

            node.next = new ListNode(sum, null);
            node = node.next;

            ptr1 = ptr1.next;
        }

        // l2位数比l1多
        while (ptr2 != null && ptr1 == null) {
            sum = ptr2.val + spill;

            if (sum >= 10) {
                sum -= 10;
                spill = 1;
            } else {
                spill = 0;
            }

            node.next = new ListNode(sum, null);
            node = node.next;

            ptr2 = ptr2.next;
        }

        // 两边都遍历结束
        if (ptr1 == null && ptr2 == null) {
            if (spill == 1) {
                node.next = new ListNode(spill, null);
                node = node.next;
            }
        }

        node.next = null;

        return res.next; 
    }
}
```

复杂度分析：

假设链表1有m个元素，链表2有n个元素：

1. 时间复杂度：时间复杂度为**O(max(m, n))**；
2. 空间复杂度：m位和n位的数相加，和最多不超过max(m, n) + 1位，故空间复杂度为**O(max(m, n))**。