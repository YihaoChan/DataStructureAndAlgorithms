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

分别收集链表的两部分。

分别创建两个头结点，一个所在链表用于收集小于x的结点，另一个所在链表用于收集大于等于x的结点。最后，将用于收集小于x的结点的next指针指向用于收集大于等于x的结点的首结点，再将末尾置空即可。

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
    ListNode* partition(ListNode* head, int x) {
        if (head == nullptr) {
            return nullptr;
        }

        ListNode* lessDummy = new ListNode(-101);
        ListNode* largerDummy = new ListNode(-101);
        ListNode* lessNode = lessDummy;
        ListNode* largerNode = largerDummy;

        while (head != nullptr) {
            if (head->val < x) {
                lessNode->next = head;
                lessNode = lessNode->next;
            } else {
                largerNode->next = head;
                largerNode = largerNode->next;
            }
            head = head->next;
        }

        lessNode->next = largerDummy->next;
        largerNode->next = nullptr;

        delete largerDummy;
        largerDummy = nullptr;
        
        return lessDummy->next;
    }
};
```

复杂度分析：

1. 时间复杂度：遍历n个结点，每次更新两个不同链表的next指针花费O(1)，因此，总时间复杂度为**O(n)**；
2. 空间复杂度：新建了两个链表的头结点，后面都是在更改原链表中结点的连接关系，只是改变了链表结构。因此，仅花费常数个额外空间，故空间复杂度为**O(1)**。

