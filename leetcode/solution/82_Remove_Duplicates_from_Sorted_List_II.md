# 第82题 删除排序链表中的重复元素 II

## 1 题目

存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。

返回同样按升序排列的结果链表。

示例 1：

```
输入：head = [1,2,3,3,4,4,5]
输出：[1,2,5]
```

示例 2：

```
输入：head = [1,1,1,2,3]
输出：[2,3]
```

**提示：**

- 链表中节点数目在范围 `[0, 300]` 内
- `-100 <= Node.val <= 100`
- 题目数据保证链表已经按升序排列

## 2 解法

快慢指针。当快指针的值和快指针下一个结点的值相等时，**记住**这个值，移动快指针，直到快指针的值不等于所记录的值为止。之后，将慢指针直接指向快指针，相当于删除掉中间有重复值的结点。

注意：当快指针的值和快指针下一个结点的值相等时，慢指针需要**留在原地**，之后才能完成真正的删除结点。

此外，构造头结点时，为防止头结点的值和链表的结点的值冲突，导致出现重复的情况，应该将头结点的值赋为链表值域的最小值再减1。

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
    ListNode* deleteDuplicates(ListNode* head) {
        if (head == nullptr) {
            return nullptr;
        }

        ListNode* dummy = new ListNode(-101, head);
        ListNode* curr = dummy;

        while (head != nullptr && head->next != nullptr) {
            if (head->val == head->next->val) {
                int memo = head->val;
                while (head != nullptr && head->val == memo) {
                    head = head->next;
                }
                curr->next = head;
            } else {
                head = head->next;
                curr = curr->next;
            }
        }

        return dummy->next;
    } 
};
```

复杂度分析：

1. 时间复杂度：快指针移动n个元素，慢指针最多n个元素，故时间复杂度为**O(n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。