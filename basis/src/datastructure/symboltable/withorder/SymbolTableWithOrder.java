package datastructure.symboltable.withorder;

/**
 * @Description: 用链表实现有序符号表
 */
public class SymbolTableWithOrder<Key extends Comparable<Key>, Value> {
    /**
     * @Description: 结点类
     */
    public class Node {
        // 键
        private Key key;

        // 值
        private Value value;

        // 下一个结点
        private Node next;

        /* 结点类构造方法 */
        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Key getKey() {
            return key;
        }

        public void setKey(Key key) {
            this.key = key;
        }

        public Value getValue() {
            return value;
        }

        public void setValue(Value value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    // 链表头结点
    private Node head;

    // 符号表中元素个数
    private int num;

    public Node getHead() {
        return head;
    }

    /**
     * @Description: 符号表类构造方法
     */
    public SymbolTableWithOrder() {
        head = new Node(null, null, null);
        num = 0;
    }

    /**
     * @Description: 符号表中键值对的个数
     */
    public int size() {
        return num;
    }

    /**
     * @Description: 插入键值对
     * 按照键的大小顺序进行插入
     */
    public void put(Key key, Value value) {
        Node prev = head;
        Node curr = head.next;

        Node newNode;

        while (curr != null && key.compareTo(curr.key) > 0) {
            prev = prev.next;
            curr = curr.next;
        }

        if (curr == null || key.compareTo(curr.key) < 0) {
            newNode = new Node(key, value, curr);
            prev.next = newNode;
            num++;
        } else if (key.compareTo(curr.key) == 0) {
            curr.value = value;
        }
    }

    /**
     * @Description: 删除键值对，并返回被删除结点
     */
    public Node delete(Key key) {
        Node prev = head;
        Node curr = head.next;

        while (curr != null) {
            if (curr.key.equals(key)) {
                prev.next = curr.next;
                num--;

                return curr;
            }

            prev = prev.next;
            curr = curr.next;
        }

        return null;
    }

    /**
     * @Description: 获取key对应的值
     */
    public Value get(Key key) {
        Node node = head;

        while (node.next != null) {
            node = node.next;

            if (node.key.equals(key)) {
                return node.value;
            }
        }

        return null;
    }

    /**
     * @Description: 遍历查看
     */
    public void show() {
        Node node = head;

        while (node.next != null) {
            node = node.next;
            System.out.println("Key=" + node.key + ", Value=" + node.value);
        }
    }
}
