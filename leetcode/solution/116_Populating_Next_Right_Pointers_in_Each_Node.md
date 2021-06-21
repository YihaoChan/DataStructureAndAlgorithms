# 第116题 填充每个节点的下一个右侧节点指针

## 1 题目

给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：

```
struct Node {
	int val;
	Node *left;
	Node *right;
	Node *next;
}
```


填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。

进阶：

你只能使用常量级额外空间。
使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。


示例：

```
输入：root = [1,2,3,4,5,6,7]
输出：[1,#,2,3,#,4,5,6,7,#]
解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
```

![116_sample](images/116_sample.png)

## 2 解法

### 2.1 递归

```c++
/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* left;
    Node* right;
    Node* next;

    Node() : val(0), left(NULL), right(NULL), next(NULL) {}

    Node(int _val) : val(_val), left(NULL), right(NULL), next(NULL) {}

    Node(int _val, Node* _left, Node* _right, Node* _next)
        : val(_val), left(_left), right(_right), next(_next) {}
};
*/

class Solution {
public:
    Node* connect(Node* root) {
        link(root);
        return root;
    }

private:
    void link(Node* &root) {
        if (
            root == nullptr || 
            (root->left == nullptr && root->right == nullptr)
        ) {
            return;
        }

        root->left->next = root->right;
        if (root->next != nullptr) {
            root->right->next = root->next->left;
        }

        link(root->left);
        link(root->right);

        return;
    }    
};
```

复杂度分析：

1. 时间复杂度：每个结点的子结点之间都进行了一次修改next指针的操作，故时间复杂度为**O(n)**；
2. 空间复杂度：递归空间复杂度为递归树深度，故空间复杂度为**O(logn)**，最坏情况下树退化为链表，空间复杂度为O(n)。其中，n为树的结点总个数。

### 2.2 迭代

```c++
/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* left;
    Node* right;
    Node* next;

    Node() : val(0), left(NULL), right(NULL), next(NULL) {}

    Node(int _val) : val(_val), left(NULL), right(NULL), next(NULL) {}

    Node(int _val, Node* _left, Node* _right, Node* _next)
        : val(_val), left(_left), right(_right), next(_next) {}
};
*/

class Solution {
public:
    Node* connect(Node* root) {
        if (
            root == nullptr ||
            (root->left == nullptr && root->right == nullptr)
        ) {
            return root;
        }

        Node* node = root;

        while (node->left != nullptr) {
            Node* levelNode = node;
            while (levelNode != nullptr) {
                levelNode->left->next = levelNode->right;
                if (levelNode->next != nullptr) {
                    levelNode->right->next = levelNode->next->left;
                }
                levelNode = levelNode->next;
            }
            node = node->left;
        }

        return root;
    }
};
```

复杂度分析：

1. 时间复杂度：每个结点都进行了一次修改next指针的操作，故时间复杂度为**O(n)**；
2. 空间复杂度：仅花费常数个额外空间，故空间复杂度为**O(1)**。