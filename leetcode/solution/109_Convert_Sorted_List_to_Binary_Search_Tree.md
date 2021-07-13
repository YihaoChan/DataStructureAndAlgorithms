# 第109题 有序链表转换二叉搜索树

## 1 题目

给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:

给定的有序链表： [-10, -3, 0, 5, 9],

一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：

        0
       / \
     -3   9
     /   /
    -10  5
## 2 解法

可以选择中间数字作为二叉搜索树的根节点，这样分给左右子树的数字个数相同或只相差1，可以使得树保持平衡。

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
    TreeNode* sortedListToBST(ListNode* head) {
        return buildBST(head, nullptr);
    }
private:
    TreeNode* buildBST(ListNode* start, ListNode* end) {
        if (start == end) {
            return nullptr;
        }

        ListNode* mid = start;
        ListNode* fast = start;
        while (fast != end && fast->next != end) {
            mid = mid->next;
            fast = fast->next->next;
        }

        TreeNode* root = new TreeNode(mid->val);
        root->left = buildBST(start, mid);
        root->right = buildBST(mid->next, end);

        return root;
    }
};
```

复杂度分析：

1. 时间复杂度：对于n个元素的链表，共创建n个结点，每次寻找中间结点时花费O(logn)[每次都经过二分后再查找]，故时间复杂度为**O(nlogn)**；
2. 空间复杂度：递归空间复杂度为递归树深度，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。其中，n为树的结点总个数。