# 第617题 合并二叉树

## 1 题目

给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

示例 1:

输入: 

```
	    Tree 1                   Tree 2
          1                         2 
         / \                       / \ 
        3   2                     1   3
       /                           \   \
      5                             4   7 
```

输出: 
合并后的树:

```
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7
```

## 2 解法

### 2.1 递归

```c++
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
    TreeNode* mergeTrees(TreeNode* root1, TreeNode* root2) {
        TreeNode* newTree = root1 == nullptr ? root2 : root1;

        if (root1 == nullptr && root2 == nullptr) {
            return nullptr;
        } else if (root1 == nullptr) {
            return root2;
        } else if (root2 == nullptr) {
            return root1;
        } else if (root1 != nullptr && root2 != nullptr) {
            newTree->val = root1->val + root2->val;
        }
        
        newTree->left = mergeTrees(root1->left, root2->left);
        newTree->right = mergeTrees(root1->right, root2->right);

        return newTree;
    }
};
```

复杂度分析：

设树1有m个结点，树2有n个结点。

1. 时间复杂度：必须要两棵树的结点同时非空时，才能进行结点的值相加的操作，否则直接返回，不进行任何运算或操作。因此，被访问到的结点树不会超过较少结点的二叉树的结点数，故时间复杂度为**O(min(m, n))**；
2. 空间复杂度：递归空间复杂度为递归树深度，故空间复杂度为**O(logmin(m, n))**，最坏情况下树退化为链表，空间复杂度为O(min(m, n))。



