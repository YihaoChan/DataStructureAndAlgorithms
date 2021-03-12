# 第438题 找到字符串中的字母异位词

# 1 题目

给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。

字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。

说明：

字母异位词指字母相同，但排列不同的字符串。
不考虑答案输出的顺序。
示例 1:

```
输入:
s: "cbaebabacd" p: "abc"

输出:
[0, 6]

解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
```

示例 2:

```
输入:
s: "abab" p: "ab"

输出:
[0, 1, 2]

解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
```

# 2 解法

滑动窗口。先用一个哈希表记录需要统计的字母及其出现次数，比如示例1中，通过p得知，a、b、c都应该出现1次。之后遍历s，判断当前字母是否为应该统计的字母，如果是，则增加其次数。当字母记录次数等于应该统计的字母出现次数时，满足条件的字母数量就加1。由于排列不会改变字符串中每个字符的个数，所以只有**当两个字符串每个字符的个数均相等**时，一个字符串才是另一个字符串的排列，即right - left + 1 == p.length。如果此时在这个窗口内，满足条件的字母数量等于应该统计的字母数量，即satisfy == targetNum，说明p是s的一个合法字母异位词(其实就是排列)，将left加到列表里。否则，继续步进right指针。

```
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        if (
            s == null || p == null || s.equals("") || p.equals("") ||
            s.length() < p.length()
        ) {
            return res;
        }

        Map<Character, Integer> target = new HashMap<>();

        char[] pArr = p.toCharArray();

        for (char c : pArr) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        int targetNum = target.size();
        int pLen = pArr.length;

        Map<Character, Integer> window = new HashMap<>();

        char[] sArr = s.toCharArray();

        int sLen = sArr.length;

        int left = 0;
        int right = 0;

        int satisfy = 0;

        for (right = 0; right < sLen; right++) {
            char current = sArr[right];

            if (target.containsKey(current)) {
                window.put(current, 
                window.getOrDefault(current, 0) + 1);

                if (
                	window.get(current).equals(target.get(current))
                ) {
                    satisfy++;
                }
            }

            while (right - left + 1 == pLen) {
                if (satisfy == targetNum) {
                    res.add(left);
                }

                char removeChar = sArr[left];

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

        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：首先将p中的元素都插入哈希表，花费O(p)。遍历s的每个字符花费O(s)。当窗口大小等于子串长度时，所进行的操作均花费O(1)。故时间复杂度为**O(s + p)**；
2. 空间复杂度：申请两个哈希表作为辅助空间，每个哈希表最多不会存放超过字符集大小的键值对，设字符集大小为C，则空间复杂度为**O(C)**。

