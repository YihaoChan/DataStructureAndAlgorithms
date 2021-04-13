# 第86题 分隔链表

## 1 题目

给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。

你应当 保留 两个分区中每个节点的初始相对位置。

示例 1：

```
输入：head = [1,4,3,2,5,2], x = 3
输出：[1,2,2,4,3,5]
```

示例 2：

```
输入：head = [2,1], x = 2
输出：[1,2]
```


提示：

链表中节点的数目在范围 [0, 200] 内
-100 <= Node.val <= 100
-200 <= x <= 200

## 2 解法

### 2.1 快慢指针+插入删除结点

如果当前结点的值小于x，就根据当前结点的值域创建一个新结点，插入到慢指针后面，同时删除当前结点。

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
 *         this.next = next; 
 *     }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode sentinel = new ListNode(-101, head);

        ListNode slow = sentinel;
        ListNode pred = sentinel;
        ListNode fast = head;

        while (fast != null) {
            if (fast.val < x) {
                ListNode insertNode = new ListNode(fast.val);
                ListNode succ = slow.next;

                slow.next = insertNode;
                insertNode.next = succ;

                fast = fast.next;
                if (pred == slow) {
                    pred = pred.next;
                }
                pred.next = fast;

                slow = slow.next;
            } else {
                fast = fast.next;
                pred = pred.next;
            }
        }

        return sentinel.next;
    }
}
```

复杂度分析：

1. 时间复杂度：遍历n个结点，每次的插入、删除操作都花费O(1)，因此，总时间复杂度为**O(n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。

### 2.2 分别收集链表的两部分

分别创建两个头结点，一个所在链表用于收集小于x的结点，另一个所在链表用于收集大于等于x的结点。之后，将用于收集小于x的结点的next指针指向用于收集大于等于x的结点的首结点，再将末尾置空即可。

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
 *         this.next = next; 
 *     }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode lessHead = new ListNode(-101);
        ListNode less = lessHead;
        ListNode largerHead = new ListNode(-101);
        ListNode larger = largerHead;

        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = less.next;
            } else {
                larger.next = head;
                larger = larger.next;
            }

            head = head.next;
        }

        less.next = largerHead.next;
        larger.next = null;

        return lessHead.next;
    }
}
```

复杂度分析：

1. 时间复杂度：遍历n个结点，每次更新两个不同链表的next指针花费O(1)，因此，总时间复杂度为**O(n)**；
2. 空间复杂度：新建了两个链表的头结点，后面都是在更改原链表中结点的连接关系，只是改变了链表结构。因此，仅花费常数个额外空间，故空间复杂度为**O(1)**。

