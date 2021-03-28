# 第922题 按奇偶排序数组 II

## 1 题目

给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。

对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。

你可以返回任何满足上述条件的数组作为答案。A.length % 2 == 0。

示例：

```
输入：[4,2,5,7]
输出：[4,5,2,7]
解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
```

## 2 解法

双指针，一个用于判断奇数下标处的元素是否为奇数，一个用于判断偶数下标处的元素是否为偶数。

```
class Solution {
    public int[] sortArrayByParityII(int[] nums) {
        int len = nums.length;

        int evenPtr = 0;
        int oddPtr = 1;

        while (evenPtr < len && oddPtr < len) {
            if (nums[evenPtr] % 2 == 0) {
                evenPtr += 2;
            } else if (nums[oddPtr] % 2 != 0) {
                oddPtr += 2;
            } else if (nums[evenPtr] % 2 != 0 && 
            	       nums[oddPtr] % 2 == 0) {
                int temp = nums[oddPtr];
                nums[oddPtr] = nums[evenPtr];
                nums[evenPtr] = temp;

                evenPtr += 2;
                oddPtr += 2;
            }
        }

        return nums;
    }
}
```

复杂度分析：

1. 时间复杂度：顺序扫描花费O(n)，交换操作花费O(1)，故总时间复杂度为**O(n)**；
2. 空间复杂度：仅用到常数个额外空间，故空间复杂度为**O(1)**。