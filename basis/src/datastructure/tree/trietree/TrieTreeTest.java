package datastructure.tree.trietree;

/**
 * @Description: 字典树 - 测试类
 */
public class TrieTreeTest {
    public static void main(String[] args) {
        TrieTree trieTree = new TrieTree();

        trieTree.insert("apple");
        trieTree.insert("apple"); // 测试重复单词是否会被计入
        trieTree.insert("dog");
        trieTree.insert("do");
        trieTree.insert("category");
        trieTree.insert("cat");
        trieTree.insert("cake");

        System.out.println("初始状态单词个数: " + trieTree.getSize());

        System.out.println("---删除apple---");
        trieTree.remove("apple");
        System.out.println("是否包含单词apple：" + trieTree.isContains("apple"));
        System.out.println("是否包含前缀a：" + trieTree.isPrefix("a"));

        System.out.println("---删除dog---");
        trieTree.remove("dog");
        System.out.println("是否包含单词dog：" + trieTree.isContains("dog"));
        System.out.println("是否包含单词do：" + trieTree.isContains("do"));
        System.out.println("是否包含前缀do：" + trieTree.isPrefix("do"));

        System.out.println("---删除category---");
        trieTree.remove("category");
        System.out.println("是否包含单词category：" + trieTree.isContains("category"));
        System.out.println("是否包含单词cat：" + trieTree.isContains("cat"));
        System.out.println("是否包含前缀cat：" + trieTree.isPrefix("cat"));

        System.out.println("---删除cake---");
        trieTree.remove("cake");
        System.out.println("是否包含单词cake：" + trieTree.isContains("cake"));
        System.out.println("是否包含单词cat：" + trieTree.isContains("cat"));
        System.out.println("是否包含前缀cat：" + trieTree.isPrefix("cat"));
        System.out.println("是否包含前缀cak：" + trieTree.isPrefix("cak"));
        System.out.println("是否包含前缀ca：" + trieTree.isPrefix("ca"));

        System.out.println("当前单词个数: " + trieTree.getSize());
    }
}
