import java.util.PriorityQueue;

public class priorityQueue {

    int[] pq;
    int size;
    private static final int init_capacity = 10;
    public priorityQueue(){
        pq = new int[init_capacity];
        size = 0;
        buildHeap(pq);
    }

    private void max_Heapify(int[] A, int i){
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int large = 0;

        if(left < size && A[left] > A[i])
            large = left;
        else
            large = i;

        if(right < size && A[right] > A[large])
            large = right;

        if(large != i){
            swap(A, i, large);
            max_Heapify(A, large);
        }

    }

    private void swap(int[] A, int i, int j){
        int temp = A[j];
        A[j] = A[i];
        A[i] = temp;
    }

    private void buildHeap(int[] A){
        int size = A.length;
        for(int i = A.length / 2 - 1; i >= 0; i--){
            max_Heapify(A, i);
        }
    }

    public int max(){
        if(this != null)
            return pq[0];
        return Integer.MAX_VALUE;
    }

    public int pop(){
        if(this == null || this.size < 1)
            return Integer.MAX_VALUE;

        int max = pq[0];
        pq[0] = pq[size - 1];
        size--;
        max_Heapify(pq, 0);
        return max;
    }

    public void heap_increase_value(int i, int value){
        if(value < pq[i]){
            pq[i] = value;
            return;
        }

        pq[i] = value;
        while(i > 0 && pq[i/2] < pq[i]){
            swap(pq, i, i/2);
            i = i / 2;
        }
    }

    public void insert(int value){
        if(size == init_capacity){
            int[] newPQ = new int[2 * this.size];
            for(int i = 0; i < this.size; i++)
                newPQ[i] = this.pq[i];
            this.pq = newPQ;
        }

        this.size += 1;
        pq[size - 1] = Integer.MIN_VALUE;
        heap_increase_value(size - 1, value);
    }

    public String toString(){
        String str = "";
        for(int i = 0; i < this.size; i++){
            str += pq[i] + " ";
        }
        return str.trim();
    }
}
