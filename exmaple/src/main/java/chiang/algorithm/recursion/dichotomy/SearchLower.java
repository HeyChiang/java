package chiang.algorithm.recursion.dichotomy;

/**
 * 二分查找出比target更小的最大值
 */
public class SearchLower {
    public static void main(String[] args) {
        Integer[] integers = new Integer[]{1, 3, 4, 4, 5, 5, 6, 8};
        System.out.println("索引位置：" + search(integers, 5));
    }

    /**
     * 查找小于target的最索引，保留左边的数值，不断扣减右边的数组，直到索引和左边的一样。
     *
     * 如果没有和左边的一样就返回-1
     */
    public static <E extends Comparable<E>> int search(E[] e, E target) {
        // l 如果没有找到数值就返回 -1 , r如果有就返回最大的值
        int l = -1, r = e.length - 1;
        while (l < r) {
            // 解决死循环
            int mid = l + (r - l + 1) / 2;

            if (e[mid].compareTo(target) < 0) {
                // mid数值要保留，因为他可能就是小于target的最大值
                l = mid;
            } else {
                // 因为要小于target，中间位置大于target所以要省略
                r = mid - 1;
            }
            System.out.println("mid:" + mid + " l:" + l + " r:" + r);
        }

        return l;
    }
}
