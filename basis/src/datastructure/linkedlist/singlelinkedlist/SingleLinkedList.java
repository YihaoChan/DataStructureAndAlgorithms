package datastructure.linkedlist.singlelinkedlist;


/**
 * @Description: 单链表
 */
public class SingleLinkedList<T> {
    /**
     * @Description: 结点类
     */
    class Node<T> {
        // 数据域(data)
        private T item;

        // 指针域(next)，指向下一个结点，next指向的下一个结点，也是一个Node
        private Node next;

        /**
         * @Description: 结点构造方法
         */
        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }

        public T getItem() {
            return this.item;
        }

        public void setItem(T item) {
            this.item = item;
        }

        public Node getNext() {
            return this.next;
        }

        /**
         * @Description: 设置尾结点的下一个结点
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

    // 头结点，不存放数据，用于指向之后的结点
    private Node head;

    /**
     * @Description: 单链表的初始化构造方法，初始时head的两个域都是空
     * 初始情况下，头结点没有后继，不存放数据
     * head本身是一个node，只不过里面放的都是null，但head本身并不是null！所以head可以使用next等属性
     */
    public SingleLinkedList() {
        head = new Node(null, null);
    }

    /**
     * @Description: 清空链表
     */
    public void clear() {
        head.next = null;
    }

    /**
     * @Description: 获取链表长度
     */
    public int length() {
        int len = 0;

        Node node = head;

        while (node.next != null) {
            node = node.next;
            len++;
        }

        return len;
    }

    /**
     * @Description: 在链表尾处向链表插入元素
     * @param: t 需要插入链表末端的元素的数据域
     */
    public void insert(T t) {
        // 找到当前最后一个结点
        Node node = head;

        while (node.next != null) {
            node = node.next;
        }

        // 根据新元素创建新结点，并作为最后一个结点，next指向null
        // 让新结点成为最后一个结点插入到链表最后
        node.next = new Node(t, null);
    }

    /**
     * @Description: 向指定位置i处插入元素t
     * @param: i 需要插入的元素在链表中的位置，区间为[0, list.length]
     * @param: t 需要插入元素的数据域
     */
    public void insert(int i, T t) {
        /* 单链表只有一个指针域，即只有next没有prev，因此要先找到i位置处的前一个元素才能实现插入 */

        if (i < 0 || i > length()) {
            throw new RuntimeException("索引应该属于[0, list.length]区间，越界！");
        }

        // 找到原来链表中i-1位置的结点
        Node pre = head;

        while (pre.next != null && i >= 0) {
            pre = pre.next;
            i--;

            if (i == 0) {
                break;
            }
        }

        // 原来链表中i位置的结点
        Node curr = pre.next;

        pre.next = new Node(t, curr);
    }

    /**
     * @Description: 删除链表最后一个元素，并返回被删除的元素
     */
    public T remove() {
        if (head.next == null) {
            return null;
        }

        // 找到链表中的倒数第二个结点
        Node pre = head;

        while (pre.next != null && pre.next.next != null) {
            pre = pre.next;
        }

        // 原来的链表中的倒数第一个结点，取出并返回
        Node removeNode = pre.next;

        // 原来的链表中的倒数第二个结点指向空
        pre.next = null;

        return (T) removeNode.item;
    }

    /**
     * @Description: 删除指定位置i处的元素，并返回被删除的元素
     * @param: i 需要删除元素的位置，区间为[0, list.length]
     */
    public T remove(int i) {
        /* 单链表只有一个指针域，即只有next没有prev，因此要先找到i位置处的前一个结点才能实现删除 */

        if (i < 0) {
            throw new RuntimeException("索引应该属于[0, list.length]区间，越界！");
        }

        Node pre = head;

        while (pre.next != null && pre.next.next != null) {
            i--;

            // 如果找到删除位置的前一个结点，就将该结点指向删除位置的后一个结点
            if (i < 0) {
                Node curr = pre.next;

                pre.next = curr.next;

                return (T) curr.item;
            }

            pre = pre.next;
        }

        if (i > 0) throw new RuntimeException("索引应该属于[0, list.length]区间，越界！");

        // 当链表只有一个结点时，将该结点返回，并将head指向空
        Node curr = pre.next;
        pre.next = null;

        return (T) curr.item;
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

        while (curr.next != null && i >= 0) {
            curr = curr.next;
            i--;

            if (i < 0) {
                curr.item = value;
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
     * @Description: 返回中间结点
     * 快慢指针法：定义两个指针，分别指向链表头部，然后同时向后遍历，快指针一次步进两个结点，慢指针一次步进一个结点
     * 在同样的时间下，快指针走的路程为慢指针的两倍，那么当快指针走到链表的最后一个结点时，慢指针刚好处于中间结点位置处
     */
    public Node getMiddleNode() {
        if (head.next == null) {
            return null;
        }

        Node slowPointer = head.next;
        Node fastPointer = head.next;

        while (fastPointer.next != null && fastPointer.next.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        // 链表结点个数为奇数时,返回的是中间结点；链表结点个数为偶数时，返回的是中间两个结点中的前一个
        return slowPointer;
    }

    /**
     * @Description: 返回链表最后一个结点
     */
    public Node getLastNode() {
        if (head.next == null) {
            return null;
        }

        Node node = head.next;

        while (node.next != null) {
            node = node.next;
        }

        return node;
    }

    /**
     * @Description: 返回指定位置i处的结点
     * @param: i 需要查找的结点在链表中的索引
     */
    public Node getSpecifiedNode(int i) {
        if (i < 0) {
            throw new RuntimeException("索引应该属于[0, list.length]区间，越界！");
        }

        Node node = head;

        while (node.next != null && i >= 0) {
            node = node.next;
            i--;

            if (i < 0) {
                return node;
            }
        }

        // 如果跳出后i仍然大于等于0，说明越界
        throw new RuntimeException("索引应该属于[0, list.length - 1]区间，越界！");
    }

    /**
     * @Description: 返回倒数第k个元素
     * 1.先后指针：使用两个指针，一个快指针，一个慢指针，开始两个指针指向头节点；
     * 2.之后快指针先移动k个结点，此时两个指针之间的距离为k；
     * 3.此后两个指针同时向前以相同间隔步进，当快指针指向的节点为null的时候，慢指针所指的节点即为倒数第k个节点
     * @param: k 倒数第几个元素，范围为[1, list.length]
     */
    public Node getLastKNode(int k) {
        if (k <= 0) {
            throw new RuntimeException("k需为正数！");
        }

        Node fastPointer = head;
        Node slowPointer = head;

        // 让快指针先移动k个结点
        while (k > 0) {
            fastPointer = fastPointer.next;
            k--;

            if (fastPointer == null) {
                throw new RuntimeException("索引应该属于[1, list.length]区间，越界！");
            }
        }

        // 快慢指针同时步进，直到快指针指向null
        while (fastPointer != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next;
        }

        return slowPointer;
    }

    /**
     * @Description: 查找元素第一次出现在链表中的位置
     * @param: t 需要查找的元素的数据域
     */
    public int indexOf(T t) {
        // 临时变量用于遍历
        Node node = head;

        // 初始时索引指向head头结点，设为-1
        int index = -1;

        while (node.next != null) {
            node = node.next;
            index++;

            if (node.item.equals(t)) {
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
        if (head.next == null) {
            return;
        }

        Node node = head;

        while (node.next != null) {
            node = node.next;
            System.out.print(node.item + "\t");
        }

        System.out.println();
    }

    /**
     * @Description: 递归反向输出链表
     * @param: node 因为head不打印，所以传入第一个结点
     */
    public void showReverseRecursion(Node node) {
        /*
         * 当链表只有一个结点时，也会打印；而没有结点时则不打印。所以判断条件就判断是否为空表即可，不关心是否只有一个结点
         * 如果链表结构为head -> null，则不满足，直接结束程序；如果链表结构为head -> 1 -> null，则打印1。
         * 所以结束条件为node，没有node.next
         */
        if (node == null) {
            return;
        }

        showReverseRecursion(node.next);

        System.out.print(node.item + "\t");
    }

    /**
     * @Description: 原地反转链表
     * @param: node 需要从哪个地方开始反转的结点
     */
    public void reverseInplace(Node node) {
        if (node == null || node.next == null) {
            return;
        }

        Node pre = node.next;

        Node curr = pre.next;

        while (curr != null) {
            // 跳过当前结点，使上一个结点越过当前结点指向后一个结点
            pre.next = curr.next;

            // 把取出来的结点放到第一个结点
            curr.next = node.next;
            node.next = curr;

            // 推进，更新下一个结点作为待反转的结点
            curr = pre.next;
        }
    }

    /**
     * @Description: 递归反转链表
     * 测试方法中用node.next接收，意思是node指向返回的结点，即指向反转的起点
     * 递归每次都是处理两个Node，第一次处理倒数第一个和倒数第二个node，返回倒数第一个node
     * 这两个处理好了的node看做一个新的node然后和倒数第三个node完成反转，此时返回值依旧是倒数第一个node
     * 每次处理好了的链表看做一个新的Node，与前一个node完成反转
     * @param: node 需要从哪个地方开始反转的结点
     */
    public Node reverseRecursion(Node node) {
        /*
         * 结束条件，假设当链表只有一个结点，或者是一个空表时就返回，不能漏掉其中一个条件
         * 要传入node.next，所以如果是空表，传入的head的next为null，此时程序退出
         * 如果链表只有一个结点，传入了head，那么node.next为1，传到函数里面，1.next为null，刚好符合不需要反转，程序退出
         */
        if (node == null || node.next == null) {
            return node;
        }

        // 递归直到最后一个结点
        Node newList = reverseRecursion(node.next);

        // 反转位置
        node.next.next = node;
        node.next = null;

        return newList;
    }

    /**
     * @Description: 判断链表是否有环
     * 1.快慢指针法：定义两个指针，分别指向链表头部，然后同时向后遍历，快指针一次步进两个结点，慢指针一次步进一个结点；
     * 2.快指针的速度为慢指针的两倍，如果它们能够在某处相遇，说明链表中必存在环；
     * 3.如果链表中不存在环，则快指针与慢指针的距离会越拉越大，不可能相遇。
     */
    public boolean isLoop() {
        Node slowPointer = head;
        Node fastPointer = head;

        // 如果链表中只有一个/两个结点，那么下面的判断条件不成立，链表为无环
        while (fastPointer.next != null && fastPointer.next.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            if (slowPointer == fastPointer) {
                return true;
            }
        }

        return false;
    }

    /**
     * @Description: 寻找有环链表的入口
     * 1.快慢指针法：定义两个指针，分别指向链表头部，然后同时向后遍历，快指针一次步进两个结点，慢指针一次步进一个结点；
     * 2.当快指针和慢指针相遇时，说明链表中存在环；
     * 3.此时引入新指针从头结点出发，每次步进一个结点，与慢指针相同；
     * 4.当新指针和原来的慢指针在步进的过程中相遇时，指向的就是链表的环入口结点。
     * @param: node 链表的头结点
     */
    public Node getLoopEntry(Node node) {
        Node slowPointer = node;
        Node fastPointer = node;

        // 当找到快慢指针相遇点时，将慢指针取出，放入temp
        Node temp = null;

        while (fastPointer.next != null && fastPointer.next.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;

            if (slowPointer == fastPointer) {
                temp = slowPointer;
                break;
            }
        }

        /* 新指针，从头结点开始，和慢指针相遇时即为环的入口 */
        Node entryLoopNode = node;

        while (entryLoopNode != temp) {
            temp = temp.next;
            entryLoopNode = entryLoopNode.next;
        }

        return entryLoopNode;
    }

    /**
     * @Description: 判断两个链表是否相交
     * @param: linkedListOne, linkedListTwo 两个需要判断是否相交的链表
     */
    public boolean isIntersectant(SingleLinkedList<T> listOne, SingleLinkedList<T> listTwo) {
        boolean isListOneLoop = listOne.isLoop();
        boolean isListTwoLoop = listTwo.isLoop();

        if ((isListOneLoop ^ isListTwoLoop)) {
            /* 如果一个链表有环，一个链表无环，那么它们必不可能相交！*/
            return false;
        } else if (!(isListOneLoop || isListTwoLoop)) {
            /* 如果两个链表都没有环，就采用以下方法判断是否相交 */

            /* 若两个链表相交，则它们尾结点一定相同(不只是元素相等，而是整个结点都相同) */

            // 临时变量用于步进，否则会影响传入链表的指针移动
            Node tempOne = listOne.head;
            Node tempTwo = listTwo.head;

            while (tempOne.next != null) {
                tempOne = tempOne.next;
            }

            while (tempTwo.next != null) {
                tempTwo = tempTwo.next;
            }

            return tempOne == tempTwo;
        } else if ((isListOneLoop && isListTwoLoop)) {
            /* 如果两个链表都有环，就采用以下方法判断是否相交 */

            /*
             * 1.先比较两个链表的入环结点是否相同，若相同，则相交；
             * 2.若不相同，则从某个链表的入环结点开始循环一周，当循环过程中遇到相同结点即为循环满一周，此时可停下；
             * 3.判断是否有结点等于另一个链表的结点；
             * 4.若有，则相交，否则不相交。
             */

            // 找到两有环链表的环入口结点
            Node entryLoopListOne = listOne.getLoopEntry(listOne.head);
            Node entryLoopListTwo = listTwo.getLoopEntry(listTwo.head);

            if (entryLoopListOne == entryLoopListTwo) {
                // 如果两入口结点相等，说明相交点在环外
                return true;
            } else {
                // 如果两入口结点不相同，说明相交点在环内，也可能不相交
                // 临时变量用于步进，否则会影响后续判断结点之间是否相同
                Node temp = entryLoopListOne;

                while (temp.next != null) {
                    temp = temp.next;

                    if (temp == entryLoopListTwo) { // 若和另一个链表的入环点相同，则相交
                        return true;
                    } else if (temp == entryLoopListOne) { // 如果绕了整整一圈遇到了自己本身，但还没有遇到相同结点，则不相交
                        return false;
                    }
                }
            }
        }

        return false;
    }

    /**
     * @Description: 相交的无环单链表求交点 - 长度相减法
     * 1.由于相交部分全部都相同，因此，只需要先得到两个链表长度的差，再让长度较长的链表的头指针先走[长度之差]步；
     * 2.在此之后两链表同时出发，当出现第一个相等的结点时，即为两链表交点。
     * @param: linkedListOne, linkedListTwo 两个无环但相交的链表
     * @return: Node 无环单链表相交的交点
     */
    public Node getNoLoopIntersectionByLength(SingleLinkedList<T> listOne, SingleLinkedList<T> listTwo) {
        /* 两链表已相交，均为无环链表 */

        // 获取两个链表的头结点
        Node nodeOne = listOne.head;
        Node nodeTwo = listTwo.head;

        int lenListOne = listOne.length();
        int lenListTwo = listTwo.length();
        int diffLen;

        if (lenListOne > lenListTwo) {
            diffLen = lenListOne - lenListTwo;

            while (diffLen > 0) {
                nodeOne = nodeOne.next;
                diffLen--;
            }
        } else {
            diffLen = lenListTwo - lenListOne;

            while (diffLen > 0) {
                nodeTwo = nodeTwo.next;
                diffLen--;
            }
        }

        while (nodeOne != nodeTwo) {
            nodeOne = nodeOne.next;
            nodeTwo = nodeTwo.next;
        }

        return nodeOne;
    }

    /**
     * @Description: 相交的无环单链表求交点 - 首尾相接法
     * 1.将第一个链表尾结点的next指针指向第二个链表的head，那么两个链表就合成了一个新链表；
     * 2.若该链表存在环，则原两个链表一定相交，新链表的环入口即为两链表的交点。
     * @param: listOne, listTwo 两个无环但相交的链表
     * @return: Node 无环单链表相交的交点
     */
    public Node getNoLoopIntersectionByHeadTail(SingleLinkedList<T> listOne, SingleLinkedList<T> listTwo) {
        /* 两链表已相交，均为无环链表 */

        // 将两个链表首尾相连，成为一个新链表
        listOne.getLastNode().next = listTwo.head;

        // 在新得到的链表上得到环的入口，即两链表的交点
        return listOne.getLoopEntry(listOne.head);
    }

    /**
     * @Description: 相交的有环链表求交点
     * 1.首先分别找到各自到环的入口点；
     * 2.如果两个链表的环入口相同，则获取两个链表从头结点到入口的长度之差，之后进行相减；
     * 3.让头结点到入口距离较长的链表先走[差值]步，再让两个链表同时步进，当结点相同时，即为相交点；
     * 4.如果入口点不同，则相交点为这两个链表的任意一个入口点。
     * @param: listOne, listTwo 两个有环并相交的链表
     * @return: Node 有环单链表相交的交点
     */
    public Node getLoopIntersection(SingleLinkedList<T> listOne, SingleLinkedList<T> listTwo) {
        /* 两链表已相交，均为有环链表 */

        // 找到各自环的入口点
        Node entryLoopListOne = getLoopEntry(listOne.head);
        Node entryLoopListTwo = getLoopEntry(listTwo.head);

        if (entryLoopListOne == entryLoopListTwo) {
            // 两链表环入口点相同，说明相交于环外
            int lenHeadToEntryOne = 0;
            int lenHeadToEntryTwo = 0;

            Node nodeOne = listOne.head;
            Node nodeTwo = listTwo.head;

            Node tempOne = nodeOne;
            Node tempTwo = nodeTwo;

            while (tempOne.next != entryLoopListOne) {
                tempOne = tempOne.next;
                lenHeadToEntryOne++;
            }

            while (tempTwo.next != entryLoopListTwo) {
                tempTwo = tempTwo.next;
                lenHeadToEntryTwo++;
            }

            int diffLen;

            if (lenHeadToEntryOne - lenHeadToEntryTwo > 0) {
                diffLen = lenHeadToEntryOne - lenHeadToEntryTwo;

                while (diffLen > 0) {
                    nodeOne = nodeOne.next;
                    diffLen--;
                }
            } else {
                diffLen = lenHeadToEntryTwo - lenHeadToEntryOne;

                while (diffLen > 0) {
                    nodeTwo = nodeTwo.next;
                    diffLen--;
                }
            }

            while (nodeOne != nodeTwo) {
                nodeOne = nodeOne.next;
                nodeTwo = nodeTwo.next;
            }

            return nodeOne;
        } else {
            // 两链表环入口点不同，说明相交于环内，则返回任意一个环的入口点即可
            return entryLoopListTwo;
        }
    }

    /**
     * @Description: 约瑟夫问题
     * n个人(编号1 ~ n)围成一圈，从编号为k的人开始报数，报到m的退出，剩下的人继续从1开始报数；
     * 求每次退出的人的编号，及幸存者的编号。
     * 1. 创建循环链表，定义curr指针和prev指针，prev指针在curr指针之前一个结点；
     * 2. 先让两指针走k步，即实现从编号为k的人开始报数；
     * 3. 计数器开始计数，当计到约定的报数数字时，将prev的next指向curr.next，即删除curr结点；
     * 4. 将计数器归1，并更新当前结点；
     * 5. 直到prev的next为prev自己本身时，此时链表中只剩一个结点，则该结点就是幸存者。
     * @param: n 需要创建多少个结点
     * @param: k 从第k个结点先开始遍历
     * @param: m 每次数m个结点
     */
    public void joseph(int n, int k, int m) {
        if (n <= 0) {
            throw new RuntimeException("n表示需要创建多少个结点，需大于0!");
        } else if (k <= 0 || k > n) {
            throw new RuntimeException("k表示需要从哪个结点开始遍历，范围为[1, n]");
        } else if (m <= 0 || m > n) {
            throw new RuntimeException("m表示每次数多少个结点，范围为[1, n]");
        }

        // 根据n的值创建n个结点
        Node node = head;

        for (int i = 1; i <= n; i++) {
            // 根据i的值创建新结点
            Node newNode = new Node(i, null);

            // 将上一个结点指向当前新结点
            node.next = newNode;

            // 步进
            node = newNode;

            System.out.print(newNode.item + "\t");
        }

        System.out.println();

        // 尾结点和第一个结点相连，构成循环链表
        node.next = head.next;

        // 当前结点的上一个结点
        Node prev = head;
        // 当前结点
        Node curr = head.next;

        // 两指针先走k步，实现从第k个结点开始遍历
        while (k != 1) {
            prev = prev.next;
            curr = curr.next;

            k--;
        }

        // 计数
        int count = 1;

        /* 留下最后一个结点 */
        while (prev.next != prev) {
            count++;
            prev = prev.next;
            curr = curr.next;

            if (count == m) {
                System.out.print(curr.item + "->");

                // 前一个结点指向当前结点的后一个结点，即删除当前结点
                prev.next = curr.next;

                // 计数器归1
                count = 1;

                // 更新当前结点
                curr = curr.next;
            }
        }

        System.out.println("[remain]" + curr.item);
    }
}