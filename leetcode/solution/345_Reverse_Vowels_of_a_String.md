# 第345题 反转字符串中的元音字母

## 1 题目

编写一个函数，以字符串作为输入，反转该字符串中的元音字母。

示例 1：

```
输入："hello"
输出："holle"
```

示例 2：

```
输入："leetcode"
输出："leotcede"
```

## 2 解法

左右指针分别指向数组两端，交换相应元素。注意元音有大小写。

```
class Solution {
    public String reverseVowels(String s) {
        char[] sArr = s.toCharArray();

        int left = 0;
        int right = sArr.length - 1;

        while (left < right) {
            boolean isLowVowel = isVowel(sArr[left]);
            boolean isHighVowel = isVowel(sArr[right]);

            if (isLowVowel && isHighVowel) {
                char temp = sArr[right];
                sArr[right] = sArr[left];
                sArr[left] = temp;

                left++;
                right--;
            } else if (isLowVowel && !isHighVowel){
                right--;
            } else if (!isLowVowel && isHighVowel) {
                left++;
            } else if (!isLowVowel && !isHighVowel) {
                left++;
                right--;
            }
        }

        return String.valueOf(sArr);
    }

    private boolean isVowel(char letter) {
        return (
            letter == 'A' || letter == 'E' || letter == 'I' ||
            letter == 'O' || letter == 'U' || letter == 'a' ||
            letter == 'e' || letter == 'i' || letter == 'o' ||
            letter == 'u'
        ); 
        
        // 逐个比较比二分查找、HashSet更快，暂时不知道为什么
    }
}
```

复杂度分析：

1. 时间复杂度：主要取决于数组的规模，判断一个字符是否为元音相对于数组规模来说花费时间较少，故时间复杂度为**O(n)**；
2. 空间复杂度：空间复杂度：只用到常数个额外空间，空间复杂度为**O(1)**。