import java.util.Arrays;

public class sorting {

    public static void main(String[] args) {
        int[] unsorted = {5, 2, 7, 1, 8};
        mergeSort(unsorted);
        System.out.println("MergeSort ");
        quickSort(unsorted);
        System.out.println("QuickSort ");

        insertionSort(unsorted);
        System.out.println("InsertionSort");
        selectionSort(unsorted);
        System.out.println("SelecitonSort");

    }
    // Merge sort implementation
    public static int[] mergeSort(int[] array) {
        int counter = 0;  // added counter

        if (array.length > 1) {
            // Split the array in half
            int[] left = Arrays.copyOfRange(array, 0, array.length / 2);
            int[] right = Arrays.copyOfRange(array, array.length / 2, array.length);

            // Recursively sort the two halves
            mergeSort(left);
            mergeSort(right);

            // Merge the sorted halves back together
            merge(array, left, right, counter);  // pass counter as argument
        }

        return array;
    }

    // Helper method for merge sort: merges two sorted arrays into a single sorted
// array
    public static void merge(int[] output, int[] left, int[] right, int counter) {  // added counter as argument
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            counter++;  // increment counter
            if (left[i] < right[j]) {
                output[k] = left[i];
                i++;
            } else {
                output[k] = right[j];
                j++;
            }
            k++;
        }
        // Handle remaining elements, if any
        while (i < left.length) {
            counter++;  // increment counter
            output[k] = left[i];
            i++;
            k++;
        }
        while (j < right.length) {
            counter++;  // increment counter
            output[k] = right[j];
            j++;
            k++;
        }
        System.out.println(counter);
    }

    public static int[] quickSort(int[] array) {
        int counter = 0;  // added counter
        quickSort(array, 0, array.length - 1, counter);  // pass counter as argument

        return array;
    }

    private static void quickSort(int[] array, int low, int high, int counter) {  // added counter as argument
        if (low < high) {
            counter++;  // increment counter
            int pivotIndex = partition(array, low, high, counter);  // pass counter as argument
            quickSort(array, low, pivotIndex - 1, counter);  // pass counter as argument
            quickSort(array, pivotIndex + 1, high, counter);  // pass counter as argument
            System.out.println(counter);
        }
    }

    private static int partition(int[] array, int low, int high, int counter) {  // added counter as argument
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            counter++;  // increment counter
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }


    public static void selectionSort(int[] array) {
        int counter =0;
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            counter++;
            for (int j = i + 1; j < array.length; j++) {
                counter++;
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        System.out.println(counter);
    }

    public static void insertionSort(int[] array) {
        int counter =0;
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            counter++;
            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j--;
                counter++;
            }

            array[j + 1] = current;
        }
        System.out.println(counter);
    }

}
