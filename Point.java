public class Point{
    int x, y;
    public Point(int a, int b){
        x = a;
        y = b;
    }

    public Point() { x = 0; y = 0; }

    @Override
    public String toString() {
        return "(" + this.x + " " + this.y + ")";
    }
}
