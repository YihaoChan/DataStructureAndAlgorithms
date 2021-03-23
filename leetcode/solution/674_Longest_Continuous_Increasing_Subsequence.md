# 第674题 最长连续递增序列

## 1 题目

给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。

连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。

示例 1：

```
输入：nums = [1,3,5,4,7]
输出：3
解释：最长连续递增序列是 [1,3,5], 长度为3。
尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。 
```

示例 2：

```
输入：nums = [2,2,2,2,2]
输出：1
解释：最长连续递增序列是 [2], 长度为1。
```

## 2 解法

双指针(左右指针)。

```
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int len = nums.length;

        if (len == 0) {
            return 0;
        }
        
        int subLen = 0;
        int maxLen = 0;

        int left = 0;
        int right = 0;

        for (right = 1; right < len; right++) {
            subLen = right - left;

            if (nums[right] <= nums[right - 1]) {
                if (subLen > maxLen) {
                    maxLen = subLen;
                }

                left = right;
            }
        }

        return Math.max(right - left, maxLen);
    }
}
```

复杂度分析：

1. 时间复杂度：从左到右顺序扫描数组，时间复杂度为**O(n)**；
2. 空间复杂度：仅用到常数个额外空间，故空间复杂度为**O(1)**。