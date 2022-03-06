package com.chiang.algorithm.merge;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {

    }

    private <E extends Comparable<E>> void sort(E[] array, int l, int r) {
        if (r >= l) {
            return;
        }
        // l可能是1w，r是2w
        int mid = l + (r - l) / 2;
        sort(array, l, mid);
        sort(array, mid, r);
        merge(array, l, mid, r);
    }

    private <E extends Comparable<E>> void merge(E[] array, int l, int mid, int r) {
        E[] temp = Arrays.copyOfRange(array, l, r);

        int i = l, j = mid;

        for (int k = l; k < mid; k++) {
            if (i > mid) {
                array[k] = temp[i - k];
                i++;
            } else if (j > r) {
                array[k] = temp[j - k];
                j++;
            }
        }
    }
}
