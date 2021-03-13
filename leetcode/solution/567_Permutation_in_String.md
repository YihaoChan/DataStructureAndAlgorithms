# 第567题 字符串的排列

## 1 题目

给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

换句话说，第一个字符串的排列之一是第二个字符串的子串。

示例 1：

```
输入: s1 = "ab" s2 = "eidbaooo"
输出: True
解释: s2 包含 s1 的排列之一 ("ba").
```

示例 2：

```
输入: s1= "ab" s2 = "eidboaoo"
输出: False
```

## 2 解法

滑动窗口。先用一个哈希表记录需要统计的字母及其出现次数，比如示例1中，通过s1得知，a、b都应该出现1次。之后遍历s2，判断当前字母是否为应该统计的字母，如果是，则增加其次数。当字母记录次数等于应该统计的字母出现次数时，满足条件的字母数量就加1。由于排列不会改变字符串中每个字符的个数，所以只有**当两个字符串每个字符的个数均相等**时，一个字符串才是另一个字符串的排列，即right - left + 1 == s1.length。如果此时在这个窗口内，满足条件的字母数量等于应该统计的字母数量，即satisfy == targetNum，说明s1是s2的一个合法排列，返回true。否则，继续步进right指针。

```
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (
            s1 == null || s2 == null || s1.equals("") || s2.equals("") 
            || s1.length() > s2.length()
        ) {
            return false;
        }

        // 统计字母及出现次数
        char[] s1Arr = s1.toCharArray();

        int s1Len = s1Arr.length;

        Map<Character, Integer> target = new HashMap<>();

        for (char c : s1Arr) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        int targetNum = target.size();

        // 遍历主串
        char[] s2Arr = s2.toCharArray();

        int s2Len = s2Arr.length;

        Map<Character, Integer> window = new HashMap<>();

        int left = 0;
        int right = 0;

        int satisfy = 0;

        for (right = 0; right < s2Len; right++) {
            char current = s2Arr[right];

            if (target.containsKey(current)) {
                window.put(
                	current, window.getOrDefault(current, 0) + 1
                );

                if (window.get(current).equals(target.get(current))) {
                    satisfy++;
                }
            }

            while (right - left + 1 == s1Len) {
                if (satisfy == targetNum) {
                    return true;
                }

                char removeChar = s2Arr[left];

                left++;

                if (target.containsKey(removeChar)) {
                    if (
                    	target.get(removeChar)
                    	.equals(window.get(removeChar))
                    ) {
                        satisfy--;
                    }

                    window.put(
                    	removeChar, window.get(removeChar) - 1
                    );
                }
            }
        }

        return false;
    }
}
```

典型测试用例：

```
s1 = "abcdxabcde",
s2 = "abcdeabcdx".
```

复杂度分析：

1. 时间复杂度：首先将s1中的元素都插入哈希表，花费O(s1)。遍历s2的每个字符花费O(s2)。当窗口大小等于子串长度时，所进行的操作均花费O(1)。故时间复杂度为**O(s1 + s2)**；
2. 空间复杂度：申请两个哈希表作为辅助空间，每个哈希表最多不会存放超过字符集大小的键值对，设字符集大小为C，则空间复杂度为**O(C)**。

