# 第901题 股票价格跨度

## 1 题目

编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。

今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。

例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。

示例：

```
输入：["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
输出：[null,1,1,1,2,1,4,6]
解释：
首先，初始化 S = StockSpanner()，然后：
S.next(100) 被调用并返回 1，
S.next(80) 被调用并返回 1，
S.next(60) 被调用并返回 1，
S.next(70) 被调用并返回 2，
S.next(60) 被调用并返回 1，
S.next(75) 被调用并返回 4，
S.next(85) 被调用并返回 6。

注意 (例如) S.next(75) 返回 4，因为截至今天的最后 4 个价格
(包括今天的价格 75) 小于或等于今天的价格。
```

## 2 解法

单调栈。从前往后扫描，维护一个单调递增的price栈，同时维护一个记录span的普通栈。

```
class StockSpanner {
    Stack<Integer> priceStack;
    Stack<Integer> spanStack;

    public StockSpanner() {
        priceStack = new Stack<>();
        spanStack = new Stack<>();    
    }
    
    public int next(int price) {
        int span = 1;

        // 单调递增栈
        while (!priceStack.isEmpty() && price >= priceStack.peek()) {
            priceStack.pop();
            span += spanStack.pop();
        }

        priceStack.push(price);
        spanStack.push(span);

        return span;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
```

复杂度分析：

1. 时间复杂度：**O(q)**，其中q为调用next的次数；
2. 空间复杂度：**O(q)**，其中q为调用next的次数。