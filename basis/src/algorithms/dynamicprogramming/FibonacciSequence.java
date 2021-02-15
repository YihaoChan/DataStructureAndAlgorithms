package algorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * 斐波那契数列
 */
public class FibonacciSequence {
    /**
     * 朴素递归
     */
    public static int fibRecursion(int n) {
        if (n < 1) {
            return 0;
        }

        // base case
        if (n == 1 || n == 2) {
            return 1;
        }

        return fibRecursion(n - 1) + fibRecursion(n - 2);
    }

    /**
     * 带备忘录的自顶向下递归
     */
    public static int fibMemo(int n) {
        if (n < 1) {
            return 0;
        }

        int[] memo = new int[n + 1]; // 第一个位置空出，因为fib(0)单独返回

        // 初始化
        Arrays.fill(memo, 0);

        return memoization(memo, n);
    }

    private static int memoization(int[] memo, int n) {
        // base case
        if (n == 1 || n == 2) {
            return 1;
        }

        if (memo[n] == 0) {
            return memoization(memo, n - 1) + memoization(memo, n - 2);
        } else {
            return memo[n]; // 已经记录过，就直接取值
        }
    }

    /**
     * 自底向上dp数组迭代
     */
    public static int fibDPArray(int n) {
        if (n < 1) {
            return 0;
        }

        // base case
        if (n == 1 || n == 2) {
            return 1;
        }

        int[] dp = new int[n + 1];

        // 初始化
        dp[0] = 0;

        dp[1] = dp[2] = 1;

        for (int i = 3; i < n + 1; i++) {
            dp[i] = 0;
        }

        // 自底向上建立
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * 自底向上dp数组迭代 - 优化，不使用数组
     */
    public static int fibDPNoArray(int n) {
        if (n < 1) {
            return 0;
        }

        // base case
        if (n == 1 || n == 2) {
            return 1;
        }

        int prev = 1;
        int curr = 1;
        int sum = 0;

        for (int i = 3; i <= n; i++) {
            sum = prev + curr;
            prev = curr;
            curr = sum;
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(fibRecursion(10000));
        System.out.println(fibMemo(10000));
        System.out.println(fibDPArray(10000));
        System.out.println(fibDPNoArray(10000));
    }
}
