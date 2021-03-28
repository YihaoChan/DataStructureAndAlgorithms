# 第136题 只出现一次的数字

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

示例 1:

```
输入: [2,2,1]
输出: 1
```

示例 2:

```
输入: [4,1,2,1,2]
输出: 4
```

## 2 解法

## 2.1 哈希集合

```
class Solution {
    public int singleNumber(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int len = nums.length;

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < len; i++) {
            int item = nums[i];

            if (!set.add(item)) {
                set.remove(item);
            }
        }

        for (Integer res : set) {
            return res;
        }

        return -1;
    }
}
```

复杂度分析：

1. 时间复杂度：顺序扫描数组花费O(n)，将哈希集合里的唯一元素输出花费O(1)，故总时间复杂度为**O(n)**；
2. 空间复杂度：需要用到哈希集合进行存储，空间复杂度为**O(n)**。

## 2.2 位运算

此方法能满足题目的进阶要求：常数空间复杂度。

对于这道题，可使用异或运算⊕。异或运算有以下三个性质：

- 任何数和0做异或运算，结果仍然是原来的数，即 a ⊕ 0 = a；
- 任何数和其自身做异或运算，结果是0，即 a ⊕ a= 0；
- 异或运算满足交换律和结合律，即 a ⊕ b ⊕ a = b ⊕ a ⊕ a = b ⊕ (a ⊕ a) = b ⊕ (a ⊕ a) = b ⊕ 0 = b。

假设数组中有2m + 1个数，其中有m个数出现2次，一个数出现1次。那么，数组所有元素相互异或的结果为：

(a1 ⊕ a1) ⊕ (a2 ⊕ a2) ⊕ (a3 ⊕ a3) ⊕ ... ⊕ a<sub>m+1</sub> = a<sub>m+1</sub>.

即，最后异或得到的结果就是那个只出现了1次的数。

```
class Solution {
    public int singleNumber(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int len = nums.length;

        int ele = 0;

        for (int i = 0; i < len; i++) {
            ele ^= nums[i];
        }

        return ele;
    }
}
```

复杂度分析：

1. 时间复杂度：顺序扫描花费**O(n)**；
2. 空间复杂度：仅用到常数个空间，故空间复杂度为**O(1)**。