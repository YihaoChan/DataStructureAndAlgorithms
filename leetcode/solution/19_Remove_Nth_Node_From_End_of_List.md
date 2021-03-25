# 第19题 删除链表的倒数第N个结点

## 1 题目

给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。

示例 1：

```
输入：head = [1,2,3,4,5], n = 2
输出：[1,2,3,5]
```

示例 2：

```
输入：head = [1], n = 1
输出：[]
```

示例 3：

```
输入：head = [1,2], n = 1
输出：[1]
```

## 2 解法

倒数第k个结点相关问题，用快慢指针。

### 2.1 三个指针

构造哨兵结点，快慢指针初始时都指向哨兵结点。快指针先走k步，之后快慢指针同时出发。当快指针为null时，慢指针指向倒数第k个结点。而为了删除这个结点，需要有一个待删除结点的前驱结点。因此初始时，还需要一个结点更晚一点出发，用于删除待删除结点。

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode sentinel = new ListNode(-1, head);

        ListNode remove = sentinel;
        ListNode slow = sentinel;
        ListNode fast = sentinel;

        int flag = 0; // 起删除作用的结点指针什么时候出发

        while (n > 0) {
            fast = fast.next;
            n--;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;

            flag++;

            if (flag >= 2) {
                remove = remove.next;
            }
        }

        remove.next = slow.next;

        return sentinel.next;
    }
}
```

复杂度分析：

1. 时间复杂度：快指针移动n次，慢指针移动n - k次，待删除指针移动n - k - 1次，故总时间复杂度为**O(n)**；
2. 空间复杂度：仅用到常数个额外空间，故空间复杂度为**O(1)**。

### 2.2 两个指针

上述方法需要一个比慢指针还晚一点出发的指针，可优化为：初始时，慢指针指向哨兵结点，而快指针指向head，这样子当快指针为null时，慢指针就指向了倒数第k个结点的前驱结点。

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode sentinel = new ListNode(-1, head);

        ListNode slow = sentinel;
        ListNode fast = head;

        while (n > 0) {
            fast = fast.next;
            n--;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return sentinel.next;
    }
}
```

复杂度分析：

1. 时间复杂度：快指针移动n次，慢指针移动n - k次，故总时间复杂度为**O(n)**；
2. 空间复杂度：仅用到常数个额外空间，故空间复杂度为**O(1)**。

