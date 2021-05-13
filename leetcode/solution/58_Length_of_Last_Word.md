# 第58题 最后一个单词的长度

## 1 题目

给你一个字符串 s，由若干单词组成，单词之间用空格隔开。返回字符串中最后一个单词的长度。如果不存在最后一个单词，请返回 0 。

单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。

示例 1：

```
输入：s = "Hello World"
输出：5
```

示例 2：

```
输入：s = " "
输出：0
```


提示：

1 <= s.length <= 104
s 仅有英文字母和空格 ' ' 组成

## 2 解法

注意特殊用例："a "

```
class Solution {
    public int lengthOfLastWord(String s) {
        int len = s.length();

        int res = 0;

        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ' && res == 0) {
                continue;
            } else if (s.charAt(i) == ' ' && res != 0) {
                break;
            }

            res++;
        }

        return res;
    }
}
```

复杂度分析：

设字符串s共有n个字符。

1. 时间复杂度：最坏情况下，字符串中的每个字符都被扫描，故时间复杂度为**O(n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。