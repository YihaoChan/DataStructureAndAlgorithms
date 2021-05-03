# 第94题 二叉树的中序遍历

## 1 题目

给定一个二叉树的根节点 root ，返回它的 中序 遍历。

示例 1：

![94-题图1](images/94-题图1.jpg)


输入：root = [1,null,2,3]
输出：[1,3,2]
示例 2：

输入：root = []
输出：[]
示例 3：

输入：root = [1]
输出：[1]
示例 4：

![94-题图4](images/94-题图4.jpg)

输入：root = [1,2]
输出：[2,1]
示例 5：

![94-题图5](images/94-题图5.jpg)

输入：root = [1,null,2]
输出：[1,2]

## 2 解法

### 2.1 递归

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
    List<Integer> res = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return res;
        }

        inorderTraversal(root.left);
        res.add(root.val);
        inorderTraversal(root.right);

        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为二叉树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。

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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<>();

        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            res.add(node.val);
            node = node.right;
        }

        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：栈中最多结点数为二叉树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。

