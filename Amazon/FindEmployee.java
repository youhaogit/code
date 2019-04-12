package Amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindEmployee {

//    input是array of (employee, manager) manager 也是employee的一种。例如[('a', 'b'), ('b, c')]
//    那答案就是 a->[], b->[a], c->[a,b], a 没有下属， b有一个下属a， c有下属b，a（因为是b下属所以也是c下属)

    public static List<List<String>> findEmployee(String[][] relations) {
        Map<String, String> map = new HashMap<>();

        // build graph
        for(String[] relation: relations) {
            String employee = relation[0];
            String boss = relation[1];

            map.put(boss, employee);
            map.putIfAbsent(employee, null);
        }

        // dfs for each node
        List<List<String>> result = new ArrayList<>();
        for(String boss: map.keySet()) {
            List<String> subset = new ArrayList<>();
            String employee = map.get(boss);
            while(employee != null) {
                subset.add(employee);
                employee = map.get(employee);
            }

            result.add(subset);
        }

        return result;
    }

    public static void main(String[] args) {
        String[][] relations = {{"a", "b"}, {"b", "c"}, {"b", "d"}};
        System.out.println(findEmployee(relations));
    }
}
