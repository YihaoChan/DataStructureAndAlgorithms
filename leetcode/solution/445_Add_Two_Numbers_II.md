# 第445题 两数相加 II

## 1 题目

给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。

你可以假设除了数字 0 之外，这两个数字都不会以零开头。

示例：

```
输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 8 -> 0 -> 7
```

## 2 解法

逆序处理，从后面的位开始相加，一直加到前面的位。对于逆序处理，应该用栈。

### 2.1 三个栈

遍历l1和l2并将每一位压入栈，需要2个栈。构造求和结果的栈，并将结果逐位弹出构造链表，需要1个栈。

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> l1Stack = new Stack<>();
        Stack<Integer> l2Stack = new Stack<>();

        // 遍历l1、l2
        ListNode ptr1 = l1;
        ListNode ptr2 = l2;

        while (ptr1 != null) {
            l1Stack.push(ptr1.val);
            ptr1 = ptr1.next;
        } 

        while (ptr2 != null) {
            l2Stack.push(ptr2.val);
            ptr2 = ptr2.next;
        }

        Stack<Integer> resStack = new Stack<>();

        int sum = 0;
        int spill = 0;

        int l1Item = 0;
        int l2Item = 0;

        while (!l1Stack.isEmpty() && !l2Stack.isEmpty()) {
            l1Item = l1Stack.pop();
            l2Item = l2Stack.pop();

            sum = l1Item + l2Item + spill;

            if (sum >= 10) {
                sum -= 10;
                spill = 1;
            } else {
                spill = 0;
            }

            resStack.push(sum);
        }

        // l1比l2位数多
        while (!l1Stack.isEmpty()) {
            l1Item = l1Stack.pop();

            sum = l1Item + spill;

            if (sum >= 10) {
                sum -= 10;
                spill = 1;
            } else {
                spill = 0;
            }

            resStack.push(sum);
        }

        // l2比l1位数多
        while (!l2Stack.isEmpty()) {
            l2Item = l2Stack.pop();

            sum = l2Item + spill;

            if (sum >= 10) {
                sum -= 10;
                spill = 1;
            } else {
                spill = 0;
            }

            resStack.push(sum);
        }

        // l1和l2都全部弹出
        if (l1Stack.isEmpty() && l2Stack.isEmpty()) {
            if (spill == 1) {
                resStack.push(spill);
            }
        }

        ListNode sentinel = new ListNode(-1);
        sentinel.next = null;

        ListNode resNode = sentinel;

        while (!resStack.isEmpty()) {
            int digit = resStack.pop();

            resNode.next = new ListNode(digit);
            resNode = resNode.next;
            resNode.next = null;
        }

        return sentinel.next;
    }
}
```

复杂度分析：

假设链表1有m个元素，链表2有n个元素：

1. 时间复杂度：

   1. 遍历链表1和链表2并将它们每一位压入栈，花费O(max(m, n))；
   2. 将栈1和栈2中的元素逐个弹出，并求和，压入求和结果的栈，花费O(max(m, n))；
   3. 将求和结果的栈逐位弹出，并构造链表，花费O(max(m, n))。

   因此，总时间复杂度为**O(max(m, n))**。

2. 空间复杂度：

   1. 遍历链表1和链表2并将它们每一位压入栈，需要构造两个栈，花费O(m + n)的空间；
   2. 两数相加的和的位数最多不超过max(m, n) + 1，因此构造求和结果的栈需要花费O(max(m, n))的空间；
   3. 将求和结果的栈逐位弹出，并构造链表，构造链表需要花费O(max(m, n))的空间。

   因此，总空间复杂度为**O(2 * max(m, n) + (m + n)) = O(m + n)**。

### 2.2 两个栈

上述方法在最后构造求和结果并将结果弹出形成链表时，需要额外创建一个栈。可优化为：直接构造链表，实现"从后往前"形成结果链表，从而不需要额外创建一个栈。

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> l1Stack = new Stack<>();
        Stack<Integer> l2Stack = new Stack<>();

        // 遍历l1、l2
        ListNode ptr1 = l1;
        ListNode ptr2 = l2;

        while (ptr1 != null) {
            l1Stack.push(ptr1.val);
            ptr1 = ptr1.next;
        } 

        while (ptr2 != null) {
            l2Stack.push(ptr2.val);
            ptr2 = ptr2.next;
        }

        int sum = 0;
        int spill = 0;

        int l1Item = 0;
        int l2Item = 0;

        ListNode res = null;
        ListNode temp = null;

        while (!l1Stack.isEmpty() && !l2Stack.isEmpty()) {
            l1Item = l1Stack.pop();
            l2Item = l2Stack.pop();

            sum = l1Item + l2Item + spill;

            if (sum >= 10) {
                sum -= 10;
                spill = 1;
            } else {
                spill = 0;
            }

            temp = new ListNode(sum, res);
            res = temp;
        }

        // l1比l2位数多
        while (!l1Stack.isEmpty()) {
            l1Item = l1Stack.pop();

            sum = l1Item + spill;

            if (sum >= 10) {
                sum -= 10;
                spill = 1;
            } else {
                spill = 0;
            }

            temp = new ListNode(sum, res);
            res = temp;
        }

        // l2比l1位数多
        while (!l2Stack.isEmpty()) {
            l2Item = l2Stack.pop();

            sum = l2Item + spill;

            if (sum >= 10) {
                sum -= 10;
                spill = 1;
            } else {
                spill = 0;
            }
            
            temp = new ListNode(sum, res);
            res = temp;
        }

        // l1和l2都全部弹出
        if (l1Stack.isEmpty() && l2Stack.isEmpty()) {
            if (spill == 1) {
                temp = new ListNode(spill, res);
                res = temp;
            }
        }

        return res;
    }
}
```

复杂度分析：

假设链表1有m个元素，链表2有n个元素：

1. 时间复杂度：

   1. 遍历链表1和链表2并将它们每一位压入栈，花费O(max(m, n))；
   2. 将栈1和栈2中的元素逐个弹出，并求和，构造结果链表，花费O(max(m, n))。

   因此，总时间复杂度为**O(max(m, n))**。

2. 空间复杂度：

   1. 遍历链表1和链表2并将它们每一位压入栈，需要构造两个栈，花费O(m + n)的空间；
   2. 将栈1和栈2中的元素逐个弹出，并求和，构造结果链表，花费O(max(m, n))的空间。

   因此，总空间复杂度为**O(1 * max(m, n) + (m + n)) = O(m + n)**。

