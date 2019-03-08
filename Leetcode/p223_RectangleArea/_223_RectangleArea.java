package Leetcode.p223_RectangleArea;

import java.util.PriorityQueue;

public class _223_RectangleArea {

//    Find the total area covered by two rectilinear rectangles in a 2D plane.
//
//    Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.


    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        //check non-cross situation
        int total = Math.abs(C - A) * Math.abs(D - B) + Math.abs(G - E) * Math.abs(H - F);
        if(E >= C || A >= G || F >= D || B >= H) {
            return total;
        }

        PriorityQueue<Integer> horizontal = new PriorityQueue<>((a, b) -> (a - b));
        horizontal.offer(A);
        horizontal.offer(C);
        horizontal.offer(E);
        horizontal.offer(G);

        PriorityQueue<Integer> vertical = new PriorityQueue<>((a, b) -> (a - b));
        vertical.offer(B);
        vertical.offer(D);
        vertical.offer(F);
        vertical.offer(H);

        horizontal.poll();
        int first_x = horizontal.poll();
        int second_x = horizontal.poll();

        vertical.poll();
        int first_y = vertical.poll();
        int second_y = vertical.poll();

        int cross =  Math.abs(second_x - first_x) * (second_y - first_y);

        return total - cross;
    }

    public static void main(String[] args) {
        System.out.println(computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
    }
}
