package datastructure.tree.huffmantree;

import java.util.*;

/**
 * @Description: 哈夫曼树、哈夫曼编码
 */
public class HuffmanTree {

    /**
     * @Description: 结点类
     */
    class Node<T> {
        // 数据域
        private T data;

        // 每个数据所占的权值
        private double weight;

        // 左子结点
        private Node left;

        // 右子结点
        private Node right;

        /* 构造方法 */
        public Node(T data, double weight) {
            this.data = data;
            this.weight = weight;
        }

        public double getWeight() {
            return weight;
        }

        public T getData() {
            return data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    /**
     * @Description: 构造哈夫曼树
     * @param: nodes 根据每个权值构建一棵只有一棵结点的二叉树，并存放在列表中，再根据权值大小对将列表中的结点进行排序
     * @return: 生成的哈夫曼树的根结点
     */
    public List<Node> createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) { // 直到结点列表中只含有一个结点，即只剩下一棵二叉树，此时为哈夫曼树
            // 对根据当前列表中结点的权值对结点进行排序
            quickSort(nodes);

            // 取出权值最小的两个结点，作为新的二叉树的左右结点(较大的在左)
            Node leftChild = nodes.get(nodes.size() - 2);
            Node rightChild = nodes.get(nodes.size() - 1);

            // 新的二叉树的根结点
            Node parent = new Node(null, leftChild.weight + rightChild.weight);
            parent.left = leftChild;
            parent.right = rightChild;

            // 在原来数组中删除取出的两个树
            int temp = nodes.size() - 1; // 保存原来的最小权值索引，否则remove后最小权值索引会发生变化！
            nodes.remove(temp);
            nodes.remove(temp - 1);

            // 将新的二叉树加入数组中
            nodes.add(parent);
        }

        return nodes;
    }

    /**
     * @Description: 将列表中i和j索引处的元素进行交换
     */
    private void swap(List<Node> nodes, int i, int j) {
        Node temp = nodes.get(j);
        nodes.set(j, nodes.get(i));
        nodes.set(i, temp);
    }

    /**
     * @Description: 对每个二叉树结点元素进行快速排序
     */
    private void subSort(List<Node> nodes, int start, int end) {
        if (start < end) {
            // 以第一个元素作为分界值
            Node base = nodes.get(start);

            // i从左边搜索，搜索大于分界值的元素的索引
            int i = start;

            // j从右边开始搜索，搜索小于分界值的元素的索引
            int j = end + 1;

            while (true) {
                // 找到大于分界值的元素的索引，或者i已经到了end处
                while (i < end && nodes.get(++i).weight >= base.weight)
                    ; // 空语句，只执行循环体

                // 找到小于分界值的元素的索引，或者j已经到了start处
                while (j > start && nodes.get(--j).weight <= base.weight)
                    ; // 空语句，只执行循环体

                if (i < j) {
                    swap(nodes, i, j);
                } else {
                    break;
                }
            }

            swap(nodes, start, j);

            //递归左边子序列
            subSort(nodes, start, j - 1);

            //递归右边子序列
            subSort(nodes, j + 1, end);
        }
    }

    private void quickSort(List<Node> nodes) {
        subSort(nodes, 0, nodes.size() - 1);
    }

    /**
     * @Description: 前序遍历 - 逻辑
     */
    private void preOrderTraversal(Node curr) {
        if (curr == null) {
            return;
        }

        System.out.print(curr.data + "\t");
        preOrderTraversal(curr.left);
        preOrderTraversal(curr.right);
    }

    /**
     * @Description: 前序遍历
     */
    public void preOrderTraversal(List<Node> nodes) {
        preOrderTraversal(nodes.get(0));
    }

    /**
     * @Description: 中序遍历 - 逻辑
     */
    private void inOrderTraversal(Node curr) {
        if (curr == null) {
            return;
        }

        inOrderTraversal(curr.left);
        System.out.print(curr.data + "\t");
        inOrderTraversal(curr.right);
    }

    /**
     * @Description: 中序遍历
     */
    public void inOrderTraversal(List<Node> nodes) {
        inOrderTraversal(nodes.get(0));
    }

    /**
     * @Description: 后序遍历 - 逻辑
     */
    private void postOrderTraversal(Node curr) {
        if (curr == null) {
            return;
        }

        postOrderTraversal(curr.left);
        postOrderTraversal(curr.right);
        System.out.print(curr.data + "\t");
    }

    /**
     * @Description: 后序遍历
     */
    public void postOrderTraversal(List<Node> nodes) {
        postOrderTraversal(nodes.get(0));
    }

    /**
     * @Description: 层序遍历
     */
    public void levelOrderTraversal(List<Node> nodes) {
        Node curr = nodes.get(0);

        if (curr == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();

        // 当前结点入队
        queue.add(curr);

        while (!queue.isEmpty()) {
            curr = queue.remove();

            System.out.print(curr.data + "\t");

            if (curr.left != null) {
                queue.add(curr.left);
            }

            if (curr.right != null) {
                queue.add(curr.right);
            }
        }
    }

    /**
     * @Description: 对字符串进行哈夫曼编码
     * @param: str 未编码的字符串
     */
    public void huffmanCoding(String str) {
        System.out.println("未编码的字符串：" + str);

        /* 处理字符串 */
        str = str.replaceAll(" ", ""); // 去掉空格
        str = str.replaceAll("[\\pP]", ""); // 去掉标点符号

        /* 统计字符串中每一个字符出现次数的权重 */
        char[] chars = str.toCharArray(); // 字符串 -> 字符数组

        Map<Character, Integer> charToCount = new HashMap<>(); // 创建哈希表用于存放字符及对应出现次数

        for (char item : chars) {
            if (!charToCount.containsKey(item)) { // 如果之前没有出现过，则该键对应的值为1
                charToCount.put(item, 1);
            } else { // 如果之前出现过，则覆盖该键对应的值，直接自增
                charToCount.put(item, charToCount.get(item) + 1);
            }
        }

        /* 根据哈希表中的键和值，用data和weight创建结点，并存入列表 */
        List<Node> charNodes = new ArrayList<>();

        for (Character data : charToCount.keySet()) {
            charNodes.add(new Node(data, charToCount.get(data)));
        }

        /* 根据列表构建哈夫曼树 */
        List<Node> huffmanCharTree = createHuffmanTree(charNodes);

        /* 拿到编码后的字符表 */
        Map<Character, String> characterCodeMap = getLeafCode(huffmanCharTree.get(0), "", new HashMap<>());

        /* 替换字符串 */
        for (Character character : characterCodeMap.keySet()) {
            // 获取每个字符对应的编码
            String characterCode = characterCodeMap.get(character);

            System.out.println("character: " + character + " -> code: " + characterCode);

            // 找到字符串中和表对应的key，用编码代替
            str = str.replace(character.toString(), characterCode);
        }

        System.out.println("哈夫曼编码后的字符串：" + str);
    }

    /**
     * @Description: 找到二叉树的叶子结点并生成哈夫曼编码
     */
    private Map getLeafCode(Node curr, String code, Map charToCode) {
        // 向左搜寻完毕之后，需要记住上一个状态，即还未遍历左兄弟结点的状态，然后根据上一个状态开始向右寻找叶子结点并编码
        String last = code;

        if (curr.left == null && curr.right == null) {
            charToCode.put(curr.data, code); // 如果遍历到叶子结点，就将叶子结点的数据和权值存入表中
            return charToCode;
        }

        if (curr.left != null) {
            code = last + "0"; // 如果向左，就加0
            getLeafCode(curr.left, code, charToCode);
        }

        if (curr.right != null) {
            code = last + "1"; // 如果向右，就加1
            getLeafCode(curr.right, code, charToCode);
        }

        return charToCode;
    }
}
