# 第234题 回文链表

## 1 题目

请判断一个链表是否为回文链表。

示例 1:

```
输入: 1->2
输出: false
```

示例 2:

```
输入: 1->2->2->1
输出: true
```

## 2 解法

### 2.1 迭代

找到中点的前一个结点，将中点后面进行反转，然后分别从整个链表的前半部分和后半部分开始遍历，比较结点的值是否相同。

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
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        slow.next = reverseList(slow.next);

        ListNode former = head;
        ListNode latter = slow.next;

        while (latter != null) {
            if (former.val != latter.val) {
                return false;
            }

            former = former.next;
            latter = latter.next;
        }

        slow.next = reverseList(slow.next);
        return true;
    }

    private ListNode reverseList(ListNode head) {
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

1. 时间复杂度：找中点花费O(n / 2)，反转中点后半部分花费O(n / 2)，比较前半部分和后半部分花费O(n / 2)，还原链表花费O(n / 2)，故时间复杂度为**O(n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。

### 2.2 递归

当递归返回时，让一个新结点从头出发，和当前回溯到的结点的值相比较，判断是否相等。同时，还要将之前的结果记录下来，确保每一对匹配都是相等的。

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
    private ListNode former;

    public boolean isPalindrome(ListNode head) {
        former = head;
        return check(head);
    }

    private boolean check(ListNode head) {
        if (head == null) {
            return true;
        }

        boolean res = check(head.next);

        res = res && former.val == head.val;

        former = former.next;
        
        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：递归时每个结点都被访问一遍，从头出发的结点也访问了所有结点，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为结点个数，故空间复杂度为**O(n)**。