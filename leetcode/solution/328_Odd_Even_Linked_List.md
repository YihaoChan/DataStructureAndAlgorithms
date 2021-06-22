# 第328题 奇偶链表

## 1 题目

给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

示例 1:

```
输入: 1->2->3->4->5->NULL
输出: 1->3->5->2->4->NULL
```

示例 2:

```
输入: 2->1->3->5->6->4->7->NULL 
输出: 2->3->6->7->1->5->4->NULL
```


说明:

应当保持奇数节点和偶数节点的相对顺序。
链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。

## 2 解法

跳跃连接。

奇数位置的结点越过后继结点，达到奇数位置的结点之间相连的效果。同时，步进奇数位置的结点之后，偶数位置的结点越过后继结点，达到偶数位置的结点之间相连的效果。最后，奇数位置的结点指向偶数位置的结点链表的首结点即可。

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
    ListNode* oddEvenList(ListNode* head) {
        if (head == nullptr) {
            return head;
        }

        ListNode* odd = head;
        ListNode* even = head->next;
        ListNode* evenHead = even;

        while (even != nullptr && even->next != nullptr) {
            odd->next = even->next;
            odd = odd->next;
            even->next = odd->next;
            even = even->next;
        }

        odd->next = evenHead;

        return head;
    }
};
```

复杂度分析：

1. 时间复杂度：遍历n个结点，每次更新链表两个不同部分的next指针花费O(1)，因此，总时间复杂度为**O(n)**；
2. 空间复杂度：在原链表中更改结点的连接关系，只是改变了链表结构，并没有新创造结点。因此，仅花费常数个额外空间，故空间复杂度为**O(1)**。