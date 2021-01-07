package datastructure.symboltable.noorder;

/**
 * @Description: 用链表实现无序符号表
 */
public class SymbolTableWithNoOrder<Key, Value> {
    /*
     * @Description: 结点类
     */
    public class Node {
        // 键
        private Key key;

        // 值
        private Value value;

        // 下一个结点
        private Node next;

        /**
         * @Description: 结点类构造方法
         */
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
    public SymbolTableWithNoOrder() {
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
     * 键具有唯一性：如果表中之前有同样的键，则将新值覆盖到原值；
     * 如果表中之前没有同样的键，则将新的键值对插入到链表头部
     */
    public void put(Key key, Value value) {
        Node node = head;

        while (node.next != null) {
            node = node.next;

            // 判断当前结点的键是否和传入的key相同，如果是，则覆盖node的值
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        // 如果表中没有键和传入的key相同，则将新键值对插入到链表头部
        Node newNode = new Node(key, value, head.next);
        head.next = newNode;

        num++;
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
