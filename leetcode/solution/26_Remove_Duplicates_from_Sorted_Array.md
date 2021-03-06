# 第26题 删除排序数组中的重复项

## 1 题目

给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。

示例 1:

```
给定数组 nums = [1,1,2], 

函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。 
```

示例 2:

```
给定 nums = [0,0,1,1,1,2,2,3,3,4],

函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
```

## 2 解法

快慢指针，判断后面一个元素是否等于当前元素，如果不等，则把它搬到前面，并更新慢指针。

```c++
class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        int slow = 0;
        int fast = 0;
        int len = nums.size();

        if (len == 0) {
            return 0;
        }
        
        while (fast < len) {
            if (nums[fast] != nums[slow]) {
                ++slow;
                nums[slow] = nums[fast];
            }

            ++fast;
        }

        return slow + 1;
    }
};
```

复杂度分析：

1. 时间复杂度：顺序扫描花费**O(n)**；
2. 空间复杂度：只用到常数个额外空间，空间复杂度为**O(1)**。