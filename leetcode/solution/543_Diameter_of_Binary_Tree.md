# 第543题 二叉树的直径

## 1 题目

给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。

示例 :
给定二叉树

          1
         / \
        2   3
       / \     
      4   5    
返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。

注意：两结点之间的路径长度是以它们之间边的数目表示。

## 2 解法

求结点的左子树和右子树深度，然后相加，即为边的个数(直径)。

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
    int diameterOfBinaryTree(TreeNode* root) {
        int diameter = 0;

        getDepthDiameter(root, diameter);

        return diameter;
    }

private:
    int getDepthDiameter(TreeNode* root, int &diameter) {
        if (root == nullptr) {
            return 0;
        }

        int leftDepth = getDepthDiameter(root->left, diameter);
        int rightDepth = getDepthDiameter(root->right, diameter);

        diameter = max(diameter, leftDepth + rightDepth);

        return max(leftDepth, rightDepth) + 1;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点均被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。

