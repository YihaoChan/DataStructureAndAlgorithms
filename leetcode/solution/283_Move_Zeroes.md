# 第283题 移动零

# 1 题目

给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

```
输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
```

# 2 解法

快慢指针，判断快指针指向的元素是否等于0，如果不等，则把它搬到慢指针处，并更新慢指针。

```
class Solution {
    public void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;

        int len = nums.length;

        while (fast < len) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];

                slow++;
            } 
            
            fast++;
        }

        while (slow < len) {
            nums[slow] = 0;

            slow++;
        }
    }
}
```

复杂度分析：

1. 时间复杂度：顺序扫描花费**O(n)**；
2. 空间复杂度：只用到常数个额外空间，空间复杂度为**O(1)**。

