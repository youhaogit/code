public class singleQueue {

    protected ListNode dummy;
    protected ListNode tail;
    protected int size;

    public singleQueue(){
        dummy = new ListNode(-1);
        tail = dummy;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void push(int value){
        if(isEmpty()){
            tail.next = new ListNode(value);
            tail = tail.next;
        }else{
            tail.next = new ListNode(value);
            tail = tail.next;
        }
        size++;
    }

    public int pop() throws Exception {
        int value;
        if(isEmpty()){
            throw new Exception("queue is empty");
        }else{
            ListNode head = dummy.next;
            value = head.val;
            dummy.next = head.next;
            head.next = null;
        }
        size--;

        return value;
    }
}
