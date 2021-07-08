# 第34题 在排序数组中查找元素的第一个和最后一个位置

## 1 题目

给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

示例 1：

```
输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
```

示例 2：

```
输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
```

示例 3：

```
输入：nums = [], target = 0
输出：[-1,-1]
```

## 2 解法

注意**升序**排列，所以想到**二分查找**。注意到题目和示例中都指明了有多个值等于target的情况，因此明确问题为寻找**左侧边界**和**右侧边界**。

准备两个函数，一个寻找左侧边界，一个寻找右侧边界。分别调用这两个函数，返回得到的左侧边界和右侧边界组成的数组即可。

```c++
class Solution {
public:
    vector<int> searchRange(vector<int>& nums, int target) {
        vector<int> res;
       
        res.push_back(searchLeftBorder(nums, target));
        res.push_back(searchRightBorder(nums, target));

        return res;
    }
private:
    int searchLeftBorder(vector<int>& nums, int target) {
        int len = nums.size();

        if (len == 0) {
            return -1;
        }

        int left = 0;
        int right = len;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] == target) {
                right = mid;
            }
        }

        if (left == len) {
            return -1;
        }

        return nums[left] == target ? left : -1;
    }

    int searchRightBorder(vector<int>& nums, int target) {
        int len = nums.size();

        if (len == 0) {
            return -1;
        }

        int left = 0;
        int right = len;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] == target) {
                left = mid + 1;
            }
        }

        if (right == 0) {
            return -1;
        }

        return nums[right - 1] == target ? right - 1 : -1;
    }
};
```

复杂度分析：

1. 时间复杂度：两个函数分别都用了**O(logn)**的时间复杂度，且调用这两个函数的时间复杂度为**相加**，故总的时间复杂度为**O(logn)**；
2. 空间复杂度：两个函数分别都用了常数个额外空间，且调用这两个函数的空间复杂度为**相加**，故总的空间复杂度为**O(1)**。

