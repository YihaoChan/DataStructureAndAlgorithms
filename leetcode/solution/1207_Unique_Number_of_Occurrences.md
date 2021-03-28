# 第1207题 独一无二的出现次数

## 1 题目

给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。

如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。

示例 1：

```
输入：arr = [1,2,2,1,1,3]
输出：true
解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
```

示例 2：

```
输入：arr = [1,2]
输出：false
```

示例 3：

```
输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
输出：true
```

## 2 解法

HashMap + HashSet.

```
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        if (arr == null) {
            return false;
        }

        int len = arr.length;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            int item = arr[i];

            map.put(item, map.getOrDefault(item, 0) + 1);
        }

        Set<Integer> set = new HashSet<>();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int times = entry.getValue();

            if (!set.add(times)) {
                return false;
            }
        }

        return true;
    }
}
```

复杂度分析：

1. 时间复杂度：顺序扫描数组并将出现次数添加到哈希表中花费O(n)，最坏情况下将哈希表中元素取出并添加到哈希集合中花费O(n)，故总时间复杂度为**O(n)**；
2. 空间复杂度：哈希表花费O(n)空间，最坏情况下哈希集合花费O(n)空间，故总空间复杂度为**O(n)**。