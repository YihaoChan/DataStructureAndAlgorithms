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

```c++
/*
// Definition for a Node.
class Node {
public:
    int val;
    vector<Node*> children;

    Node() {}

    Node(int _val) {
        val = _val;
    }

    Node(int _val, vector<Node*> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
public:
    vector<vector<int>> levelOrder(Node* root) {
        vector<vector<int>> res;
        if (root == nullptr) {
            return res;
        }

        queue<Node*> q;
        q.push(root);

        while (!q.empty()) {
            int count = q.size();
            vector<int> level;

            while (count > 0) {
                Node* node = q.front();
                q.pop();
                level.push_back(node->val);

                vector<Node*> childrenNodes = node->children;
                for (Node* child : childrenNodes) {
                    q.push(child);
                }

                --count;
            }

            if (!level.empty()) {
                res.push_back(level);
            }
        }

        return res;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点入队、出队一次，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中最多不超过n个结点，故空间复杂度为**O(n)**。