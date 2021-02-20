# 第744题 寻找比目标字母大的最小字母

## 1 题目

给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母 target，请你寻找在这一有序列表里比目标字母大的最小字母。

在比较时，字母是依序循环出现的。举个例子：

如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'

示例：

```
输入:
letters = ["c", "f", "j"]
target = "a"
输出: "c"

输入:
letters = ["c", "f", "j"]
target = "c"
输出: "f"

输入:
letters = ["c", "f", "j"]
target = "d"
输出: "f"

输入:
letters = ["c", "f", "j"]
target = "g"
输出: "j"

输入:
letters = ["c", "f", "j"]
target = "j"
输出: "c"

输入:
letters = ["c", "f", "j"]
target = "k"
输出: "c"
```

## 2 解法

字符列表有序，故用二分查找。

```
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int len = letters.length;

        int left = 0;
        int right = len - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            char letter = letters[mid];

            if (target < letter) {
                right = mid - 1;
            } else if (target > letter) {
                left = mid + 1;
            } else {
                left = mid + 1;
            }
        }

        return letters[left % len]; // 求模形成下标循环
    }
}
```

注意：当下标恰好等于数组长度时需返回0，此时可通过求模运算制造循环。

复杂度分析：

1. 时间复杂度：二分查找时间复杂度为**O(logn)**，n为字符个数；
2. 空间复杂度：只用到常数个额外空间，所以空间复杂度为**O(1)**。