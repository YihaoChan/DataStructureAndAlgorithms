package datastructure.stackbylinkedlist;

/**
 * @Description: 用链表实现栈 - 测试类
 */
public class StackByLinkedListTest {
    public static void main(String[] args) {
        StackByLinkedList<Integer> stackByLinkedList = new StackByLinkedList<>();

        /* 元素入栈 */
        stackByLinkedList
                .push(10)
                .push(20)
                .push(30)
                .push(40);

        System.out.println("初始时的栈：");
        stackByLinkedList.show();
        System.out.println("初始时栈的元素个数为：" + stackByLinkedList.size());

        /* 查看栈顶元素 */
        System.out.println("栈顶元素：" + stackByLinkedList.peek().getItem());

        /* 修改栈中元素的值 */
        System.out.println("修改元素后的栈：");
        stackByLinkedList.update(3, 100);
        stackByLinkedList.show();

        /* 元素出栈 */
        System.out.println("第一次弹栈的元素为：" + stackByLinkedList.pop().getItem());
        System.out.println("第一次弹栈后的栈：");
        stackByLinkedList.show();
        System.out.println("第二次弹栈的元素为：" + stackByLinkedList.pop().getItem());
        System.out.println("第二次弹栈后的栈：");
        stackByLinkedList.show();

        /* 查看栈顶元素 */
        System.out.println("栈顶元素：" + stackByLinkedList.peek().getItem());

        System.out.println("弹栈完成后栈的元素个数为：" + stackByLinkedList.size());
    }
}
