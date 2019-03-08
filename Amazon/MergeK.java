package Amazon;


import java.util.*;

public class MergeK {
    public static List<List<Integer>> traversal(TreeNode root) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        Queue<Integer> pos = new LinkedList<>();
        Queue<TreeNode> node = new LinkedList<>();
        TreeNode cur = root;

        pos.offer(0);
        node.offer(cur);

        int min = 0, max = 0;

        while (!node.isEmpty()) {
            int size = node.size();
            for (int i = 0; i < size; i++) {
                cur = node.poll();
                int curPos = pos.poll();

                if (map.containsKey(curPos)) {
                    map.get(curPos).add(cur.val);
                } else {
                    map.put(curPos, new ArrayList<>(Arrays.asList(cur.val)));
                }

                if (cur.left != null) {
                    pos.offer(curPos - 1);
                    node.offer(cur.left);

                    min = Math.min(min, curPos - 1);
                }

                if (cur.right != null) {
                    pos.offer(curPos + 1);
                    node.offer(cur.right);

                    max = Math.max(max, curPos + 1);
                }
            }
        }

        List<List<Integer>> res = new ArrayList<>();

        for (int i = min; i <= max; i++) {
            res.add(map.get(i));
        }

        return res;

    }

    public static void main(String[] args) {
        LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();
    }
}
