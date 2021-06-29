# 第103题 二叉树的锯齿形层序遍历

## 1 题目

给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树 [3,9,20,null,null,15,7],

      3
     / \
    9  20
       / \
      15  7

 返回锯齿形层序遍历如下：

```
[
  [3],
  [20,9],
  [15,7]
]
```

## 2 解法

层序遍历时，每一层结点采取链表结构：当奇数行时采用尾插法，当偶数行时采用头插法。

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
    vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
        vector<vector<int>> res;
        if (root == nullptr) {
            return res;
        }

        queue<TreeNode*> q;
        q.push(root);
        int levelCount = 1;

        while (!q.empty()) {
            int count = q.size();
            deque<int> level;

            while (count > 0) {
                TreeNode* node = q.front();
                q.pop();

                if (levelCount % 2 != 0) {
                    level.push_back(node->val);
                } else {
                    level.push_front(node->val);
                }

                if (node->left != nullptr) {
                    q.push(node->left);
                }
                if (node->right != nullptr) {
                    q.push(node->right);
                }

                --count;
            }

            if (level.size() != 0) {
                res.push_back(vector<int> {level.begin(), level.end()});
            }

            ++levelCount;
        }

        return res;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点入队、出队一次，链表头插、尾插花费O(1)，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。

