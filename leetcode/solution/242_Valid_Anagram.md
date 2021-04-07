# 第242题 有效的字母异位词

## 1 题目

给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

示例 1:

```
输入: s = "anagram", t = "nagaram"
输出: true
```

示例 2:

```
输入: s = "rat", t = "car"
输出: false
```

说明:
你可以假设字符串只包含小写字母。

## 2 解法

注意：获取字符串中的一个字符时，str.charAt(i)在O(1)时间内完成，而如果先将string通过str.toCharArray()转成字符数组，再通过下标获得的话，就要花费O(n)，因为toCharArray()方法要创建一个新数组，然后遍历字符不断添加进去，比较花时间。

### 2.1 哈希表

首先，将模式串中的字符及其出现次数记录到表中。当遍历主串时，如果字符出现在表中，则将出现次数-1，否则，出现次数为0 - 1 = -1。如果该字符在表中的value小于0，说明在模式串中没有出现过这个字符，或主串中的出现次数大于模式串中的出现次数，则直接返回false。

```
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) {
            return false;
        }

        int sLen = s.length();
        int tLen = t.length();

        if (sLen != tLen) {
            return false;
        }

        Map<Character, Integer> sCharToTimes = new HashMap<>();

        for (int i = 0; i < sLen; i++) {
            char sChar = s.charAt(i);

            sCharToTimes.put(sChar, 
            sCharToTimes.getOrDefault(sChar, 0) + 1);
        }

        for (int j = 0; j < tLen; j++) {
            char tChar = t.charAt(j);

            sCharToTimes.put(tChar, 
            sCharToTimes.getOrDefault(tChar, 0) - 1);

            if (sCharToTimes.get(tChar) < 0) {
                return false;
            }
        }

        return true;
    }
}
```

复杂度分析：

设模式串的字符个数为m，主串的字符个数为n。如果m != n，直接返回false，否则开始遍历，此时m == n。

1. 时间复杂度：遍历模式串和主串，共花费**O(m + n) = O(2n) = O(n)**；
2. 空间复杂度：建立哈希表最坏情况下存储字符集中所有元素，因此空间复杂度为**O(C)**，C为字符集大小。

### 2.2 数组

题目已说明字符串中只包含小写字母，因此可以构造一个长度为26的数组，数组的下标为字符相对于'a'的大小。其余思路与哈希表相同。数组相对于哈希表的好处就是，哈希表扩容时会花费掉一些时间，而数组在这一点上能够节约一些时间。

```
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null || t == null) {
            return false;
        }

        int sLen = s.length();
        int tLen = t.length();

        if (sLen != tLen) {
            return false;
        }

        int[] table = new int[26];

        for (int i = 0; i < sLen; i++) {
            char sChar = s.charAt(i);

            table[(int) (sChar - 'a')]++;
        }

        for (int j = 0; j < tLen; j++) {
            char tChar = t.charAt(j);

            table[(int) (tChar - 'a')]--;

            if (table[(int) (tChar - 'a')] < 0) {
                return false;
            }
        }

        return true;
    }
}
```

复杂度分析：

设模式串的字符个数为m，主串的字符个数为n。如果m != n，直接返回false，否则开始遍历，此时m == n。

1. 时间复杂度：遍历模式串和主串，共花费**O(m + n) = O(2n) = O(n)**；
2. 空间复杂度：建立数组最坏情况下存储26个元素，因此空间复杂度为**O(C)**，C为26。

## 3 进阶

如果输入字符串包含unicode字符怎么办？你能否调整你的解法来应对这种情况？

答：只能用哈希表。

