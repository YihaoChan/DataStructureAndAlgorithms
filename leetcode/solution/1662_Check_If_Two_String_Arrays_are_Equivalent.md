# 第1662题 检查两个字符串是否相等

## 1 题目

给你两个字符串数组 word1 和 word2 。如果两个数组表示的字符串相同，返回 true ；否则，返回 false 。

数组表示的字符串 是由数组中的所有元素 按顺序 连接形成的字符串。

示例 1：

```
输入：word1 = ["ab", "c"], word2 = ["a", "bc"]
输出：true
解释：
word1 表示的字符串为 "ab" + "c" -> "abc"
word2 表示的字符串为 "a" + "bc" -> "abc"
两个字符串相同，返回 true
```

示例 2：

```
输入：word1 = ["a", "cb"], word2 = ["ab", "c"]
输出：false
```

示例 3：

```
输入：word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
输出：true
```

## 2 解法

```
class Solution {
    public boolean arrayStringsAreEqual(String[] word1, 
                                        String[] word2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for (String comp1 : word1) {
            sb1.append(comp1);
        }
        for (String comp2 : word2) {
            sb2.append(comp2);
        }

        int len1 = sb1.length();
        int len2 = sb2.length();

        if (len1 != len2) {
            return false;
        }

        int len = len1;

        for (int i = 0; i < len; i++) {
            if (sb1.charAt(i) != sb2.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
```

复杂度分析：

1. 时间复杂度：时间复杂度取决于两个字符串的字符数，最坏情况下两个字符串数组拼接后的结果相等，每个字符串拼接后有n个字符，故需要花费**O(n)**的时间；
2. 空间复杂度：需要新建两个额外字符串用于拼接每个组成部分，每个字符串拼接后有n个字符，故空间复杂度为**O(n)**。



