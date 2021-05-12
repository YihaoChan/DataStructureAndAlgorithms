# 第387题 字符串中的第一个唯一字符

## 1 题目

给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。

示例：

```
s = "leetcode"
返回 0

s = "loveleetcode"
返回 2
```


提示：你可以假定该字符串只包含小写字母。

## 2 解法

### 2.1 通用解法-哈希表

```
class Solution {
    public int firstUniqChar(String s) {
        if (s == null) {
            return -1;
        }

        Map<Character, Integer> charToTimes = new HashMap<>();

        int len = s.length();

        for (int i = 0; i < len; i++) {
            charToTimes.put(
                s.charAt(i), 
                charToTimes.getOrDefault(s.charAt(i), 0) + 1
            );
        }

        for (int j = 0; j < len; j++) {
            if (charToTimes.get(s.charAt(j)) == 1) {
                return j;
            }
        }

        return -1;
    }
}
```

复杂度分析：

1. 时间复杂度：最坏情况下需要完整遍历两次字符串，故时间复杂度为**O(n)**；
2. 空间复杂度：创建哈希表用于记录字符及其出现次数，设字符集大小为C，故空间复杂度为**O(C)**。

### 2.2 针对小写字符-数组速度更快

```
class Solution {
    public int firstUniqChar(String s) {
        if (s == null) {
            return -1;
        }

        int[] charToTimes = new int[26];

        int len = s.length();

        for (int i = 0; i < len; i++) {
            charToTimes[s.charAt(i) - 'a']++;
        }

        for (int j = 0; j < len; j++) {
            if (charToTimes[s.charAt(j) - 'a'] == 1) {
                return j;
            }
        }

        return -1;
    }
}
```

复杂度分析：

1. 时间复杂度：最坏情况下需要完整遍历两次字符串，故时间复杂度为**O(n)**；
2. 空间复杂度：创建数组用于记录字符及其出现次数，设字符集大小为C，故空间复杂度为**O(C)**。