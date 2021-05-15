# 二分查找及其变种

## 1 原始二分查找

在有序数组中，搜索一个数，如果存在，返回其索引，否则返回-1。

```
int binarySearch(int[] nums, int target) {
	if (nums.length == 0) return -1;
	
    int left = 0; 
    int right = nums.length - 1; // (1)

    while (left <= right) { // (2)
        int mid = left + (right - left) / 2; (3)
        
        if(nums[mid] == target)
            return mid; 
        else if (nums[mid] < target)
            left = mid + 1; // (4)
        else if (nums[mid] > target)
            right = mid - 1; // (5)
        }
        
    return -1;
}
```

1. 注意(1)处为length - 1；
2. 因为(1)处为length - 1，故**搜索区间为[left, right]**，即可以包含左右边界。而(2)处while (left <= right)的结束条件为left == right + 1，代入区间为[right + 1, right]，确实应该跳出，所以while中可取等号；
3. (3)处不直接写(left + right) / 2而采用**left + (right - left) / 2**，是为了防止left + right太大造成溢出；
4. 当搜索不命中时，需要分为左右两个子区间，都是闭区间：**[left, mid - 1]**和**[mid + 1, right]**。故(4)(5)两处表达式如上。

复杂度分析

1. 时间复杂度：二分查找在最坏的情况下依次是n/2, n/4, n/8...，直到1为止。假设循环x次后查找到目标数。可以观察到分母是每次都乘以1/2，分子不变，所以可以根据题意列出下面等式：

   n * (1/2)<sup>x</sup> = 1.

   变换可得x = log<sub>2</sub>n = logn.

   故二分查找时间复杂度为**O(logn)**。

2. 空间复杂度：只用到常数个额外空间，所以空间复杂度为**O(1)**。

## 2 寻找左侧边界的二分搜索

当有多个待搜索值相等时，找到它们的左侧边界。

![左侧边界](images/左侧边界.png)

```
int left_bound(int[] nums, int target) {
    if (nums.length == 0) return -1;
    
    int left = 0;
    int right = nums.length; // (1)

    while (left < right) { // (2)
        int mid = left + (right - left) / 2;
        
        if (nums[mid] == target) {
            right = mid; // (3)
        } else if (nums[mid] < target) {
            left = mid + 1; // (4)
        } else if (nums[mid] > target) {
            right = mid; // (5)
        }
    }
    
    if (left == nums.length) return -1; // (6)
    
    return nums[left] == target ? left : -1; // (7)
}
```

1. 注意(1)处为length；
2. 因为(1)处为length，故**搜索区间为[left, right)**，即不可以包含右边界。而(2)处while (left < right)的结束条件为left == right ，代入区间为[right, right)，此时就应该跳出来了，所以while中不取等号；
3. 当搜索不命中时，需要分为左右两个子区间，都是左闭右开区间：**[left, mid)**和**[mid + 1, right)**。故(4)(5)两处表达式如上；
4. (3)表示当命中第一个和target相等的元素时，不要停下来，接着往左搜索；
5. (6)处表示target比数组中所有值都大；
6. (7)处表示，当left处元素等于target时，说明找不到该元素，返回-1。否则，返回left；
7. Trick：因为不断往右搜索，而且left左区间是**闭区间**，所以返回时返回left即可。而如果**left == length，会越界**，所以要单独分类left为length的情况。

复杂度分析

1. 时间复杂度：二分查找在最坏的情况下依次是n/2, n/4, n/8...，直到1为止。假设循环x次后查找到目标数。可以观察到分母是每次都乘以1/2，分子不变，所以可以根据题意列出下面等式：

   n * (1/2)<sup>x</sup> = 1.

   变换可得x = log<sub>2</sub>n = logn.

   故二分查找时间复杂度为**O(logn)**。

2. 空间复杂度：只用到常数个额外空间，所以空间复杂度为**O(1)**。****

## 3 寻找右侧边界的二分搜索

当有多个待搜索值相等时，找到它们的右侧边界。

![右侧边界](images/右侧边界.png)

```
int right_bound(int[] nums, int target) {
    if (nums.length == 0) return -1;
    
    int left = 0;
    int right = nums.length; // (1)

    while (left < right) { // (2)
        int mid = left + (right - left) / 2;
        
        if (nums[mid] == target) {
            left = mid + 1; // (3)
        } else if (nums[mid] < target) {
            left = mid + 1; // (4)
        } else if (nums[mid] > target) {
            right = mid; // (5)
        }
    }
    
    if (right == 0) return -1; // (6)
    
    return nums[right - 1] == target ? right - 1 : -1; // (7)
}
```

1. 注意(1)处为length；
2. 因为(1)处为length，故**搜索区间为[left, right)**，即不可以包含右边界。而(2)处while (left < right)的结束条件为left == right ，代入区间为[right, right)，此时就应该跳出来了，所以while中不取等号；
3. 当搜索不命中时，需要分为左右两个子区间，都是左闭右开区间：**[left, mid)**和**[mid + 1, right)**。故(4)(5)两处表达式如上；
4. (3)表示当命中第一个和target相等的元素时，不要停下来，接着**往右搜索**；
5. (6)处表示target比数组中所有值都小；
6. (7)处表示，当right - 1处元素等于target时，说明找不到该元素，返回-1。否则，返回left；
7. Trick：因为不断往右搜索，而且right右区间是**开区间**，所以right会比较大，故返回时要返回**right - 1**。而如果**right - 1== -1，会越界**，所以要单独分类right为0的情况。

复杂度分析

1. 时间复杂度：二分查找在最坏的情况下依次是n/2, n/4, n/8...，直到1为止。假设循环x次后查找到目标数。可以观察到分母是每次都乘以1/2，分子不变，所以可以根据题意列出下面等式：

   n * (1/2)<sup>x</sup> = 1.

   变换可得x = log<sub>2</sub>n = logn.

   故二分查找时间复杂度为**O(logn)**。

2. 空间复杂度：只用到常数个额外空间，所以空间复杂度为**O(1)**。

## 4 旋转数组

需求：寻找旋转数组的最小值。

用二分法查找，需要始终将目标值（最小值）套住，并不断收缩左边界或右边界。

左、中、右三个位置的值相比较，有以下几种情况：

1. 左值 < 中值，中值 < 右值 ：没有旋转，最小值在最左边，可以收缩右边界

   ```
         右
      中
   左
   ```

   e.g. [1, 3, 5]

2. 左值 > 中值，中值 < 右值 ：有旋转，最小值在左半边，可以收缩右边界

   ```
   左       
         右
      中
   ```

   e.g. [3, 1, 2]

3. 左值 < 中值，中值 > 右值 ：有旋转，最小值在右半边，可以收缩左边界

   ```
      中  
   左 
         右
   ```

   e.g. [3, 5, 1]

4. 左值 > 中值，中值 > 右值 ：单调递减，不可能出现

   ```
   左
      中
         右
   ```

   e.g. [5, 3, 1]

观察情况1和情况3，都是左值 < 中值，但是最小值一个在左边，一个在右边，说明不能用左值和中值判断，要用**中值和右值**。

1. 初始情况下，left为0，right为**len - 1**，因为要判断右边界的元素，所以不能越界；
2. 循环条件：left < right，保证左闭右开区间里面始终套住最小值；
3. 当nums[mid] > nums[right]时，nums[mid]一定不是最小值，所以直接往右走，**left = mid + 1**；
4. 当nums[mid] < nums[right]时，nums[mid]有可能就是最小值，所以如果此时right = mid - 1的话，就会错过最小值。所以，**right = mid**收缩右边界；
5. 搜索区间：**[left, mid]**和**[mid + 1, right]**。

特别地，如果题目中已说明可能出现**重复元素**，则在每一次比较nums[mid]和nums[right]时，如果nums[mid] == nums[right]，则让right--，**退化**一下。

### 4.1 无重复

```
class Solution {
    public int findMin(int[] nums) {
        int len = nums.length;

        int left = 0;
        int right = len - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            }
        }

        return nums[left];
    }
}
```

复杂度分析

1. 时间复杂度：二分查找时间复杂度为**O(logn)**;

2. 空间复杂度：只用到常数个额外空间，所以空间复杂度为**O(1)**。

### 4.2 有重复

```
class Solution {
    public int findMin(int[] nums) {
        int len = nums.length;

        int left = 0;
        int right = len - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                right = mid;
            } else if (nums[mid] == nums[right]) {
                right--;
            }
        }

        return nums[left];
    }
}
```

复杂度分析

1. 时间复杂度：无重复时的时间复杂度为O(logn)，而有重复时，最坏情况下会退化到O(n)，如[1, 1, 1, 1, 1]。因此，时间复杂度为**O(n)**；

2. 空间复杂度：只用到常数个额外空间，所以空间复杂度为**O(1)**。

## 5 题目

看到有序数组，肯定不能只是顺序扫描，这样就浪费了**有序**这个条件。所以要首先想到二分查找。

| 题目                                                         | 描述                         | 解答                                                         |
| ------------------------------------------------------------ | ---------------------------- | ------------------------------------------------------------ |
| [704. 二分查找](https://leetcode-cn.com/problems/binary-search/) | 二分查找序列                 | [704_Binary_Search](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/704_Binary_Search.md) |
| [35. 搜索插入位置](https://leetcode-cn.com/problems/search-insert-position/) | 二分查找序列                 | [35_Search_Insert_Position](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/35_Search_Insert_Position.md) |
| [278. 第一个错误的版本](https://leetcode-cn.com/problems/first-bad-version/) | 二分查找序列                 | [278_First_Bad_Version](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/278_First_Bad_Version.md) |
| [34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | 左侧边界与右侧边界           | [34_Find_First_and_Last_Position_of_Element_in_Sorted_Array](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/34_Find_First_and_Last_Position_of_Element_in_Sorted_Array.md) |
| [剑指 Offer 53 - I. 在排序数组中查找数字 I](https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/) | 左侧边界与右侧边界           | [J_53_I_Find_Same_Number_Count_in_Sorted_Array](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/J_53_I_Find_Same_Number_Count_in_Sorted_Array.md) |
| [剑指 Offer 53 - II. 0～n-1中缺失的数字](https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/) | 右侧边界                     | [J_53_II_Missing_Number_Between_Zero_N_1](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/J_53_II_Missing_Number_Between_Zero_N_1.md) |
| [69. x的平方根](https://leetcode-cn.com/problems/sqrtx/)     | 二分查找序列                 | [69_Sqrt(x)](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/69_Sqrt(x).md) |
| [367. 有效的完全平方数](https://leetcode-cn.com/problems/valid-perfect-square/) | 二分查找序列                 | [367_Valid_Perfect_Square](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/367_Valid_Perfect_Square.md) |
| [374. 猜数字大小](https://leetcode-cn.com/problems/guess-number-higher-or-lower/) | 二分查找序列                 | [374_Guess_Number_Higher_or_Lower](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/374_Guess_Number_Higher_or_Lower.md) |
| [744. 寻找比目标字母大的最小字母](https://leetcode-cn.com/problems/find-smallest-letter-greater-than-target/) | 二分查找序列<br>循环数组下标 | [744_Find_Smallest_Letter_Greater_Than_Target](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/744_Find_Smallest_Letter_Greater_Than_Target.md) |
| [852. 山脉数组的峰顶索引](https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/) | 二分查找序列                 | [852_Peak_Index_in_a_Mountain_Array](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/852_Peak_Index_in_a_Mountain_Array.md) |
| [540. 有序数组中的单一元素](https://leetcode-cn.com/problems/single-element-in-a-sorted-array/) | 二分查找序列                 | [540_Single_Element_in_Sorted_Array](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/540_Single_Element_in_Sorted_Array.md) |
| [153. 寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/) | 旋转数组，无重复             | [153_Find_Minimum_in_Rotated_Sorted_Array](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/153_Find_Minimum_in_Rotated_Sorted_Array.md) |
| [154. 寻找旋转排序数组中的最小值 II](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/) | 旋转数组，有重复             | [154_Find_Minimum_in_Rotated_Sorted_Array_II](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/154_Find_Minimum_in_Rotated_Sorted_Array_II.md) |

