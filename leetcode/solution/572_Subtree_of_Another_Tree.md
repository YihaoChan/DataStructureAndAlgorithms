# 第572题 另一个树的子树

## 1 题目

给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。

示例 1:
给定的树 s:

       3
      / \
      4  5
     / \
    1   2
给定的树 t：

```
  4 
 / \
1   2
```


返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。

示例 2:
给定的树 s：

        3
       / \
      4   5
     / \
    1   2
        /
       0
给定的树 t：

```
  4
 / \
1   2
```


返回 false。

## 2 解法

### 2.1 深度优先搜索暴力匹配

类似第1367题，每次都要从树s的当前结点检查整棵树t，即根据树s的当前结点和从树t的根结点出发的子结点们判断是否一一匹配成功。如果匹配失败，就根据树s的当前结点的左右结点继续匹配。

```java
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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }

        return dfs(root, subRoot) ||
               isSubtree(root.left, subRoot) || 
               isSubtree(root.right, subRoot);
    }

    private boolean dfs(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        
        if ((root == null) ^ (subRoot == null)) {
            return false;
        }

        if (root.val != subRoot.val) {
            return false;
        }

        boolean leftRes = dfs(root.left, subRoot.left);
        boolean rightRes = dfs(root.right, subRoot.right);

        return leftRes && rightRes;
    }
}
```

复杂度分析：

设树s有n个结点，树t有m个结点。

1. 时间复杂度：最坏情况下，对于树s的每一个结点，在搜索树t时都搜索到树t的最后一个结点处发现不匹配，故时间复杂度为**O(n · m)**；
2. 空间复杂度：递归栈空间为二叉树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。