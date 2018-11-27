package com.yeepay.g3.core.ymf.utils.web;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * 批量EXCEL用
 * Created by chen.liu on 15/11/27.
 */
public class BatchExcelBean {

    /**
     * 行号
     */
    private int index;

    /**
     * 属性个数
     * @return
     */
    @JsonIgnore
    public int getPropertyNumber() {
        return getClass().getDeclaredFields().length;
    }

    /**
     *
     * @return
     */
    @JsonIgnore
    public List<Field> getOrderedFieldList() {
        return Arrays.asList(getOrderedFields());
    }

    /**
     * 按顺序获得Field
     * @return
     */
    @JsonIgnore
    public Field[] getOrderedFields() {
        Field[] fields = getClass().getDeclaredFields();
        quicksort(fields, 0, fields.length - 1);
        return fields;
    }

    /**
     * 快速排序
     * @param fields 属性array
     * @param low 0
     * @param high max
     */
    private void quicksort(Field[] fields, int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = getValue(fields[low + (high-low)/2]);
        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller then the pivot
            // element then get the next element from the left list
            while (getValue(fields[i]) < pivot) {
                i++;
            }
            // If the current value from the right list is larger then the pivot
            // element then get the next element from the right list
            while (getValue(fields[j]) > pivot) {
                j--;
            }
            // If we have found a values in the left list which is larger then
            // the pivot element and if we have found a value in the right list
            // which is smaller then the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange(fields, i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(fields, low, j);
        if (i < high)
            quicksort(fields, i, high);
    }

    private void exchange(Field[] fields, int i, int j) {
        Field temp = fields[i];
        fields[i] = fields[j];
        fields[j] = temp;
    }

    private int getValue(Field f) {
        return f.getAnnotation(ExcelField.class).value();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
