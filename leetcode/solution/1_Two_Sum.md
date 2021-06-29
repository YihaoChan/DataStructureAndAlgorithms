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

思路：利用哈希表，存储之前扫描过的元素和其在数组中的下标。

每次扫描到当前元素，首先判断target和该元素**之差**有没有被存入哈希表中。如果有，说明之前存入的数 + 当前元素 == target，返回这两个数的下标即可。否则，将当前元素和它在数组中的下标存入哈希表中，继续往后扫描。

```c++
class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        vector<int> res;

        int size = nums.size();
        if (size == 0) {
            return res;
        }

        unordered_map<int, int> m;

        for (int i = 0; i < size; ++i) {
            int item = nums[i];
            int diff = target - item;
            if (m.find(diff) != m.end()) {
                res.push_back(m[diff]);
                res.push_back(i);
                break;
            }
            m[item] = i;
        }

        return res;
    }
};
```

复杂度分析：

1. 时间复杂度：只进行一次顺序扫描，扫描n个元素，且哈希表查询时间为O(1)，因此时间复杂度为**O(n)**；
2. 空间复杂度：新建哈希表用于存储之前扫描过的元素，空间复杂度为**O(n)**。

Tips：**用空间换时间**。