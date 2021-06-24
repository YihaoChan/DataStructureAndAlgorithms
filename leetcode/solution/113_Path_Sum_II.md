# 第113题 路径总和 II

## 1 题目

给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点。

示例 1：

![113-题图1](images/113-题图1.jpg)

输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：[[5,4,11,2],[5,8,4,5]]
示例 2：

![113-题图2](images/113-题图2.jpg)


输入：root = [1,2,3], targetSum = 5
输出：[]
示例 3：

输入：root = [1,2], targetSum = 0
输出：[]

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
    vector<vector<int>> pathSum(TreeNode* root, int targetSum) {
        vector<vector<int>> res;
        vector<int> temp;
        addPath(root, targetSum, temp, res);
        return res;
    }
private:
    void addPath(
        TreeNode* root, int targetSum, vector<int> temp, vector<vector<int>> &res
    ) {
        if (root == nullptr) {
            return;
        }

        temp.push_back(root->val);

        if (root->left == nullptr && root->right == nullptr) {
            if (root->val == targetSum) {
                res.push_back(temp);
            }
        }

        addPath(root->left, targetSum - root->val, temp, res);
        addPath(root->right, targetSum - root->val, temp, res);

        return;
    }  
};
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n<sup>2<)**；
2. 空间复杂度：递归栈空间为树的深度O(logn)，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。

### 2.2 迭代-三队列

用一个队列存放结点，另一个队列存放差值，最后一个队列存放路径。当出队列的叶子结点的值等于差值时，将出队的路径添加进结果集中。

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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);

        Queue<Integer> sumQueue = new LinkedList<>();
        sumQueue.offer(targetSum);

        Queue<List<Integer>> pathQueue = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        pathQueue.offer(path);

        while (!nodeQueue.isEmpty()) {
            int count = nodeQueue.size();

            while (count > 0) {
                TreeNode dequeueNode = nodeQueue.poll();
                Integer dequeueSum = sumQueue.poll();
                List<Integer> dequeuePath = pathQueue.poll();

                Integer diff = dequeueSum - dequeueNode.val;

                if (dequeueNode.left != null) {
                    nodeQueue.offer(dequeueNode.left);
                    sumQueue.offer(diff);
                    path = new ArrayList<>(dequeuePath);
                    path.add(dequeueNode.left.val);
                    pathQueue.offer(path);
                }

                if (dequeueNode.right != null) {
                    nodeQueue.offer(dequeueNode.right);
                    sumQueue.offer(diff);
                    path = new ArrayList<>(dequeuePath);
                    path.add(dequeueNode.right.val);
                    pathQueue.offer(path);
                }

                if (dequeueNode.left == null && 
                    dequeueNode.right == null) {
                    if (dequeueNode.val == dequeueSum) {
                        res.add(dequeuePath);
                    }
                }

                count--;
            }
        }

        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：每个队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。

### 2.2 迭代-哈希表记录父结点

用一个队列存放结点，另一个队列存放差值，再用一个哈希表记录当前结点的父结点。当出队列的叶子结点的值等于差值时，不断寻找当前结点的父结点，直到根结点为止，再将路径添加到结果集中。

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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);

        Queue<Integer> sumQueue = new LinkedList<>();
        sumQueue.offer(targetSum);

        Map<TreeNode, TreeNode> parent = new HashMap<>();

        while (!nodeQueue.isEmpty()) {
            int count = nodeQueue.size();

            while (count > 0) {
                TreeNode dequeueNode = nodeQueue.poll();
                Integer dequeueSum = sumQueue.poll();

                Integer diff = dequeueSum - dequeueNode.val;

                if (dequeueNode.left != null) {
                    nodeQueue.offer(dequeueNode.left);
                    sumQueue.offer(diff);
                    parent.put(dequeueNode.left, dequeueNode);
                }

                if (dequeueNode.right != null) {
                    nodeQueue.offer(dequeueNode.right);
                    sumQueue.offer(diff);
                    parent.put(dequeueNode.right, dequeueNode);
                }
 
                if (dequeueNode.left == null && 
                    dequeueNode.right == null) {
                    if (dequeueNode.val == dequeueSum) {
                        res.add(findPath(dequeueNode, parent));
                    }
                }

                count--;
            }
        }

        return res;
    }

    private List<Integer> findPath(TreeNode node, 
                                   Map<TreeNode, TreeNode> map) {
        List<Integer> path = new ArrayList<>();

        while (node != null) {
            path.add(node.val);
            node = map.get(node);
        }

        Collections.reverse(path);

        return path;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：每个队列中最多结点数为树的最深层结点数，哈希表记录的结点个数为二叉树的总结点数，故空间复杂度为**O(n)**。

