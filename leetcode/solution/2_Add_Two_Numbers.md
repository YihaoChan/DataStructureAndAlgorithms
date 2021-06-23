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

```c++
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        if (l1 == nullptr) {
            return l2;
        } else if (l2 == nullptr) {
            return l1;
        }

        ListNode* sentinel = new ListNode(-1, nullptr);
        ListNode* node = sentinel;

        int sum = 0;
        int split = 0;

        expandDoubleLists(l1, l2, node, sum, split);

        if (l1 == nullptr) {
            expandSingleList(l2, node, sum, split);
        } else if (l2 == nullptr) {
            expandSingleList(l1, node, sum, split);
        }

        return sentinel->next;
    }

private:
    void expandDoubleLists(
        ListNode* &l1, ListNode* &l2, ListNode* &node, int &sum, int &split
    ) {
        while (l1 != nullptr && l2 != nullptr) {
            sum = l1->val + l2->val + split;
            if (sum >= 10) {
                split = 1;
                sum -= 10;
            } else {
                split = 0;
            }
            node->next = new ListNode(sum);
            node = node->next;
            l1 = l1->next;
            l2 = l2->next;
        }

        return;
    }
    
    void expandSingleList(
        ListNode* &list, ListNode* &node, int &sum, int &split
    ) {
        while (list != nullptr) {
            if (split == 0) {
                node->next = list;
                return;
            }
            sum = list->val + split;
            if (sum >= 10) {
                split = 1;
                sum -= 10;
            } else {
                split = 0;
            }
            node->next = new ListNode(sum);
            node = node->next;
            list = list->next;
        }

        if (split == 1) {
            node->next = new ListNode(1);
        }
        
        return;
    }
};
```

复杂度分析：

假设链表1有m个元素，链表2有n个元素：

1. 时间复杂度：时间复杂度为**O(max(m, n))**；
2. 空间复杂度：m位和n位的数相加，和最多不超过max(m, n) + 1位，故空间复杂度为**O(max(m, n))**。