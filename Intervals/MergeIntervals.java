package algorithm.Intervals;

import java.util.*;

public class MergeIntervals {
    public static void main(String[] args) {
        MergeIntervals solution = new MergeIntervals();
        int[] intervalRaw = new int[]{-1,1, -1,2, 3,4, 8,11, 7,9};
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < intervalRaw.length; i+= 2){
            intervals.add(new Interval(intervalRaw[i], intervalRaw[i + 1]));
        }
        List<Interval> merged1 = solution.mergeInterval(intervals);
        for (Interval it : merged1) {
            System.out.println(it.toString());
        }
        System.out.println("merged2");
        List<Interval> merged2 = solution.mergeIntervalMergeSort(intervals);
        for (Interval it : merged2) {
            System.out.println(it.toString());
        }

        System.out.println(solution.substractInterval(new Interval(0, 5), new Interval(1, 4)));
    }

    public List<Interval> mergeInterval(List<Interval> intervalList) {
        List<Interval> ans = new ArrayList<>();
        Collections.sort(intervalList, new Comparator<Interval>() {
                    @Override
                    public int compare(Interval o1, Interval o2) {
                        if (o1.start != o2.start) return o1.start < o2.start ? -1 : 1;
                        return 0;
                    }
                }
        );
        Interval cur = null;
        for (Interval itv : intervalList) {
            if (cur == null) {
                cur = new Interval(itv.start, itv.end);
            } else {
                if (itv.start > cur.end) {
                    ans.add(cur);
                    cur = new Interval(itv.start, itv.end);
                } else {
                    cur.end = Math.max(itv.end, cur.end);
                }
            }
        }
        if (cur != null) ans.add(cur);
        return ans;
    }
    public List<Interval> mergeIntervalMergeSort(List<Interval> intervalList) {
        if (intervalList.size() <= 1) return intervalList;
        List<Interval> left = mergeIntervalMergeSort(intervalList.subList(0, intervalList.size() / 2));
        List<Interval> right = mergeIntervalMergeSort(intervalList.subList(intervalList.size() / 2, intervalList.size()));
        return mergeTwoListOfSortedIntervals(left, right);
    }
    public int totalCoveredLength(List<Interval> intervalList) {
        Collections.sort(intervalList, new Comparator<Interval>() {
                    @Override
                    public int compare(Interval o1, Interval o2) {
                        if (o1.start != o2.start) return o1.start < o2.start ? -1 : 1;
                        return 0;
                    }
                }
        );
        int totalLength = 0;
        if (intervalList.size() < 1) return 0;
        Interval cur = new Interval(intervalList.get(0));
        for (int i = 1; i < intervalList.size(); i++) {
            Interval itv = intervalList.get(i);
            if (itv.start > cur.end) {
                totalLength += cur.end - cur.start;
                cur = new Interval(itv);
            } else {
                cur.end = Math.max(cur.end, itv.end);
            }
        }
        return  totalLength + cur.end - cur.start;
    }
    public List<Interval> mergeTwoListOfSortedIntervals(List<Interval> intervals1, List<Interval> intervals2) {
        List<Interval> result = new ArrayList<>();
        Interval cur = null;
        int pt1 = 0, pt2 = 0;
        while (pt1 < intervals1.size() || pt2 < intervals2.size()) {
            int start1 = pt1 < intervals1.size() ? intervals1.get(pt1).start : Integer.MAX_VALUE;
            int start2 = pt2 < intervals2.size() ? intervals2.get(pt2).start : Integer.MAX_VALUE;
            if (cur == null ||(cur.end < start1 && cur.end < start2) ) {
                if (cur != null) result.add(cur);
                cur = new Interval(start1 <= start2 ?
                intervals1.get(pt1++) : intervals2.get(pt2++));
            } else {
                cur.end = Math.max(cur.end, start1 <= start2 ?
                intervals1.get(pt1++).end : intervals2.get(pt2++).end);
            }
        }
        result.add(new Interval(cur));
        return result;
    }
    public List<Interval> insertInterval(List<Interval> intervals, Interval insertedInterval) {
        List<Interval> ans = new ArrayList<>();
        boolean alreadyDone = false;
        for (Interval itv : intervals) {
            if (itv.end < insertedInterval.start) {
                ans.add(itv);
            } else if (itv.start > insertedInterval.end) {
                if (!alreadyDone) {
                    ans.add(insertedInterval);
                    alreadyDone = true;
                }
                ans.add(itv);
            } else {
                insertedInterval.start = Math.min(insertedInterval.start, itv.start);
                insertedInterval.end = Math.max(insertedInterval.end, itv.end);
            }
        }
        if (!alreadyDone) ans.add(insertedInterval);
        return ans;
    }
    public List<Interval> intersections(List<Interval> intervals1, List<Interval> intervals2) {
        List<Interval> ans = new ArrayList<>();
        int pt1 = 0, pt2 = 0;
        while (pt1 < intervals1.size() && pt2 < intervals2.size()) {
            if (intervals1.get(pt1).end > intervals2.get(pt2).end) {
                if (intervals2.get(pt2).end > intervals1.get(pt1).start) {
                    ans.add(new Interval(Math.max(intervals1.get(pt1).start, intervals2.get(pt2).start),
                            intervals2.get(pt2).end));
                }
                pt2++;
            } else {
                if (intervals1.get(pt1).end > intervals2.get(pt2).start) {
                    ans.add(new Interval(Math.max(intervals2.get(pt2).start, intervals1.get(pt1).start),
                            intervals1.get(pt1).end));
                }
                pt1++;
            }
        }
        return ans;
    }
    public List<Interval> substractInterval(Interval i1, Interval i2) {
        List<Interval> ans = new ArrayList<>();
        if (i1.start < i2.start && i1.end > i2.start) {
            ans.add(new Interval(i1.start, i2.start));
        }
        if (i1.end > i2.end && i1.start < i2.end) {
            ans.add(new Interval(i2.end, i1.end));
        }
        return ans;
    }
}
