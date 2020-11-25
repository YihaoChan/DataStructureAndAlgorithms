package priv.yihaochan.datastructure.linkedlist.twowaylinkedlist;

/**
 * @Description: 双链表测试类，添加元素至链表，测试相应方法
 */
public class TwoWayLinkedListTest {
    public static void main(String[] args) {
        TwoWayLinkedList<Integer> list = new TwoWayLinkedList<>();

        /* 添加链表结点 */
        list.insert(1)
                .insert(3)
                .insert(5)
                .insert(7)
                .insert(9)
                .insert(5, 10)
                .insert(6, 30)
                .insert(7, 50)
                .insert(8, 70)
                .insert(9, 90)
                .insert(10, 110)
                .insert(11, 130);

        /* 打印链表 */
        System.out.println("原始链表：");
        list.show();
        System.out.println("原始链表元素个数为：" + list.length());

        /* 删除结点 */
        System.out.println("删除该位置处的结点对应的元素为：" + list.remove(11));
        System.out.println("删除链表末端处的结点对应的数据为：" + list.remove());
        System.out.println("删除元素后的链表：");
        list.show();

        /* 修改结点 */
        list.update(9, 500);
        System.out.println("修改结点后的链表：");
        list.show();

        /* 查看元素对应位置 */
        System.out.println("元素对应位置为：" + list.indexOf(10));

        /* 查找链表元素 */
        System.out.println("查看对应位置元素为：" + list.getSpecifiedNode(9).getItem());

        /* 查找链表尾结点 */
        System.out.println("查看尾结点元素为：" + list.getTail().getItem());

        /* 清空链表 */
        list.clear();
        System.out.println("清空后的链表元素个数为：" + list.length());
    }
}
