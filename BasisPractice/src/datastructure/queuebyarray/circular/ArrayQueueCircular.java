package datastructure.queuebyarray.circular;

/**
 * @Description: 定义队列基本元素和方法：根据大小创建，判断队列空/满，出队/入队，查看队列、队头元素，获得有效数据个数
 */
public class ArrayQueueCircular {
    // 用数组表示队列
    private int[] arr;
    // 队列长度
    private int maxSize;
    // 头指针，指向队列第一个元素
    private int front;
    // 尾指针，指向队列最后一个元素的【后一个】位置
    private int rear;

    /* 构造方法，传递数组大小，通过该值创建队列 */
    public ArrayQueueCircular(int arrSize) {
        maxSize = arrSize;
        arr = new int[maxSize];

        // 初始时，头指针和尾指针都指向0
        front = 0;
        rear = 0;
    }

    /**
     * @Description: 判断队列是否为空
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * @Description: 判断队列是否已满
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * @Description: 元素入队
     * @param: n 入队元素
     */
    public void enqueue(int n) {
        // 判断队列是否已满
        if (isFull()) {
            System.out.println("队列已满，无法插入数据！");
            return;
        }

        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    /**
     * @Description: 元素出队
     * @return: int 出队元素
     */
    public int dequeue() {
        // 判读队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空，元素无法出队！");
        }

        int num = arr[front];
        front = (front + 1) % maxSize;

        return num;
    }

    /**
     * @Description: 查看队列元素
     */
    public void showQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            return;
        }

        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * @Description: 当前队列有效数据个数
     */
    public int size() {
        return (rear - front + maxSize) % maxSize;
    }

    /**
     * @Description: 查看队头元素
     */
    public int peekQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空，无法查看队头数据！");
        }

        return arr[front];
    }
}
