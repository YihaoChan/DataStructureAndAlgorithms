# 第101题 对称二叉树

## 1 题目

给定一个二叉树，检查它是否是镜像对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
```
    1
   / \
  2   2
 / \ / \
3  4 4  3
```

但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
```
   1
  / \
 2   2
  \   \
   3   3
```

## 2 解法

### 2.1 递归

如果是空树或者只有一个结点的树，直接返回true；

否则，比较当前结点的两个子结点：

1. 如果不是同时非空，直接返回false；
2. 如果同时为空，直接返回true；
3. 如果都非空，先判断它们之间是否相等，再递归往下判断左子结点和右子结点以及右子结点和左子结点。

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
    bool isSymmetric(TreeNode* root) {
        if (!root) {
            return true;
        }

        return isSymmetric(root->left, root->right);
    }

    bool isSymmetric(TreeNode* leftRoot, TreeNode* rightRoot) {
        if (!leftRoot && !rightRoot) {
            return true;
        }

        if (!leftRoot ^ !rightRoot) {
            return false;
        }

        if (leftRoot->val != rightRoot->val) {
            return false;
        }

        bool outerRes = isSymmetric(leftRoot->left, rightRoot->right);
        bool innerRes = isSymmetric(leftRoot->right, rightRoot->left);

        return outerRes && innerRes;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点都递归了一次，故时间复杂度为**O(n)**；
2. 空间复杂度：不同于其他递归题目的空间复杂度为树深度，这道题对每一层的所有结点都同时执行了递归，而不是每一层执行一个递归，故空间复杂度为**O(n)**。

### 2.2 迭代

层序遍历。

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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            if ((node1 == null) ^ (node2 == null)) {
                return false;
            }

            if (node1 == null && node2 == null) {
                continue;
            }
            
            if (node1.val != node2.val) {
                return false;
            }

            queue.offer(node1.left);
            queue.offer(node2.right);

            queue.offer(node1.right);
            queue.offer(node2.left);
        }

        return true;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都递归了一次，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。