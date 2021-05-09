# 第125题 验证回文串

## 1 题目

给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

说明：本题中，我们将空字符串定义为有效的回文串。

示例 1:

```
输入: "A man, a plan, a canal: Panama"
输出: true
```

示例 2:

```
输入: "race a car"
输出: false
```

## 2 解法

```
class Solution {
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }

        int len = s.length();

        int left = 0;
        int right = len - 1;

        while (left < right) {
            char leftChar = Character.toLowerCase(s.charAt(left));
            char rightChar = Character.toLowerCase(s.charAt(right));

            if (!Character.isLetterOrDigit(leftChar)) {
                left++;
                continue;
            }

            if (!Character.isLetterOrDigit(rightChar)) {
                right--;
                continue;
            }

            if (leftChar == rightChar) {
                left++;
                right--;
            }

            if (leftChar != rightChar) {
                return false;
            }
        }

        return true;
    }
}
```

复杂度分析：

假设字符串共有n个字符。

1. 时间复杂度：最坏情况下，每个字符都进行比较，故时间复杂度为**O(n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。



