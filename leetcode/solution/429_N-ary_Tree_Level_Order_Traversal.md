# 第429题 N叉树的层序遍历

## 1 题目

给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。

树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。 

示例 1：

![429-题图1](images/429-题图1.png)

输入：root = [1,null,3,2,4,null,5,6]
输出：[[1],[3,2,4],[5,6]]
示例 2：

![429-题图2](images/429-题图2.png)

输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
输出：[[1],[2,3,4,5],[6,7,8,9,10],[11,12,13],[14]]

## 2 解法

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
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int count = queue.size();

            List<Integer> level = new ArrayList<>();

            while (count > 0) {
                Node dequeueNode = queue.poll();

                List<Node> childrenNodes = dequeueNode.children;

                if (dequeueNode != null) {
                    for (Node child : childrenNodes) {
                        queue.offer(child);
                    }
                    level.add(dequeueNode.val);
                }

                count--;
            }

            if (level.size() > 0) {
                res.add(level);
            }
        }

        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：每个结点入队、出队一次，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多不超过n个结点，故空间复杂度为**O(n)**。