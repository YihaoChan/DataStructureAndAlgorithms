# 第239题 滑动窗口最大值

## 1 题目

给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。

示例 1：

```
输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值

---------------               -----

[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

示例 2：

```
输入：nums = [1], k = 1
输出：[1]
```

示例 3：

```
输入：nums = [1,-1], k = 1
输出：[1,-1]
```

示例 4：

```
输入：nums = [9,11], k = 2
输出：[11]
```

示例 5：

```
输入：nums = [4,-2], k = 2
输出：[4]
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

   

