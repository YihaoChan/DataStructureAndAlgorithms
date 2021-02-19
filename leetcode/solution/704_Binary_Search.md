# 第704题 二分查找

## 1 题目

给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。

示例 1:

```
输入: nums = [-1,0,3,5,9,12], target = 9
输出: 4
解释: 9 出现在 nums 中并且下标为 4
```

示例 2:

```
输入: nums = [-1,0,3,5,9,12], target = 2
输出: -1
解释: 2 不存在 nums 中因此返回 -1
```

## 2 解法

最先想到的最简单的方法肯定是顺序查找，花费线性时间。但是顺序查找会浪费题目的**有序**这个条件，所以要用二分查找。

```
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int item = nums[mid];

            if (target < item) {
                right = mid - 1;
            } else if (target > item) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
```

复杂度分析：

1. 时间复杂度：二分查找时间复杂度为**O(logn)**；
2. 空间复杂度：只用到常数个额外空间，所以空间复杂度为**O(1)**。