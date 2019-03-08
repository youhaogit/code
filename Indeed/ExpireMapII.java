package Indeed;

import java.util.*;

public class ExpireMapII<K, V> extends TimerTask{

    static class Record<V> {
        V value;
        long startTime;
        long expireTime;
        public Record (V value, long startTime, long duration) {
            this.startTime = startTime;
            this.value = value;
            this.expireTime = startTime + duration;
        }

    }

    Map<K, Record> map;
    PriorityQueue<Record> heap;

    public ExpireMapII() {
        this.map = new HashMap<>();
        this.heap = new PriorityQueue<Record>(new Comparator<Record>() {
            public int compare(Record a, Record b) {
                if(a.expireTime - b.expireTime < 0) {
                    return -1;
                }else{
                    return 1;
                }
            }
        });
    }

    @Override
    public void run() {
        clearCache();
    }

    public synchronized void  put(K key, V value, long duration) {
        long curTime = System.currentTimeMillis();
        Record record = new Record(value, curTime, duration);
        map.put(key, record);
        heap.offer(record);


    }

    private void clearCache() {
        while(!heap.isEmpty()) {
            Record head = heap.peek();
            if(head.expireTime < System.currentTimeMillis()) {
                heap.poll();
                map.remove(head);
            }
        }
    }

    public Record get(K key) {
        if(map.containsKey(key)) {
            Record record = map.get(key);
            if(System.currentTimeMillis() > record.expireTime) {
                return null;
            }
            return record;
        }else {
            return null;
        }
    }



    public static void main(String[] args) throws Exception{
        ExpireMapII map = new ExpireMapII();
        Timer timer = new Timer();
        timer.schedule(map, 0, 1000);

    }

}
