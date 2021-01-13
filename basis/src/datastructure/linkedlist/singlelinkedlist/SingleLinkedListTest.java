package datastructure.linkedlist.singlelinkedlist;

/**
 * @Description: 单链表 - 测试类
 */
public class SingleLinkedListTest {
    public static void main(String[] args) {
        /* 原链表 */
        SingleLinkedList<Integer> singleLinkedList = new SingleLinkedList<>();

        /* 添加链表结点 */
        singleLinkedList.insert(1);
        singleLinkedList.insert(2);
        singleLinkedList.insert(3);
        singleLinkedList.insert(4);
        singleLinkedList.insert(5);
        singleLinkedList.insert(5, 10);
        singleLinkedList.insert(6, 30);
        singleLinkedList.insert(7, 50);
        singleLinkedList.insert(8, 70);
        singleLinkedList.insert(9, 90);
        singleLinkedList.insert(10, 110);
        singleLinkedList.insert(11, 130);

        /* 正序打印链表 */
        System.out.println("原始链表：");
        singleLinkedList.show();
        System.out.println("原始链表元素个数为：" + singleLinkedList.length());

        /* 删除结点 */
        System.out.println("删除该位置处的元素为：" + singleLinkedList.remove(11));
        System.out.println("删除链表末端处的元素为：" + singleLinkedList.remove());
        System.out.println("删除元素后的链表：");
        singleLinkedList.show();

        /* 修改结点 */
        singleLinkedList.update(9, 500);
        System.out.println("修改结点后的链表：");
        singleLinkedList.show();

        /* 查找链表中间元素 */
        System.out.println("链表中间结点的结点对应的数据为：" + singleLinkedList.getMiddleNode().getItem());

        /* 查找链表元素 */
        System.out.println("查看对应位置结点对应的数据为：" + singleLinkedList.getSpecifiedNode(9).getItem());

        /* 查看元素第一次出现位置 */
        System.out.println("该元素第一次出现的位置索引为：" + singleLinkedList.indexOf(500));

        /* 倒序查找元素 */
        System.out.println("倒数第k个结点对应的数据为：" + singleLinkedList.getLastKNode(1).getItem());

        /* 原地反转链表，在第二个结点及后面进行反转 */
        singleLinkedList.reverseInplace(singleLinkedList.getHead().getNext());
        System.out.println("原地反转后的链表：");
        singleLinkedList.show();

        /* 递归反转链表 */
        System.out.println("递归反转后的链表：");
        singleLinkedList.getHead().setNext(singleLinkedList.reverseRecursion(singleLinkedList.getHead().getNext()));
        singleLinkedList.show();

        /* 反向输出链表 */
        System.out.println("反向打印的链表：");
        singleLinkedList.showReverseRecursion(singleLinkedList.getHead().getNext());
        System.out.println();

        /* 链表是否有环 */
        System.out.println("默认链表是否有环：" + singleLinkedList.isLoop());
        // 将链表的最后一个结点指向自己的结点，制造环
        singleLinkedList.getLastNode().setNext(singleLinkedList.getSpecifiedNode(4));

        boolean isCurrLoop = singleLinkedList.isLoop();
        System.out.println("现链表是否制造环：" + isCurrLoop);

        // 如果链表有环，则查找链表环入口
        if (isCurrLoop) {
            System.out.println(
                    "有环链表的环入口为：" + singleLinkedList.getLoopEntry(singleLinkedList.getHead()).getItem()
            );
        }

        /* 新链表 */
        SingleLinkedList<Integer> newList = new SingleLinkedList<>();

        /* 添加链表结点 */
        newList.insert(2);
        newList.insert(4);
        newList.insert(6);
        newList.insert(8);
        newList.insert(10);
        newList.insert(5, 12);
        newList.insert(6, 14);
        newList.insert(7, 16);
        newList.insert(8, 18);
        newList.insert(9, 20);

        /* 链表是否相交 */
        System.out.println("未制造相交的新链表：");
        newList.show();

        System.out.println("默认新链表与原链表是否相交：" + newList.isIntersectant(singleLinkedList, newList));

        // 将新链表的最后一个结点指向旧链表的结点，制造相交
        newList.getLastNode().setNext(singleLinkedList.getSpecifiedNode(8));
        boolean isCurrInter = newList.isIntersectant(singleLinkedList, newList);
        System.out.println("现新链表与原链表是否制造相交：" + isCurrInter);

        if (isCurrInter) {
            if (isCurrLoop) {
                System.out.println("相交的有环链表的交点为：" +
                        newList.getLoopIntersection(singleLinkedList, newList).getItem());
            } else {
                System.out.println("相交的无环链表通过长度相减法得到的交点为：" +
                        newList.getNoLoopIntersectionByLength(singleLinkedList, newList).getItem());

                System.out.println("相交的无环链表通过头尾相连法得到的交点为：" +
                        newList.getNoLoopIntersectionByHeadTail(singleLinkedList, newList).getItem());
            }
        }

        /* 清空链表 */
        singleLinkedList.clear();
        System.out.println("清空后的链表元素个数为：" + singleLinkedList.length());
    }
}
