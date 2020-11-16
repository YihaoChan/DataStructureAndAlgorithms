package priv.yihaochan.datastructure.linkedlist.singlelinkedlist;

/**
 * @Description: 单链表测试类，添加元素至链表，测试相应方法
 */
public class SingleLinkedListTest {
    public static void main(String[] args) {
        /* 原链表 */
        SingleLinkedList<Integer> list = new SingleLinkedList<>();

        /* 添加链表结点 */
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        list.insert(5, 10);
        list.insert(6, 30);
        list.insert(7, 50);
        list.insert(8, 70);
        list.insert(9, 90);
        list.insert(10, 110);
        list.insert(11, 130);

        /* 正序打印链表 */
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

        /* 查找链表中间元素 */
        int middleNode = list.getMiddleNode().getItem();
        System.out.println("链表中间结点的元素为：" + middleNode);

        /* 查找链表元素 */
        int specifiedNode = list.getSpecifiedNode(9).getItem();
        System.out.println("查看对应位置元素为：" + specifiedNode);

        /* 查看元素第一次出现位置 */
        int firstIndex = list.indexOf(500);
        System.out.println("该元素第一次出现的位置索引为：" + firstIndex);

        /* 倒序查找元素 */
        int lastKNode = list.getLastKNode(1).getItem();
        System.out.println("倒数第k个元素为：" + lastKNode);

        /* 原地反转链表，在第二个结点及后面进行反转 */
        list.reverseInplace(list.getHead().getNext());
        System.out.println("原地反转后的链表：");
        list.show();

        /* 递归反转链表，在第二个结点及后面进行反转 */
        list.getHead().getNext().setNext(list.reverseRecursion(list.getHead().getNext().getNext()));
        System.out.println("递归反转后的链表：");
        list.show();

        /* 反向输出链表 */
        System.out.println("反向打印的链表：");
        list.showReverseRecursion(list.getHead().getNext());
        System.out.println();

        /* 链表是否有环 */
        boolean isPrevLoop = list.isLoop();
        System.out.println("默认链表是否有环：" + isPrevLoop);
        // 将链表的最后一个结点指向自己的结点，制造环
        list.getLastNode().setNext(list.getSpecifiedNode(4));
        boolean isCurrLoop = list.isLoop();
        System.out.println("现链表是否制造环：" + isCurrLoop);
        // 如果链表有环，则查找链表环入口
        if (isCurrLoop) {
            SingleLinkedList.Node enrtyNode = list.getLoopEntry(list.getHead());
            System.out.println("有环链表的环入口为：" + enrtyNode.getItem());
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
        boolean isPrevInter = newList.isIntersectant(list, newList);
        System.out.println("默认新链表与原链表是否相交：" + isPrevInter);
        // 将新链表的最后一个结点指向旧链表的结点，制造相交
        newList.getLastNode().setNext(list.getSpecifiedNode(8));
        boolean isCurrInter = newList.isIntersectant(list, newList);
        System.out.println("现新链表与原链表是否制造相交：" + isCurrInter);

        if (isCurrInter) {
            if (isCurrLoop) {
                SingleLinkedList.Node interLoop = newList.getLoopIntersection(list, newList);
                System.out.println("相交的有环链表的交点为：" + interLoop.getItem());
            } else {
                SingleLinkedList.Node interNoLoopByLen = newList.getNoLoopIntersectionByLength(list, newList);
                System.out.println("相交的无环链表通过长度相减法得到的交点为：" + interNoLoopByLen.getItem());

                SingleLinkedList.Node interNoLoopByHeadTail = newList.getNoLoopIntersectionByHeadTail(list, newList);
                System.out.println("相交的无环链表通过头尾相连法得到的交点为：" + interNoLoopByHeadTail.getItem());
            }
        }

        /* 清空链表 */
        list.clear();
        System.out.println("清空后的链表元素个数为：" + list.length());
    }
}
