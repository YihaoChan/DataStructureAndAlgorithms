# 第108题 将有序数组转换为二叉搜索树

## 1 题目

给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。

高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。

示例 1：

![108-题图1](images/108-题图1.jpg)

输入：nums = [-10,-3,0,5,9]
输出：[0,-3,9,-10,null,5]

![108-题图2](images/108-题图2.jpg)

解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：

示例 2：

![108-题图3](images/108-题图3.jpg)


输入：nums = [1,3]
输出：[3,1]
解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。


提示：

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums 按 严格递增 顺序排列

## 2 解法

可以选择中间数字作为二叉搜索树的根节点，这样分给左右子树的数字个数相同或只相差1，可以使得树保持平衡。

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
    TreeNode* sortedArrayToBST(vector<int>& nums) {
        return sortedArrayToBST(nums, 0, nums.size() - 1);
    }
private:
    TreeNode* sortedArrayToBST(vector<int>& nums, int low, int high) {
        if (low > high) {
            return nullptr;
        }

        int mid = low + (high - low) / 2;
        TreeNode* root = new TreeNode(nums[mid]);
        root->left = sortedArrayToBST(nums, low, mid - 1);
        root->right = sortedArrayToBST(nums, mid + 1, high);

        return root;
    }
};
```

复杂度分析：

1. 时间复杂度：对于n个元素的数组，共创建n个结点，故时间复杂度为**O(n)**；
2. 空间复杂度：递归空间复杂度为递归树深度，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。其中，n为树的结点总个数。