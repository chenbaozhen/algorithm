package algorithm.dp;

import myUtility.Display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumIV {

    public List<int[]> allWaysDFS1(int[] coins, int target) {
        List<int[]> result = new ArrayList<>();
        allWaysDFS1(coins, coins.length - 1, target, new int[coins.length], result);
        return result;
    }
    private void allWaysDFS1(int[] coins, int index, int target, int[] choices, List<int[]> result) {

        if (target == 0 && index >= -1) {
            result.add(Arrays.copyOf(choices, choices.length));
            return;
        }
        if (index < 0 || target < 0) return;
        for (int i = 0; i * coins[index] <= target; i++) {
            choices[index] = i;
            allWaysDFS1(coins, index - 1, target - i * coins[index], choices, result );

        }
        choices[index] = 0;
    }
    public List<int[]> allWaysDFS2(int[] coins, int target) {
       List<int[]> result = new ArrayList<>();
       allWaysDFS2(coins, coins.length - 1, target, new int[coins.length], result);
       return result;
    }
    private void allWaysDFS2(int[] coins, int index, int target, int[] choices, List<int[]> result) {
        if (target == 0) {
            result.add(Arrays.copyOf(choices, choices.length));
            return;
        }
        if (index < 0 || target < 0) return;
        for (int i = index; i >= 0; i--) {
            choices[i]++;
            allWaysDFS2(coins, i, target - coins[i], choices, result);
            choices[i]--;
        }
    }
    public List<int[]> allWaysDFS3(int[] coins, int target) {
        List<int[]> result = new ArrayList<>();
        allWaysDFS3(coins, coins.length - 1, target, new int[coins.length], result);
        return result;
    }
    private void allWaysDFS3(int[] coins, int index, int target, int[] choices, List<int[]> result) {

        if (target == 0 && index >= -1) {
            result.add(Arrays.copyOf(choices, choices.length));
            return;
        }
        if (index < 0 || target < 0) return;
        allWaysDFS3(coins, index - 1, target, choices, result);
        choices[index]++;
        allWaysDFS3(coins, index, target - coins[index], choices, result);
        choices[index]--;
    }


    public static void main(String[] args) {
        CombinationSumIV solution = new CombinationSumIV();
        int[] coins = new int[] {1, 3, 5, 8};
        int target = 8;
        List<int[]> result = solution.allWaysDFS3(coins, target);
        Display.myPrintListOfArray(result);

    }
}








