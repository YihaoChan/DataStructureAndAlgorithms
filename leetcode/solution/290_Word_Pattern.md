# 第290题 单词规律

## 1 题目

给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。

这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。

示例1:

```
输入: pattern = "abba", str = "dog cat cat dog"
输出: true
```

示例 2:

```
输入:pattern = "abba", str = "dog cat cat fish"
输出: false
```

示例 3:

```
输入: pattern = "aaaa", str = "dog cat cat dog"
输出: false
```

示例 4:

```
输入: pattern = "abba", str = "dog dog dog dog"
输出: false
```

## 2 解法

定义两个哈希表，一个用于映射模式串中的字符到主串中的单词的关系，一个用于映射主串中的单词到模式串中的字符的对应关系，两个关系应该相等，称为**双射**。

![双射](images/双射.png)

```
class Solution {
    public boolean wordPattern(String pattern, String s) {
        if (pattern == null || s == null) {
            return false;
        }

        int patternLen = pattern.length();
        int sLen = s.length();

        if (patternLen == 0 || sLen == 0) {
            return false;
        }

        Map<Character, String> char2WordMap = new HashMap<>();
        Map<String, Character> word2CharMap = new HashMap<>();
        int patternPtr = 0;
        int sPtr = 0;

        for (patternPtr = 0; patternPtr < patternLen; patternPtr++) {
            if (sPtr == sLen) {
                return false;
            }

            StringBuilder sb = new StringBuilder();
            while (sPtr < sLen && s.charAt(sPtr) != ' ') {
                sb.append(s.charAt(sPtr));
                sPtr++;
            }

            Character currChar = pattern.charAt(patternPtr);
            String currWord = sb.toString();
            Character prevChar = word2CharMap.get(currWord);
            String prevWord = char2WordMap.get(currChar);

            if (prevChar == null && prevWord == null) {
                char2WordMap.put(currChar, currWord);
                word2CharMap.put(currWord, currChar);
            } else if (
                !currChar.equals(prevChar) ||
                !currWord.equals(prevWord)
            ) {
                return false;
            }

            if (sPtr < sLen) {
                sPtr++;
            }
        }

        return patternPtr == patternLen && sPtr == sLen;
    }
}
```

复杂度分析：

设模式串中有m个字符，主串中有n个字符。

1. 时间复杂度：最坏情况下，模式串和主串中的所有字符均被遍历一遍，故时间复杂度为**O(m + n)**；
2. 空间复杂度：存放模式串中字符对应关系的哈希表最多存放的字符个数为为字符集大小，存放主串中单词对应关系的哈希表最多存放的单词个数小于主串字符个数n，故空间复杂度为**O(n)**。