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
    ListNode* rotateRight(ListNode* head, int k) {
        if (head == nullptr) {
            return head;
        }

        ListNode* lastOne = head;
        int len = 1;
        while (lastOne->next != nullptr) {
            ++len;
            lastOne = lastOne->next;
        }

        int lastK = k % len + 1;
        int frontStep = len - lastK;

        ListNode* prev = head;
        while (frontStep > 0) {
            prev = prev->next;
            --frontStep;
        }

        lastOne->next = head;
        ListNode* newHead = prev->next;
        prev->next = nullptr;

        return newHead;
    }
};
```

复杂度分析：

1. 时间复杂度：最坏情况下需要遍历两次链表，故时间复杂度为**O(n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。



