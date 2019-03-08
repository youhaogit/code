public class singleStack {

    protected ListNode dummy;
    protected int size;

    public singleStack(){
        dummy = new ListNode(-1);
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
            dummy.next = new ListNode(value);
        }else{
            ListNode head = dummy.next;
            ListNode newNode = new ListNode(value);
            dummy.next = newNode;
            newNode.next = head;
        }
        size++;
    }

    public int pop() throws Exception {
        int value;
        if(isEmpty()){
                throw new Exception("stack is empty");
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
