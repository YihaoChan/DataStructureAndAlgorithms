# 第35题 搜索插入位置

## 1 题目

给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。假设数组中无重复元素。

示例 1：

```
输入: [1,3,5,6], 5
输出: 2
```


示例 2：

```
输入: [1,3,5,6], 2
输出: 1
```


示例 3：

```
输入: [1,3,5,6], 7
输出: 4
```


示例 4：

```
输入: [1,3,5,6], 0
输出: 0
```

## 2 解法

注意**升序**排列，所以想到**二分查找**。当搜索不到target时跳出，此时left即为待插入位置。

```
class Solution {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;

        int left = 0;
        int right = len - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int item = nums[mid];

            if (target == item) {
                return mid;
            } else if (target < item) {
                right = mid - 1;
            } else if (target > item) {
                left = mid + 1;
            }
        }

        return left;
    }
}
```

复杂度分析：

1. 时间复杂度：二分查找时间复杂度为**O(logn)**；
2. 空间复杂度：只用到常数个额外空间，所以空间复杂度为**O(1)**。

