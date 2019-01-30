package algorithm.myUtility;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Display {
    public static void myPrint(List<List<Integer>> list) {
        Iterator it = list.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }


    public static void myPrint(int[][] a) {
        for (int[] b : a) {
            System.out.println(Arrays.toString(b));
        }
    }


    public static void myPrint(int a) {
        System.out.println(a);
    }
    public static void myPrint(double a) {
        System.out.println(a);
    }
    public static void myPrint(char c) {
        System.out.println(c);
    }
    public static void myPrint(int[] a) {
        System.out.println(Arrays.toString(a));
    }
    public static void myPrint(double[] a) {
        System.out.println(Arrays.toString(a));
    }
    public static void myPrint(String varName, int a) {
        System.out.println(varName + " = " + a);
    }
    public static void myPrintListOfArray(List<int[]> list) {
        Iterator it = list.iterator();
        while(it.hasNext()) {
            System.out.println(Arrays.toString((int[]) it.next()));
        }
    }
    public static void myPrint(String str) {
        System.out.println(str);
    }
    public static void myPrint(boolean bl) {
        System.out.println(bl);
    }
    public static void myPrintString(List<String> a) {
        if (a.size() == 0) System.out.println("[]");
        for (String b : a) {
            System.out.println(b);
        }
    }
}
