package algorithm.miscs.june82019;

public class Solution {
    public int firstElementEqualsToIndex(int[] array) {
        if (array == null || array.length == 0) return -1;
        int l = 0, r = array.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (array[m] >= m) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        if (array[l] == l) return l;
        return -1;
    }
}
