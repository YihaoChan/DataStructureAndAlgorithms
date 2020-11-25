package priv.yihaochan.datastructure.linkedlist.twowaylinkedlist;

/**
 * @Description: 结点类
 * 在测试时需要创建Node类型的对象，则该结点类需要被外部类使用，所以类的权限修饰符不能是private
 * 同时，该结点类不只是会被链表类使用，在外部的测试类也会借助该类实现结点之间的连接等操作，
 * 因此，不能将它作为链表类的内部类
 */
class Node<T> {
    // 数据域(data)
    private T item;

    // 指针域(prev)，指向上一个结点，prev指向的上一个结点，也是一个Node
    private Node prev;

    // 指针域(next)，指向下一个结点，next指向的下一个结点，也是一个Node
    private Node next;

    /**
     * @Description: 结点构造方法
     */
    public Node(T item, Node prev, Node next) {
        this.item = item;
        this.prev = prev;
        this.next = next;
    }

    public T getItem() {
        return this.item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Node getPrev() {
        return this.prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getNext() {
        return this.next;
    }

    /**
     * @Description: 设置尾结点的下一个节点
     * 举例：
     * Person p = new Person("XXX", 18);
     * 若没有把age进行private：p.age = 20;
     * 若把age进行private：p.setAge(20);
     * 同理：
     * 若没有把next进行private：listOne.getHead().next = listTwo.getHead()
     * 若把next进行private：listOne.getHead().setNext(listTwo.getHead())
     */
    public void setNext(Node next) {
        this.next = next;
    }
}

/**
 * @Description: 实现双链表各种常见操作
 */
public class TwoWayLinkedList<T> {
    // 头结点，不存放数据，用于指向之后的结点
    private final Node head;

    // 尾结点，最后一个存放元素的结点
    private Node tail;

    /**
     * @Description: 单链表的初始化构造方法，初始时head的两个域都是空
     * 初始情况下，头结点没有后继，不存放数据
     * head本身是一个node，只不过里面放的都是null，但head本身并不是null！所以head可以使用next等属性
     * 一开始根本没有尾结点
     */
    public TwoWayLinkedList() {
        head = new Node(null, null, null);
        tail = null;
    }

    /**
     * @Description: 清空链表
     */
    public void clear() {
        head.setNext(null);
        tail = null;
    }

    /**
     * @Description: 获取链表长度
     */
    public int length() {
        int len = 0;

        Node node = head;

        while (node.getNext() != null) {
            node = node.getNext();
            len++;
        }

        return len;
    }

    /**
     * @Description: 在链表尾处向链表插入元素
     * @param: t 需要插入链表末端的元素的数据域
     */
    public TwoWayLinkedList insert(T t) {
        /* 空链表没有尾结点，要单独处理 */

        Node newNode;

        // 如果链表为空，则创建新结点，让新结点成为尾结点，并让头结点指向它
        if (head.getNext() == null) {
            newNode = new Node(t, head, null);
            tail = newNode;
            head.setNext(tail);
        } else { // 如果链表不为空

            // 根据新元素创建新结点，并作为尾结点，prev指向原来的尾结点，next指向null
            newNode = new Node(t, tail, null);

            // 让原来的尾结点的next指向新结点
            tail.setNext(newNode);

            // 再让新结点成为尾结点
            tail = newNode;
        }

        return this;
    }

    /**
     * @Description: 向指定位置i处插入元素t
     * @param: i 需要插入的元素在链表中的位置，区间为[0, list.length]
     * @param: t 需要插入元素的数据域
     */
    public TwoWayLinkedList insert(int i, T t) {
        /* 双链表有两个指针域，prev可以指向之前的元素，所以找到位置i处结点即可 */

        if (i < 0 || i > length()) {
            throw new RuntimeException("索引应该属于[0, list.length]区间，越界！");
        }

        // 用于创建新结点
        Node newNode;

        // 找到原来链表中i-1位置的结点
        Node pre = head;

        while (pre.getNext() != null && i >= 0) {
            pre = pre.getNext();
            i--;

            if (i == 0) {
                break;
            }
        }

        // 原来链表中i位置的结点
        Node curr = pre.getNext();

        newNode = new Node(t, pre, curr);
        pre.setNext(newNode);

        /* 原来第i处的结点的prev指向，如果插入位置的后一个位置为null，就没有prev指针，需要单独处理 */
        if (curr != null) {
            curr.setPrev(newNode);
        } else {
            // 如果curr是空，表明插入的元素索引到了链表末端，则将它作为尾结点
            tail = newNode;
        }

        return this;
    }

    /**
     * @Description: 删除链表最后一个元素，并返回被删除的元素，即删除尾结点
     */
    public T remove() {
        if (head.getNext() == null) {
            return null;
        }

        Node removeNode = tail;

        // 尾结点的前一个结点的next指向null
        tail.getPrev().setNext(null);

        // 尾结点的上一个结点作为新的尾结点
        tail = tail.getPrev();

        return (T) removeNode.getItem();
    }

    /**
     * @Description: 删除指定位置i处的元素，并返回被删除的元素
     * @param: i 需要删除元素的位置，区间为[0, list.length]
     */
    public T remove(int i) {
        // 双链表有两个指针域，prev可以指向之前的元素，所以找到位置i处结点即可实现独立删除

        if (i < 0) {
            throw new RuntimeException("索引应该属于[0, list.length]区间，越界！");
        }

        Node curr = head;

        // 找到对应索引的结点
        while (curr.getNext() != null) {
            curr = curr.getNext();
            i--;

            if (i < 0) {
                break;
            }
        }

        if (i >= 0) throw new RuntimeException("索引应该属于[0, list.length]区间，越界！");

        // 被删除的结点
        Node removeNode = curr;

        // 前一个结点的next指向后一个结点
        curr.getPrev().setNext(curr.getNext());

        // 如果后一个结点为null，要单独讨论
        if (curr.getNext() != null) {
            // 如果被删除的结点不是尾结点，则后一个结点的prev指向前一个结点
            curr.getNext().setPrev(curr.getPrev());
        } else {
            // 如果被删除的结点是尾结点，那么后一个位置为null，没有prev
            // 同时将删除的结点的前一个结点作为尾结点
            tail = curr.getPrev();
        }

        return (T) removeNode.getItem();
    }

    /**
     * @Description: 根据索引修改对应元素
     * @param: i 需要修改的元素的位置，区间为[0, list.length-1]
     * @param: value 将该位置元素的数据域赋值为新值
     */
    public void update(int i, T value) {
        if (i < 0) {
            throw new RuntimeException("索引应该属于[0, list.length]区间，越界！");
        }

        Node curr = head;

        while (curr.getNext() != null) {
            curr = curr.getNext();
            i--;

            if (i < 0) {
                curr.setItem(value);
                return;
            }
        }

        // 如果跳出后i仍然大于等于0，说明越界
        throw new RuntimeException("索引大于链表长度-1，越界！");
    }

    /**
     * @Description: 返回头结点
     */
    public Node getHead() {
        return head;
    }

    /**
     * @Description: 返回链表最后一个结点
     */
    public Node getTail() {
        return tail;
    }

    /**
     * @Description: 返回指定位置i处的元素
     * @param: i 需要查找的元素在链表中的索引
     */
    public Node getSpecifiedNode(int i) {
        if (i < 0) {
            throw new RuntimeException("索引应该属于[0, list.length]区间，越界！");
        }

        Node curr = head;

        while (curr.getNext() != null) {
            curr = curr.getNext();
            i--;

            if (i < 0) {
                return curr;
            }
        }

        // 如果跳出后i仍然大于等于0，说明越界
        throw new RuntimeException("索引应该属于[0, list.length - 1]区间，越界！");
    }


    /**
     * @Description: 找到元素在链表中第一次出现的位置
     * @param: t 需要查找的元素的数据域
     */
    public int indexOf(T t) {
        // 临时变量用于遍历
        Node node = head;

        // 初始时索引指向head头结点，设为-1
        int index = -1;

        while (node.getNext() != null) {
            node = node.getNext();
            index++;

            if (node.getItem().equals(t)) {
                // ==：本质上是比较地址；equals：比较的是两个对象之间的内容
                return index;
            }
        }

        // 如果链表中没有相应元素，则返回-1
        return -1;
    }

    /**
     * @Description: 遍历查看元素
     */
    public void show() {
        if (head.getNext() == null) {
            return;
        }

        Node node = head;

        while (node.getNext() != null) {
            node = node.getNext();
            System.out.print(node.getItem() + "\t");
        }

        System.out.println();
    }
}
