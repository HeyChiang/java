package chiang.algorithm.more.count;

/**
 * 计数排序。从第一个字母开始排序，然后根据第一个字母分组继续排序。这个算法不适合数字，因为数字的第一个肯定是最大的，不像字母。
 */
public class MSDSort {

    private MSDSort(){}

    public static void sort(String[] arr){

        int N = arr.length;
        String[] temp = new String[N];
        sort(arr, 0, N - 1, 0, temp);
    }

    /**
     * 排序字符串
     *
     * @param arr 需要排序的数据
     * @param left 数组操作的左界限
     * @param right 数组操作的右界限
     * @param r 当前操作字符串长度中的第r个进行比较
     * @param temp 临时存放数据
     */
    private static void sort(String[] arr, int left, int right, int r, String[] temp){

        if(left >= right) return;

        int R = 256;
        int[] cnt = new int[R + 1];
        int[] index = new int[R + 2];

        // O(n) 如果字符的宽度r大于当前字符的长度，都放入0里面
        for(int i = left; i <= right; i ++)
            cnt[r >= arr[i].length() ? 0 : (arr[i].charAt(r) + 1)] ++;

        // O(R) 每个字符的索引位置
        for(int i = 0; i < R + 1; i ++)
            index[i + 1] = index[i] + cnt[i];

        // O(n) 排序
        for(int i = left; i <= right; i ++){
            temp[index[r >= arr[i].length() ? 0 : (arr[i].charAt(r) + 1)] + left] = arr[i];
            index[r >= arr[i].length() ? 0 : (arr[i].charAt(r) + 1)] ++;
        }

        // O(n) 将临时空间里的数组回写
        for(int i = left; i <= right; i ++)
            arr[i] = temp[i];

        // 根据第一个字母的分组，递归排序下去
        for(int i = 0; i < R; i ++)
            sort(arr, left + index[i], left + index[i + 1] - 1, r + 1, temp);
    }

    public static void main(String[] args) {

        String[] arr = {"BCA", "CBAA", "AC", "BADFE", "ABC", "CBA"};
        MSDSort.sort(arr);
        for(String s: arr)
            System.out.println(s);
    }
}