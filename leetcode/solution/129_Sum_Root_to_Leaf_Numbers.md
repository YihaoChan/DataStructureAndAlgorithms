# 第129题 求根节点到叶节点数字之和

## 1 题目

给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
每条从根节点到叶节点的路径都代表一个数字：

例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
计算从根节点到叶节点生成的 所有数字之和 。

叶节点 是指没有子节点的节点。

示例 1：

![129-题图1](images/129-题图1.jpg)

输入：root = [1,2,3]
输出：25
解释：
从根到叶子节点路径 1->2 代表数字 12
从根到叶子节点路径 1->3 代表数字 13
因此，数字总和 = 12 + 13 = 25
示例 2：

![129-题图2](images/129-题图2.jpg)


输入：root = [4,9,0,5,1]
输出：1026
解释：
从根到叶子节点路径 4->9->5 代表数字 495
从根到叶子节点路径 4->9->1 代表数字 491
从根到叶子节点路径 4->0 代表数字 40
因此，数字总和 = 495 + 491 + 40 = 1026


提示：

树中节点的数目在范围 [1, 1000] 内
0 <= Node.val <= 9
树的深度不超过 10

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
    int sumNumbers(TreeNode* root) {
        int sum = 0;
        getSum(root, sum, 0);
        return sum;
    }
private:
    void getSum(TreeNode* root, int& sum, int temp) {
        if (root == nullptr) {
            return;
        }

        temp = 10 * temp + root->val;
        if (root->left == nullptr && root->right == nullptr) {
            sum += temp;
        }

        getSum(root->left, sum, temp);
        getSum(root->right, sum, temp);

        return;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为树的深度，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。

### 2.2 迭代

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
    int sumNumbers(TreeNode* root) {
        if (root == nullptr) {
            return 0;
        }

        queue<TreeNode*> q;
        queue<int> s;
        q.push(root);
        s.push(0);
        int sum = 0;

        while (!q.empty()) {
            int count = q.size();
            while (count > 0) {
                TreeNode* node = q.front();
                int currSum = s.front();
                q.pop();
                s.pop();
                
                int temp = currSum * 10 + node->val;
                if (node->left == nullptr && node->right == nullptr) {
                    sum += temp;
                }

                if (node->left != nullptr) {
                    q.push(node->left);
                    s.push(temp);
                }
                if (node->right != nullptr) {
                    q.push(node->right);
                    s.push(temp);
                }

                --count;
            }
        }

        return sum;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：每个队列中最多结点数为树的最深层结点数，故空间复杂度为**O(2<sup>h-1</sup>)**。

