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

初始时，慢指针指向哨兵结点，而快指针指向head，这样子当快指针为null时，慢指针就指向了倒数第k个结点的前驱结点。

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
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        if (head == nullptr) {
            return nullptr;
        }

        ListNode* sentinel = new ListNode(-1,head);
        ListNode* slow = sentinel;
        ListNode* fast = head;

        while (n > 0) {
            fast = fast->next;
            --n;
        }

        while (fast != nullptr) {
            slow = slow->next;
            fast = fast->next;
        }

        slow->next = slow->next->next;

        return sentinel->next;
    }
};
```

复杂度分析：

1. 时间复杂度：快指针移动n次，慢指针移动n - k次，故总时间复杂度为**O(n)**；
2. 空间复杂度：仅用到常数个额外空间，故空间复杂度为**O(1)**。

