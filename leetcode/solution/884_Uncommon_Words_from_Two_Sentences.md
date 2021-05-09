# 第884题 两句话中的不常见单词

## 1 题目

给定两个句子 A 和 B 。 （句子是一串由空格分隔的单词。每个单词仅由小写字母组成。）

如果一个单词在其中一个句子中只出现一次，在另一个句子中却没有出现，那么这个单词就是不常见的。

返回所有不常用单词的列表。

您可以按任何顺序返回列表。

示例 1：

```
输入：A = "this apple is sweet", B = "this apple is sour"
输出：["sweet","sour"]
```

示例 2：

```
输入：A = "apple apple", B = "banana"
输出：["banana"]
```

## 2 解法

```
class Solution {
    public String[] uncommonFromSentences(String A, String B) {
        if (A.length() == 0 || B.length() == 0) {
            return new String[0];
        }

        StringBuilder all = new StringBuilder();
        all.append(A).append(" ").append(B);

        String[] words = all.toString().split(" ");

        Map<String, Integer> map = new HashMap<>(words.length);

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String> list = new ArrayList<>(words.length);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                list.add(entry.getKey());
            }
        }

        String[] res = new String[list.size()];
        int ptr = 0;

        for (String item : list) {
            res[ptr] = item;
            ptr++;
        }

        return res;
    }
}
```

复杂度分析：

设字符串A中有m个单词，字符串B中有n个单词。

1. 时间复杂度：统计两字符串中的每个单词的出现次数共花费O(m + n)的时间，扫描并找出出现次数为1的单词花费O(m + n)的时间，后续输出数组部分时间小于O(m + n)，因此，总时间复杂度为**O(m + n)**；
2. 空间复杂度：拼接字符串花费O(m + n)的空间，创建哈希表花费O(m + n)的空间，后续输出数组部分空间小于O(m + n)，因此，总空间复杂度为**O(m + n)**。



