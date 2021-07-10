# 第501题 二叉搜索树中的众数

## 1 题目

给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

假定 BST 有如下定义：

结点左子树中所含结点的值小于等于当前结点的值
结点右子树中所含结点的值大于等于当前结点的值
左子树和右子树都是二叉搜索树
例如：
给定 BST [1,null,2,2],

```
   1
    \
     2
    /
   2
```


返回[2].

提示：如果众数超过1个，不需考虑输出顺序

进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）

## 2 解法

中序遍历有序。

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
    vector<int> findMode(TreeNode* root) {
        vector<int> res;
        int lastNum = INT_MIN;
        int maxTime = 0;
        int count = 0;
        inorder(root, lastNum, maxTime, count, res);
        return res;
    }
private:
    void inorder(
        TreeNode* root, int& lastNum, int& maxTime, int& count, vector<int>& res
    ) {
        if (root == nullptr) {
            return;
        }

        inorder(root->left, lastNum, maxTime, count, res);

        if (root->val == lastNum) {
            ++count;
        } else {
            lastNum = root->val;
            count = 1;
        }
        
        if (count > maxTime) {
            maxTime = count;
            res.clear();
            res.push_back(root->val);
        } else if (count == maxTime) {
            res.push_back(root->val);
        }
        
        inorder(root->right, lastNum, maxTime, count, res);
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点都被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为树的深度O(logn)，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。

