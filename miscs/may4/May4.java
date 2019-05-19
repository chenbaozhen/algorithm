package algorithm.miscs.may4;

import algorithm.myUtility.Display;

public class May4 {
    public int maxAfterFlip(String str) {
        //input is GR string,
        // find the max of G - R after one flip;
        int N = str.length();
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = str.charAt(i) == 'G' ? 1 : -1;
        }
        int[] prefixSum = new int[N];
        for (int i = 0; i < N; i++) {
            prefixSum[i] = array[i] + (i > 0 ? prefixSum[i - 1] : 0);
        }
        int curSum = prefixSum[N - 1];
        //for any possible subarray, find the min value among these subarrays;
        int min = 0; // assume i can do 0 flip. need to confirm with interviewer
        int maxValue = 0; // a variable to keep record the maximum value seen from 0 till now
        for (int n : prefixSum) {
            min = Math.min(min, n - maxValue); // update the minValue
            maxValue = Math.max(maxValue, n); // update previous maxValue;
        }
        return curSum - 2 * min; // flip the min;
    }
    public static void main(String[] args) {
        String str = "RGRGGG";
        May4 solultion = new May4();
        System.out.println(solultion.maxAfterFlip(str));
    }
}

/*

1 2 -3 5 -4 5 7 -1
1 3 0  5 -1 4 11 10

 */