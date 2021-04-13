# 第1171题 从链表中删除总和值为零的连续节点

## 1 题目

给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。

删除完毕后，请你返回最终结果链表的头节点。

你可以返回任何满足题目要求的答案。

（注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。）

示例 1：

```
输入：head = [1,2,-3,3,1]
输出：[3,1]
提示：答案 [1,2,1] 也是正确的。
```

示例 2：

```
输入：head = [1,2,3,-3,4]
输出：[1,2,4]
```

示例 3：

```
输入：head = [1,2,3,-3,-2]
输出：[1]
```

## 2 解法

两次遍历链表。第一次将当前累加和及对应链表结点放入map中，如果相同的累加和出现多次，则覆盖新的结点。第二次遍历链表时，重新计算累加和，此时累加和对应的结点已经是最新的结点(因为出现多个相同值时会覆盖旧结点)，将next指针指向新结点的后续结点即可，相当于删除这个区间内所有的结点。仔细理解：**若当前结点处的累加在下一处出现，表明两结点之间所有结点和为0，则直接删除区间所有结点**。

```
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        Map<Integer, ListNode> map = new HashMap<>();

        int sum = 0;
        ListNode curr = sentinel;

        // 记录每个结点所对应的累加和，如果相同的累加和出现多次，则覆盖
        while (curr != null) {
            sum += curr.val;
            map.put(sum, curr);
            curr = curr.next;
        }

        sum = 0;
        curr = sentinel;

        // 第二次遍历，累加和处对应的结点已经是最新的结点
        // 若当前结点处的累加在下一处出现，表明两结点之间所有结点和为0，
        // 则直接删除区间所有结点
        while (curr != null) {
            sum += curr.val;
            curr.next = map.get(sum).next;
            curr = curr.next;
        }

        return sentinel.next;
    }
}
```

复杂度分析：

1. 时间复杂度：两次遍历链表，共花费**O(n)**；
2. 空间复杂度：使用额外哈希表，花费**O(n)**的额外空间。

