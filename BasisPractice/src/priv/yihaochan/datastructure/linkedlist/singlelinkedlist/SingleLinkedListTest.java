package priv.yihaochan.datastructure.linkedlist.singlelinkedlist;

/**
 * @Description: 单链表测试类，添加元素至链表，测试相应方法
 */
public class SingleLinkedListTest {
    public static void main(String[] args) {
        /* 原链表 */
        SingleLinkedList<Integer> list = new SingleLinkedList<>();

        /* 添加链表结点 */
        list.insert(1)
                .insert(2)
                .insert(3)
                .insert(4)
                .insert(5)
                .insert(5, 10)
                .insert(6, 30)
                .insert(7, 50)
                .insert(8, 70)
                .insert(9, 90)
                .insert(10, 110)
                .insert(11, 130);

        /* 正序打印链表 */
        System.out.println("原始链表：");
        list.show();
        System.out.println("原始链表元素个数为：" + list.length());

        /* 删除结点 */
        System.out.println("删除该位置处的元素为：" + list.remove(11));
        System.out.println("删除链表末端处的元素为：" + list.remove());
        System.out.println("删除元素后的链表：");
        list.show();

        /* 修改结点 */
        list.update(9, 500);
        System.out.println("修改结点后的链表：");
        list.show();

        /* 查找链表中间元素 */
        System.out.println("链表中间结点的结点对应的数据为：" + list.getMiddleNode().getItem());

        /* 查找链表元素 */
        System.out.println("查看对应位置结点对应的数据为：" + list.getSpecifiedNode(9).getItem());

        /* 查看元素第一次出现位置 */
        System.out.println("该元素第一次出现的位置索引为：" + list.indexOf(500));

        /* 倒序查找元素 */
        System.out.println("倒数第k个结点对应的数据为：" + list.getLastKNode(1).getItem());

        /* 原地反转链表，在第二个结点及后面进行反转 */
        list.reverseInplace(list.getHead().getNext());
        System.out.println("原地反转后的链表：");
        list.show();

        /* 递归反转链表 */
        System.out.println("递归反转后的链表：");
        list.getHead().setNext(list.reverseRecursion(list.getHead().getNext()));
        list.show();

        /* 反向输出链表 */
        System.out.println("反向打印的链表：");
        list.showReverseRecursion(list.getHead().getNext());
        System.out.println();

        /* 链表是否有环 */
        System.out.println("默认链表是否有环：" + list.isLoop());
        // 将链表的最后一个结点指向自己的结点，制造环
        list.getLastNode().setNext(list.getSpecifiedNode(4));

        boolean isCurrLoop = list.isLoop();
        System.out.println("现链表是否制造环：" + isCurrLoop);

        // 如果链表有环，则查找链表环入口
        if (isCurrLoop) {
            System.out.println("有环链表的环入口为：" + list.getLoopEntry(list.getHead()).getItem());
        }

        /* 新链表 */
        SingleLinkedList<Integer> newList = new SingleLinkedList<>();

        /* 添加链表结点 */
        newList
                .insert(2)
                .insert(4)
                .insert(6)
                .insert(8)
                .insert(10)
                .insert(5, 12)
                .insert(6, 14)
                .insert(7, 16)
                .insert(8, 18)
                .insert(9, 20);

        /* 链表是否相交 */
        System.out.println("未制造相交的新链表：");
        newList.show();

        System.out.println("默认新链表与原链表是否相交：" + newList.isIntersectant(list, newList));

        // 将新链表的最后一个结点指向旧链表的结点，制造相交
        newList.getLastNode().setNext(list.getSpecifiedNode(8));
        boolean isCurrInter = newList.isIntersectant(list, newList);
        System.out.println("现新链表与原链表是否制造相交：" + isCurrInter);

        if (isCurrInter) {
            if (isCurrLoop) {
                System.out.println("相交的有环链表的交点为：" +
                        newList.getLoopIntersection(list, newList).getItem());
            } else {
                System.out.println("相交的无环链表通过长度相减法得到的交点为：" +
                        newList.getNoLoopIntersectionByLength(list, newList).getItem());

                System.out.println("相交的无环链表通过头尾相连法得到的交点为：" +
                        newList.getNoLoopIntersectionByHeadTail(list, newList).getItem());
            }
        }

        /* 清空链表 */
        list.clear();
        System.out.println("清空后的链表元素个数为：" + list.length());
    }
}
