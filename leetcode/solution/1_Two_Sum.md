# 第1题 两数之和

## 1 题目

给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。

示例1：

```
输入：nums = [2,7,11,15], target = 9
输出：[0,1]
解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
```

示例2：

```
输入：nums = [3,2,4], target = 6
输出：[1,2]
```

示例3：

```
输入：nums = [3,3], target = 6
输出：[0,1]
```

## 2 解法

### 2.1 二重循环遍历

对于当前扫描到的元素i，以它为起点进行二重扫描数组中元素j。如果发现i + j的值等于target，则返回i、j。

复杂度分析：

1. 时间复杂度：进行两重扫描，时间复杂度为**O(n<sup>2</sup>)**；

2. 空间复杂度：只有常数个额外空间，空间复杂度为**O(1)**。

由于时间复杂度过高，因此需要优化。

### 2.2 一次哈希

思路：利用哈希表，存储之前扫描过的元素和其在数组中的下标。

每次扫描到当前元素，首先判断target和该元素**之差**有没有被存入哈希表中。如果有，说明之前存入的数 + 当前元素 == target，返回这两个数的下标即可。否则，将当前元素和它在数组中的下标存入哈希表中，继续往后扫描。

```
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;

        Map<Integer, Integer> visited = new HashMap<>();

        int i;

        Integer prevIndex = null;

        for (i = 0; i < len; i++) {
            int item = nums[i];

            prevIndex = visited.get(target - item);

            if (prevIndex == null) {
                visited.put(item, i);
            } else {
                break;
            }
        }

        return new int[] {i, prevIndex};
    }
}
```

复杂度分析：

1. 时间复杂度：只进行一次顺序扫描，扫描n个元素，且哈希表查询时间为O(1)，因此时间复杂度为**O(n)**；
2. 空间复杂度：新建哈希表用于存储之前扫描过的元素，空间复杂度为**O(n)**。

Tips：**用空间换时间**。