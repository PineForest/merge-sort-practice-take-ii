package algorithms.practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MergeSort {
    // sort({3,4,7,6,8,2,3}) -> {2,3,3,4,6,7,8}
    // sort = split({3,4,7,6,8,2,3}) -> [{3,4,7},{6,8,2,3}];
    //        sort({3,4,7}) -> {3,4,7};
    //        sort({6,8,2,3}) -> {2,3,6,8};
    //        mergeSort({3,4,7},{6,8,2,3}) -> {2,3,3,4,6,7,8}
    // sort: if array is empty or size of 1, the array is returned unaltered
    private static void sort(int[] array, int start, int end) {
        int size = end - start;
        if (size < 2) {
            return;
        }
        int middle = size / 2 + start;
        sort(array, start, middle);
        sort(array, middle, end);
        mergeSort(array, start, middle, end);
    }

    private static void mergeSort(int[] array, int start, int middle, int end) {
        if (end - start < 2) {
            return;
        }
        while (middle < end) {
            mergeSort(array[middle], array, start, middle);
            ++middle;
        }
    }

    private static void mergeSort(int value, int[] array, int start, int end) {
        int originalEnd = end;
        int middleIndex;
        while (end - start > 1) {
            middleIndex = (end - start) / 2 + start;
            if (array[middleIndex] >= value) {
                end = middleIndex;
            } else {
                start = middleIndex;
            }
        }
        if (end - start == 1 && array[start] < value) {
            start = end;
        }
        for (; start < originalEnd; ++start) {
            int temp = array[start];
            array[start] = value;
            value = temp;
        }
        array[originalEnd] = value;
    }

    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String line;
        while ((line = buffer.readLine()) != null) {
            line = line.trim();
            String[] stringArray = line.split(",");
            int[] array = convert(stringArray);
            sort(array, 0, array.length);
            System.out.println(Arrays.toString(array));
        }
    }

    private static int[] convert(String[] stringArray) {
        int[] result = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; ++i) {
            result[i] = Integer.parseInt(stringArray[i]);
        }
        return result;
    }
}
