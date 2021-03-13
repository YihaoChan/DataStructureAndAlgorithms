# 第217题 存在重复元素

## 1 题目

给定一个整数数组，判断是否存在重复元素。

如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。

示例 1:

```
输入: [1,2,3,1]
输出: true
```

示例 2:

```
输入: [1,2,3,4]
输出: false
```

示例 3:

```
输入: [1,1,1,3,3,4,3,2,4,2]
输出: true
```

## 2 解法

哈希集合HashSet，add一个元素，如果元素在集合中已经存在，则add方法返回false，即该元素在数组中至少出现两次，直接返回true即可。

```
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();

        int len = nums.length;

        for (int i = 0; i < len; i++) {
            int item = nums[i];

            if (!set.add(item)) {
                return true;
            }
        }

        return false;
    }
}
```

复杂度分析：

1. 时间复杂度：顺序扫描数组花费**O(n)**；
2. 空间复杂度：创建哈希集合作为辅助空间，空间复杂度为**O(n)**。