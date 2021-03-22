# 第496题 下一个更大元素 I

## 1 题目

给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。

请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。

nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。

示例 1:

```
输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
输出: [-1,3,-1]
解释:
    对于 num1 中的数字 4 ，你无法在第二个数组中找到下一个更大的数字，因此输出 -1 。
    对于 num1 中的数字 1 ，第二个数组中数字1右边的下一个较大数字是 3 。
    对于 num1 中的数字 2 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
```

示例 2:

```
输入: nums1 = [2,4], nums2 = [1,2,3,4].
输出: [3,-1]
解释:
    对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
    对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
```

## 2 解法

单调栈。维护一个从栈顶到栈底的元素单调递增的栈：因为要找**后一个**更大的值，所以nums2**从后往前**遍历，如果栈不为空且当前扫描到的元素**大于等于**栈顶元素，就将栈顶元素弹出。当扫描到的元素找到了合适的入栈时机的时候，如果此时栈为空，说明没有下一个比当前扫描到的元素还大的元素，故记此时栈顶元素为-1。之后，先将当前扫描的元素和栈顶元素(或-1)作为键值对放入一个哈希表中，即记录下扫描的元素及其下一个更大元素的对应关系。最后遍历nums1，在哈希表中查询所遍历到的元素的下一个更大元素，原地修改nums1数组并返回即可。

```
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> monotonicStack = new Stack<>();

        // 键：数组中的元素；值：下一个更大的元素
        Map<Integer, Integer> itemToNext = new HashMap<>();

        int nums2Len = nums2.length;

        for (int i = nums2Len - 1; i >= 0; i--) {
            int nums2Item = nums2[i];

            while (
            	!monotonicStack.isEmpty() && 
            	nums2Item >= monotonicStack.peek()
            ) {
                monotonicStack.pop();
            }

            int nextGreaterItem = monotonicStack.isEmpty() ? 
            	-1 : monotonicStack.peek();

            itemToNext.put(nums2Item, nextGreaterItem);

            monotonicStack.push(nums2Item);
        }

        // 在nums1中原地修改，不必创建新数组
        int nums1Len = nums1.length;

        for (int j = 0; j < nums1Len; j++) {
            int nums1Item = nums1[j];

            nums1[j] = itemToNext.get(nums1Item);
        }

        return nums1;
    }
}
```

复杂度分析：

1. 时间复杂度：从后往前扫描nums2数组中n个元素，其中每个元素入栈1次(push操作花费O(1))，至多出栈1次(pop操作花费O(1))，且存入哈希表花费O(1)，因此共花费O(n)。从前往后扫描nums1数组中m个元素，并在哈希表中查询，查询操作花费O(1)，因此共花费O(m)。故总时间复杂度为**O(m + n)**。

2. 空间复杂度：单调栈花费至多O(n)的空间，哈希表花费至多O(n)的空间，故总空间复杂度为**O(n)**。