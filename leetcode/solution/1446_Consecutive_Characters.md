# 第1446题 连续字符

## 1 题目

给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。

请你返回字符串的能量。

示例 1：

```
输入：s = "leetcode"
输出：2
解释：子字符串 "ee" 长度为 2 ，只包含字符 'e' 。
```

示例 2：

```
输入：s = "abbcccddddeeeeedcba"
输出：5
解释：子字符串 "eeeee" 长度为 5 ，只包含字符 'e' 。
```

示例 3：

```
输入：s = "triplepillooooow"
输出：5
```

示例 4：

```
输入：s = "hooraaaaaaaaaaay"
输出：11
```

示例 5：

```
输入：s = "tourist"
输出：1
```

## 2 解法

```
class Solution {
    public int maxPower(String s) {
        int len = s.length();

        int slow = 0;
        int fast = 0;

        int maxLen = 0;

        for (fast = 0; fast < len; fast++) {
            if (s.charAt(fast) != s.charAt(slow)) {
                if (fast - slow > maxLen) {
                    maxLen = fast - slow;
                }
                slow = fast;
            }
        }

        if (fast - slow > maxLen) {
            maxLen = fast - slow;
        }

        return maxLen;
    }
}
```

复杂度分析：

1. 时间复杂度：每个字符都要遍历一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。



