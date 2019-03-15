package algorithm.heap;

import algorithm.myUtility.Display;

public class Heap {

    public int[] heapify(int[] array) {

        for (int i = (array.length) / 2; i >= 0; i--) {
            percolateDown2(array, i, array.length);
        }
        return array;
    }
    private void percolateDown(int[] array, int i, int len) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        if (left >= len) return;
        //  || left right
        if (left == len - 1) {
            if (array[i] < array[left])
            swap(array, i, left);
        } else {
            if (array[left] >= array[right]) {
                if (array[left] > array[i]) {
                    swap(array, i, left);
                    percolateDown(array, left, len);
                }
            } else {
                if (array[right] > array[i]) {
                    swap(array, i, right);
                    percolateDown(array, right, len);
                }
            }
        }
    }

    private void percolateDown2(int[] array, int i, int len) {
        int left = i * 2 + 1, right = i * 2 + 2;
        int leftValue = left < len ? array[left] : Integer.MIN_VALUE;
        int rightValue = right < len ? array[right] : Integer.MIN_VALUE;
        if (leftValue >= rightValue) {
            if (array[i] < leftValue) {
                swap(array, i, left);
                percolateDown2(array, left, len);
            }
        } else {
            if (array[i] < rightValue) {
                swap(array, i, right);
                percolateDown2(array, right,len);
            }
        }
    }
    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        Heap heap = new Heap();
        int[] array = new int[]{1, 3, 36, 2, 19, 25, 100, 17, 7};
        heap.heapify(array);
        Display.myPrint(array);
    }
}
