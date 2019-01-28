package algorithm.Intervals;

import java.util.List;

public class Coverage {
    public boolean isCovered(List<Interval> intervals, int n) {
        int l = 0, r = intervals.size() - 1;
        while (l + 1< r) {
           int m = l + (r - l) / 2;
           Interval interval = intervals.get(m);
           if (interval.start <= n) {
               l = m;
           } else if (interval.start > n) {
               r = m - 1;
           }
        }
        if (intervals.get(r).start <= n) return intervals.get(r).end > n;
        if (intervals.get(l).start <= n) return intervals.get(l).end > n;
        return false;
    }
}
