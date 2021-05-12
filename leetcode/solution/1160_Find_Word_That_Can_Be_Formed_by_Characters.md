# 第1160题 拼写单词

## 1 题目

给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。

假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。

注意：每次拼写（指拼写词汇表中的一个单词）时，chars 中的每个字母都只能用一次。

返回词汇表 words 中你掌握的所有单词的 长度之和。

示例 1：

```


输入：words = ["cat","bt","hat","tree"], chars = "atach"
输出：6
解释： 
可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
```

示例 2：

```
输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
输出：10
解释：
可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
```


提示：

1 <= words.length <= 1000
1 <= words[i].length, chars.length <= 100
所有字符串中都仅包含小写英文字母

## 2 解法

### 2.1 通用解法-哈希表

哈希表的深拷贝用new HashMap<>(OldMap)方法。

```
class Solution {
    public int countCharacters(String[] words, String chars) {
        Map<Character, Integer> provision = new HashMap<>();

        int charsLen = chars.length();

        for (int i = 0; i < charsLen; i++) {
            provision.put(
                chars.charAt(i), 
                provision.getOrDefault(chars.charAt(i), 0) + 1
            );
        }

        int res = 0;

        for (String word : words) {
            Map<Character, Integer> provisionCopy = 
                                    new HashMap<>(provision);
            boolean satisfy = true;

            for (int j = 0; j < word.length(); j++) {
                provisionCopy.put(
                    word.charAt(j), 
                    provisionCopy.getOrDefault(word.charAt(j), 0) - 1
                );

                if (provisionCopy.get(word.charAt(j)) < 0) {
                    satisfy = false;
                    break;
                }
            }

            if (satisfy) {
                res += word.length();
            }
        }

        return res;
    }
}
```

复杂度分析：

设words共有n个字符，chars有m个字符。

1. 时间复杂度：统计chars中的字符及其出现次数共花费O(m)，最坏情况下，words中每个字符都被扫描，花费O(n)，因此，总时间复杂度为**O(n + m)**；
2. 空间复杂度：申请一个哈希表作为辅助空间，哈希表最多不会存放超过字符集大小的键值对，在遍历每个word时创建一个统计字符及其频数的哈希表的副本，设字符集大小为C，故空间复杂度为**O(C)**。

### 2.2 针对小写字符-数组-速度更快

一维数组的深拷贝用clone()方法。

```
class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] provision = new int[26];

        int charsLen = chars.length();

        for (int i = 0; i < charsLen; i++) {
            int idx = (int) (chars.charAt(i) - 'a');
            provision[idx]++;
        }

        int res = 0;

        for (String word : words) {
            int[] provisionCopy = provision.clone();
            boolean satisfy = true;

            for (int j = 0; j < word.length(); j++) {
                int idx = (int) (word.charAt(j) - 'a');
                provisionCopy[idx]--;

                if (provisionCopy[idx] < 0) {
                    satisfy = false;
                    break;
                }
            }

            if (satisfy) {
                res += word.length();
            }
        }

        return res;
    }
}
```

复杂度分析：

设words共有n个字符，chars有m个字符。

1. 时间复杂度：统计chars中的字符及其出现次数共花费O(m)，最坏情况下，words中每个字符都被扫描，花费O(n)，因此，总时间复杂度为**O(n + m)**；
2. 空间复杂度：申请一个数组作为辅助空间，数组中最多不会存放超过字符集大小的键值对，在遍历每个word时创建一个统计字符及其频数的数组的副本，设字符集大小为C，故空间复杂度为**O(C)**。

