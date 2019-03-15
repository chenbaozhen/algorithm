package algorithm.miscs;

public class Mar32019 {
    public int splitCandies(int[] bags, int k){
        int totalCandy = 0;
        int min = Integer.MAX_VALUE;
        for (int candy : bags) {
            totalCandy  += candy;
            min = Math.min(min, candy);
        }
        int left = min, right = totalCandy;
        while (left < right) {
            int mid = right - (right - left ) / 2;
            if (getSplitNumber(bags, mid) < k) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (getSplitNumber(bags, left) >= k) return left;
        return -1;
    }
    private int getSplitNumber(int[] bags, int number) {
        int cnt = 0;
        int curSum = 0;
        for (int n : bags) {
            curSum += n;
            if (curSum >= number) {
                cnt++;
                curSum = 0;
            }
        }
        return cnt;
    }
    public static void main(String[] args) {
        Mar32019 solution = new Mar32019();
        System.out.println(solution.splitCandies(new int[]{1, 2, 1, 1, 3}, 3));
    }
    public int findMissingElement(int[] array) {
        if (array.length % 2 == 0) return -1;
        if (array.length == 1) return array[0];
        int left = 0, right = array.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (mid % 2 == 0) {
                if (array[mid + 1] == array[mid]) {
                    left = mid ;
                } else {
                    right = mid;
                }
            } else {
                if (array[mid] == array[mid - 1]) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
        }
        if (array[left] == array[right]) return -1;
        if (left == 0 || array[left] != array[left - 1]) {
            return left;
        }
        return 0;
    }
}
