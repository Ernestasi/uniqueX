package org.example.ernestas.sorters;

import java.util.Comparator;
import java.util.List;

public class MergeSort {
    private MergeSort() {

    }
    public static <T> void mergeSort(List<T> list, Comparator<? super T> comp) {
        mergeSort(list, comp, 0, list.size() - 1);
    }

    private static <T> void mergeSort(List<T> list, Comparator<? super T> comp, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(list, comp, start, mid);
            mergeSort(list, comp, mid + 1, end);
            merge(list, comp, start, mid, end);
        }
    }

    private static <T> void merge(List<T> list, Comparator<? super T> comp, int start, int mid, int end) {
        int leftSize = mid - start + 1;
        int rightSize = end - mid;
        T[] left = (T[]) new Object[leftSize];
        T[] right = (T[]) new Object[rightSize];
        for (int i = 0; i < leftSize; i++) {
            left[i] = list.get(start + i);
        }
        for (int j = 0; j < rightSize; j++) {
            right[j] = list.get(mid + 1 + j);
        }
        int i = 0, j = 0, k = start;
        while (i < leftSize && j < rightSize) {
            if (comp.compare(left[i], right[j]) <= 0) {
                list.set(k++, left[i++]);
            } else {
                list.set(k++, right[j++]);
            }
        }
        while (i < leftSize) {
            list.set(k++, left[i++]);
        }
        while (j < rightSize) {
            list.set(k++, right[j++]);
        }
    }

}
