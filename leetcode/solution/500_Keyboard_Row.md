# 第500题 键盘行

## 1 题目

给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。

美式键盘 中：

第一行由字符 "qwertyuiop" 组成。
第二行由字符 "asdfghjkl" 组成。
第三行由字符 "zxcvbnm" 组成。

示例 1：

```
输入：words = ["Hello","Alaska","Dad","Peace"]
输出：["Alaska","Dad"]
```

示例 2：

```
输入：words = ["omk"]
输出：[]
```

示例 3：

```
输入：words = ["adsdf","sfd"]
输出：["adsdf","sfd"]
```


提示：

1 <= words.length <= 20
1 <= words[i].length <= 100
words[i] 由英文字母（小写和大写字母）组成

## 2 解法

```
class Solution {
    public String[] findWords(String[] words) {
        List<String> res = new ArrayList<>(words.length);
        Set<Character> topLine = new HashSet<>(10);
        Set<Character> midLine = new HashSet<>(9);
        Set<Character> bottomLine = new HashSet<>(7);

        addKeyToSet(topLine, "qwertyuiop");
        addKeyToSet(midLine, "asdfghjkl");
        addKeyToSet(bottomLine, "zxcvbnm");

        Set[] sets = {topLine, midLine, bottomLine};

        for (String word : words) {
            String lowerCopy = word.toLowerCase();

            Set<Character> line = chooseSet(
            	sets, lowerCopy.charAt(0)
            );
            boolean writeFlag = true;

            for (int i = 0; i < lowerCopy.length(); i++) {
                if (!line.contains(lowerCopy.charAt(i))) {
                    writeFlag = false;
                    break;
                }
            }

            if (writeFlag) {
                res.add(word);
            }
        }

        String[] ans = new String[res.size()];
        int ptr = 0;

        for (String w : res) {
            ans[ptr] = w;
            ptr++;
        }

        return ans;
    }

    private void addKeyToSet(Set<Character> set, String keys) {
        int keysNum = keys.length();
        for (int i = 0; i < keysNum; i++) {
            set.add(keys.charAt(i));
        }
        return;
    }

    private Set<Character> chooseSet(Set[] sets, char firstChar) {
        for (Set<Character> set : sets) {
            if (set.contains(firstChar)) {
                return set;
            }
        }
        return null;
    }
}
```

复杂度分析：

设共有m个单词，所有字符的个数为n。

1. 时间复杂度：将键盘上26个字符添加进HashSet共花费O(26)时间，最坏情况下，每个单词的每个字符都被扫描一遍，其余开销均小于O(n)，故时间复杂度为**O(n)**；
2. 空间复杂度：存放26个字符的HashSet共花费O(26)空间，最坏情况下，所有m个单词都符合要求，故空间复杂度为**O(m)**。

