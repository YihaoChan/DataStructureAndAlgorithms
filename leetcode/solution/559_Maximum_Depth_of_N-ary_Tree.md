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
    int maxDepth(Node* root) {
        if (root == nullptr) {
            return 0;
        }

        int depth = 1;
        vector<Node*> childrenNodes = root->children;
        for (Node* child : childrenNodes) {
            depth = max(depth, 1 + maxDepth(child));
        }

        return depth;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点均被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：递归栈空间为树的深度，故空间复杂度为**O(logn)**，最坏情况下二叉树退化为链表，空间复杂度为O(n)。

### 2.2 迭代

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
    int maxDepth(Node* root) {
        if (root == nullptr) {
            return 0;
        }

        queue<Node*> q;
        q.push(root);
        int depth = 0;

        while (!q.empty()) {
            int count = q.size();
            ++depth;

            while (count > 0) {
                Node* node = q.front();
                q.pop();

                vector<Node*> childrenNodes = node->children;
                for (Node* child : childrenNodes) {
                    q.push(child);
                }

                --count;
            }
        }

        return depth;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点均被访问一遍，故时间复杂度为**O(n)**；
2. 空间复杂度：队列中结点数不超过n，故空间复杂度为**O(n)**。