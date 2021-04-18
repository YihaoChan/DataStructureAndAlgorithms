# 剑指 Offer 06. 从尾到头打印链表

## 1 题目

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

示例：

```
输入：head = [1,3,2]
输出：[2,3,1]
```

## 2 解法

![从尾到头打印链表](images/从尾到头打印链表.jpg)

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
    Queue<ListNode> queue = new LinkedList<>();

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }

        reversePushNodes(head);

        int[] res = new int[queue.size()];
        int ptr = 0;

        while (!queue.isEmpty()) {
            res[ptr] = queue.poll().val;
            ptr++;
        }

        return res;
    }

    private void reversePushNodes(ListNode head) {
        if (head == null) {
            return;
        }

        reversePushNodes(head.next);

        queue.offer(head);

        return;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点完成反转操作花费O(1)，被添加到List中花费O(1)，故完成反转操作共花费O(n)。从List中输出元素至数组中共花费O(n)。故总时间复杂度为**O(n)**；
2. 空间复杂度：List花费O(n)空间，数组花费O(n)空间，故总空间复杂度为**O(n)**。