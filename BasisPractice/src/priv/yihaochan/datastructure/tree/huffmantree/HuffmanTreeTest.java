package priv.yihaochan.datastructure.tree.huffmantree;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description: 哈夫曼树 - 哈夫曼编码与测试
 */
public class HuffmanTreeTest {
    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree();

        List<Node> nodes = new ArrayList<>();

        nodes.add(new Node("a", 7));
        nodes.add(new Node("b", 5));
        nodes.add(new Node("c", 2));
        nodes.add(new Node("d", 4));

        List<Node> huffmanNodes = huffmanTree.createHuffmanTree(nodes);

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
