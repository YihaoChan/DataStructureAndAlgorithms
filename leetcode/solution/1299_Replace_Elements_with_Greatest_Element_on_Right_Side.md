# 第1299题 将每个元素替换为右侧最大元素

## 1 题目

给你一个数组 arr ，请你将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换。

完成所有替换操作后，请你返回这个数组。

示例 1：

```
输入：arr = [17,18,5,4,6,1]
输出：[18,6,6,6,1,-1]
解释：

- 下标 0 的元素 --> 右侧最大元素是下标 1 的元素 (18)
- 下标 1 的元素 --> 右侧最大元素是下标 4 的元素 (6)
- 下标 2 的元素 --> 右侧最大元素是下标 4 的元素 (6)
- 下标 3 的元素 --> 右侧最大元素是下标 4 的元素 (6)
- 下标 4 的元素 --> 右侧最大元素是下标 5 的元素 (1)
- 下标 5 的元素 --> 右侧没有其他元素，替换为 -1
```

示例 2：

```
输入：arr = [400]
输出：[-1]
解释：下标 0 的元素右侧没有其他元素。
```

## 2 解法

### 2.1 单调栈

```
class Solution {
    public int[] replaceElements(int[] arr) {
        int len = arr.length;

        Deque<Integer> monotonicStack = new LinkedList<>();
        int[] res = new int[len];

        for (int i = len - 1; i >= 0; i--) {
            int item = arr[i];

            int stackTop = 0;

            if (monotonicStack.isEmpty()) {
                stackTop = -1;
            } else {
                stackTop = monotonicStack.peek();
            }

            if (item > stackTop) {
                monotonicStack.push(item);
            }

            res[i] = stackTop;
        }

        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：从后往前遍历数组，时间复杂度为**O(n)**；
2. 空间复杂度：最坏情况下，单调栈存放n个元素，故空间复杂度为**O(n)**。

### 2.2 原地

```
class Solution {
    public int[] replaceElements(int[] arr) {
        int len = arr.length;

        int max = -1;

        for (int i = len - 1; i >= 0; i--) {
            int item = arr[i];

            arr[i] = max;

            if (item > max) {
                max = item;
            }
        }

        return arr;
    }
}
```

复杂度分析：

1. 时间复杂度：从后往前遍历数组，时间复杂度为**O(n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。