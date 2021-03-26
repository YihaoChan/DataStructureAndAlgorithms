# 剑指 Offer 59 - I. 滑动窗口的最大值

## 1 题目

给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。

示例:

```
输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 

  滑动窗口的位置                最大值

---------------               -----

[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

## 2 解法

用双向链表实现单调递增队列。

```
class Solution {
    static class MonotonicQueue {
        private LinkedList<Integer> queue = new LinkedList<>();

        public void push(int item) {
            while (queue.size() != 0 && queue.peekLast() < item) {
                queue.removeLast();
            }

            queue.addLast(item);
        }

        public int max() {
            return queue.peekFirst();
        }

        public void pop(int item) {
            if (queue.peekFirst() == item) {
                queue.removeFirst();
            }
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int len = nums.length;

        MonotonicQueue deque = new MonotonicQueue();

        int[] res = new int[len - k + 1];

        for (int i = 0; i < len; i++) {
            int item = nums[i];

            if (i < k - 1) {
                deque.push(item);
            } else {
                deque.push(item);

                res[i - k + 1] = deque.max();

                deque.pop(nums[i - k + 1]);
            }
        }

        return res;
    }
}
```

复杂度分析：

1. 时间复杂度：每一个元素恰好入队一次，并且最多出队一次，因此时间复杂度为**O(n)**，n为数组的长度。

2. 空间复杂度：由于维护队列中最多不会有超过k个元素，因此队列使用的空间为 **O(k)**。


