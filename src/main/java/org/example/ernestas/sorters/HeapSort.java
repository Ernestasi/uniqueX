package org.example.ernestas.sorters;

import java.util.Comparator;
import java.util.List;

public class HeapSort {
    private HeapSort() {

    }
    public static <T> void heapSort(List<T> list, Comparator<? super T> comparator) {
        int n = list.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(list, n, i, comparator);
        }

        for (int i = n - 1; i >= 0; i--) {
            T temp = list.get(0);
            list.set(0, list.get(i));
            list.set(i, temp);

            heapify(list, i, 0, comparator);
        }
    }

    private static <T> void heapify(List<T> list, int n, int i, Comparator<? super T> comp) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && comp.compare(list.get(left), list.get(largest)) > 0)
            largest = left;

        if (right < n && comp.compare(list.get(right), list.get(largest)) > 0)
            largest = right;

        if (largest != i) {
            T swap = list.get(i);
            list.set(i, list.get(largest));
            list.set(largest, swap);

            heapify(list, n, largest, comp);
        }
    }


}
