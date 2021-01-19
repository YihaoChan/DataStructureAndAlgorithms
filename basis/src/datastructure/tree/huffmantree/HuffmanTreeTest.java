package datastructure.tree.huffmantree;


import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 哈夫曼树及哈夫曼编码 - 测试类
 */
public class HuffmanTreeTest {
    /**
     * @Description: 预处理字符串，并计算字符串中每个字符的频率
     */
    private static Map<Character, Integer> getCharFreq(String str) {
        str = str.replaceAll(" ", ""); // 去掉空格
        str = str.replaceAll("[\\pP]", ""); // 去掉标点符号

        char[] chars = str.toCharArray();

        Map<Character, Integer> charToFreq = new HashMap<>();

        for (char item : chars) {
            if (!charToFreq.containsKey(item)) { // 如果之前没有出现过，则该键出现的次数为1
                charToFreq.put(item, 1);
            } else { // 如果之前出现过，则该键出现的次数+1
                charToFreq.put(item, charToFreq.get(item) + 1);
            }
        }

        return charToFreq;
    }

    public static void main(String[] args) {
        // 《清华数据结构》 P146  a:7, b:5, c:2, d:4
        String str = "aaaaaaabbbbbccdddd";

        Map<Character, Integer> charFreq = getCharFreq(str);

        HuffmanTree huffmanTree = new HuffmanTree(charFreq.size());

        String codedStr = huffmanTree.huffmanCoding(charFreq, str);

        System.out.println("原始字符串：" + str);
        System.out.println("编码后字符串：" + codedStr);
    }
}
