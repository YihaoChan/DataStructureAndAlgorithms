# 第559题 N叉树的最大深度

## 1 题目

给定一个 N 叉树，找到其最大深度。

最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。

N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。

示例 1：

![559-题图1](images/559-题图1.png)

输入：root = [1,null,3,2,4,null,5,6]
输出：3
示例 2：

![559-题图2](images/559-题图2.png)

输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
输出：5

## 2 解法

### 2.1 递归

```
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        List<Node> childrenNodes = root.children;

        int depth = 1;

        for (Node child : childrenNodes) {
            depth = Math.max(depth, 1 + maxDepth(child));
        }

        return depth;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点均被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。