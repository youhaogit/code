package Indeed;

import java.util.*;

public class ExpireMap<K, V> extends TimerTask{

    static class Record<K, V> {
        private K key;
        private V value;
        private long startTime;
        private long duration;

        public Record(K key, V value, long startTime, long duration) {
            this.key = key;
            this.value = value;
            this.startTime = startTime;
            this.duration = duration;
        }

        public long expireTime() {
            return this.startTime + this.duration;
        }
    }


    Map<K, Record> map;
    Deque<Record> list;

    public ExpireMap() {
        this.map = new HashMap<>();
        this.list = new LinkedList<>();
    }

    @Override
    public void run() {
        clearCache();
    }

    private void clearCache() {
        while(!list.isEmpty()) {
            Record head = list.getFirst();
            if(head.expireTime() < System.currentTimeMillis()) {
                list.removeFirst();
                map.remove(head.key);
            }
        }
    }

    public void put(K key, V value, long duration) {
        Record record = new Record(key, value, System.currentTimeMillis(), duration);
        map.put(key, record);
        list.addFirst(record);
    }

    public V get(K key) {
        long curremtTime = System.currentTimeMillis();
        if(map.containsKey(key)) {
            Record record = map.get(key);
            if(record.startTime + record.duration >= curremtTime) {
                return (V)record.value;
            }
            return null;
        }else{
            return null;
        }
    }



    public static void main(String[] args) throws Exception {

        ExpireMap map = new ExpireMap();
        Timer timer = new Timer();
        timer.schedule(map, 0, 1000);


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run(){
                int key = 1;
                int value = 1;
                int duration = 5000;
                while(true) {
                    map.put(key++, value++, duration);

                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run(){
                int key1 = 1;
                int key2 = key1 - 5;
                while(true) {
                    System.out.println("Key" + key1++);
                    System.out.println(map.get(key1));
                    try {
                        Thread.sleep(4000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(map.get(key1));
                }
            }
        });

        t1.start();
        t2.start();

    }
}
