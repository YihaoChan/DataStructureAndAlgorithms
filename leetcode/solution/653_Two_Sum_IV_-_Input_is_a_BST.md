# 第653题 两数之和 IV - 输入BST

## 1 题目

给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。

案例 1:

```
输入: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

输出: True
```

案例 2:

```
输入: 
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

输出: False
```

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
    private Set<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        boolean isCurrExists = set.contains(k - root.val);

        set.add(root.val);

        return isCurrExists || 
               findTarget(root.left, k) || 
               findTarget(root.right, k);
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为二叉树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。

### 2.2 迭代

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
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode dequeueNode = queue.poll();

            if (dequeueNode != null) {
                if (set.contains(k - dequeueNode.val)) {
                    return true;
                } else {
                    set.add(dequeueNode.val);
                }

                queue.offer(dequeueNode.left);
                queue.offer(dequeueNode.right);
            }
        }

        return false;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故总时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。

### 2.3 二叉搜索树性质

二叉搜索树的性质之一：中序遍历的结果按升序排列。故可以将中序排列的结果写到一个数组中，然后按照输入升序排列的数组的两数之和问题进行求解。

#### 2.3.1 递归

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
    private List<Integer> list;

    public boolean findTarget(TreeNode root, int k) {
        list = new ArrayList<>();

        inOrderTraverse(root);

        int left = 0;
        int right = list.size() - 1;

        while (left < right) {
            int sum = list.get(left) + list.get(right);

            if (sum > k) {
                right--;
            } else if (sum < k) {
                left++;
            } else if (sum == k) {
                return true;
            }
        }

        return false;
    }

    private void inOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrderTraverse(root.left);
        list.add(root.val);
        inOrderTraverse(root.right);

        return;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍花费O(n)，搜索两数之和等于目标值花费O(n)，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为二叉树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。

#### 2.3.2 迭代

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
    private List<Integer> list;

    public boolean findTarget(TreeNode root, int k) {
        list = new ArrayList<>();

        inOrderTraverse(root);

        int left = 0;
        int right = list.size() - 1;

        while (left < right) {
            int sum = list.get(left) + list.get(right);

            if (sum > k) {
                right--;
            } else if (sum < k) {
                left++;
            } else if (sum == k) {
                return true;
            }
        }

        return false;
    }

    private void inOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }

            node = stack.pop();
            list.add(node.val);
            node = node.right;
        }

        return;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍花费O(n)，搜索两数之和等于目标值花费O(n)，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为二叉树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。
