# 剑指 Offer 53 - I. 在排序数组中查找数字 I

## 1 题目

统计一个数字在排序数组中出现的次数。

示例1：

```
输入: nums = [5,7,7,8,8,10], target = 8
输出: 2
```

示例2：

```
输入: nums = [5,7,7,8,8,10], target = 6
输出: 0
```

## 2 解法

寻找左边界和右边界，相减再加1即为相同数组出现的次数。

```
class Solution {
    public int search(int[] nums, int target) {
        int leftBorder = findLeftBorder(nums, target);
        int rightBorder = findRightBorder(nums, target);

        if (leftBorder == -1 || rightBorder == -1) {
            return 0;
        } else {
            return (rightBorder - leftBorder + 1);
        }
    }

    private int findLeftBorder(int[] nums, int target) {
        int len = nums.length;

        int left = 0;
        int right = len;

        while (left < right) {
            int mid = left + (right - left) / 2;

            int item = nums[mid];

            if (target > item) {
                left = mid + 1;
            } else if (target < item) {
                right = mid;
            } else {
                right = mid;
            }
        }

        if (left == len) return -1;

        return nums[left] == target ? left : -1;
    }

    private int findRightBorder(int[] nums, int target) {
        int len = nums.length;

        int left = 0;
        int right = len;

        while (left < right) {
            int mid = left + (right - left) / 2;

            int item = nums[mid];

            if (target > item) {
                left = mid + 1;
            } else if (target < item) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        if (right == 0) return -1;

        return nums[right - 1] == target ? right - 1 : -1;
    }
}
```

复杂度分析：

1. 时间复杂度：两个函数分别都用了**O(logn)**的时间复杂度，且调用这两个函数的时间复杂度为**相加**，故总的时间复杂度为**O(logn)**；
2. 空间复杂度：两个函数分别都用了常数个额外空间，且调用这两个函数的空间复杂度为**相加**，故总的空间复杂度为**O(1)**。