package chiang.algorithm.recursion.quick;

import chiang.algorithm.SortHelper;

import java.util.Random;

/**
 * 如果有重复的数字，需要先去重，否则给到的
 */
class SelectKSolution {

    public static void main(String[] args) {
        int[] arrayInt = SortHelper.generateArrayInt(5);
        int keyPosition = new SelectKSolution().findKthLargest(arrayInt, 1);
        SortHelper.print("keyPosition："+keyPosition ,arrayInt);

    }

    public int findKthLargest(int[] nums, int k) {

        Random rnd = new Random();
        return selectK(nums, 0, nums.length - 1,  k, rnd);
    }

    private int selectK(int[] arr, int l, int r, int k, Random rnd){

        int p = partition(arr, l, r, rnd);

        if(k == p) return arr[p];

        if(k < p) return selectK(arr, l, p - 1, k, rnd);
        return selectK(arr, p + 1, r, k, rnd);
    }

    private int partition(int[] arr, int l, int r, Random rnd){

        // 生成 [l, r] 之间的随机索引
        int p = l + rnd.nextInt(r - l + 1);
        swap(arr, l, p);

        // arr[l+1...i-1] <= v; arr[j+1...r] >= v
        int i = l + 1, j = r;
        while(true){

            while(i <= j && arr[i] < arr[l])
                i ++;

            while(j >= i && arr[j] > arr[l])
                j --;

            if(i >= j) break;

            swap(arr, i, j);

            i ++;
            j --;
        }

        swap(arr, l, j);
        return j;
    }

    private void swap(int[] arr, int i, int j){

        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
