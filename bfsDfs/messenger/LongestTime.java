package algorithm.bfsDfs.messenger;

import java.util.List;

public class LongestTime {
    public int longestTime(EmployeeNode boss){
        if (boss == null || boss.directReports== null || boss.directReports.size() == 0) {
            return 0;
        }
        int max = 0;
        for (EmployeeNode directReport : boss.directReports) {
            max = Math.max(max, longestTime(directReport));
        }
        return max + boss.cost;
    }


}

