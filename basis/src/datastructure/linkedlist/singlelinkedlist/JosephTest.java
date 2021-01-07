package datastructure.linkedlist.singlelinkedlist;

/**
 * @Description: 约瑟夫问题 - 测试类
 */
public class JosephTest {
    public static void main(String[] args) {
        SingleLinkedList<Integer> josephList = new SingleLinkedList<>();

        /*
         * @param_1: n 需要创建多少个结点
         * @param_2: k 从第k个结点先开始遍历
         * @param_3: m 每次数m个结点
         */
        josephList.joseph(41, 1, 3);
    }
}
