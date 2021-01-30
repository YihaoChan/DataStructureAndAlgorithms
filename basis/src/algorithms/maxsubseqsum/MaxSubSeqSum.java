package algorithms.maxsubseqsum;

/**
 * @Description: 最大子序列和
 */
public class MaxSubSeqSum {
    /**
     * @Description: 分治法 - 最大值 = 左子序列最大值、右子序列最大值、横跨边界线的最大值三者中的最大者
     */
    public static int maxSubSeqSum(int[] arr, int left, int right) {
        // 结束条件
        if (left == right) {
            if (arr[left] > 0) {
                return arr[left];
            } else {
                return 0;
            }
        }

        int center = (left + right) / 2;

        // 求左右子序列的最大值
        int maxLeftSum = maxSubSeqSum(arr, left, center);
        int maxRightSum = maxSubSeqSum(arr, center + 1, right);

        // 从中间往左扩张
        int centerLeftSum = 0;
        int maxCenterLeftSum = 0;

        for (int i = center; i >= left; i--) {
            centerLeftSum += arr[i];

            if (centerLeftSum > maxCenterLeftSum) {
                maxCenterLeftSum = centerLeftSum;
            }
        }

        // 从中间往右扩张
        int centerRightSum = 0;
        int maxCenterRightSum = 0;

        for (int j = center + 1; j <= right; j++) {
            centerRightSum += arr[j];

            if (centerRightSum > maxCenterRightSum) {
                maxCenterRightSum = centerRightSum;
            }
        }

        // 比较选出最大
        int tempMax = Math.max(maxLeftSum, maxRightSum);
        int max = Math.max(tempMax, maxCenterLeftSum + maxCenterRightSum);

        return max;
    }

    /**
     * @Description: 动态规划
     */
    public static int maxSubSeqSum(int[] arr) {
        int sum = 0;
        int maxSum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (sum > maxSum) {
                maxSum = sum;
            } else if (sum < 0) {
                // 如果子序列和变成负数，就清零，直到从正数才算开始
                sum = 0;
            }
        }

        return maxSum;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{4, -3, 5, -2, -1, 2, 6, -2};

        System.out.println(maxSubSeqSum(arr, 0, arr.length - 1));
        System.out.println(maxSubSeqSum(arr));
    }
}
