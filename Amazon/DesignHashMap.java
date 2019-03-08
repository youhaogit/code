package Amazon;

import java.util.Map;
import java.util.Objects;

public class DesignHashMap {

    static class HashMap<K, V>  {

        class Node<K,V> {
            K key;
            V val;
            Node next;

            Node(K key, V val) {
                this.key = key;
                this.val = val;
            }
        }

        static final int INIT_CAPACITY = 16;

        Node[] table;
        int size;


        public HashMap() {
            table = new Node[INIT_CAPACITY];
            this.size = 0;
        }

        public HashMap(int size) {
            table = new Node[INIT_CAPACITY];
            this.size = 0;
        }

        public V get(K key) {
            int idx = hash(key);

            Node node = table[idx];
            if(node == null) {
                return null;
            }else {
                while(node != null) {
                    if(node.key == key || node.key.equals(key)) {
                        return (V)node.val;
                    }
                }
            }

            return null;

        }

        public V put(K key, V val) {
            int idx = hash(key);

            Node node = table[idx];
            while(node != null) {
                if(node.key == key || node.key.equals(key)) {
                    Object oldVal = node.val;
                    node.val = val;
                    return (V)oldVal;
                }
                node = node.next;
            }

            //add new value if not exist
            node = table[idx];
            Node newNode = new Node(key, val);
            newNode.next = node;

            table[idx] = newNode;

            return null;
        }


        private int hash(K key) {
            return Objects.hashCode(key);
        }
    }

    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "aa");
        map.put(2, "bb");
        System.out.println(map.get(1));
        System.out.println(map.get(2));

        map.put(2, "cc");
        System.out.println(map.get(2));
        System.out.println(map.get(4));
    }
}
