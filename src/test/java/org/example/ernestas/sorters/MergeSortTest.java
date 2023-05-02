package org.example.ernestas.sorters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class MergeSortTest {
    @Test
    void testMergeSort() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(2);
        list.add(8);
        list.add(1);

        MergeSort.mergeSort(list, Comparator.naturalOrder());

        List<Integer> expected = List.of(1, 2, 5, 8);
        Assertions.assertEquals(expected, list);
    }
}
