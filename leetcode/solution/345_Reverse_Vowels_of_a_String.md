# 第345题 反转字符串中的元音字母

# 1 题目

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

# 2 解法

双指针，注意元音有大小写。

```
class Solution {
    public String reverseVowels(String s) {
        char[] sArr = s.toCharArray();

        int low = 0;
        int high = sArr.length - 1;

        while (low < high) {
            boolean isLowVowel = isVowel(sArr[low]);
            boolean isHighVowel = isVowel(sArr[high]);

            if (isLowVowel && isHighVowel) {
                char temp = sArr[high];
                sArr[high] = sArr[low];
                sArr[low] = temp;

                low++;
                high--;
            } else if (isLowVowel && !isHighVowel){
                high--;
            } else if (!isLowVowel && isHighVowel) {
                low++;
            } else if (!isLowVowel && !isHighVowel) {
                low++;
                high--;
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
        
        // 逐个比较比二分查找、HashSet快很多，暂时不知道为什么
    }
}
```

复杂度分析：

1. 时间复杂度：主要取决于数组的规模，判断一个字符是否为元音相对于数组规模来说花费时间较少，故时间复杂度为**O(n)**；
2. 空间复杂度：空间复杂度：只用到常数个额外空间，空间复杂度为**O(1)**。