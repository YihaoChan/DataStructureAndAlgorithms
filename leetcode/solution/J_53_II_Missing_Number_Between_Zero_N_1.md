# 剑指offer第53 - II题 0 ~ n - 1中缺失的数字

## 1 题目

一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。

示例1：

```
输入: [0,1,3]
输出: 2
```

示例2：

```
输入: [0,1,2,3,4,5,6,7,9]
输出: 8
```

## 2 解法

如果数组下标 == 该位置处的元素，说明缺失的数字在当前位置的右边，所以向右继续搜索；否则，向左搜索。有点类似于二分查找搜索右侧边界的问题。

```
class Solution {
    public int missingNumber(int[] nums) {
        int len = nums.length; 

        int left = 0;
        int right = len;

        while (left < right) {
            int mid = left + (right - left) / 2;

            int item = nums[mid];

            if (mid < item) {
                right = mid;
            } else if (mid == item) { // 不可能有大于的情况
                left = mid + 1;
            }
        } 

        return right;
    }
}
```

复杂度分析：

1. 时间复杂度：二分查找时间复杂度为**O(logn)**；
2. 空间复杂度：只用到常数个额外空间，所以空间复杂度为**O(1)**。