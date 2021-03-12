# 第76题 最小覆盖子串

给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。

示例 1：

```
输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
```

示例 2：

```
输入：s = "a", t = "a"
输出："a"
```

# 2 解法

滑动窗口。先用一个哈希表记录需要统计的字母及其出现次数，比如示例1中，通过子串t得知，A、B、C都应该出现1次。之后遍历主串，判断当前字母是否为应该统计的字母，如果是，则增加其次数。当字母记录次数等于应该统计的字母出现次数时，满足条件的字母数量就加1。当满足条件的字母数量等于应该统计的字母数量时，记录下此刻的主串起始位置start和在主串中的长度len，然后开始通过增加left指针收缩窗口。当删除的left指向的字母次数恰好等于需要统计的字母出现次数时，说明删除完这个字母后满足条件的字母数量就减1，此时停止收缩，开始通过增加right指针扩张窗口。不断循环上述过程，并更新最小的len和新的start，返回substring(start, start + len + 1)。

```
class Solution {
    public String minWindow(String s, String t) {
        if (
            s == null || t == null ||
            s.length() == 0 || t.length() == 0 ||
            s.length() < t.length()
        ) {
            return "";
        }

        Map<Character, Integer> target = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        // 记录需要匹配的字母及其个数
        for (Character c : tArr) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        int sLen = sArr.length;

        int left = 0;
        int right = 0;

        int satisfy = 0; // 满足条件的字母个数

        int start = 0; // 包含匹配字符串的源子字符串起始位置
        int subStrLen = sLen; // 包含匹配字符串的源子字符串长度

        for (right = 0; right < sLen; right++) {
            char current = sArr[right];

            // 如果当前字母为需要搜索的字母，就更新window中对应出现次数
            if (target.containsKey(current)) {
            	window.put(
                	current, window.getOrDefault(current, 0) + 1
                );

                // 如果当前字符凑够了次数，满足条件的字母数量就+1
                if (window.get(current).equals(target.get(current))) {
                    satisfy++;
                }
            }

            // 如果需要统计的字母都已经满足出现次数，就开始收缩窗口
            while (satisfy == target.size()) {
                if (right - left < subStrLen) {
                    start = left;

                    subStrLen = right - left;
                }

                char removeChar = sArr[left];

                left++;

                if (target.containsKey(removeChar)) {
                    // 如果删除左边字符前，该字符满足出现次数
                    // 那么删除后就不满足，故满足出现次数的字母数量应该-1
                    if (
                         window.get(removeChar)
                         .equals(target.get(removeChar))
                    ) {
                        satisfy--;
                    }

                    window.put(
                    	removeChar, window.get(removeChar) - 1
                    );
                }
            }
        }

        return subStrLen == sLen ?
                "" : s.substring(start, start + subStrLen + 1);
    }
}
```

PS. 判断Integer是否相等时，最好不要用==，因为当用==判断时，会判断地址，而Integer只会缓存-128~127之间的数值，所以当超过这个区间的值后，就会new一个对象，出现地址不一致，此时就出现判断错误(在这道题里面就会无法通过最后一个测试用例)。所以，要用equals！

复杂度分析：

1. 时间复杂度：首先将t中的元素都插入哈希表，花费O(t)。遍历s的每个字符花费O(s)。故时间复杂度为**O(s + t)**；
2. 空间复杂度：申请两个哈希表作为辅助空间，每个哈希表最多不会存放超过字符集大小的键值对，设字符集大小为C，则空间复杂度为**O(C)**。

