package algorithms.dynamicprogramming;


/**
 * 0 - 1背包问题
 */
public class ZeroOneBag {
    public static int bagMaxValue(int bagNum, int capacity, int[] weights, int[] values) {
        if (bagNum == 0 || capacity == 0) {
            return 0;
        }

        int[][] dp = new int[bagNum + 1][capacity + 1];

        for (int i = 0; i <= bagNum; i++) {
            for (int j = 0; j <= capacity; j++) {
                dp[i][j] = 0;
            }
        }

        for (int n = 1; n <= bagNum; n++) {
            for (int w = 1; w <= capacity; w++) {
                if (weights[n - 1] > w) {
                    dp[n][w] = dp[n - 1][w];
                } else {
                    dp[n][w] = Math.max(
                            dp[n - 1][w],
                            dp[n - 1][w - weights[n - 1]] + values[n - 1]
                    );
                }
            }
        }

        return dp[bagNum][capacity];
    }

    public static void main(String[] args) {
        int bagNum = 3;
        int capacity = 4;
        int[] weights = {2, 1, 3};
        int[] values = {4, 2, 3};

        System.out.println(bagMaxValue(bagNum, capacity, weights, values));
    }
}
