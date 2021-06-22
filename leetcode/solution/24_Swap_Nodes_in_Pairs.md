# 第24题 两两交换链表中的节点

## 1 题目

给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例 1：

```
输入：head = [1,2,3,4]
输出：[2,1,4,3]
```

示例 2：

```
输入：head = []
输出：[]
```

示例 3：

```
输入：head = [1]
输出：[1]
```

## 2 解法

### 2.1 迭代

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
    ListNode* swapPairs(ListNode* head) {
        ListNode* sentinel = new ListNode(-1, head);
        ListNode* prev = sentinel;
        ListNode* curr = head;

        while (curr != nullptr && curr->next != nullptr) {
            ListNode* nextNode = curr->next;
            ListNode* succ = nextNode->next;
            nextNode->next = curr;
            curr->next = succ;
            prev->next = nextNode;
            prev = curr;
            curr = succ;
        }

        return sentinel->next;
    }
};
```

典型用例：[1, 2, 3]，考虑步进后curr == 3、nextNode == nullptr的情况，此时直接跳出。

复杂度分析：

1. 时间复杂度：遍历每个结点，最坏情况下每个结点都进行O(1)的修改指针操作，故时间复杂度为**O(n)**；
2. 空间复杂度：仅用到常数个额外空间，故空间复杂度为**O(1)**。

### 2.2 递归

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
    ListNode* swapPairs(ListNode* head) {
        if (head == nullptr) {
            return head;
        }

        ListNode* curr = head;
        ListNode* nextNode = curr->next;
        if (nextNode == nullptr) {
            return curr;
        }
        ListNode* succ = nextNode->next;

        nextNode->next = curr;
        curr->next = swapPairs(succ);

        return nextNode;
    }
};
```

复杂度分析：

1. 时间复杂度：遍历每个结点，最坏情况下每个结点都进行O(1)的修改指针操作，故时间复杂度为**O(n)**；
2. 空间复杂度：递归n层，每一层花费常数个额外空间，故空间复杂度为**O(n)**。

