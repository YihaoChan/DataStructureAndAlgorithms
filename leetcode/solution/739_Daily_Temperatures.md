# 第739题 每日温度

## 1 题目

请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。

例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

## 2 解法

单调栈。维护一个从栈顶到栈底的元素单调递增的栈，栈中存放数组元素的索引：T从后往前遍历，如果栈不为空且当前索引指向的元素**大于等于**栈顶索引指向的元素，就将栈顶的索引弹出。当扫描到的索引找到了合适的入栈时机的时候，如果此时栈为空，说明找不到下一个比当前扫描的索引指向的元素还大的元素，故在结果数组中记此时位置的值为0。如果栈不为空，则在结果数组的相应位置记录值：栈顶索引 - 当前扫描的索引。

```
class Solution {
    public int[] dailyTemperatures(int[] T) {
        if (T == null) {
            return null;
        }

        Stack<Integer> monotonicStack = new Stack<>();

        int tLen = T.length;

        int[] res = new int[tLen];

        for (int i = tLen - 1; i >= 0; i--) {
            while (!monotonicStack.isEmpty() && 
            	   T[i] >= T[monotonicStack.peek()]) {
                monotonicStack.pop();
            }

            res[i] = monotonicStack.isEmpty() ? 0 :
            		 (monotonicStack.peek() - i);

            monotonicStack.push(i);
        }

        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：从后往前扫描数组花费O(n)，每个索引入栈一次，且至多出栈一次，均为O(1)操作，故总时间复杂度为**O(n)**；
2. 空间复杂度：单调栈中最多存放n个索引，故空间复杂度为**O(n)**。