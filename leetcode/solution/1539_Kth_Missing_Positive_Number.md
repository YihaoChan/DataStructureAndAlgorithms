# 第1539题 第k个缺失的数字

## 1 题目

给你一个 严格升序排列 的正整数数组 arr 和一个整数 k 。

请你找到这个数组里第 k 个缺失的正整数。

示例 1：

```
输入：arr = [2,3,4,7,11], k = 5
输出：9
解释：缺失的正整数包括 [1,5,6,8,9,10,12,13,...] 。第 5 个缺失的正整数为 9 。
```

示例 2：

```
输入：arr = [1,2,3,4], k = 2
输出：6
解释：缺失的正整数包括 [5,6,7,...] 。第 2 个缺失的正整数为 6 。
```


提示：

1 <= arr.length <= 1000
1 <= arr[i] <= 1000
1 <= k <= 1000
对于所有 1 <= i < j <= arr.length 的 i 和 j 满足 arr[i] < arr[j] 

## 2 解法

### 2.1 顺序枚举

```
class Solution {
    public int findKthPositive(int[] arr, int k) {
        int len = arr.length;

        int generate = 0;
        int ptr = 0;
        int miss = 0;

        while (true) {
            generate++;

            if (ptr < len) {
                if (generate != arr[ptr]) {
                    miss++;
                } else {
                    ptr++;
                }
            } else {
                miss++;
            }

            if (miss == k) {
                break;
            }
        }

        return generate;
    }
}
```

复杂度分析：

1. 时间复杂度：最坏情况下，数组从1开始顺序增长到n，此时在数组外面还要搜索k次才能查找到第k个缺失的数字，故时间复杂度为**O(n + k)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。

### 2.2 线性搜索



### 2.3 二分查找



