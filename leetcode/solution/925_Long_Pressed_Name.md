# 第925题 长按键入

## 1 题目

你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。

你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。

示例 1：

```
输入：name = "alex", typed = "aaleex"
输出：true
解释：'alex' 中的 'a' 和 'e' 被长按。
```

示例 2：

```
输入：name = "saeed", typed = "ssaaedd"
输出：false
解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
```

示例 3：

```
输入：name = "leelee", typed = "lleeelee"
输出：true
```

示例 4：

```
输入：name = "laiden", typed = "laiden"
输出：true
解释：长按名字中的字符并不是必要的。
```

## 2 解法

### 2.1 初版代码

```
class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int nameLen = name.length();
        int typedLen = typed.length();

        if (nameLen > typedLen) {
            return false;
        }

        int namePtr = 0;
        int typedPtr = 0;

        char memo = '\0';

        while (namePtr < nameLen && typedPtr < typedLen) {
            char nameChar = name.charAt(namePtr);
            char typedChar = typed.charAt(typedPtr);

            if (nameChar == typedChar) {
                memo = typedChar;
                namePtr++;
                typedPtr++;
                continue;
            } 
            
            if (nameChar != typedChar && typedChar == memo) {
                typedPtr++;
            } else if (nameChar != typedChar && typedChar != memo) {
                return false;
            }    
        }

        if (namePtr == nameLen && typedPtr < typedLen) {
            while (typedPtr < typedLen) {
                if (typed.charAt(typedPtr) != memo) {
                    return false;
                }
                typedPtr++;
            }

            if (typedPtr == typedLen) {
                return true;
            }
        }
        
        if (namePtr < nameLen && typedPtr == typedLen) {
            return false;
        }

        return true;
    }
}
```

复杂度分析：

设name共有m个字符，typed共有n个字符。

1. 时间复杂度：最坏情况下，name和typed都扫描一遍，故时间复杂度为**O(m + n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。

### 2.2 优化版代码

```
class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int namePtr = 0;
        int typedPtr = 0;

        int nameLen = name.length();
        int typedLen = typed.length();

        while (typedPtr < typedLen) {
            if (namePtr < nameLen && 
                name.charAt(namePtr) == typed.charAt(typedPtr)) {
                namePtr++;
                typedPtr++;
            } else if (typedPtr > 0 && 
                       typed.charAt(typedPtr) == 
                       typed.charAt(typedPtr - 1)) {
                typedPtr++;
            } else {
                return false;
            }
        }

        return namePtr == nameLen;
    }
}
```

复杂度分析：

设name共有m个字符，typed共有n个字符。

1. 时间复杂度：最坏情况下，name和typed都扫描一遍，故时间复杂度为**O(m + n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。



