package strategiesOfAlgorithms.graph.shortestPathAlgorithms.routing;

import java.util.*;


public class Routing {
    private static final double INF = 987654321.0;
    private static List<Pair>[] adj;
    public static String sol(int n,int[][] lines,double[] weights){
        makeGraph(n,lines,weights);
        // 소수점 및 10자리까지 출력.
        double ans =bfs(new Pair(0,1),n);
        // 사실 이 아래 내용은 0을 붙여서 내보내려고 짠 코드이고, 위에 bfs()로 끝났다 사실.
        // 만약 double이라면, String이 아닌 이상 0을 붙여낼 방법이 없는건지 못찾은건지 .. 그러하다.

        // 0까지 내보내야한다면, String으로 내보내야한다.
        String a = String.valueOf(ans);
        int len = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i)=='.'){
                len = a.length()-i-1;
            }
        }
        for (int i = 0; i < 10 - len; i++) {
            a+="0";
        }
        //start default
        return a;
    }
    //start는 항상 (0,1)
    private static double bfs(Pair start,int n){
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Double.compare(o1.weight,o2.weight);
            }
        });
        double[] dist = new double[n];
        Arrays.fill(dist,INF);
        dist[0] = 1.0;
        pq.add(start);
        while(!pq.isEmpty()){
            Pair here = pq.poll();
            // 그 간선이 가지고 있는 weight보다
            // dist에 있는 값이 더 작으면 계산할 이유가 없다.
            if (dist[here.vertex]<here.weight) continue;
            //인접한 정점 모두 검사.
            for (int i = 0; i < adj[here.vertex].size(); i++) {
                int there = adj[here.vertex].get(i).vertex;
                double cost = adj[here.vertex].get(i).weight*here.weight;
                if (dist[there]>cost){
                    dist[there] = cost;
                    pq.add(new Pair(there,cost));
                }
            }
        }
        //마지막에 있는 값이 답.
        return dist[n-1];
    }
    private static void makeGraph(int n,int[][] lines,double[] weights){
        adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        int m = lines.length;
        //undirected graph
        for (int i = 0; i < m; i++) {
            adj[lines[i][0]].add(new Pair(lines[i][1],weights[i]));
            adj[lines[i][1]].add(new Pair(lines[i][0],weights[i]));
        }
    }

    public static void main(String[] args) {
        int n = 7;
        int m = 14;
        int[][] lines = {
                {0,1},
                {0,2},
                {0,3},
                {3,4},
                {3,5},
                {3,1},
                {1,2},
                {1,2},
                {1,4},
                {1,5},
                {5,4},
                {4,6},
                {5,6},
                {2,6},
        };
        double[] weights = {1.3,1.1,1.24,1.17,1.24,2,1.31,1.26,1.11,1.37,1.24,1.77,1.11,1.2};
        System.out.println(sol(n,lines,weights));
    }
}
