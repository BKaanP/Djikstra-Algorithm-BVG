import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class sortingTest {

    @Test
    public void testSortingMethod() {
        int[] unsorted = {5, 2, 7, 1, 8};
        int[] sorted = {1, 2, 5, 7, 8};
        int[] empty = {};

        assertArrayEquals(sorted, sorting.quickSort(unsorted));
        assertArrayEquals(sorted, sorting.quickSort(sorted));
        assertArrayEquals(empty, sorting.quickSort(empty));
        assertArrayEquals(sorted, sorting.mergeSort(unsorted));
        assertArrayEquals(sorted, sorting.mergeSort(sorted));
        assertArrayEquals(empty, sorting.mergeSort(empty));
    }





}
