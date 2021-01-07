package datastructure.hashtable.chainaddress;


/**
 * @Description: 除留余数法构造哈希函数；链地址法处理哈希冲突
 */
public class HashTable {
    /**
     * @Description: 结点类
     */
    private class Node {
        // 数据域
        public Integer data; // 头结点数据域为null，所以要用Integer

        // next域
        public Node next;

        /**
         * @Description: 结点的构造方法
         */
        public Node(Integer data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    // 哈希表
    private Node[] hashTable;

    // 哈希表长
    private int hashTableLength;

    // 哈希表中元素个数
    private int size;

    /**
     * @Description: 返回哈希表中存放元素个数
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @Description: 返回哈希表表长
     */
    public int getHashTableLength() {
        return this.hashTableLength;
    }

    /**
     * @Description: 判断一个数是否为素数
     */
    private boolean isPrime(int num) {
        double sqrt = Math.sqrt(num);

        if (num < 2) {
            return false;
        }

        if (num == 2 || num == 3) {
            return true;
        }

        if (num % 2 == 0) { // 先判断是否为偶数，若偶数就直接结束程序
            return false;
        }

        for (int i = 3; i <= sqrt; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * @Description: 如果哈希表长恰好为素数，则直接返回该表长；否则，计算大于它的的最小素数
     */
    private int getPrime(int num) {
        if (isPrime(num)) {
            return num;
        }

        int answer = num + 1;

        while (!isPrime(answer)) {
            answer++;
        }

        return answer;
    }

    /**
     * @Description: 哈希表构造方法 - 带表长
     */
    public HashTable(int hashTableLength) {
        // 让哈希表长成为一个素数
        if (!isPrime(hashTableLength)) {
            hashTableLength = getPrime(hashTableLength);
        }

        // 创建哈希表
        this.hashTableLength = hashTableLength;
        hashTable = new Node[this.hashTableLength];
    }

    /**
     * @Description: 哈希表构造方法 - 不带表长，默认传入10
     */
    public HashTable() {
        this(10);
    }

    /**
     * @param item 需要计算哈希值的元素
     * @Description: 哈希函数，除留余数法，除数使用表长
     */
    private int hash(int item) {
        return item % hashTableLength;
    }

    /**
     * @Description: 插入元素
     */
    public void insert(int item) {
        // 计算元素哈希地址
        int hashAddress = hash(item);

        // 根据待插入元素创建结点
        Node newNode = new Node(item, null);

        if (hashTable[hashAddress] == null) { // 如果哈希表中对应位置元素为空，则直接插入元素
            hashTable[hashAddress] = new Node(null, newNode);
        } else { // 如果哈希表中对应位置元素不为空，说明产生哈希冲突，则在链表结尾处插入该元素
            // 该哈希地址处链表的头结点
            Node node = hashTable[hashAddress];

            // 到链表末尾
            while (node.next != null) {
                node = node.next;
            }

            node.next = newNode;
        }

        size++;
    }

    /**
     * @Description: 查找元素并返回元素哈希地址，产生哈希冲突的结点都放在同一哈希地址的链表中
     */
    public int find(int item) {
        // 计算元素哈希地址
        int hashAddress = hash(item);

        if (hashTable[hashAddress] == null) { // 哈希地址处没有结点
            return -1;
        } else { // 哈希地址处有结点，则遍历该链表
            Node node = hashTable[hashAddress].next; // 链表首结点

            while (node.data != item) {
                node = node.next;

                // 遍历完链表都找不到这个元素
                if (node == null) {
                    return -1;
                }
            }
        }

        return hashAddress;
    }

    /**
     * @Description: 删除元素
     */
    public void remove(int item) {
        // 哈希地址
        int hashAddress = find(item);

        // 待删除元素不存在
        if (hashAddress == -1) {
            return;
        }

        // 待删除元素存在
        Node prev = hashTable[hashAddress]; // 待删除元素的前驱结点，初始时为头结点
        Node curr = prev.next; // 待删除元素对应的结点，初始时为第一个结点

        while (curr.data != item) {
            prev = prev.next;
            curr = curr.next;
        }

        // 前驱结点的next域指向后继结点，即将待删除结点删除
        prev.next = curr.next;

        size--;
    }

    /**
     * @Description: 查看哈希表中元素
     */
    public void printHashTable() {
        for (int i = 0; i < hashTableLength; i++) {
            Node node = hashTable[i];

            System.out.print(i);

            if (node == null) {
                System.out.println("-" + node);
                continue;
            }

            while (node.next != null) {
                node = node.next;

                System.out.print("--->" + node.data);
            }

            System.out.println();
        }
        System.out.println();
    }
}
