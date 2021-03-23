# 第1456题 定长子串中元音的最大数目

## 1 题目

给你字符串 s 和整数 k 。

请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。

英文中的 元音字母 为（a, e, i, o, u）。

示例 1：

```
输入：s = "abciiidef", k = 3
输出：3
解释：子字符串 "iii" 包含 3 个元音字母。
```


示例 2：

```
输入：s = "aeiou", k = 2
输出：2
解释：任意长度为 2 的子字符串都包含 2 个元音字母。
```


示例 3：

```
输入：s = "leetcode", k = 3
输出：2
解释："lee"、"eet" 和 "ode" 都包含 2 个元音字母。
```


示例 4：

```
输入：s = "rhythms", k = 4
输出：0
解释：字符串 s 中不含任何元音字母。
```


示例 5：

```
输入：s = "tryhard", k = 4
输出：1
```

## 2 解法

滑动窗口。

### 2.1 哈希表

```
class Solution {
    public int maxVowels(String s, int k) {
        if (s == null) {
            return -1;
        }

        char[] charArr = s.toCharArray();


        int len = charArr.length;

        int thisCount = 0;
        int maxCount = 0;

        Map<Character, Integer> window = new HashMap<>();

        for (int i = 0; i < len; i++) {
            char curr = charArr[i];

            if (isVowel(curr)) {
                thisCount++;
            }

            if (thisCount > maxCount) {
                maxCount = thisCount;
            }

            window.put(curr, window.getOrDefault(curr, 0) + 1);

            if (i >= k - 1) {
                // 收缩
                char removeChar = charArr[i - k + 1];

                window.put(removeChar, window.get(removeChar) - 1);

                if (isVowel(removeChar)) {
                    thisCount--;
                }
            }
        }

        return maxCount;
    }

    private boolean isVowel(char letter) {
        if (
            letter == 'a' || letter == 'e' || letter == 'i' ||
            letter == 'o' || letter == 'u'
        ) {
            return true;
        }

        return false;
    }
}
```

复杂度分析：

1. 时间复杂度：从左到右顺序扫描，且哈希表中put、get操作均只花费O(1)，故时间复杂度为**O(n)**；
2. 空间复杂度：需要额外的哈希表形成窗口，故空间复杂度为**O(n)**。

### 2.2 原地记录

使用哈希表需要额外O(n)的空间，还可以进行优化，即原地统计已记录的最大元音字母个数。

```
class Solution {
    public int maxVowels(String s, int k) {
        if (s == null) {
            return -1;
        }

        char[] charArr = s.toCharArray();

        int len = charArr.length;

        int thisCount = 0;
        int maxCount = 0;

        for (int i = 0; i < len; i++) {
            char curr = charArr[i];

            if (isVowel(curr)) {
                thisCount++;
            }

            if (thisCount > maxCount) {
                maxCount = thisCount;
            }
            
            if (i >= k - 1) {
                // 收缩
                char removeChar = charArr[i - k + 1];

                if (isVowel(removeChar)) {
                    thisCount--;
                }
            }
        }

        return maxCount;
    }

    private boolean isVowel(char letter) {
        if (
            letter == 'a' || letter == 'e' || letter == 'i' ||
            letter == 'o' || letter == 'u'
        ) {
            return true;
        }

        return false;
    }
}
```

复杂度分析：

1. 时间复杂度：从左到右顺序扫描，故时间复杂度为**O(n)**；
2. 空间复杂度：仅需要常数个额外空间，故空间复杂度为**O(1)**。



