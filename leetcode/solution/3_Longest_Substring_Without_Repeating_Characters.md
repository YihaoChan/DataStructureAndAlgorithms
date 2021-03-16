# 第3题 无重复字符的最长子串

## 1 题目

给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

```
输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

示例 2:

```
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
```

示例 3:

```
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
```

示例 4:

```
输入: s = ""
输出: 0
```

## 2 解法

滑动窗口。记录当前字母出现的次数，如果大于1次，说明发生重复，开始缩小窗口，直到窗口内的字母不重复即可。

```
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();

        char[] sArr = s.toCharArray();

        int sLen = sArr.length;

        int left = 0;
        int right = 0;

        int subStrLen = 0;

        for (right = 0; right < sLen; right++) {
            char curr = sArr[right];

            window.put(curr, window.getOrDefault(curr, 0) + 1);

            while (window.get(curr) > 1) {
                char removeChar = sArr[left];
                
                left++;
                
                window.put(removeChar, window.get(removeChar) - 1);
            }

            if (right - left + 1 > subStrLen) {
                subStrLen = right - left + 1;
            }
        }

        return subStrLen;
    }
}
```

复杂度分析：

1. 时间复杂度：right顺序扫描花费O(n)，left最多移动n次，故时间复杂度为**O(n)**；
2. 空间复杂度：申请一个哈希表作为辅助空间，每个哈希表最多不会存放超过字符集大小的键值对，设字符集大小为C，本题并无明确指定字符集，故可认为字符均在ASCII码[0, 127]范围内。故空间复杂度为**O(C)**。