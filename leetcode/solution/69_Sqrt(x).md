# 第69题 x的平方根

## 1 题目

实现 int sqrt(int x) 函数。

计算并返回 x 的平方根，其中 x 是非负整数。

由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

示例1：

```
输入: 4
输出: 2
```

示例2：

```
输入: 8
输出: 2
说明: 8 的平方根是 2.82842..., 
     由于返回类型是整数，小数部分将被舍去。
```

## 2 解法

### 2.1 二分查找

注意：计算item = mid * mid，和x做比较时，很可能导致item溢出。因此要变换为mid和x / mid做比较！

```
class Solution {
    public int mySqrt(int x) {
        if (x == 0) return 0;

        int left = 1;
        int right = x;

        while (left <= right) {
            int mid = left + ((right - left) / 2);

            int item = x / mid; // 防止溢出

            if (mid > item) {
                right = mid - 1;
            } else if (mid < item) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return right;
    }
}
```

复杂度分析：

1. 时间复杂度：二分查找时间复杂度为**O(logx)**；
2. 空间复杂度：只用到常数个额外空间，所以空间复杂度为**O(1)**。

### 2.2 牛顿迭代法

计算x<sup>2</sup> = n的解，令f(x) = x<sup>2</sup> - n，相当于求解f(x) = 0的解。

任何时刻，有：f(x) = f(x<sub>i</sub>) + f'(x<sub>i</sub>) (x - x<sub>i</sub>).

又f'(x<sub>i</sub>) = 2 * x<sub>i</sub>，故为求横坐标令f(x) = 0，有：

x = (x<sub>i</sub> + n / x<sub>i</sub>) / 2.

当x<sub>i</sub>和x<sub>i+1</sub>的差距很小时，可认为找到对应解。

```
class Solution {
    public int mySqrt(int x) {
        double last = 0;
        double res = x;

        while (Math.abs(last - res) > 1e-7) {
            last = res; // 记住上一次的值
            res = 0.5 * (res + x / res); // 更新当前值
        }

        return (int) res;
    }
}
```

复杂度分析：

1. 时间复杂度：**O(logx)**；
2. 空间复杂度：只用到常数个额外空间，所以空间复杂度为**O(1)**。