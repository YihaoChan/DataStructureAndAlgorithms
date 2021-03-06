# 双指针

## 1 概念

在线性结构中，用两个指针分别指向结构中的两个元素，对这两个指针所指向的元素进行操作，即为双指针。具体来说，双指针可分为：

1. 左右指针：left和right指向数组的两端，然后不断靠近；
2. 快慢指针：让快指针(right)在前面"探路"，如果遇到某些情况，就让慢指针(left)靠进；
3. 上下指针：两个指针指向不同的序列，通过比较这两个不同序列中的指针所指向的元素，进行相应操作。

小结：涉及到移动元素、去重、两个数组之间的关系的，一般都要想到左右指针/快慢指针/上下指针。

## 2 题目

| 题目                                                         | 描述                                   | 解答                                                         |
| ------------------------------------------------------------ | -------------------------------------- | ------------------------------------------------------------ |
| [167. 两数之和 II - 输入有序数组](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/) | 左右指针搜索数组                       | [167_Two_Sum_II_Input_array_is_sorted](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/167_Two_Sum_II_Input_array_is_sorted.md) |
| [392. 判断子序列](https://leetcode-cn.com/problems/is-subsequence/) | 上下指针搜索字符串                     | [392_Is_Subsequence](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/392_Is_Subsequence.md) |
| [26. 删除排序数组中的重复项](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/) | 快慢指针搜索数组，删除元素             | [26_Remove_Duplicates_from_Sorted_Array](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/26_Remove_Duplicates_from_Sorted_Array.md) |
| [27. 移除元素](https://leetcode-cn.com/problems/remove-element/) | 快慢指针搜索数组，删除元素             | [27_Remove_Element](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/27_Remove_Element.md) |
| [283. 移动零](https://leetcode-cn.com/problems/move-zeroes/) | 快慢指针搜索数组，移动元素             | [283_Move_Zeroes](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/283_Move_Zeroes.md) |
| [83. 删除排序链表中的重复元素](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/) | 快慢指针搜索有序链表，删除元素         | [83_Remove_Duplicates_from_Sorted_List](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/83_Remove_Duplicates_from_Sorted_List.md) |
| [面试题 02.01. 移除重复节点](https://leetcode-cn.com/problems/remove-duplicate-node-lcci/) | 快慢指针搜索无序链表，删除元素；哈希表 | [M_02.01_Remove_Duplicate_Node](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/M_02.01_Remove_Duplicate_Node.md) |
| [203. 移除链表元素](https://leetcode-cn.com/problems/remove-linked-list-elements/) | 快慢指针搜索链表，删除元素；哨兵结点   | [203_Remove_Linked_List_Elements](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/203_Remove_Linked_List_Elements.md) |
| [剑指 Offer 18. 删除链表的节点](https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/) | 快慢指针搜索链表，删除元素；哨兵结点   | [J_18_Remove_the_Node_of_the_Linked_List](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/J_18_Remove_the_Node_of_the_Linked_List.md) |
| [剑指 Offer 22. 链表中倒数第k个节点](https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/) | 快慢指针搜索链表，先走k步              | [J_22_Kth_Node_From_End_of_List](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/J_22_Kth_Node_From_End_of_List.md) |
| [面试题 02.02. 返回倒数第 k 个节点](https://leetcode-cn.com/problems/kth-node-from-end-of-list-lcci/) | 快慢指针搜索链表，先走k步              | [M_02.02_Kth_Node_From_End_of_List](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/M_02.02_Kth_Node_From_End_of_List.md) |
| [344. 反转字符串](https://leetcode-cn.com/problems/reverse-string/) | 左右指针遍历数组                       | [344_Reverse_String](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/344_Reverse_String.md) |
| [345. 反转字符串中的元音字母](https://leetcode-cn.com/problems/reverse-vowels-of-a-string/) | 左右指针遍历数组                       | [345_Reverse_Vowels_of_a_String](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/345_Reverse_Vowels_of_a_String.md) |
| [876. 链表的中间结点](https://leetcode-cn.com/problems/middle-of-the-linked-list/) | 快慢指针遍历链表，速度差               | [876_Middle_of_the_Linked_List](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/876_Middle_of_the_Linked_List.md) |
| [674. 最长连续递增序列](https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence/) | 左右指针搜索数组                       | [674_Longest_Continuous_Increasing_Subsequence](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/674_Longest_Continuous_Increasing_Subsequence.md) |
| [面试题 02.05. 链表求和](https://leetcode-cn.com/problems/sum-lists-lcci/) | 上下指针搜索链表                       | [M_02.05_Sum_Lists](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/M_02.05_Sum_Lists.md) |
| [2. 两数相加](https://leetcode-cn.com/problems/add-two-numbers/) | 上下指针搜索链表                       | [2_Add_Two_Numbers](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/2_Add_Two_Numbers.md) |
| [445. 两数相加 II](https://leetcode-cn.com/problems/add-two-numbers-ii/) | 上下指针搜索链表；栈                   | [445_Add_Two_Numbers_II](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/445_Add_Two_Numbers_II.md) |
| [1721. 交换链表中的节点](https://leetcode-cn.com/problems/swapping-nodes-in-a-linked-list/) | 快慢指针搜索链表                       | [1721_Swapping_Nodes_in_a_Linked_List](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/1721_Swapping_Nodes_in_a_Linked_List.md) |
| [905. 按奇偶排序数组](https://leetcode-cn.com/problems/sort-array-by-parity/) | 快慢指针搜索数组                       | [905_Sort_Array_By_Parity](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/905_Sort_Array_By_Parity.md) |
| [922. 按奇偶排序数组 II](https://leetcode-cn.com/problems/sort-array-by-parity-ii/) | 双指针，判断奇偶                       | [922_Sort_Array_By_Parity_II](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/922_Sort_Array_By_Parity_II.md) |
| [82. 删除排序链表中的重复元素 II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/) | 快慢指针搜索链表                       | [82_Remove_Duplicates_from_Sorted_List_II](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/82_Remove_Duplicates_from_Sorted_List_II.md) |
| [21. 合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/) | 上下指针搜索链表                       | [21_Merge_Two_Sorted_Lists](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/21_Merge_Two_Sorted_Lists.md) |
| [1669. 合并两个链表](https://leetcode-cn.com/problems/merge-in-between-linked-lists/) | 上下指针搜索链表                       | [1669_Merge_In_Between_Linked_Lists](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/1669_Merge_In_Between_Linked_Lists.md) |
| [328. 奇偶链表](https://leetcode-cn.com/problems/odd-even-linked-list/) | 双指针，跳跃连接                       | [328_Odd_Even_Linked_List](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/328_Odd_Even_Linked_List.md) |
| [86. 分隔链表](https://leetcode-cn.com/problems/partition-list/) | 新建两个头结点，分别收集链表的两部分   | [86_Partition_List](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/86_Partition_List.md) |
| [141. 环形链表](https://leetcode-cn.com/problems/linked-list-cycle/) | 快慢指针判断链表是否有环               | [141_Linked_List_Cycle](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/141_Linked_List_Cycle.md) |
| [142. 环形链表 II](https://leetcode-cn.com/problems/linked-list-cycle-ii/) | 快慢指针判断链表是否有环               | [142_Linked_List_Cycle_II](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/142_Linked_List_Cycle_II.md) |
| [160. 相交链表](https://leetcode-cn.com/problems/intersection-of-two-linked-lists/) | 上下指针求链表的相交结点               | [160_Intersection_of_Two_Linked_Lists](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/160_Intersection_of_Two_Linked_Lists.md) |
| [面试题 02.07. 链表相交](https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci/) | 上下指针求链表的相交结点               | [M_02.07_Intersection_of_Two_Linked_Lists](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/M_02.07_Intersection_of_Two_Linked_Lists.md) |
| [125. 验证回文串](https://leetcode-cn.com/problems/valid-palindrome/) | 左右指针搜索字符串                     | [125_Valid_Palindrome](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/125_Valid_Palindrome.md) |
| [80. 删除有序数组中的重复项 II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/) | 快慢指针搜索数组                       | [80_Remove_Duplicates_from_Sorted_Array_II](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/80_Remove_Duplicates_from_Sorted_Array_II.md) |
| [1446. 连续字符](https://leetcode-cn.com/problems/consecutive-characters/) | 快慢指针搜索字符串                     | [1446_Consecutive_Characters](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/1446_Consecutive_Characters.md) |
| [1550. 存在连续三个奇数的数组](https://leetcode-cn.com/problems/three-consecutive-odds/) | 快慢指针搜索数组                       | [1550_Three_Consecutive_Odds](https://github.com/YihaoChan/DataStructureAndAlgorithms/blob/main/leetcode/solution/1550_Three_Consecutive_Odds.md) |

