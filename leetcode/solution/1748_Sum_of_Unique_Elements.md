# 第1748题 唯一元素的和

## 1 题目

给你一个整数数组 nums 。数组中唯一元素是那些只出现 恰好一次 的元素。

请你返回 nums 中唯一元素的 和 。

示例 1：

```
输入：nums = [1,2,3,2]
输出：4
解释：唯一元素为 [1,3] ，和为 4 。
```

示例 2：

```
输入：nums = [1,1,1,1,1]
输出：0
解释：没有唯一元素，和为 0 。
```

示例 3 ：

```
输入：nums = [1,2,3,4,5]
输出：15
解释：唯一元素为 [1,2,3,4,5] ，和为 15 。
```

- 1 <= nums.length <= 100
- 1 <= nums[i] <= 100

## 2 解法

用一个额外数组保存元素出现的次数，比哈希表更快。根据原数组的最大值开辟空间。

```
class Solution {
    public int sumOfUnique(int[] nums) {
        int len = nums.length;

        int[] info = new int[101];

        for (int i = 0; i < len; i++) {
            int item = nums[i];

            info[item] += 1;
        }

        int sum = 0;

        for (int j = 0; j < 101; j++) {
            if (info[j] == 1) {
                sum += j;
            }
        }

        return sum;
    }
}
```

复杂度分析：

1. 时间复杂度：顺序扫描花费**O(n)**；
2. 空间复杂度：额外空间花费**O(n)**。