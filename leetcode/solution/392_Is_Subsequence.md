# 第392题 判断子序列

# 1 题目

给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

示例1：

```
输入：s = "abc", t = "ahbgdc"
输出：true
```

示例2：

```
输入：s = "axc", t = "ahbgdc"
输出：false
```

# 2 解法

双指针

```
class Solution {
    public boolean isSubsequence(String s, String t) {
        int sPtr = 0;
        int tPtr = 0;

        int sLen = s.length();
        int tLen = t.length();

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        while (sPtr != sLen && tPtr != tLen) {
            char sItem = sArr[sPtr];
            char tItem = tArr[tPtr];

            if (sItem == tItem) {
                sPtr++;
            }

            tPtr++;
        }

        return (sPtr == sLen);
    }
}
```

复杂度分析：

假设字符串s的长度为m，字符串t的长度为n。

1. 时间复杂度：顺序扫描字符串t后，移动字符串s的指针，最坏情况下时间复杂度为**O(m + n)**；
2. 空间复杂度：只用到常数个额外空间，所以空间复杂度为**O(1)**。