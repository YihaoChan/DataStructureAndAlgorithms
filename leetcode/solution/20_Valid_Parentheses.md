# 第20题 有效的括号

## 1 题目

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。

示例 1：

```
输入：s = "()"
输出：true
```

示例 2：

```
输入：s = "()[]{}"
输出：true
```

示例 3：

```
输入：s = "(]"
输出：false
```

示例 4：

```
输入：s = "([)]"
输出：false
```

示例 5：

```
输入：s = "{[]}"
输出：true
```

## 2 解法

```
class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();

        int len = s.length();

        for (int i = 0; i < len; i++) {
            Character item = s.charAt(i);

            if (item.equals('(') || item.equals('[') || 
                item.equals('{')) {
                stack.push(item);
                continue;
            } 

            if (stack.isEmpty()) {
                return false;
            }
            
            Character popItem = stack.pop();

            if (item.equals(')')) {
                if (!popItem.equals('(')) {
                    return false;
                }
            } else if (item.equals(']')) {
                if (!popItem.equals('[')) {
                    return false;
                }
            } else if (item.equals('}')) {
                if (!popItem.equals('{')) {
                    return false;
                }
            }    
        }

        return stack.isEmpty();
    }
}
```

复杂度分析：

1. 时间复杂度：最坏情况下字符串的每个元素都要扫描一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：额外开辟一个栈，故空间复杂度为**O(n)**。

