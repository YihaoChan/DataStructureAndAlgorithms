# 第1550题 存在连续三个奇数的数组

## 1 题目

给你一个整数数组 arr，请你判断数组中是否存在连续三个元素都是奇数的情况：如果存在，请返回 true ；否则，返回 false 。

示例 1：

```
输入：arr = [2,6,4,1]
输出：false
解释：不存在连续三个元素都是奇数的情况。
```

示例 2：

```
输入：arr = [1,2,34,3,4,5,7,23,12]
输出：true
解释：存在连续三个元素都是奇数的情况，即 [5,7,23] 。
```

## 2 解法

```
class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        int len = arr.length;

        int slow = 0;
        int fast = 0;
        int oddCount = 0;

        while (fast < len) {
            if (arr[fast] % 2 != 0) {
                oddCount++;
            } else {
                slow = fast;
                oddCount = 0;
            }

            if (oddCount == 3) {
                return true;
            }

            fast++;
        }

        return false;
    }
}
```

复杂度分析：

1. 时间复杂度：最坏情况下每个元素都被扫描一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。



