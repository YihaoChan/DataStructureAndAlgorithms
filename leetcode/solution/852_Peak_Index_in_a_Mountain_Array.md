# 第852题  山脉数组的峰顶索引

# 1 题目

符合下列属性的数组 arr 称为 山脉数组 ：
arr.length >= 3
存在 i（0 < i < arr.length - 1）使得：
arr[0] < arr[1] < ... arr[i-1] < arr[i]
arr[i] > arr[i+1] > ... > arr[arr.length - 1]
给你由整数组成的山脉数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i 。

示例1：

```
输入：arr = [0,1,0]
输出：1
```

示例2：

```
输入：arr = [0,2,1,0]
输出：1
```

示例3：

```
输入：arr = [0,10,5,2]
输出：1
```

# 2 解法



```
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid == 0) {
                left = mid + 1;
                continue;
            }

            int currItem = arr[mid];
            int leftItem = arr[mid - 1];
            int rightItem = arr[mid + 1];

            if (leftItem < currItem && currItem < rightItem) {
                left = mid + 1;
            } else if (leftItem > currItem && currItem > rightItem) {
                right = mid - 1;
            } else if (leftItem < currItem && currItem > rightItem) {
                return mid;
            }
        }

        return -1;
    }
}
```

复杂度分析：

1. 时间复杂度：二分查找时间复杂度为**O(logn)**；
2. 空间复杂度：只用到常数个额外空间，所以空间复杂度为**O(1)**。