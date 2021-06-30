# 第349题 两个数组的交集

## 1 题目

给定两个数组，编写一个函数来计算它们的交集。

示例 1：

```
输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2]
```

示例 2：

```
输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[9,4]
```

## 2 解法

```c++
class Solution {
public:
    vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
        vector<int> res;
        int len1 = nums1.size();
        int len2 = nums2.size();
        if (len1 == 0 || len2 == 0) {
            return res;
        }

        unordered_set<int> s1;
        unordered_set<int> s2;

        for (int i = 0; i < len1; ++i) {
            s1.emplace(nums1[i]);
        }

        for (int j = 0; j < len2; ++j) {
            if (s1.find(nums2[j]) != s1.end()) {
                s2.emplace(nums2[j]);
            }
        }

        for (int item : s2) {
            res.push_back(item);
        }

        return res;
    }
};
```

复杂度分析：

1. 时间复杂度：将第一个数组中的元素添加到哈希集合中需要花费O(n)，判断第二个数组中的元素有无在第一个哈希集合中出现过并添加到第二个哈希集合中花费O(m)，故扫描两数组阶段共花费O(m+n)，最后将哈希集合中的元素输出到数组花费O(m)。因此，总时间复杂度为**O(m+n)**；

2. 空间复杂度：第一个哈希集合花费O(n)的空间，第二个哈希集合花费O(m)的空间，输出数组花费O(m)的空间。因此，总空间复杂度为**O(m+n)**。

   