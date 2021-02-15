package algorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * 钢条切割
 */
public class SteelCutting {
    public static int steelCutting(int[] price, int len) {
        if (len <= 0) {
            return 0;
        }

        // 初始化
        int[] dp = new int[len + 1];

        dp[0] = 0;

        Arrays.fill(dp, 1, dp.length, Integer.MIN_VALUE);

        // 自底向上建立
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] + price[j - 1]);
            }
        }

        return dp[len];
    }

    public static void main(String[] args) {
        // 《算法导论》 P205
        int[] steel = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int demand = 4;

        System.out.println(steelCutting(steel, demand));
    }
}
