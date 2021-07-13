# 第654题 最大二叉树

## 1 题目

给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：

二叉树的根是数组 nums 中的最大元素。
左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
返回有给定数组 nums 构建的 最大二叉树 。

示例 1：

![654-题图1](images/654-题图1.jpg)


输入：nums = [3,2,1,6,0,5]
输出：[6,3,5,null,2,0,null,null,1]
解释：递归调用如下所示：
- [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
    - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
        - 空数组，无子节点。
        - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
            - 空数组，无子节点。
            - 只有一个元素，所以子节点是一个值为 1 的节点。
    - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
        - 只有一个元素，所以子节点是一个值为 0 的节点。
        - 空数组，无子节点。

示例 2：

![654-题图2](images/654-题图2.jpg)


输入：nums = [3,2,1]
输出：[3,null,2,null,1]


提示：

1 <= nums.length <= 1000
0 <= nums[i] <= 1000
nums 中的所有整数 互不相同

## 2 解法

找到当前数组中的最大值，然后分别递归往左、往右继续寻找当前最大值，并创建结点。

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
    TreeNode* constructMaximumBinaryTree(vector<int>& nums) {
        return buildTree(nums, 0, nums.size() - 1);
    }
private:
    TreeNode* buildTree(vector<int>& nums, int low, int high) {
        if (low > high) {
            return nullptr;
        }

        int maxValue = INT_MIN;
        int maxValueIndex = -1;
        for (int i = low; i <= high; ++i) {
            int num = nums[i];
            if (num > maxValue) {
                maxValue = num;
                maxValueIndex = i;
            }
        }

        TreeNode* root = new TreeNode(maxValue);
        root->left = buildTree(nums, low, maxValueIndex - 1);
        root->right = buildTree(nums, maxValueIndex + 1, high);

        return root;
    }
};
```

复杂度分析：

1. 时间复杂度：对于n个元素的数组，共创建n个结点。每创建一个结点时，就要从头到尾扫描一遍数组，故时间复杂度为**O(n<sup>2</sup>)**；
2. 空间复杂度：递归空间复杂度为递归树深度，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。其中，n为树的结点总个数。