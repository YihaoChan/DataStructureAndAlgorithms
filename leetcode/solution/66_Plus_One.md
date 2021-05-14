# 第66题 加一

## 1 题目

给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。

示例 1：

```
输入：digits = [1,2,3]
输出：[1,2,4]
解释：输入数组表示数字 123。
```

示例 2：

```
输入：digits = [4,3,2,1]
输出：[4,3,2,2]
解释：输入数组表示数字 4321。
```

示例 3：

```
输入：digits = [0]
输出：[1]
```

## 2 解法

如果原数全为9，加1之后必为100...000。所以new一个数组，第一位为1，后面默认0即可，不用再for循环添加。如果最末位加1之后没有产生进位，则直接返回即可。

```
class Solution {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int spilt = 0;
        int sum = 0;

        for (int i = len - 1; i >= 0; i--) {
            if (i == len - 1) {
                sum = digits[i] + 1 + spilt;
            } else {
                sum = digits[i] + spilt;
            }

            if (sum == 10) {
                digits[i] = 0;
                spilt = 1;
            } else if (sum != 10){
                digits[i] = sum;
                spilt = 0;
                if (i == len - 1) {
                    return digits;
                }
            }
        }

        if (spilt == 1) {
            int[] newArr = new int[digits.length + 1];
            newArr[0] = 1;
            return newArr;
        }

        return digits;
    }
}
```



