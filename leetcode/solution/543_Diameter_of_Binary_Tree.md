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

```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int res = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        getDiameter(root);

        return res;
    }

    private int getDiameter(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = getDiameter(root.left);
        int rightDepth = getDiameter(root.right);

        res = Math.max(res, leftDepth + rightDepth);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点均被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。

