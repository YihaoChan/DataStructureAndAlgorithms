package datastructure.tree.huffmantree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 哈夫曼树、哈夫曼编码 - 测试类
 */
public class HuffmanTreeTest {
    public static void main(String[] args) {
        // 《清华数据结构》 P146
        HuffmanTree huffmanTree = new HuffmanTree();

        List<HuffmanTree.Node> nodes = new ArrayList<>();

        nodes.add(huffmanTree.new Node("a", 7));
        nodes.add(huffmanTree.new Node("b", 5));
        nodes.add(huffmanTree.new Node("c", 2));
        nodes.add(huffmanTree.new Node("d", 4));

        List<HuffmanTree.Node> huffmanNodes = huffmanTree.createHuffmanTree(nodes);

        System.out.print("前序遍历：");
        huffmanTree.preOrderTraversal(huffmanNodes);

        System.out.println();

        System.out.print("中序遍历：");
        huffmanTree.inOrderTraversal(huffmanNodes);

        System.out.println();

        System.out.print("后序遍历：");
        huffmanTree.postOrderTraversal(huffmanNodes);

        System.out.println();

        System.out.print("层序遍历：");
        huffmanTree.levelOrderTraversal(huffmanNodes);

        System.out.println();

        // a:7, b:5, c:2, d:4 转为哈夫曼编码
        huffmanTree.huffmanCoding("aaaaaaabbbbbccdddd");
    }
}
