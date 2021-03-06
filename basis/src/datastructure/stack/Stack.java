package datastructure.stack;


/**
 * @Description: 栈
 */
public class Stack<T> {
    /**
     * @Description: 结点类
     */
    class Node<T> {
        // 数据域
        private T item;

        // 指针域
        private Node next;

        /**
         * @Description: 构造方法
         */
        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }

        public T getItem() {
            return this.item;
        }

        public Node getNext() {
            return this.next;
        }
    }

    // 栈顶结点
    private Node top;

    // 链表长度
    private int len;

    /**
     * @Description: 栈构造方法
     */
    public Stack() {
        // 链表头结点
        top = new Node(null, null);

        len = 0;
    }

    /**
     * @Description: 获取栈中第一个结点
     */
    public Node getTop() {
        return top;
    }

    /**
     * @Description: 判断栈是否为空
     */
    public boolean isEmpty() {
        return len == 0;
    }

    /**
     * @Description: 栈中元素个数
     */
    public int size() {
        return len;
    }

    /**
     * @Description: 把t元素压入栈
     */
    public void push(T t) {
        // 新结点的next指向原栈顶元素
        Node newNode = new Node(t, top);

        // 新结点更新为栈顶元素
        top = newNode;

        len++;
    }

    /**
     * @Description: 弹出栈顶元素并返回
     */
    public Node pop() {
        if (top.next == null) {
            return null;
        }

        Node popNode = top;

        // 让原来栈的第二个元素变为栈顶元素
        top = top.next;

        len--;

        return popNode;
    }

    /**
     * @Description: 修改栈中相应索引的结点的值
     */
    public void update(int i, T value) {
        if (i < 0) {
            throw new RuntimeException("索引不得为负数！");
        } else if (top.getNext() == null) {
            return;
        }

        Node node = top;

        // node.next.next为空，表示node.next为头结点
        while (node.next.next != null && i > 0) {
            node = node.next;

            i--;
        }

        if (i > 0) {
            throw new RuntimeException("索引应该属于[0, list.length - 1]区间，越界！");
        }

        node.item = value;
    }

    /**
     * @Description: 查看栈顶元素
     */
    public Node peek() {
        return top;
    }

    /**
     * @Description: 遍历查看栈
     */
    public void show() {
        Node node = top;

        while (node.next != null) {
            System.out.println(node.item);
            node = node.next;
        }
    }
}
