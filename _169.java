
import sun.reflect.generics.tree.Tree;

import java.util.*;


public class _169 {

   private static void sort(int[] A){
       quickSort1(A, 0, A.length - 1);
//      quickSort2(A, 0, A.length - 1);
   }

   private static void quickSort2(int[] A, int start, int end){
       int left = start;
       int right = end;
       int pivot = A[(left + right) / 2];

       while(left <= right){
           while(left <= right && A[left] < pivot)
               left++;
           while(left <= right && A[right] > pivot)
               right--;

           if(left <= right){
               swap(A, left, right);
               left++;
               right--;
           }

           quickSort2(A, start, right);
           quickSort2(A, left, end);
       }
   }

   private static void quickSort1(int[] A, int left, int right){
       while(left < right){
           int partition = random_partition(A, left, right);
           quickSort1(A, left, partition - 1);
           left = partition + 1;
       }
   }

   private static int random_partition(int[] A, int left, int right){
       Random rd = new Random();
       int i = left + rd.nextInt(right - left + 1);
       swap(A, i, right);
       return partition(A, left, right);
   }

   private static int partition(int[] A, int left, int right){
       int pivot = A[right];
       int i = left - 1;
       for(int j = left; j < right; j++){
           if(A[j] < pivot){
               i = i + 1;
               swap(A, i, j);
           }
       }
       swap(A, i + 1, right);
       return i + 1;
   }

   private static void swap(int[] A, int i, int j){
       int temp = A[j];
       A[j] = A[i];
       A[i] = temp;
   }


    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[] tmp = new int[n + m];

        int i = 0, j = 0, k = 0;
        while(i < n && j < m) {
            if(nums1[i] < nums2[j]) {
                tmp[k++] = nums1[i];
                i++;
            }else {
                tmp[k++] = nums2[j];
                j++;
            }
        }

        while(i < n) {
            tmp[k++] = nums1[i++];
        }
        while(j < m) {
            tmp[k++] = nums2[j++];
        }

        return (m + n) % 2 == 0 ? (tmp[k/2] + tmp[k/2 + 1]) / 2 : tmp[k/2 + 1];
    }

    public static String longestPalindrome(String s) {
        if (s == null)
            return null;
        if (s.length() < 1)
            return "";

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandFromCenter(s, i , i);
            int len2 = expandFromCenter(s, i, i+1);
            int len = Math.max(len1, len2);

            if(len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);

    }

    public static int expandFromCenter(String s, int left, int right) {
        if (s == null || s.length() == 0)
            return 0;

        int L = left, R = right;
        while(L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }

        return R - L + 1;
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1)
            return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(s.length(), numRows); i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean movingDown = false;
        for (char c: s.toCharArray()) {
            rows.get(curRow).append(c);
            if(curRow == 0 || curRow == numRows - 1) {
                movingDown = !movingDown;
            }
            curRow += movingDown? 1 : -1;
        }

        StringBuilder res = new StringBuilder();
        for(StringBuilder string: rows) {
            res.append(string);
        }

        return res.toString();

    }

    public static int myAtoi(String str) {
        if(str == null || str.length() == 0) {
            return 0;
        }

        str = str.trim();
        int len = str.length();
        int sum = 0;
        int sign = 1;
        int index = 0;
        if(str.length() == 0)
            return 0;
        if (str.charAt(index) == '+') {
            sign = 1;
            index++;

        }else if (str.charAt(index) == '-') {
            sign = -1;
            index++;
        }


        for(int i = index; i < len; i++) {
            if(!Character.isDigit(str.charAt(i))) {
                return (int) sum * sign;
            }
            sum = sum * 10 + str.charAt(i) - '0';
            if(sign == 1 && sum > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if(sign == -1 && sum < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
        }

        return (int)sum * sign;
    }


    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty())
                    return "";
            }
        return prefix;
    }

    private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public static List<String> letterCombinations(String digits) {
        if(digits.equals("")) {
            return new ArrayList<String>();
        }

        List<String> ret = new LinkedList<String>();
        combination("", digits, 0, ret);
        return ret;
    }

    private static void combination(String prefix, String digits, int offset, List<String> ret) {
        if (offset >= digits.length()) {
            ret.add(prefix);
            return;
        }
        String letters = KEYS[(digits.charAt(offset) - '0')];
        for (int i = 0; i < letters.length(); i++) {
            combination(prefix + letters.charAt(i), digits, offset + 1, ret);
        }
    }

    public static boolean isValid(String s) {
        if(s == null || s.length() % 2 == 1)
            return false;

        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if(ch == ')' || ch == ']' || ch == '}'){
                if(ch == ')') {
                    if(stack.peek() == '(')
                        stack.pop();
                    else
                        stack.push(ch);
                }
                if(ch == ']') {
                    if(stack.peek() == '[')
                        stack.pop();
                    else
                        stack.push(ch);
                }
                if(ch == '}') {
                    if(stack.peek() == '{')
                        stack.pop();
                    else
                        stack.push(ch);
                }

            }else{
                stack.push(ch);
            }
        }

        if(!stack.isEmpty())
            return false;
        return true;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;

        ListNode walker1 = l1;
        ListNode walker2 = l2;
        ListNode newNode = new ListNode(-1);
        ListNode dummy = newNode;
        while(walker1 != null && walker2 != null) {
            if(walker1.val < walker2.val){
                newNode.next = new ListNode(walker1.val);
                walker1 = walker1.next;
            }else{
                newNode.next = new ListNode(walker2.val);
                walker2 = walker2.next;
            }
            newNode = newNode.next;
        }

        while(walker1 != null)
            newNode.next = walker1;
        while(walker2 != null)
            newNode.next = walker2;

        return dummy.next;
    }


    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if(n <= 0) {
            return res;
        }

        String digits = "()";
        combinations(0, n, "", res, digits);

        return res;
    }

    private static void combinations(int index, int n, String prefix, List<String> res, String digits) {
        if(index >= n) {
            if(isValid(prefix)) {
                res.add(prefix);
            }
            return;
        }

        for(int i = 0; i < digits.length(); i++) {
            Character ch = digits.charAt(i);
            combinations(index + 1, n, prefix + ch, res, digits);
        }

    }

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if(m > n || head == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode preM = dummy;
        for(int i = 1; i < m; i++) {
            if(preM.next == null) {
                return null;
            }
            preM = preM.next;
        }

        ListNode nodeM = preM.next;
        ListNode nodeN = nodeM;
        ListNode postN = nodeN.next;

        for(int i = m; i < n; i++) {
            ListNode temp = postN.next;


            postN.next = nodeN;
            nodeN = postN;
            postN = temp;
        }

        preM.next = nodeN;
        nodeM.next = postN;

        return dummy.next;
    }

    public static int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0, right = nums.length - 1;
        Set<Integer> set = new HashSet<>();
        while(left < right) {
            if(!set.contains(nums[left])) {
                set.add(nums[left]);
                left++;
            }else{
                swap(nums, left, right);
                right--;
            }
        }

        return left + 1;
    }

    public static int strStr(String haystack, String needle) {
        if(needle == "") {
            return 0;
        }
        if(needle.length() > haystack.length()) {
            return -1;
        }

        for(int i = 0; i <= haystack.length() - needle.length(); i++) {
            Character firstChar = haystack.charAt(i);
            if(isEqual(haystack, needle, i)){
                return i;
            }
        }

        return -1;
    }

    private static boolean isEqual(String haystack, String needle, int i) {
        for(int j = 0; j < needle.length(); j++) {
            if(needle.charAt(j) != haystack.charAt(i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static int search(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return -1;

        int n = nums.length;
        int lo = 0, rotation = 0, hi = n - 1;
        while(lo < hi) {
            int mid = (lo + hi) / 2;
            if(nums[mid] > nums[hi]) {
                lo = mid + 1;
            }else {
                hi = mid;
            }
        }

        rotation = lo;
        lo = 0;
        hi = n - 1;
        while(lo <= hi) {
            int mid = (lo + hi) / 2;
            int realMid = (mid + rotation) % n;
            if(nums[realMid] < target) {
                lo = realMid + 1;
            }else if(nums[realMid] > target) {
                hi = realMid - 1;
            }else {
                return realMid;
            }
        }
        return -1;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length == 0)
            return null;

        Map<String, List<String>> map = new HashMap<>();
        for(String s: strs){
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            if(!map.containsKey(keyStr))
                map.put(keyStr, new ArrayList<String>());
            map.get(keyStr).add(s);
        }

        for(List<String> set: map.values()){
            Collections.sort(set);
        }

        return new ArrayList<List<String>>(map.values());
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if(matrix == null) {
            return res;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};

        int r = 0, c = 0;
        int cr = 0, cc = 0, di = 0;
        for(int i = 0; i < rows * cols; i++) {
            visited[r][c] = true;
            res.add(matrix[r][c]);
            cr = r + dr[di];
            cc = c + dc[di];
            if(cr >= 0 && cr < rows && cc >= 0 && cc < cols && !visited[cr][cc]) {
                r = cr;
                c = cc;
            }else {
                di = (di + 1) % 4;
                r = r + dr[di];
                c = c + dc[di];
            }
        }

        return res;
    }

    public static int jump(int[] nums) {

        int level=0, currentMax=0, i=0, nextMax=0;
        int n = nums.length;
        while(currentMax-i+1>0){		//nodes count of current level>0
            level++;
            for(;i<=currentMax;i++){	//traverse current level , and update the max reach of next level
                nextMax=Math.max(nextMax,nums[i]+i);
                if(nextMax>=n-1)
                    return level;   // if last element is in level+1,  then the min jump=level
            }
            currentMax=nextMax;
        }
        return 0;
    }

    public static int uniquePaths(int m, int n) {
        if(m < 0 || n < 0) {
            return 0;
        }

        int[][] memo = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(m == 0 || j == 0) {
                    memo[i][j] = 1;
                    continue;
                }
                memo[i][j] = memo[i][j - 1] + memo[i - 1][j];
            }
        }

        return memo[m - 1][n - 1];
    }


    private static int dp(int i, int j) {
        if(i == 0 || j == 0) {
            return 1;
        }
        return dp(i, j - 1) + dp(i - 1, j);
    }

    public static void sortColors(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }

        int red = 0, blue = nums.length -1;
        for(int i = 0; i <= blue; i++) {
            if(nums[i] == 0) {
                swap(nums, i, red);
                red++;
            }else if(nums[i] == 2) {
                swap(nums, i, blue);
                blue--;
                i--;
            }
        }

    }

    public static String minWindow(String s, String t) {
        int[] map = new int[128];

        for (char c : t.toCharArray()) map[c]++;

        int count = t.length(), begin = 0, end = 0, d = Integer.MAX_VALUE, head = 0;

        while (end < s.length()) {
            if (map[s.charAt(end++)]-- > 0)
                count--;
            while (count == 0) {
                if (end - begin < d) {
                    d = end - begin;
                    head = begin;
                }
                if (map[s.charAt(begin++)]++ == 0)
                    count++;
            }
        }

        return d == Integer.MAX_VALUE ? "" : s.substring(head, head+d);
    }

    public static String minWindow1(String s, String t) {
        int[] map = new int[128];
        for(Character c: t.toCharArray()) {
            map[c]++;
        }

        int left = 0, right = 0, length = Integer.MAX_VALUE, head = 0;
        int count = t.length();
        while(right < s.length()) {
            if(map[s.charAt(right)] > 0) {
                map[s.charAt(right)]--;
                count--;
            }
            right++;

            while(count == 0) {
                if(right - left < length) {
                    length = right - left;
                    head = left;
                }

                if(map[s.charAt(left)] == 0) {
                    map[s.charAt(left)]++;
                    count++;
                    left++;
                }
            }
        }

        return length == Integer.MAX_VALUE ? "" : s.substring(head, head + length);
    }

    public static boolean exist(char[][] board, String word) {
        if(word == null || word.length() == 0) {
            return true;
        }

        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if(board[i][j] == word.charAt(0) && dfs(visited, board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean dfs(boolean[][] visited, char[][] board, String word, int i, int j, int index) {
        if(index == word.length()) {
            return true;
        }

        if(i < 0 || j < 0 || i >= board.length || j >= board[i].length
                || visited[i][j] || word.charAt(index) != board[i][j]) {
            return false;
        }

        visited[i][j] = true;
        if(dfs(visited, board, word, i + 1, j, index + 1) || dfs(visited, board, word, i - 1, j, index + 1) ||
                dfs(visited, board, word, i, j + 1, index + 1) || dfs(visited, board, word, i, j - 1, index + 1)) {
            return true;
        }
        visited[i][j] = false;

        return false;
    }

    public static int trap(int[] height) {
        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                max_right = Math.max(max_right, height[j]);
            }
            ans += Math.min(max_left, max_right) - height[i];
        }
        return ans;
    }


//    public static int maxSubArray(int[] nums) {
//        SegmentTreeNode root = SegmentTreeNode.build(0, nums.length - 1, nums);
//        return root.sum;
//    }

    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    public static void moveZeroes(int[] nums) {
        if(nums == null || nums.length == 0) {
            return;
        }

        //get the first index of zero and non zero number
        int zeroIdx = -1;
        int nonZeroIdx = -1;
        for(int i = 0; i < nums.length; i++) {
            if(zeroIdx == -1 && nums[i] == 0) {
                zeroIdx = i;
            }
            if(nonZeroIdx == -1 && nums[i] != 0) {
                nonZeroIdx = i;
            }
        }

        //no zeros in array
        if(zeroIdx == -1 || nonZeroIdx == -1) {
            return;
        }

        while(nonZeroIdx < nums.length) {
            swap(nums, zeroIdx, nonZeroIdx);
            zeroIdx++;
            while(zeroIdx < nums.length && nums[zeroIdx] != 0) {
                zeroIdx++;
            }
            while(nonZeroIdx < nums.length && nums[nonZeroIdx] == 0) {
                nonZeroIdx++;
            }
        }

        return;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null)
            return list;

        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;

        }
        return list;
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int len = 1;
        Queue<String> queue = new LinkedList<>();
        boolean[] visited = new boolean[wordList.size()];
        queue.offer(beginWord);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < queue.size(); i++) {
                String nextWord = queue.poll();
                if(nextWord.equals(endWord)) {
                    return len;
                }

                for(int j = 0; j < wordList.size(); j++) {
                    if(!visited[j] && replaceOneChar(nextWord, wordList.get(j))) {
                        visited[j] = true;
                        queue.offer(wordList.get(j));
                    }
                }
            }
            if(queue.size() == 0) {
                return 0;
            }
            len++;
        }

        return len;
    }


    private static boolean replaceOneChar(String s1, String s2) {
        for(int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) {
                return s1.substring(i + 1).equals(s2.substring(i + 1));
            }
        }
        return false;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        SegmentTreeNode root = SegmentTreeNode.build(0, nums.length - 1, nums);

        for(int k_start = 0; k_start <= nums.length - k; k_start++) {
            int k_end = k_start + k - 1;
            res[k_start] = SegmentTreeNode.query(root, k_start, k_end);
        }

        return res;
    }

    public static int[] maxSlidingWindow1(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        if(nums == null || k <= 0) {
            return new int[0];
        }

        int idx = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i < nums.length; i++) {
            if(!deque.isEmpty() && deque.peek() <= i - k) {
                deque.poll();
            }
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.poll();
            }
            deque.offer(i);
            if(i >= k - 1) {
                res[idx++] = nums[deque.peek()];
            }
        }
        return res;
    }

    public static int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);

        dp[0] = 0;
        for(int i = 1; i <= amount; i++) {
            for(int j = 0; j < coins.length; j++) {
                if(coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while(root!=null) {
            if(root.val > p.val) {
                res = root;
                root = root.left;
            }
            else
                root = root.right;
        }
        return res;
    }

    public static void connect(TreeNode root) {
        if(root == null) {
            return;
        }

        if(root.left == null && root.right == null) {
            return;
        }

        root.left.next = root.right;
        root.right.next = root.next == null ? null: root.next.left;

        connect(root.left);
        connect(root.right);

        return;
    }

    private static HashSet<Integer> set = new HashSet<>();
    public static boolean isHappy(int n) {
        if(n == 1) {
            return true;
        }

        set.add(n);
        int sum = 0, reminder = 0;
        while(n > 0) {
            reminder = n % 10;
            sum += reminder * reminder;
            n = n / 10;
        }
        sum += n* n;

        if(set.contains(sum)) {
            return false;
        }

        return isHappy(sum);
    }

    public static int longestSubstring(String s, int k) {
        char[] str = s.toCharArray();
        return helper1(str,0,s.length(),k);
    }

    private static int helper1(char[] str, int start, int end,  int k){
        if (end - start < k) return 0;//substring length shorter than k.
        int[] count = new int [26];
        for (int i = start; i<end; i++) {
            int idx = str[i] - 'a';
            count[idx]++;
        }
        for (int i=0; i<26; i++) {
            if (count[i] < k && count[i] > 0) { //count[i]=0 => i+'a' does not exist in the string, skip it.
                for (int j = start; j<end; j++) {
                    if (str[j] == i+'a') {
                        int left = helper1(str, start, j, k);
                        int right = helper1(str, j+1, end, k);
                        return Math.max(left, right);
                    }
                }
            }
        }
        return end - start;
    }

    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        boolean[][] visited = new boolean[n][n];

        int r = 0, c = 0, cur_row = 0, cur_col = 0, di = 0;
        int number = 1;
        for(int i = 0; i < n * n; i++) {
            visited[r][c] = true;
            matrix[r][c] = number++;

            cur_row = r + dr[di];
            cur_col = c + dc[di];
            if(cur_row < 0 || cur_row > n - 1 || cur_col < 0 || cur_row > n - 1 || visited[cur_row][cur_col]) {
                di = (di + 1) % 4;
                r = r + dr[di];
                c = c + dc[di];
            }else {
                r = cur_row;
                c = cur_col;
            }
        }
        return matrix;
    }

    private static int res;
    public static int minPathSum(int[][] grid) {
        res = Integer.MAX_VALUE;
        int sum = 0;
        dfs(grid, 0, 0, sum);
        return res;
    }

    private static void dfs(int[][] grid, int i, int j, int sum) {
        if(i == grid.length - 1 && j == grid[0].length - 1) {
            sum += grid[i][j];
            res = Math.min(res, sum);
            return;
        }

        if(i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1) {
            return;
        }

        sum += grid[i][j];
        dfs(grid, i + 1, j, sum);
        dfs(grid, i, j + 1, sum);
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int n = matrix.length;
        int m = matrix[0].length;

        int start = 0, end = m * n - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(matrix[mid / m][mid % m] > target) {
                start = mid + 1;
            }else if(matrix[mid / m][mid % m] < target) {
                end = mid - 1;
            }else {
                return true;
            }
        }

        if(matrix[start / m][start % m] == target)
            return true;
        if(matrix[end / m][end % m] == target)
            return true;
        return false;
    }

    public static int search1(int[] nums, int target) {
        if(nums == null || nums.length == 0)
            return -1;

        int n = nums.length;
        int lo = 0, hi = n - 1;
        int pivot = nums[n - 1];
        while(lo + 1 < hi) {
            int mid = (lo + hi) / 2;
            if(target > pivot) {
                if(nums[mid] > target) {
                    hi = mid - 1;
                }
            }else{
                if(nums[mid] < target) {
                    hi = mid - 1;
                }
            }
        }
        if(nums[lo] == target)
            return lo;
        if(nums[hi] == target)
            return hi;
        return -1;

    }

    public static int minCost(int[][] costs) {
        if(costs == null || costs.length == 0) {
            return 0;
        }

        int R = costs[0][0];
        int G = costs[0][1];
        int B = costs[0][2];

        for(int i = 1;i < costs.length; i++) {
            R = Math.min(G, B) + costs[i][0];
            G = Math.min(R, B) + costs[i][1];
            B = Math.min(R, G) + costs[i][2];
//            R = curR;
//            G = curG;
//            B = curB;
        }

        return Math.min(Math.min(R,G), B);
    }

    public static int minFallingPathSum(int[][] A) {
        if(A.length == 1) {
            return A[0][0];
        }

        int n = A.length;
        int m = A[0].length;
        int[][] dp = new int[n][m];
        for(int i = 0; i < m; i++) {
            dp[0][i] = A[0][i];
        }

        for(int i = 1; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(j == 0) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + 1]);
                }else if (j == m - 2) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]);
                }else{
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i - 1][j + 1]));
                }
                dp[i][j] += A[i][j];
            }
        }

        int res = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++) {
            res = Math.min(res, dp[n-1][i]);
        }

        return res;
    }

    public static int countSubstrings(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int sum = 1;
        for(int i = 1; i < n; i++) {
            for(int j = i - 1; j >= 0; j--) {
                if(isPalindrome(s, i, j)) {
                    dp[i] = dp[j] + 1;
                    break;
                }
            }
            sum += dp[i];
        }

        return sum;
    }

    private static boolean isPalindrome(String s, int i, int j) {
        while(j < i) {
            if(s.charAt(j) != s.charAt(i)) {
                return false;
            }
            j++;
            i--;
        }
        return true;
    }

    public static int minimumDeleteSum(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = s1.length() - 1; i >= 0; i--) {
            dp[i][s2.length()] = dp[i+1][s2.length()] + s1.codePointAt(i);
        }
        for (int j = s2.length() - 1; j >= 0; j--) {
            dp[s1.length()][j] = dp[s1.length()][j+1] + s2.codePointAt(j);
        }
        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i+1][j+1];
                } else {
                    dp[i][j] = Math.min(dp[i+1][j] + s1.codePointAt(i),
                            dp[i][j+1] + s2.codePointAt(j));
                }
            }
        }
        return dp[0][0];
    }

    public static int maxProfit(int[] prices, int fee) {
        int profit = 0;
        for(int i = 0; i < prices.length - 1; i++) {
            for(int j = i + 1; j < prices.length; j++) {
                if(prices[j] - prices[i] > 2) {
                    profit += prices[j] - prices[i] - 2;
                    i = j;
                    break;
                }
            }
        }
        return profit;
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) {
            return 0;
        }
        if(triangle.size() == 1) {
            return triangle.get(0).get(0);
        }

        int n = triangle.size();
        int[][] dp = new int[n][n];
        for(int[] row: dp) {
            Arrays.fill(row, 10000);
        }
        dp[0][0] = triangle.get(0).get(0);

        for(int i = 1; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j + 1]) + triangle.get(i).get(j);
            }
        }

        int res = Integer.MAX_VALUE;
        for(int j = 0; j < n; j++) {
            res = Math.min(dp[n - 1][j], res);
        }

        return res;
    }

    public static int shortestDistance(String[] words, String word1, String word2) {
        int loc = -1;
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(word1)) {
                loc = i;
                int left = i - 1, right = i + 1;
                while(left >= 0 || right < words.length) {
                    while(left >= 0 && !words[left].equals(word2)) {
                        left--;
                    }
                    while(right < words.length && !words[right].equals(word2)) {
                        right++;
                    }

                    int leftDis = Math.abs(loc - left);
                    int rightDis = Math.abs(loc - right);
                    return leftDis < rightDis? leftDis: rightDis;
                }
            }
        }
        return loc;
    }

    public static List<String> subdomainVisits(String[] cpdomains) {
        List<String> res = new ArrayList<>();
        if(cpdomains == null || cpdomains.length == 0) {
            return res;
        }

        Map<String, Integer> map = new HashMap<>();
        for(String pair: cpdomains) {
            int count = Integer.parseInt(pair.split("\\s+")[0]);
            String[] domains = (pair.split("\\s+")[1]).split("\\.");
            String cur = "";
            for(int i = domains.length - 1; i >= 0; i--) {
                cur = i == domains.length - 1 ? "" : "." + domains[i];
                map.put(cur, map.getOrDefault(cur, 0) + count);
            }
        }

        for(String domain: map.keySet()) {
            res.add(map.get(domain) + " " + domain);
        }
        return res;
    }

    public static int sherlockAndAnagrams(String s) {
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++) {
            for(int j = i + 1; j <= s.length(); j++) {
                String substring = s.substring(i,j);
                int[] signature = new int[26];
                for(Character c: substring.toCharArray()) {
                    signature[c - 'a']++;
                }
                map.put(Arrays.toString(signature), map.getOrDefault(Arrays.toString(signature), 0) + 1);
            }
        }

        int ans = 0;
        for(Integer count: map.values()) {
            ans += count * (count - 1) / 2;
        }

        return ans;
    }

    public static long countTriplets(List<Long> arr, long r) {
        Map<Long, Long> t2 = new HashMap<>();
        Map<Long, Long> t3 = new HashMap<>();
        long result = 0L;

        for(Long a : arr) {
            result += t3.getOrDefault(a, 0L);
            if (t2.containsKey(a)){
                t3.put(a*r, t3.getOrDefault(a*r, 0L) + t2.get(a));
            }
            t2.put(a*r, t2.getOrDefault(a*r, 0L) + 1);
        }
        return result;
    }

    public static int activityNotifications(int[] expenditure, int d) {
        int count = 0;
        for(int i = d; i < expenditure.length; i++) {
            if(expenditure[i] >= 2 * getMedian(expenditure, i - d, i - 1)) {
                count++;
            }
        }
        return count;
    }

    private static int getMedian(int[] exp, int start, int end) {
        int size = end - start + 1;
        int[] arr = new int[size];
        for(int i = 0; i < size; i++) {
            arr[i] = exp[start++];
        }

        Arrays.sort(arr);
        if(size % 2 == 1) {
            return arr[size / 2];
        }else {
            return (arr[size / 2] + arr[size / 2 + 1]) / 2;
        }
    }

    static long ans = 0;
    public static long countInversions(int[] arr) {
        mergeCount(arr, new int[arr.length], 0, arr.length - 1);
        return ans;
    }

    private static void mergeCount(int[] arr, int[] temp, int leftStart, int rightEnd) {
        if(leftStart >= rightEnd) {
            return;
        }

        int mid = (leftStart + rightEnd) / 2;
        mergeCount(arr, temp, leftStart, mid);
        mergeCount(arr, temp, mid + 1, rightEnd);
        merge(arr, temp, leftStart, rightEnd);
    }

    private static void merge(int[] arr, int[] temp, int leftStart, int rightEnd) {
        int leftEnd = (leftStart + rightEnd) / 2;
        int rightStart = leftEnd + 1;

        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        while(left <= leftEnd && right <= rightEnd) {
            if(arr[left] <= arr[right]) {
                temp[index] = arr[left];
                left++;
            }else {
                temp[index] = arr[right];
                right++;
                ans++;
            }
            index++;
        }

        while(left <= leftEnd) {
            temp[index++] = arr[left++];
        }
        while(right <= rightEnd) {
            temp[index++] = arr[right++];
        }

        System.arraycopy(temp, leftStart, arr, leftStart, rightEnd - leftStart + 1);

    }


    public static long[] riddle(long[] arr) {
        // complete this function
        int len = arr.length;
        long[] res = new long[len];
        Arrays.fill(res, Long.MIN_VALUE);

        Map<Integer, Long> map = new HashMap<>();
        divideCount(map, arr, 0, len - 1);

        for(Map.Entry<Integer, Long> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            int key = entry.getKey();
            long value = entry.getValue();
            res[key - 1] = value;
        }

        for(int i = len - 1; i >= 0; i--) {
            if(res[i] == Long.MIN_VALUE) {
                res[i] = res[i + 1];
            }
        }



        return res;

    }

    private static void divideCount(Map<Integer, Long> map, long[] arr, int left, int right) {
        if(left > right) {
            return;
        }

        long min = Long.MAX_VALUE;
        int index = 1;
        for(int i = left; i <= right; i++) {
            if(arr[i] < min) {
                min = arr[i];
                index = i;
            }
        }

        int window = right - left + 1;
        if(map.containsKey(window)) {
            long prev = map.get(window);
            map.put(window, Math.max(prev, min));
        }else {
            map.put(window, min);
        }

        divideCount(map, arr, left, index - 1);
        divideCount(map, arr, index + 1, right);

        return;
    }



    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Complete the minimumMoves function below.
    static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {
        int n = grid.length;
        int m = grid[0].length();
        char[][] matrix = new char[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                matrix[i][j] = grid[i].charAt(j);
            }
        }

        boolean[][] visited = new boolean[n][m];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(startX, startY));
        visited[startX][startY] = true;

        int ans = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Point cur = queue.poll();
                if(cur.x == goalX && cur.y == goalY) {
                    return ans;
                }

                for(Point neighbor: getNextPoint(cur, visited, matrix)) {
                    queue.offer(neighbor);
                }
            }
            ans++;
        }

        return ans;
    }

    private static List<Point> getNextPoint(Point cur, boolean[][] visited, char[][] matrix) {
        List<Point> res = new LinkedList<>();
        int curX = cur.x;
        int curY = cur.y;

        //find left most point
        int j;
        for(j = curY; j >= 0; j--) {
            if(!visited[curX][j] && matrix[curX][j] != 'X') {
                res.add(new Point(curX, j));
                visited[curX][j] = true;
            }else if (matrix[curX][j] == 'X'){
                break;
            }
        }


        for(j = curY; j <= matrix[0].length - 1; j++) {
            if(!visited[curX][j] && matrix[curX][j] != 'X') {
                res.add(new Point(curX, j));
                visited[curX][j] = true;
            }else if (matrix[curX][j] == 'X'){
                break;
            }
        }


        int i;
        for(i = curX; i <= matrix.length - 1; i++) {
            if(!visited[i][curY] && matrix[i][curY] != 'X') {
                res.add(new Point(i, curY));
                visited[i][curY] = true;
            }else if (matrix[i][curY] == 'X'){
                break;
            }
        }



        for(i = curX; i >= 0; i--) {
            if(!visited[i][curY] && matrix[i][curY] != 'X') {
                res.add(new Point(i, curY));
                visited[i][curY] = true;
            }else if (matrix[i][curY] == 'X'){
                break;
            }
        }


        return res;

    }


    static int poisonousPlants(int[] p) {
        List<Deque<Integer>> list = new LinkedList<>();
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(p[0]);

        int idx = 1;
        while(idx < p.length) {
            if(p[idx] <= p[idx - 1]) {
                queue.offer(p[idx]);
            }else {
                list.add(queue);
                queue = new LinkedList<>();
                queue.offer(p[idx]);
            }
            idx++;
        }
        list.add(queue);

        int ans = 0;
        while(list.size() > 1) {
            for(int i = 1; i < list.size(); i++) {
                Deque<Integer> deque = list.get(i);
                deque.poll();

                if(deque.size() == 0) {
                    list.remove(i);
                    i--;
                    continue;
                }
                if(deque.peekFirst() <= list.get(i - 1).getLast()) {
                    list.get(i - 1).addAll(deque);
                    list.remove(i);
                    i--;
                }
            }
            ans++;
        }

        return ans;
    }


    private static class graphNode {
        public int label;
        public long color;
        public List<graphNode> child;
        public graphNode(int label, long color) {
            this.label = label;
            this.color = color;
            this.child = new ArrayList<>();
        }

        //add node to child list;
        public void addEdge(graphNode node) {
            this.child.add(node);
        }
    }
    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        // solve here
        Map<Integer, graphNode> graph = new HashMap<>();
        for(int i = 0; i < ids.length; i++) {
            int label = i + 1;
            long color = ids[i];
            graph.put(label, new graphNode(label, color));
        }

        for(int i = 0; i < graphFrom.length; i++) {
            graphNode fromNode = graph.get(graphFrom[i]);
            graphNode toNode = graph.get(graphTo[i]);
            fromNode.addEdge(toNode);
            toNode.addEdge(fromNode);
        }

        Queue<graphNode> queue = new LinkedList<>();
        for(graphNode node: graph.values()) {
            if(node.color == val) {
                queue.offer(node);
            }
        }

        // no valid nodes in graph
        if(queue.isEmpty()) {
            return -1;
        }

        int res = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            graphNode head = queue.poll();
            res = Math.min(res, bfs(head, graph, val));
        }

        return res;
    }

    private static int bfs(graphNode head, Map<Integer, graphNode> graph, int val) {
        int ans = 0;
        Queue<graphNode> queue = new LinkedList<>();
        queue.offer(head);
        Set<graphNode> visited = new HashSet<>();

        while(!queue.isEmpty()) {
            ans++;
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                graphNode node = queue.poll();
                visited.add(node);
                for(graphNode child: node.child) {
                    if(!visited.contains(child)) {
                        if(child.color == val) {
                            return ans;
                        }else {
                            queue.offer(child);
                            visited.add(child);
                        }
                    }
                }
            }
        }

        return -1;
    }


    // Complete the abbreviation function below.
    static boolean abbreviation(String a, String b) {
        String res = null;
        boolean[][] dp = new boolean[b.length() + 1][a.length() + 1];
        dp[0][0] = true;

        boolean containsCaps = false;
        for(int j = 1; j < dp[0].length; j++) {
            if(Character.isLowerCase(a.charAt(j - 1)) && !containsCaps) {
                dp[0][j] = true;
            }else {
                containsCaps = true;
                dp[0][j] = false;
            }
        }

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(Character.isUpperCase(a.charAt(j - 1))) {
                    if(a.charAt(j - 1) == b.charAt(i - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }else {
                        dp[i][j] = false;
                        continue;
                    }
                }else {
                    if(a.charAt(j - 1) == b.charAt(i - 1) || Character.toUpperCase(a.charAt(j - 1)) == b.charAt(i - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] || dp[i][j - 1];
                    }else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
        }

        return dp[b.length()][a.length()];
    }


    static long candies(int[] arr) {
        long[] dp = new long[arr.length];
        Arrays.fill(dp, 1l);

        long ans = 0l;
        for(int i = 1; i < dp.length; i++) {
            if(arr[i] > arr[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
        }

        for(int i = dp.length - 2; i >= 0; i--) {
            if(arr[i] > arr[i + 1]) {
                dp[i] = Math.max(dp[i + 1] + 1, dp[i]);
            }
        }


        for(long number: dp) {
            ans += number;
        }
        return ans;
    }

    static class Interval {
        int left, right, degree;
        public Interval(int left, int right, int degree) {
            this.left = left;
            this.right = right;
            this.degree = degree;
        }

        public int length() {
            return right - left + 1;
        }

    }

    public static int findShortestSubArray(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        Interval interval = null;
        int maxDegree = 0;
        Map<Integer, Interval> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                interval = map.get(nums[i]);
                interval.right = i;
                interval.degree++;
                map.put(nums[i], interval);
            }else {
                interval = new Interval(i, i, 1);
                map.put(nums[i], interval);
            }
            maxDegree = Math.max(maxDegree, interval.degree);
        }

        int ans = Integer.MAX_VALUE;
        for(Interval iter: map.values()) {
            if(iter.degree == maxDegree) {
                ans = Math.min(ans, iter.length());
            }
        }

        return ans;
    }

    public static int[] nextGreaterElements(int[] nums) {
        if(nums == null) {
            return null;
        }
        if(nums.length == 0) {
            return nums;
        }

        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < i + nums.length; j++) {
                int idx = j % nums.length;
                if(nums[idx] > nums[i]) {
                    res[i] = nums[idx];
                    break;
                }
            }
        }

        return res;
    }

    public static int[] sortArray(int arr[]) {
        int len = arr.length;
        int small, pos, i, j, temp;
        for(i = 0; i <= len - 1; i++) {
            for(j = i; j < len; j++) {
                temp = 0;
                if(arr[i] < arr[j]) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static String removeVowels(String A) {
        String regex = "aoeiuAOEIU";
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < A.length(); i++) {
            if(regex.indexOf(A.charAt(i)) != -1) {
                continue;
            }
            sb.append(A.charAt(i));
        }

        return sb.toString();
    }

    public static boolean validParenthesis(String A) {
        if(A == null || A.length() == 0) {
            return true;
        }

        Deque<Character> stack = new LinkedList<>();
        for(int i = 0; i < A.length(); i++) {
            if(stack.isEmpty()) {
                stack.push(A.charAt(i));
            }else if(A.charAt(i) - stack.peek() == 1 || A.charAt(i) - stack.peek() == 2) {
                stack.pop();
            }else {
                stack.push(A.charAt(i));
            }
        }

        return stack.isEmpty();


    }

//    public static ListNode reverseSecondHalfList(ListNode head){
//        if(head == null || head.next == null) {
//            return head;
//        }
//
//        ListNode slow = head;
//        ListNode fast = head;
//        while(fast.next != null && fast.next.next != null) {
//            fast = fast.next.next;
//            slow = slow.next;
//        }
//
//        ListNode pre = slow.next;
//        ListNode cur = pre.next;
//        while(cur != null) {
//            ListNode temp = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = temp;
//        }
//        slow.next.next = null;
//        slow.next = pre;
//
//        return head;
//    }

    public static ListNode reverseSecondHalfList(ListNode head) {
        if (head == null || head.next == null)      return head;
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode pre = slow.next;
        ListNode cur = pre.next;
        while (cur != null) {
            pre.next = cur.next;
            cur.next = slow.next;
            slow.next = cur;
            cur = pre.next;
        }
        return head;
    }


    public static List<Integer> GetSum(List<Integer> A, int k) {
        ArrayList<Integer> result  = new ArrayList<>();
        if(A == null || A.size() == 0 || k <= 0 || k > A.size()) {
            return result;
        }

        int sum = 0;
        for(int i = 0; i < A.size(); i++) {
            sum += A.get(i);
            if(i >= k - 1) {
                result.add(sum);
                sum -= A.get(i - k + 1);
            }

        }

        return result;
    }

    public static int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }


    private static int[][] transpose(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] rvalue = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                rvalue[i][j] = matrix[j][i];
        return rvalue;
    }


    public static int[] minSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();

        int idx = 0;
        for(int i = 0; i < nums.length; i++) {
            while(!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }
            while(!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {
                deque.pollLast();
            }

            deque.offer(i);
            if(i >= k - 1) {
                res[idx++] = nums[deque.peek()];
            }

        }

        return res;
    }

    static class Rating {
        int id, rating;
        public Rating(int id, int rating) {
            this.id = id;
            this.rating = rating;
        }
    }

    public static List<Double> highestFiveRatings(List<Rating> record) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for(Rating r: record) {
            if(map.containsKey(r.id)) {
                map.get(r.id).offer(r.rating);
            }else {
                PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer a, Integer b) {
                        return b - a;
                    }
                });

                pq.offer(r.rating);
                map.put(r.id, pq);
            }
        }

        List<Double> res = new ArrayList<>();
        for(PriorityQueue<Integer> pq: map.values()) {
            double avg = 0.0;
            if(pq.size() >= 5) {
                for(int i = 0; i < 5; i++) {
                    avg += pq.poll();
                }
                res.add(avg / 5);
            }else {
                for(Integer rating: pq) {
                    avg += rating;
                }
                res.add(avg / pq.size());
            }
        }

        return res;
    }


    public static List<String> kwords(String text, List<String> wordsToexclude) {
        List<String> result = new ArrayList<>();

        String[] words = text.split("[\\p{Punct}\\s]+");
        Set<String> exclude = new HashSet<>();
        for(String word: wordsToexclude) {
            exclude.add(word);
        }

        Map<String, Integer> map = new HashMap<>();
        for(String word: words) {
            word = word.toLowerCase();
            if(!exclude.contains(word)) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }

        int maxFreq = Integer.MIN_VALUE;
        for(Integer freq: map.values()) {
            if(freq > maxFreq) {
                maxFreq = freq;
            }
        }

        for(Map.Entry<String, Integer> entry: map.entrySet()) {
            String word = entry.getKey();
            int freq = entry.getValue();
            if(freq == maxFreq) {
                result.add(word);
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception{
        String s = "Rose is a flower red rose are flower";
        List<String> set = new ArrayList<>();
        set.add("is");
        set.add("are");
        set.add("a");
        System.out.println(kwords(s, set));


    }
}
