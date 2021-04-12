# 第1669题 合并两个链表

## 1 题目

给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。

请你将 list1 中第 a 个节点到第 b 个节点删除，并将list2 接在被删除节点的位置。

下图中蓝色边和节点展示了操作后的结果：


请你返回结果链表的头指针。

示例 1：

```
输入：list1 = [0,1,2,3,4,5], a = 3, b = 4, list2 = [1000000,1000001,1000002]
输出：[0,1,2,1000000,1000001,1000002,5]
解释：我们删除 list1 中第三和第四个节点，并将 list2 接在该位置。上图中蓝色的边和节点为答案链表。
```

示例 2：

```
输入：list1 = [0,1,2,3,4,5,6], a = 2, b = 5, list2 = [1000000,1000001,1000002,1000003,1000004]
输出：[0,1,1000000,1000001,1000002,1000003,1000004,6]
解释：上图中蓝色的边和节点为答案链表。
```


提示：

3 <= list1.length <= 104
1 <= a <= b < list1.length - 1
1 <= list2.length <= 104

## 2 解法

分别找到两个链表的拼接首尾处。

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
    public ListNode mergeInBetween(
        ListNode list1, int a, int b, ListNode list2
    ) {
        ListNode sentinel = new ListNode(-1, list1);

        // 第a - 1个结点
        ListNode mergeStart = list1;

        for (int i = 0; i < a - 1; i++) {
            mergeStart = mergeStart.next;
        }

        // 第b个结点
        ListNode mergeEndPred = mergeStart;

        for (int j = a; j <= b; j++) {
            mergeEndPred = mergeEndPred.next;
        }

        ListNode mergeEnd = mergeEndPred.next; // 第b + 1个结点
        mergeEndPred.next = null; // 断开第b个结点与后继结点的连接
        
        ListNode list2Start = list2; 
        ListNode list2End = list2;

        while (list2End.next != null) {
            list2End = list2End.next;
        }

        mergeStart.next = list2Start;
        list2End.next = mergeEnd;

        return sentinel.next;
    }
}
```

复杂度分析：

记list1结点个数为m，list2结点个数为n。

1. 时间复杂度：找到list2的首结点和尾结点花费O(n)，最坏情况下b等于list1的最大长度，此时找到list1的尾结点花费O(m)，因此，总时间复杂度为**O(m + n)**；
2. 空间复杂度：仅用到常数个额外空间，故空间复杂度为**O(1)**。  