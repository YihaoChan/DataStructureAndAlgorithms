package algorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * 零钱兑换
 */
public class CoinChange {
    /**
     * 朴素递归
     */
    public static int coinChangeRecursion(int[] coins, int amount) {
        // base case
        if (amount == 0) {
            return 0;
        } else if (amount < 0) {
            return -1;
        }

        int res = Integer.MAX_VALUE;

        for (int coin : coins) {
            int subProblem = coinChangeRecursion(coins, amount - coin);

            if (subProblem != -1) {
                res = Math.min(res, 1 + subProblem); // 硬币个数+1
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 带备忘录的递归
     */
    public static int coinChangeMemo(int[] coins, int amount) {
        int[] memo = new int[amount + 1];

        Arrays.fill(memo, 0);

        return memoization(coins, memo, amount);
    }

    private static int memoization(int[]coins, int[] memo, int amount) {
        // base case
        if (amount == 0) {
            return 0;
        } else if (amount < 0) {
            return -1;
        }

        if (memo[amount] != 0) {
            return memo[amount];
        }

        int res = Integer.MAX_VALUE;

        for (int coin : coins) {
            int subProblem = memoization(coins, memo, amount - coin);

            if (subProblem != -1) {
                memo[amount - coin] = subProblem;
                res = Math.min(res, 1 + subProblem);
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * dp数组
     */
    public static int coinChangeDP(int[] coins, int amount) {
        // base case
        if (amount == 0) {
            return 0;
        } else if (amount < 0) {
            return -1;
        }

        int[] dp = new int[amount + 1];

        dp[0] = 0;

        Arrays.fill(dp, 1, amount + 1, Integer.MAX_VALUE);

        int coinNum = coins.length;

        // 自底向上建立
        for (int i = 1; i <= amount; i++) {
            for (int j = 1; j <= coinNum; j++) {
                if (i - coins[j - 1] >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j - 1]]);
                }
            }
        }

        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {2, 1, 5};
        int amount = 1400;

        // System.out.println(coinChangeRecursion(coins, amount));
        // System.out.println(coinChangeMemo(coins, amount));
        System.out.println(coinChangeDP(coins, amount));
    }
}
