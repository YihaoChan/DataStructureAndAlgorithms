# 第1367题 二叉树中的列表

## 1 题目

给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。

如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。

一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。

示例 1：

![1367-题图1](images/1367-题图1.png)

输入：head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
输出：true
解释：树中蓝色的节点构成了与链表对应的子路径。
示例 2：

![1367-题图2](images/1367-题图2.png)

输入：head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
输出：true
示例 3：

输入：head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
输出：false
解释：二叉树中不存在一一对应链表的路径。

## 2 解法

每次都要从当前结点检查整个链表，即根据链表结点和从当前结点出发的子结点们判断是否一一匹配成功。如果匹配失败，就根据当前结点的左右结点继续匹配。（不用怀疑，时间复杂度就是有点高...）

![图解](images/图解.jpg)

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
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    bool isSubPath(ListNode* head, TreeNode* root) {
        if (root == nullptr) {
            return false;
        }

        return dfs(head, root) ||
               isSubPath(head, root->left) ||
               isSubPath(head, root->right);
    }

    bool dfs(ListNode* head, TreeNode* root) {
        if (head == nullptr) {
            return true;
        }

        if (root == nullptr) {
            return false;
        }

        if (head->val != root->val) {
            return false;
        }

        bool leftRes = dfs(head->next, root->left);
        bool rightRes = dfs(head->next, root->right);

        return leftRes || rightRes;
    }
};
```

复杂度分析：

设树有n个结点，链表有m个结点。

1. 时间复杂度：最坏情况下，对于树的每一个结点，在搜索链表时都搜索到链表的最后一个结点处发现不匹配，故时间复杂度为**O(n · m)**；
2. 空间复杂度：递归栈空间为二叉树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。