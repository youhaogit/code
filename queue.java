public class queue {

    private final int MAX_SIZE = 5;
    private int[] queue;
    private int front;
    private int rear;

    public queue(){
        queue = new int[MAX_SIZE];
        front = -1;
        rear = -1;
    }

    public int size(){
        if(isEmpty())
            return 0;
        else
            return (rear - front + MAX_SIZE) % MAX_SIZE + 1;
    }

    public boolean isEmpty(){
        return front == -1 && rear == -1;
    }

    public boolean isFull(){
        return (rear + 1) % MAX_SIZE == front ? true : false;
    }

    public void offer(int value) throws Exception{
        if(isFull())
            throw new Exception("Queue is full!");

        if(isEmpty()){
            front = rear = 0;
        }else{
            rear = (rear + 1) % MAX_SIZE;
        }
        queue[rear] = value;
    }

    public int poll() throws Exception{
        if(isEmpty())
            throw new Exception("Queue is empty!");

        int value = queue[front];
        front++;

        return value;
    }

    public int front() throws Exception{
        if(isEmpty())
            throw new Exception("Queue is empty!");

        return queue[front];
    }

    public int rear() throws Exception{
        if(isEmpty())
            throw new Exception("Queue is Empty!");

        return queue[rear];
    }

    public String toString(){
        String str = "";
        int count = (rear - front + MAX_SIZE) % MAX_SIZE + 1;
        for(int i = 0; i < count; i++){
            int index = (front + i) % MAX_SIZE;
            str += queue[index] + " ";
        }
        return str.trim();
    }
}
