# 第1721题 交换链表中的节点

## 1 题目

给你链表的头节点 head 和一个整数 k 。

交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）。

示例 1：

```
输入：head = [1,2,3,4,5], k = 2
输出：[1,4,3,2,5]
```

示例 2：

```
输入：head = [7,9,6,6,7,8,3,0,9,5], k = 5
输出：[7,9,6,6,8,7,3,0,9,5]
```

示例 3：

```
输入：head = [1], k = 1
输出：[1]
```

示例 4：

```
输入：head = [1,2], k = 1
输出：[2,1]
```

示例 5：

```
输入：head = [1,2,3], k = 2
输出：[1,2,3]
```

## 2 解法

快慢指针。

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
    ListNode* swapNodes(ListNode* head, int k) {
        if (head == nullptr || head->next == nullptr) {
            return head;
        }

        ListNode* slow = head;
        ListNode* fast = head;
        ListNode* front = head;

        while (k > 0) {
            fast = fast->next;
            if (k > 1) {
                front = front->next;
            }
            --k;
        }

        while (fast != nullptr) {
            slow = slow->next;
            fast = fast->next;
        }

        int temp = slow->val;
        slow->val = front->val;
        front->val = temp;

        return head;
    }
};
```

复杂度分析：

1. 时间复杂度：快指针走n步，慢指针走n - k步，记录正数第k位置的指针走k步，故总时间复杂度为**O(n)**；
2. 空间复杂度：仅用到常数个额外空间，故空间复杂度为**O(1)**。

