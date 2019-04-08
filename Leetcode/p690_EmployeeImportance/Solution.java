package Leetcode.p690_EmployeeImportance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    }

    Map<Integer, Employee> map;
    public int getImportance(List<Employee> employees, int id) {
        map = new HashMap<>();
        for(Employee employee: employees) {
            map.put(employee.id, employee);
        }

        return dfs(id);
    }

    private int dfs(int id) {
        Employee cur = map.get(id);
        int ans = cur.importance;
        for(int subId: cur.subordinates) {
            ans += dfs(subId);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution s = new Solution();

    }
}