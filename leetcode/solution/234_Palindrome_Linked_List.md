# 第234题 回文链表

## 1 题目

请判断一个链表是否为回文链表。

示例 1:

```
输入: 1->2
输出: false
```

示例 2:

```
输入: 1->2->2->1
输出: true
```

## 2 解法

### 2.1 迭代

找到中点的前一个结点，将中点后面进行反转，然后分别从整个链表的前半部分和后半部分开始遍历，比较结点的值是否相同。

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
    bool isPalindrome(ListNode* head) {
        if (head == nullptr) {
            return true;
        }

        ListNode* slow = head;
        ListNode* fast = head;

        while (fast->next != nullptr && fast->next->next != nullptr) {
            slow = slow->next;
            fast = fast->next->next;
        }

        slow->next = reverseList(slow->next);

        ListNode* formerStart = head;
        ListNode* latterStart = slow->next;

        while (latterStart != nullptr) {
            if (formerStart->val != latterStart->val) {
                return false;
            }
            formerStart = formerStart->next;
            latterStart = latterStart->next;
        }

        slow->next = reverseList(slow->next);

        return true;
    }

private:
    ListNode* reverseList(ListNode* head) {
        if (head == nullptr) {
            return nullptr;
        }

        ListNode* prev = nullptr;
        ListNode* curr = head;

        while (curr != nullptr) {
            ListNode* succ = curr->next;
            curr->next = prev;
            prev = curr;
            curr = succ;
        }

        return prev;
    }
};
```

复杂度分析：

1. 时间复杂度：找中点花费O(n / 2)，反转中点后半部分花费O(n / 2)，比较前半部分和后半部分花费O(n / 2)，还原链表花费O(n / 2)，故时间复杂度为**O(n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。

### 2.2 递归

当递归返回时，让一个新结点从头出发，和当前回溯到的结点的值相比较，判断是否相等。同时，还要将之前的结果记录下来，确保每一对匹配都是相等的。

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
    bool isPalindrome(ListNode* head) {
        _start = head;
        return check(head);
    }

private:
    ListNode* _start = nullptr;

    bool check(ListNode* head) {
        if (head == nullptr) {
            return true;
        }

        bool res = check(head->next);

        if (head != nullptr) {
            res = res && (head->val == _start->val);
            _start = _start->next;
        }

        return res;
    }
};
```

复杂度分析：

1. 时间复杂度：递归时每个结点都被访问一遍，从头出发的结点也访问了所有结点，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为结点个数，故空间复杂度为**O(n)**。