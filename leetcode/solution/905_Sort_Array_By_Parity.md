# 第905题 按奇偶排序数组

# 1 题目

给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。

你可以返回满足此条件的任何数组作为答案。

示例：

```
输入：[3,1,2,4]
输出：[2,4,3,1]
输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
```

# 2 解法

快慢指针，当快指针遇到偶数时，和慢指针所指元素交换位置，之后快、慢指针都更新即可。

```
class Solution {
    public int[] sortArrayByParity(int[] A) {
        int left = 0;
        int right = 0;

        int len = A.length;

        for (right = 0; right < len; right++) {
            int item = A[right];

            if (item % 2 == 0) {
                int temp = A[right];
                A[right] = A[left];
                A[left] = temp;
                left++;
            }
        }

        return A;
    }
}
```

复杂度分析：

1. 时间复杂度：right顺序扫描花费O(n)，left最多移动n次，故时间复杂度为**O(n)**；
2. 空间复杂度：仅用到常数个额外空间，故空间复杂度为**O(1)**。