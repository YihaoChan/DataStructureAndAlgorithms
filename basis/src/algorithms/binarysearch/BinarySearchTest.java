package algorithms.binarysearch;

/**
 * @Description: 二分查找
 */
public class BinarySearchTest {
    public static <T extends Comparable<? super T>> int binarySearch(T[] arr, T item, int left, int right) {
        // 结束条件
        if (item.compareTo(arr[left]) < 0 || item.compareTo(arr[right]) > 0 || left > right) {
            return -1;
        }

        int center = (left + right) / 2;

        if (item.compareTo(arr[center]) < 0) {
            return binarySearch(arr, item, left, center - 1);
        } else if (item.compareTo(arr[center]) > 0) {
            return binarySearch(arr, item, center + 1, right);
        } else {
            return center;
        }
    }

    public static <T extends Comparable<? super T>> int binarySearch(T[] arr, T item) {
        int left = 0;
        int right = arr.length - 1;
        int center;

        while (left <= right) {
            center = (left + right) / 2;

            if (item.compareTo(arr[center]) < 0) {
                right = center - 1;
            } else if (item.compareTo(arr[center]) > 0) {
                left = center + 1;
            } else {
                return center;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {5, 16, 39, 45, 51, 98, 100, 202, 226, 321, 368, 444, 501};

        System.out.println(binarySearch(arr, 51, 0, arr.length - 1));
        System.out.println(binarySearch(arr, 51));
    }
}
