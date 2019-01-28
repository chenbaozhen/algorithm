package algorithm.bfsDfs.messenger;

import java.util.ArrayList;
import java.util.List;

public class EmployeeNode {
        String Name;
        int cost;
        List<EmployeeNode> directReports;
    public EmployeeNode(String Name, int cost) {
        this.Name = Name;
        this.cost = cost;
        directReports = new ArrayList<>();
    }
    public String toString() {
        return "Name = " + Name + ", cost = " + cost;
    }
}
