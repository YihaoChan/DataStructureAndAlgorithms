package datastructure.linkedlist.doublelinkedlist;

/**
 * @Description: 双链表 - 测试类
 */
public class DoubleLinkedListTest {
    public static void main(String[] args) {
        DoubleLinkedList<Integer> doubleLinkedList = new DoubleLinkedList<>();

        /* 添加链表结点 */
        doubleLinkedList.insert(1);
        doubleLinkedList.insert(3);
        doubleLinkedList.insert(5);
        doubleLinkedList.insert(7);
        doubleLinkedList.insert(9);
        doubleLinkedList.insert(5, 10);
        doubleLinkedList.insert(6, 30);
        doubleLinkedList.insert(7, 50);
        doubleLinkedList.insert(8, 70);
        doubleLinkedList.insert(9, 90);
        doubleLinkedList.insert(10, 110);
        doubleLinkedList.insert(11, 130);

        /* 打印链表 */
        System.out.println("原始链表：");
        doubleLinkedList.show();
        System.out.println("原始链表元素个数为：" + doubleLinkedList.length());

        /* 删除结点 */
        System.out.println("删除该位置处的结点对应的元素为：" + doubleLinkedList.remove(11));
        System.out.println("删除链表末端处的结点对应的数据为：" + doubleLinkedList.remove());
        System.out.println("删除元素后的链表：");
        doubleLinkedList.show();

        /* 修改结点 */
        doubleLinkedList.update(9, 500);
        System.out.println("修改结点后的链表：");
        doubleLinkedList.show();

        /* 查看元素对应位置 */
        System.out.println("元素对应位置为：" + doubleLinkedList.indexOf(10));

        /* 查找链表元素 */
        System.out.println("查看对应位置元素为：" + doubleLinkedList.getSpecifiedNode(9).getItem());

        /* 查找链表尾结点 */
        System.out.println("查看尾结点元素为：" + doubleLinkedList.getTail().getItem());

        /* 清空链表 */
        doubleLinkedList.clear();
        System.out.println("清空后的链表元素个数为：" + doubleLinkedList.length());
    }
}
