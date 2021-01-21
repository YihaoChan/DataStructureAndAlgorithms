package datastructure.tree.huffmantree;

import java.util.*;

/**
 * @Description: 哈夫曼树及哈夫曼编码
 */
public class HuffmanTree {
    /**
     * @Description: 结点类
     */
    private class Node {
        // 数据域
        private Character data;

        // 每个数据所占的权值
        private int weight;

        // 左子结点
        private Node left;

        // 右子结点
        private Node right;

        // 哈夫曼编码
        private String code;

        /* 构造方法 */
        private Node(Character data, int weight) {
            this.data = data;
            this.weight = weight;
            this.code = "";
        }
    }

    // 初始哈夫曼树数组容量
    private int capacity;

    // 数组中的二叉树数量
    private int size;

    /**
     * @Description: 构造方法
     */
    public HuffmanTree(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @Description: 插入二叉树
     */
    private void insert(Node[] arr, Node node) {
        arr[this.size++] = node;
    }

    /**
     * @Description: 删除给定下标对应的二叉树
     */
    private void remove(Node[] arr, int index) {
        arr[index] = null;

        this.size--;
    }

    /**
     * @Description: 快速排序 - 确定枢轴位置
     */
    private int partition(Node[] nodes, int low, int high) {
        Node pivot = nodes[low];
        
        while (low < high) {
            while (low < high && nodes[high].weight <= pivot.weight) {
                high--;
            }
            
            nodes[low] = nodes[high];
            
            while (low < high && nodes[low].weight >= pivot.weight) {
                low++;
            }
            
            nodes[high] = nodes[low];
        }
        
        nodes[low] = pivot;
        
        return low;
    }

    /**
     * @Description: 快速排序 - 逻辑
     */
    private void quickSort(Node[] arr, int low, int high) {
        if (low < high) {
            int pivotLoc = partition(arr, low, high);

            quickSort(arr, low, pivotLoc); // 递归完成左边

            quickSort(arr, pivotLoc + 1, high); // 递归完成右边
        }
    }

    /**
     * @Description: 快速排序 - 按权值从大到小排序
     */
    private void sort(Node[] arr) {
        quickSort(arr, 0, this.size - 1);
    }

    /**
     * @Description: 构建哈夫曼树
     */
    private Node buildHuffmanTree(Node[] nodes) {
        // 直到数组中只含有一棵二叉树时，哈夫曼树形成
        while (this.size > 1) {
            // 按二叉树根结点权值从大到小排序
            sort(nodes);

            // 取出根结点权值最小的两棵二叉树，作为新的二叉树的左右子树(根结点权值较大的在左)
            Node leftChild = nodes[this.size - 2];
            Node rightChild = nodes[this.size - 1];

            // 新二叉树
            Node parent = new Node(null, leftChild.weight + rightChild.weight);
            parent.left = leftChild;
            parent.right = rightChild;

            // 删除取出的两棵树
            remove(nodes, this.size - 1);
            remove(nodes, this.size - 1);

            // 插入新二叉树
            insert(nodes, parent);
        }

        return nodes[0];
    }

    /**
     * @Description: 层序遍历计算哈夫曼编码
     * @param node 哈夫曼树根结点
     */
    private Map<Character, String> getHuffmanCode(Node node) {
        if (node == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();

        Map<Character, String> charToCode = new HashMap<>();

        // 根结点入队
        queue.add(node);

        while (!queue.isEmpty()) {
            node = queue.remove();

            // 添加叶子结点的编码
            if (node.left == null && node.right == null) {
                charToCode.put(node.data, node.code);
            }

            if (node.left != null) {
                node.left.code = node.code + "0";
                queue.add(node.left);
            }

            if (node.right != null) {
                node.right.code = node.code + "1";
                queue.add(node.right);
            }
        }

        return charToCode;
    }

    /**
     * @Description: 递归计算哈夫曼编码
     */
    private Map<Character, String> getHuffmanCode(Node node, Map<Character, String> charToCode) {
        // 添加叶子结点的编码
        if (node.left == null && node.right == null) {
            charToCode.put(node.data, node.code);
        }

        if (node.left != null) {
            node.left.code = node.code + "0";
            getHuffmanCode(node.left, charToCode);
        }

        if (node.right != null) {
            node.right.code = node.code + "1";
            getHuffmanCode(node.right, charToCode);
        }

        return charToCode;
    }

    /**
     * @Description: 根据字符及其出现频率计算哈夫曼编码
     */
    public String huffmanCoding(Map<Character, Integer> charToFreq, String str) {
        // 根据每个字符及其频率创建结点，并存入数组
        Node[] nodes = new Node[charToFreq.size()];

        for (Character data : charToFreq.keySet()) {
            nodes[this.size++] = new Node(data, charToFreq.get(data));
        }

        // 构建哈夫曼树
        Node huffmanTree = buildHuffmanTree(nodes);

        // 计算哈夫曼编码
        Map<Character, String> charCode = getHuffmanCode(huffmanTree, new HashMap<>());

        // 编码字符串
        for (Character character : charCode.keySet()) {
            String code = charCode.get(character);

            System.out.println("char: " + character + " -> code: " + code);

            str = str.replace(String.valueOf(character), code);
        }

        return str;
    }
}
