package priv.yihaochan.datastructure.linkedlist.twowaylinkedlist;

/**
 * @Description: 双链表测试类，添加元素至链表，测试相应方法
 */
public class TwoWayLinkedListTest {
    public static void main(String[] args) {
        TwoWayLinkedList<Integer> list = new TwoWayLinkedList<>();

        /* 添加链表结点 */
        list.insert(1);
        list.insert(3);
        list.insert(5);
        list.insert(7);
        list.insert(9);
        list.insert(5, 10);
        list.insert(6, 30);
        list.insert(7, 50);
        list.insert(8, 70);
        list.insert(9, 90);
        list.insert(10, 110);
        list.insert(11, 130);

        /* 打印链表 */
        System.out.println("原始链表：");
        list.show();
        System.out.println("原始链表元素个数为：" + list.length());

        /* 删除结点 */
        int removeSpecified = list.remove(11).getItem();
        System.out.println("删除该位置处的元素为：" + removeSpecified);
        int removeTail = list.remove().getItem();
        System.out.println("删除链表末端处的元素为：" + removeTail);
        System.out.println("删除元素后的链表：");
        list.show();

        /* 修改结点 */
        list.update(9, 500);
        System.out.println("修改结点后的链表：");
        list.show();

        /* 查找链表元素 */
        int specifiedNode = list.getSpecifiedNode(9).getItem();
        System.out.println("查看对应位置元素为：" + specifiedNode);

        /* 查找链表尾结点 */
        int tailNode = list.getTail().getItem();
        System.out.println("查看尾结点元素为：" + tailNode);

        /* 清空链表 */
        list.clear();
        System.out.println("清空后的链表元素个数为：" + list.length());
    }
}
