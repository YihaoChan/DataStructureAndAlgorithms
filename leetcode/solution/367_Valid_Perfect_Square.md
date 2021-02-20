# 第367题 有效的完全平方数

## 1 题目

给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。

说明：不要使用任何内置的库函数，如 sqrt。

示例1：

```
输入：16
输出：True
```

示例2：

```
输入：14
输出：False
```

## 2 解法

### 2.1 二分查找

```
class Solution {
    public boolean isPerfectSquare(int num) {
        int left = 1;
        int right = num;

        while (left <= right) {
            double mid = (double) (left + (right - left) / 2);

            double item = (double) (num / mid); // 防止溢出和截断

            if (mid > item) {
                right = (int) (mid) - 1;
            } else if (mid < item) {
                left = (int) (mid) + 1;
            } else {
                return true;
            }
        }

        return false;
    }
}
```

复杂度分析：

1. 时间复杂度：二分查找时间复杂度为**O(log num)**；
2. 空间复杂度：只用到常数个额外空间，所以空间复杂度为**O(1)**。



