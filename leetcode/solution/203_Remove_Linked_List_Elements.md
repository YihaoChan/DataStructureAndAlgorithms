# 第203题 移除链表元素

## 1 题目

删除链表中等于给定值 val 的所有节点。

示例:

```
输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5
```

## 2 解法

定义一个哨兵结点作为头结点，其next指向第一个结点。slow初始为头结点，fast初始为第一个结点。最后返回头结点的next，即第一个结点。

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
    ListNode* removeElements(ListNode* head, int val) {
        if (head == nullptr) {
            return nullptr;
        }

        ListNode* sentinel = new ListNode(-1);
        ListNode* slow = sentinel;
        ListNode* fast = head;

        while (fast != nullptr) {
            if (fast->val != val) {
                slow->next = fast;
                slow = slow->next;
            }

            fast = fast->next;
        }

        slow->next = nullptr;
        
        return sentinel->next;
    }
};
```

复杂度分析：

1. 时间复杂度：顺序扫描花费**O(n)**；
2. 空间复杂度：只用到常数个额外空间，空间复杂度为**O(1)**。