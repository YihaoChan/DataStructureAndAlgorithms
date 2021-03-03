# 第709题 转换成小写字母

# 1 题目

实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。

示例 1：

```
输入: "Hello"
输出: "hello"
```

示例 2：

```
输入: "here"
输出: "here"
```

示例 3：

```
输入: "LOVELY"
输出: "lovely"
```

# 2 解法

```
class Solution {
    public String toLowerCase(String str) {
        char[] charArr = str.toCharArray();

        int len = charArr.length;

        for (int i = 0; i < len; i++) {
            int letterASCII = (int) charArr[i];

            if (65 <= letterASCII && letterASCII <= 90) {
                charArr[i] = (char) (letterASCII + 32);
            }
        }

        return String.valueOf(charArr);
    }
}
```

复杂度分析：

1. 时间复杂度：每个元素都扫描一次，花费**O(n)**；
2. 空间复杂度：只用到常数个额外空间，空间复杂度为**O(1)**。