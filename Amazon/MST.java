package Amazon;

import java.util.*;

public class MST {

    public static class Connection {
        String city1;
        String city2;
        int cost;
        public Connection(String a, String b, int c) {
            city1 = a;
            city2 = b;
            cost = c;
        }
    }

    static class UF {
        Map<String, Integer> map;
        int setId;

        public UF() {
            this.map = new HashMap<>();
            this.setId = 0;
        }

        public boolean union(Connection c) {
            String _cityA = c.city1;
            String _cityB = c.city2;

            // two disjoint set
            if(!map.containsKey(_cityA) && !map.containsKey(_cityB)) {
                map.put(_cityA, setId);
                map.put(_cityB, setId);
                setId++;
                return true;
            }

            //either one is included
            if(!map.containsKey(_cityA) && map.containsKey(_cityB)) {
                map.put(_cityA, map.get(_cityB));
                return true;
            }
            if(map.containsKey(_cityA) && !map.containsKey(_cityB)) {
                map.put(_cityB, map.get(_cityA));
                return true;
            }

            //union two set if both included in map
            if(map.containsKey(_cityA) && map.containsKey(_cityB)) {
                int _setA = map.get(_cityA);
                int _setB = map.get(_cityB);

                if(_setA == _setB) {
                    return false;
                }

                for(String city: map.keySet()) {
                    if(map.get(city) == _setB) {
                        map.put(city, _setA);
                    }
                }

                return true;
            }

            return false;
        }
    }




    public static List<Connection> findMinimum(List<Connection> list) {
        List<Connection> res = new ArrayList<>();
        if(list == null || list.size() == 0) {
            return res;
        }

        UF uf = new UF();

        // sort connections by cost
        Collections.sort(list, new Comparator<Connection>() {
            @Override
            public int compare(Connection o1, Connection o2) {
                return o1.cost - o2.cost;
            }
        });


        for(Connection connection: list) {
            if(uf.union(connection)) {
                res.add(connection);
            }
        }

        //check two disjoint set
        int _setId = Integer.MAX_VALUE;
        for(Integer setId: uf.map.values()) {
            if(_setId == Integer.MAX_VALUE) {
                _setId = setId;
            }else {
                if(_setId != setId) {
                    return new ArrayList<>();
                }
            }
        }

        Collections.sort(list, new Comparator<Connection>() {
            @Override
            public int compare(Connection o1, Connection o2) {
                if(o1.city1.equals(o2.city1)) {
                    return o1.city2.compareTo(o2.city2);
                }
                return o1.city1.compareTo(o2.city1);
            }
        });

        return res;
    }

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static List<List<Integer>> nearestRestaurant(List<List<Integer>> restaurant, int n) {
        // Write your code here
        List<List<Integer>> res = new ArrayList<>();
        if(restaurant == null || restaurant.size() == 0 || n <= 0) {
            return res;
        }

        PriorityQueue<Point> pq = new PriorityQueue<>((Point a, Point b) ->  b.x * b.x + b.y * b.y - a.x * a.x - a.y * a.y);

        Point point = null;
        for(List<Integer> cor: restaurant) {
            point = new Point(cor.get(0), cor.get(1));
            pq.offer(point);

            if(pq.size() > n) {
                pq.poll();
            }
        }

        if(pq.size() < n) {
            return res;
        }

        Point maxSoFar = pq.poll();
        double distance = maxSoFar.x * maxSoFar.x + maxSoFar.y * maxSoFar.y;

        for(List<Integer> cor: restaurant) {
            if(getDistance(cor) <= distance) {
                res.add(cor);
            }
            if(res.size() == n) {
                return res;
            }
        }

        return res;
    }

    private static double getDistance(List<Integer> p) {
        return Math.pow(p.get(0), 2) + Math.pow(p.get(1), 2);
    }

    public static Point[] kClosest(Point[] points, Point origin, int k) {
        // write your code here
        Point[] res = new Point[k];
        if(points == null || points.length == 0 || k <= 0) {
            return res;
        }

        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point a, Point b) {
                if(getDistance(a, origin) == getDistance(b, origin)) {
                    if(a.x == b.x) {
                        return a.y - b.y;
                    }
                    return a.x - b.x;
                }
                return (int)(getDistance(a, origin) - getDistance(b, origin));
            }
        });

        for(Point point: points) {
            pq.offer(point);
        }

        if(pq.size() < k) {
            return res;
        }else {
            for(int i = 0; i < k; i++) {
                res[i] = pq.poll();
            }
        }

        for(Point p: res) {
            System.out.println(p);
        }
        return res;
    }

    private static double getDistance(Point a, Point b) {
        return Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2);
    }


    //飞机题 找最靠近的俩电影
    public static int[] aerial_Movie(int t, int[] dur) {
        // Write your code here
        int[] res = new int[2];
        if(t <= 30 || dur == null || dur.length <= 1) {
            return res;
        }

        Arrays.sort(dur);
        int left = 0, right = dur.length - 1;
        int delta = Integer.MAX_VALUE;
        while(left < right) {
            int sum = dur[left] + dur[right];
            if(sum > t - 30) {
                right--;
            }else if(sum < t - 30) {
                int newDelta = t - 30 - dur[left] - dur[right];
                if(newDelta < delta) {
                    res[0] = dur[left];
                    res[1] = dur[right];
                    delta = newDelta;
                }
                left++;
            }else {
                res[0] = dur[left];
                res[1] = dur[right];
                return res;
            }
        }

        return res;
    }

    //找出字符串中有k个不同字符的长度为k的子串
    public static List<String> KSubstring(String s, int k) {
        List<String> res = new ArrayList<>();
        if(s == null || s.length() == 0 || k <= 0) {
            return res;
        }

        Set<Character> set = new HashSet<>();
        Set<String> duplicate = new HashSet<>();

        int start = 0;
        for(int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
            if(i - start + 1 == k && set.size() == k) {
                String str = s.substring(start, start + k);
                if(!duplicate.contains(str)) {
                    duplicate.add(str);
                }
                set.remove(s.charAt(start++));
            }
        }

        for(String str: duplicate) {
            res.add(str);
        }

        return res;
    }

    public static void main (String[] args) {
//        ArrayList<Connection> connections = new ArrayList<>();
//        //下面的图是个苯环，中间加上了几根，如果想验证空表，去掉几根线就行。
//        connections.add(new Connection("A","B",6));
////        connections.add(new Connection("B","C",4));
//        connections.add(new Connection("C","D",5));
//        connections.add(new Connection("D","E",8));
////        connections.add(new Connection("E","F",2));
//        connections.add(new Connection("B","F",10));
//        connections.add(new Connection("E","C",9));
////        connections.add(new Connection("F","C",7));
////        connections.add(new Connection("B","E",3));
//        connections.add(new Connection("A","F",16));
//        //这里就输出一下看看结果。
//        List<Connection> res = findMinimum(connections);
//        for (Connection c : res){
//            System.out.println(c.city1 + " -> " + c.city2 + " " + c.cost);
//        }

//        List<List<Integer>> rest = new ArrayList<>();
//        List<Integer> r = new ArrayList<>();
////        0,1],[1,2],[2,1],[1,0]
//        r.add(0);
//        r.add(1);
//        rest.add(new ArrayList<>(r));
//
//        r = new ArrayList<>();
//        r.add(1);
//        r.add(2);
//        rest.add(new ArrayList<>(r));
//
//        r = new ArrayList<>();
//        r.add(2);
//        r.add(1);
//        rest.add(new ArrayList<>(r));
//
//        r = new ArrayList<>();
//        r.add(1);
//        r.add(0);
//        rest.add(new ArrayList<>(r));
//
//        System.out.println(nearestRestaurant(rest, 3));


//        [[4,6],[4,7],[4,4],[2,5],[1,1]]
//[0,0]
//        3
//        Point[] points = new Point[3];
//        points[0] = new Point(1,3);
//        points[1] = new Point(1,5);
//        points[2] = new Point(1,10);
////        points[3] = new Point(2,5);
////        points[4] = new Point(1,1);
//
//        Point origin = new Point(1,7);
//
//        kClosest(points, origin, 2);

        int[] dur = {15,15,16,13};
        for(int a: aerial_Movie(60, dur)) {
            System.out.println(a);
        }




    }
}
