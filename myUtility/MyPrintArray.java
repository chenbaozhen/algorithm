package algorithm.myUtility;

import java.util.Arrays;

public class MyPrintArray {
    public static void myPrintArray(int[][] arr) {
        for (int[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
    }
    public static void myPrintArray(String[][] arr) {
        for (String[] a : arr) {
            System.out.println(Arrays.toString(a));
        }
    }
}
