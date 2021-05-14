# 第1816题 截断句子

## 1 题目

句子 是一个单词列表，列表中的单词之间用单个空格隔开，且不存在前导或尾随空格。每个单词仅由大小写英文字母组成（不含标点符号）。

例如，"Hello World"、"HELLO" 和 "hello world hello world" 都是句子。
给你一个句子 s 和一个整数 k ，请你将 s 截断 ，使截断后的句子仅含 前 k 个单词。返回 截断 s 后得到的句子。

示例 1：

```
输入：s = "Hello how are you Contestant", k = 4
输出："Hello how are you"
解释：
s 中的单词为 ["Hello", "how" "are", "you", "Contestant"]
前 4 个单词为 ["Hello", "how", "are", "you"]
因此，应当返回 "Hello how are you"
```

示例 2：

```
输入：s = "What is the solution to this problem", k = 4
输出："What is the solution"
解释：
s 中的单词为 ["What", "is" "the", "solution", "to", "this", "problem"]
前 4 个单词为 ["What", "is", "the", "solution"]
因此，应当返回 "What is the solution"
```

示例 3：

```
输入：s = "chopper is not a tanuki", k = 5
输出："chopper is not a tanuki"
```


提示：

1 <= s.length <= 500
k 的取值范围是 [1,  s 中单词的数目]
s 仅由大小写英文字母和空格组成
s 中的单词之间由单个空格隔开
不存在前导或尾随空格.

## 2 解法

```
class Solution {
    public String truncateSentence(String s, int k) {
        int len = s.length();

        int spaceCount = 0;
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < len; i++) {
            char curr = s.charAt(i);

            if (curr == ' ') {
                spaceCount++;
            }

            if (spaceCount < k) {
                sb.append(curr);
            } else {
                break;
            }
        }

        return sb.toString();
    }
}
```

复杂度分析：

设共有n个字符。

1. 时间复杂度：最坏情况下每个字符都扫描一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：开辟一个新StringBuilder对象存储答案，故空间复杂度为**O(n)**。

