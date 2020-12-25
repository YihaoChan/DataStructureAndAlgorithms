package datastructure.tree.trietree;

import java.util.Stack;
import java.util.TreeMap;

/**
 * @Description: 字典树
 */
public class TrieTree {
    /**
     * @Description: 结点类
     */
    private class Node {
        // 判断是否为单词结尾的结束符
        public boolean endOfWordFlag;

        // next域，Key为子结点的字符，Value用于判断子结点是否存在
        public TreeMap<Character, Node> next;

        /* 结点构造方法 */
        public Node() {
            this.endOfWordFlag = false;
            this.next = new TreeMap<>();
        }
    }

    // 根结点
    private Node root;

    // 字典树中单词个数
    private int size;

    /**
     * @Description: 字典树的构造方法
     */
    public TrieTree() {
        this.root = new Node();
        this.size = 0;
    }

    /**
     * @Description: 返回单词个数
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @Description: 插入单词
     */
    public void insert(String word) {
        if (isContains(word)) {
            return;
        }

        Node curr = root;

        char[] chars = word.toCharArray();

        for (char character : chars) {
            if (curr.next.get(character) == null) {
                // 如果表中不存在字符，就根据该字符创建一个新结点
                curr.next.put(character, new Node());
            }

            // 步进
            curr = curr.next.get(character);

        }

        // 标记该位置为单词结束符
        curr.endOfWordFlag = true;
        size++;
    }

    /*
     * @Description: 查找单词是否在该字典树中
     */
    public boolean isContains(String word) {
        Node curr = root;

        char[] chars = word.toCharArray();

        for (char letter : chars) {
            if (curr.next.get(letter) == null) {
                return false;
            } else {
                curr = curr.next.get(letter);
            }
        }

        return curr.endOfWordFlag;
    }

    /**
     * @Description: 判断在字典树中是否有以给定字符串为前缀的单词
     */
    public boolean isPrefix(String word) {
        Node curr = root;

        char[] chars = word.toCharArray();

        for (char letter : chars) {
            if (curr.next.get(letter) == null) {
                return false;
            } else {
                curr = curr.next.get(letter);
            }
        }

        return curr.next.size() > 0; // 如果只有一个单词本身，则不算前缀
    }

    /**
     * @Description: 删除单词
     */
    public void remove(String word) {
        if (!isContains(word)) {
            return;
        }

        Node curr = root;
        Node pre; // 当前结点的前驱结点
        char currLetter; // 当前字符

        Stack<Node> preNodes = new Stack<>(); // 存放路径上的结点

        char[] chars = word.toCharArray();

        for (char letter : chars) {
            preNodes.push(curr);

            curr = curr.next.get(letter); // 步进到最后一个字符
        }

        if (curr.next.size() == 0) { // 待删除单词的最后一个字符是叶子结点
            for (int i = word.length() - 1; i >= 0; i--) { // 从后往前逐个字符删
                currLetter = chars[i];

                pre = preNodes.pop();

                if (pre.endOfWordFlag || pre.next.size() > 1) {
                    // 如果碰到了其他单词，或者碰到了和其他单词的公有前缀，则删完直接跳出
                    pre.next.remove(currLetter); // 把当前字符删掉
                    break;
                } else {
                    pre.next.remove(currLetter); // 把当前字符删掉
                }
            }
        } else { // 待删除单词的最后一个字符不是叶子结点，则直接把标志置为false
            curr.endOfWordFlag = false;
        }

        size--;
    }
}
