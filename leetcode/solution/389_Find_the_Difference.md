# 第389题 找不同

## 1 题目

给定两个字符串 s 和 t，它们只包含小写字母。

字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。

请找出在 t 中被添加的字母。

示例 1：

```
输入：s = "abcd", t = "abcde"
输出："e"
解释：'e' 是那个被添加的字母。
```

示例 2：

```
输入：s = "", t = "y"
输出："y"
```

示例 3：

```
输入：s = "a", t = "aa"
输出："a"
```

示例 4：

```
输入：s = "ae", t = "aea"
输出："a"
```

## 2 解法

O(n)空间的解法很容易，但是应该要想到O(1)空间的解法。

## 2.1 异或

将两个字符串拼接在一起，之后遍历新字符串的每个字符，并进行异或操作，最后的值即为那个不同的字符的ASCII码。

```
class Solution {
    public char findTheDifference(String s, String t) {
        StringBuffer stringBuffer = new StringBuffer().append(s).append(t);    

        int len = stringBuffer.length();

        int res = 0;

        for (int i = 0; i < len; i++) {
            res ^= (int) stringBuffer.charAt(i);    
        }

        return (char) res;
    }
}
```

或：

```
class Solution {
    public char findTheDifference(String s, String t) {
        StringBuffer stringBuffer = new StringBuffer().append(s).append(t);

        char res = '\0';

        int len = stringBuffer.length();

        for (int i = 0; i < len; i++) {
            res ^= stringBuffer.charAt(i);
        }

        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：记字符串s有n个字符，则一共遍历2n + 1个字符，故时间复杂度为**O(n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。

## 2.2 ASCII码求和

分别对字符串s和字符串t中的每个字符的ASCII码进行累加，之后将字符串t的累加结果减去字符串s的累加结果，即为那个不同的字符的ASCII码。

```
class Solution {
    public char findTheDifference(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        int sVal = 0;
        int tVal = 0;

        for (int i = 0; i < sLen; i++) {
            sVal += (int) s.charAt(i);
        }

        for (int j = 0; j < tLen; j++) {
            tVal += (int) t.charAt(j);
        }

        return (char) (tVal - sVal);
    }
}
```

复杂度分析：

1. 时间复杂度：记字符串s有n个字符，则一共遍历2n + 1个字符，故时间复杂度为**O(n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。

