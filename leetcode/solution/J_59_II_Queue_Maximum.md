# 剑指 Offer 59 - II. 队列的最大值

## 1 题目

请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。

若队列为空，pop_front 和 max_value 需要返回 -1

示例 1：

```
输入: 
["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
[[],[1],[2],[],[],[]]
输出: [null,null,null,2,1,2]
```

示例 2：

```
输入: 
["MaxQueue","pop_front","max_value"]
[[],[],[]]
输出: [null,-1,-1]
```

## 2 解法

实现一个单调递增队列，同时用一个普通队列记录元素入队顺序。需要注意的是，当pop_front时，如果普通队列弹出的值恰好等于单调队列的第一个值，说明单调队列在push这个值的时候没有弹出其余的值，此时，要把单调队列的第一个值也一并弹出。

典型用例：

① push 94 -> push 16 -> pop_front -> pop_front；

② push 1 -> push 2.

```
class MaxQueue {
    private LinkedList<Integer> monotonicQueue;
    
    private LinkedList<Integer> queue;

    public MaxQueue() {
        monotonicQueue = new LinkedList<>();
        queue = new LinkedList<>();
    }
    
    public int max_value() {
        if (monotonicQueue.size() > 0) {
            return monotonicQueue.peekFirst();
        }
        
        return -1;
    }
    
    public void push_back(int value) {
        while (monotonicQueue.size() > 0 && 
        	   value >= monotonicQueue.peekLast()) {
            monotonicQueue.removeLast();
        }

        monotonicQueue.addLast(value);
        
        queue.addLast(value);
    }
    
    public int pop_front() {
        if (queue.size() > 0) {
            int popItem = queue.removeFirst();

            if (popItem == monotonicQueue.peekFirst()) {
                monotonicQueue.removeFirst();
            }

            return popItem;
        } 
        
        return -1;
    }
}


/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
```

复杂度分析：

1. 时间复杂度：

   删除与求最大值时，只需要remove或peek队头元素，显然只需要O(1)的时间。

   而插入操作虽然看起来有循环，做一个插入操作时最多可能会有n次出队操作。但要注意，由于每个数字只会出队一次，因此对于所有的n个数字的插入过程，对应的所有出队操作也不会大于n次。因此将出队的时间均摊到每个插入操作上，时间复杂度为 O(1)。

   故每个操作的均摊时间复杂度均为**O(1)**；

2. 空间复杂度：需要一个O(n)空间的队列用于构造单调队列，还需要另一个O(n)空间的队列按先后顺序存储插入的元素，故总空间复杂度为**O(n)**。

