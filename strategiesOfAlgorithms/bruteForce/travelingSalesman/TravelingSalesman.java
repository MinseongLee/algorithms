package strategiesOfAlgorithms.bruteForce.travelingSalesman;

import java.util.ArrayList;
import java.util.List;

public class TravelingSalesman {
    public static void main(String[] args) {
        int n = 4;
        double dist[][] = {
                {0.0000000000, 326.0008994586, 503.1066076077, 290.0250922998},
                {326.0008994586, 0.0000000000, 225.1785728436, 395.4019367384},
                {503.1066076077, 225.1785728436, 0.0000000000, 620.3945520632},
                {290.0250922998, 395.4019367384, 620.3945520632, 0.000000000}
        };
        boolean[] visited = new boolean[n];
        List<Integer> row = new ArrayList<>();
        System.out.println(salesman(n-1,dist,visited,row));
    }
    /**
     * 2<=n<=10
     * solution
     * 1. 첫 시작을 항상 첫 번째 마을.
     * O(n-1!) why?
     * n=4일 때, 이 경우들은 다 같은 경우다.
     * 그래서 첫 시작을 강제해서 풀면 시간복잡도를 줄일 수 있다.
     * ex) A-B-C-D-A
     *     B-C-D-A-B
     *     C-D-A-B-C
     *     D-A-B-C-D
     */
    //00 이 기준. min
    public static double salesman(int n, double[][] dist, boolean[] visited, List<Integer> row){
        //base case
        if (n==0){
            System.out.println("row="+row);
            double sum = dist[0][row.get(0)];
            for (int i = 0; i < row.size()-1; i++) {
                sum += dist[row.get(i)][row.get(i+1)];
            }
            sum += dist[row.get(row.size()-1)][0];
            return sum;
        }
        double ans = Double.MAX_VALUE;
        for (int i = 1; i < dist.length; i++) {
            //이게 바로 순열 로직의 기본이다.
            if (!visited[i]){
                row.add(i);
                visited[i] = true;
                ans = Math.min(ans,salesman(n-1,dist,visited,row));
                visited[i] = false;
                row.remove(Integer.valueOf(i));
            }
        }
        return ans;
    }
}
