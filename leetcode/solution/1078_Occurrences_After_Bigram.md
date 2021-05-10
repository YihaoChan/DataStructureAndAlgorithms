# 第1078题 Bigram分词

## 1 题目

给出第一个词 first 和第二个词 second，考虑在某些文本 text 中可能以 "first second third" 形式出现的情况，其中 second 紧随 first 出现，third 紧随 second 出现。

对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案。

示例 1：

```
输入：text = "alice is a good girl she is a good student", first = "a", second = "good"
输出：["girl","student"]
```

示例 2：

```
输入：text = "we will we will rock you", first = "we", second = "will"
输出：["we","rock"]
```

## 2 解法

```
class Solution {
    public String[] findOcurrences(String text, 
                                   String first, String second) {
        List<String> res = new ArrayList<>();

        String[] words = text.split(" ");

        int len = words.length;

        for (int i = 0; i < len - 2; i++) {
            if (words[i].equals(first) && 
                words[i + 1].equals(second)) {
                res.add(words[i + 2]);
            }
        }

        String[] ans = new String[res.size()];
        int ptr = 0;

        for (String item : res) {
            ans[ptr] = item;
            ptr++;
        }

        return ans;
    }
}
```

复杂度分析：

设text中有n个单词。

1. 时间复杂度：顺序扫描一遍分割text后的数组，故时间复杂度为**O(n)**；
2. 空间复杂度：最坏情况下，list中存放O(n)个单词，返回的数组也存放O(n)个单词，故空间复杂度为**O(n)**。



