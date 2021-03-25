# 第1019题 链表中的下一个更大节点

## 1 题目

给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。

每个节点都可能有下一个更大值（next larger value）：对于 node_i，如果其 next_larger(node_i) 是 node_j.val，那么就有 j > i 且  node_j.val > node_i.val，而 j 是可能的选项中最小的那个。如果不存在这样的 j，那么下一个更大值为 0 。

返回整数答案数组 answer，其中 answer[i] = next_larger(node_{i+1}) 。

注意：在下面的示例中，诸如 [2,1,5] 这样的输入（不是输出）是链表的序列化表示，其头节点的值为 2，第二个节点值为 1，第三个节点值为 5 。

示例 1：

```
输入：[2,1,5]
输出：[5,5,0]
```

示例 2：

```
输入：[2,7,4,3,5]
输出：[7,0,5,5,0]
```

示例 3：

```
输入：[1,7,5,1,9,2,5,1]
输出：[7,9,9,9,0,5,0,0]
```

## 2 解法

普通栈存储链表元素，单调递增栈**从后往前**求解**后一个**更大结点。

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
    public int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return new int[0];
        }

        // 把链表结点逆序压栈
        Stack<Integer> listStack = new Stack<>();
        
        while (head != null) {
            listStack.push(head.val);

            head = head.next;
        }

        int[] res = new int[listStack.size()];
        int ptr = res.length - 1;

        // 单调栈
        Stack<Integer> monotonicStack = new Stack<>();

        while (!listStack.isEmpty()) {
            int item = listStack.pop();

            while (!monotonicStack.isEmpty() && 
            	   item >= monotonicStack.peek()) {
                monotonicStack.pop();
            }

            if (monotonicStack.isEmpty()) {
                res[ptr] = 0;
            } else {
                res[ptr] = monotonicStack.peek();
            }

            ptr--;

            monotonicStack.push(item);
        }

        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：

   1. 从前往后遍历链表并将结点压入普通栈，花费O(n)；
   2. 从普通栈中弹出一个元素花费O(1)，每个元素压入单调栈一次花费O(1)，最多弹出单调栈一次花费O(1)；
   3. 弹出元素记录到数组中花费O(1)。

   因此，总时间复杂度为**O(n)**；

2. 空间复杂度：

   1. 普通栈用于记录链表元素，花费O(n)空间；
   2. 单调栈最多花费O(n)空间；
   3. 记录数组花费O(n)空间。

   因此，总空间复杂度为**O(n)**。