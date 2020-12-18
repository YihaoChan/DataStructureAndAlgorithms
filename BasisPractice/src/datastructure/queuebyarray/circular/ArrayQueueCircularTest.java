package datastructure.queuebyarray.circular;

import java.util.Scanner;

/**
 * @Description: 用数组实现循环队列 - 测试类
 */
public class ArrayQueueCircularTest {
    public static void main(String[] args) {
        ArrayQueueCircular arrayQueue = new ArrayQueueCircular(4);

        // 程序是否继续运行的标志
        boolean flag = true;

        while (flag == true) {
            System.out.println("请根据以下选项进行输入：");

            System.out.println("s(show): 显示队列");
            System.out.println("e(enqueue): 入队");
            System.out.println("d(dequeue): 出队");
            System.out.println("p(peek): 查看队列头数据");
            System.out.println("q(quit): 退出程序");

            Scanner sc = new Scanner(System.in);
            char key = sc.next().charAt(0);

            switch (key) {
                case 's':
                    try {
                        arrayQueue.showQueue();
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 'e':
                    if (arrayQueue.isFull()) {
                        System.out.println("队列元素已满，无法入队！");
                        break;
                    }
                    System.out.print("请输入要入队的元素: ");
                    int elementEnqueue = sc.nextInt();
                    arrayQueue.enqueue(elementEnqueue);
                    System.out.println("入队成功！");

                    break;
                case 'd':
                    try {
                        int elementDequeue = arrayQueue.dequeue();
                        System.out.println("出队的元素为：" + elementDequeue);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 'p':
                    try {
                        int headElement = arrayQueue.peekQueue();
                        System.out.println("队列头元素为：" + headElement);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 'q':
                    sc.close();
                    flag = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("队列操作完毕！");
    }
}
