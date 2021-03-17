# 第485题 最大连续1的个数

## 1 题目

给定一个二进制数组， 计算其中最大连续 1 的个数。

示例：

```
输入：[1,1,0,1,1,1]
输出：3
解释：开头的两位和最后的三位都是连续 1 ，所以最大连续 1 的个数是 3.
```

## 2 解法

顺序扫描。

```
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int len = nums.length;

        int maxConOneNum = 0;

        int count = 0;

        for (int i = 0; i < len; i++) {
            if (nums[i] == 1) {
                count++;

                if (count > maxConOneNum) {
                    maxConOneNum = count;
                }
            } else {
                count = 0;
            }
        }

        return maxConOneNum;
    }
}
```

复杂度分析：

1. 时间复杂度：顺序扫描花费O(n)，故时间复杂度为**O(n)**；
2. 空间复杂度：仅用到常数个额外空间，故空间复杂度为**O(1)**。