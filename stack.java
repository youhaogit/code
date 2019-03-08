
public class stack {

    int[] stack;
    public int top;
    private final int INIT_CAP = 6;

    public stack(){
        stack = new int[INIT_CAP];
        top = 0;
    }

    public String toString(){
        String str = "";
        for(int i = 1; i <= top; i++){
            str += stack[i] + " ";
        }
        return str.trim();
    }

    public boolean isEmpty(){
        return top == 0;
    }

    public int size(){
        return this.top;
    }

    public void push(int value){
        if(top + 1 == stack.length){
            int[] newStack = new int[2 * top];
            for(int i = 1; i <= top; i++)
                newStack[i] = stack[i];
            stack = newStack;
        }
        stack[++top] = value;
    }

    public int pop() throws Exception{
        if(top == 0)
            throw new Exception("outOfBoundery");
        else{
            top--;
            return stack[top + 1];
        }
    }
}
