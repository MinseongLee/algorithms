package baekjoon.implementation.superEasy.manhattanDistance;

/**
 * 유클리드 기하학 vs 택시 기하학.
 */
public class ManhattanDistance {
    public static void main(String[] args) {
        long r = 10000;

        System.out.println(r*r*3.14);
        // 유클리드 기하학에서 원의 넓이는 PI*r^2;
        double area1 = Math.PI*r*r;
        // 택시 기하학에서 원의 넓이는 2*r^2;
        double area2 = 2*r*r;
        System.out.println(String.format("%.6f",area1)+ " "+String.format("%.6f",area2));
        System.out.println(String.format("%,.6f",area1)+ " "+String.format("%,.6f",area2));
    }
}
