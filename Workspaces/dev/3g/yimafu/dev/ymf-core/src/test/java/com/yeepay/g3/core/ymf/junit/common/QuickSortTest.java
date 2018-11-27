package com.yeepay.g3.core.ymf.junit.common;

import com.yeepay.g3.core.ymf.vo.material.BatchTermRequest;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 17/3/13.
 */
public class QuickSortTest {

    public static void main(String[] args) {
        int[] fields = new int[] { 2, 1, 3, 5, 6, 0, 11};
        int length = fields.length;
        quicksort(fields, 0, length - 1);
        System.out.println(Arrays.toString(fields));


        BatchTermRequest request = new BatchTermRequest();
        for (Field field : request.getOrderedFields()) {
            System.out.println(field.getName());
        }
    }

    private static void quicksort(int[] numbers, int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = numbers[low + (high-low)/2];
        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller then the pivot
            // element then get the next element from the left list
            while (numbers[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger then the pivot
            // element then get the next element from the right list
            while (numbers[j] > pivot) {
                j--;
            }
            // If we have found a values in the left list which is larger then
            // the pivot element and if we have found a value in the right list
            // which is smaller then the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange(numbers, i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(numbers, low, j);
        if (i < high)
            quicksort(numbers, i, high);
    }

    private static void exchange(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
