# 第350题 两个数组的交集 II

## 1 题目

给定两个数组，编写一个函数来计算它们的交集。

示例 1：

```
输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2,2]
```

示例 2:

```
输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[4,9]
```

## 2 解法

```c++
class Solution {
public:
    vector<int> intersect(vector<int>& nums1, vector<int>& nums2) {
        vector<int> res;
        int len1 = nums1.size();
        int len2 = nums2.size();
        if (len1 == 0 || len2 == 0) {
            return res;
        }

        unordered_map<int, int> num2count;

        for (int i = 0; i < len1; ++i) {
            ++num2count[nums1[i]];
        }

        for (int j = 0; j < len2; ++j) {
            int num = nums2[j];
            if (num2count[num] > 0) {
                res.push_back(num);
                --num2count[num];
            }
        }

        return res;
    }
};
```

复杂度分析：

设nums1有m个元素，nums2有n个元素。

1. 时间复杂度：扫描nums1和nums2两个数组花费O(m + n)，故总时间复杂度为**O(m + n)**；
2. 空间复杂度：最坏情况下，nums1是nums2的子集，或nums2是nums1的子集。此时，哈希表空间最大花费O(min(m, n))，即哈希表最多容纳m和n的较小者个元素；记录相交元素的list花费O(min(m, n))；返回的答案数组花费O(min(m, n))。因此，总空间复杂度为**O(min(m, n))**。