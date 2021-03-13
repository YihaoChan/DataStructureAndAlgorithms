# 第209题 长度最小的子数组

## 1 题目

给定一个含有 n 个正整数的数组和一个正整数 target 。

找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。

示例 1：

```
输入：target = 7, nums = [2,3,1,2,4,3]
输出：2
解释：子数组 [4,3] 是该条件下的长度最小的子数组。
```

示例 2：

```
输入：target = 4, nums = [1,4,4]
输出：1
```

示例 3：

```
输入：target = 11, nums = [1,1,1,1,1,1,1,1]
输出：0
```

## 2 解法

快慢指针形成滑动窗口。遍历数组，累加当前数组元素，当sum ≥ target时，减去窗口最左边的元素，并步进慢指针，相当于收缩窗口。

```
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int slow = 0;
        int fast = 0;

        int sum = 0;

        int numsLen = nums.length;

        int subLen = Integer.MAX_VALUE;

        for (fast = 0; fast < numsLen; fast++) {
            int current = nums[fast];

            sum += current;

            while (sum >= target) {
                sum -= nums[slow];
                subLen = Math.min(fast - slow + 1, subLen);
                slow++;
            }
        }

        return subLen == Integer.MAX_VALUE ? 0 : subLen;
    }
}
```

复杂度分析：

1. 时间复杂度：fast顺序扫描花费O(n)，slow最多移动n次，所以时间复杂度为**O(n)**；
2. 空间复杂度：仅用到常数个额外空间，故空间复杂度为**O(1)**。