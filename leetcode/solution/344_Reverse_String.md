# 第344题 反转字符串

## 1 题目

编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。

不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。

你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。

示例 1：

```
输入：["h","e","l","l","o"]
输出：["o","l","l","e","h"]
```

示例 2：

```
输入：["H","a","n","n","a","h"]
输出：["h","a","n","n","a","H"]
```

## 2 解法

### 2.1 迭代

左右指针分别指向数组两端，交换相应元素。

```
class Solution {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            char temp = s[right];
            s[right] = s[left];
            s[left] = temp;

            left++;
            right--;
        }
    }
}
```

复杂度分析：

1. 时间复杂度：每个元素都扫描一次，花费**O(n)**；
2. 空间复杂度：只用到常数个额外空间，空间复杂度为**O(1)**。

### 2.2 递归

当交换的起始点到达数组的一半时，停止交换。

```
class Solution {
    public void reverseString(char[] s) {
        if (s == null) {
            return;
        }

        reverseString(s, 0, s.length - 1);
    }

    private void reverseString(char[] s, int start, int end) {
        if (start == s.length / 2) {
            return;
        }

        reverseString(s, start + 1, end - 1);

        char temp = s[end];
        s[end] = s[start];
        s[start] = temp;

        return;
    }
}
```

复杂度分析：

1. 时间复杂度：start只走了n / 2步，每一步进行的start和end交换操作花费O(1)，因此总时间复杂度为O(n / 2) = **O(n)**；
2. 空间复杂度：递归n / 2层，因此总空间复杂度为O(n / 2) = **O(n)**。