package strategiesOfAlgorithms.combinatorialSearch;

import importantInfo.tree.disjointSet.OptimizedDisjointSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 깊이 우선탐색의 효율에 더 좋은 알고리즘은 MST Heuristic
 * 단순한 휴리스틱은 선택한 간선들이 하나로 연결되지 않고 보통 떨어져있다.
 * 왜냐하면 가장 작은 값을 기준으로 선택하므로,
 * -----
 * 하지만 MST Heuristic은 현재 위치에서 시작해서 방문하지 않은 정점들을
 * 모두 방문하고, 시작점으로 돌아오는 최단 경로 또한 이 정점들을
 * 모두 연결하는 스패닝 트리이다.
 * 따라서 최소 스패닝 트리의 가중치의 합은 항상 최단 경로보다 작다.
 */

public class TspMstHeuristic {
    // 모든 도시 간의 도로를 길이 순으로 정렬해 저장해둔다.
    private static List<Pair> edges = new ArrayList<>();
    private static int[][] nearest;
    private static final double INF = 987654321.0;
    private static final double[][] travel= {
            {0.0000000000, 326.0008994586, 503.1066076077, 290.0250922998},
            {326.0008994586, 0.0000000000, 225.1785728436, 395.4019367384},
            {503.1066076077, 225.1785728436, 0.0000000000, 620.3945520632},
            {290.0250922998, 395.4019367384, 620.3945520632, 0.000000000}
    };
    private static double best;
    private static int n;

    // here와 시작점,아직 방문하지 않은 도시들을 모두 연결하는 MST를 찾는다.
    private static double mstHeuristic(int here,boolean[] visited){
        //Kruskal's MST
        OptimizedDisjointSet disjointSet = new OptimizedDisjointSet(n);
        double taken = 0;
        //edges에 있는 정점들을 weight에 따라 오름차순 정렬을 했으므로,
        //disjointSet.merge()를 순차적으로 진행해도 된다.
        for (int i = 0; i < edges.size(); i++) {
            int a = edges.get(i).pair.vertex;
            int b = (int)edges.get(i).pair.weight;
            if (a!=0&&a!=here&&visited[a]) continue;
            if (b!=0&&b!=here&&visited[b]) continue;
            if (disjointSet.merge(a,b)){
                taken += edges.get(i).weight;
            }
        }
        return taken;
    }
    // path: 지금까지 만든 경로
    // visited: 각 도시의 방문여부
    // currentLength 지금까지만든 경로의 길이
    // 나머지 도시들을 모두 방문하는 경로들을
    // 만들어 보고 가능하면 최적해를 갱신.
    private static void search(List<Integer> path, boolean[] visited, double currentLength){
        // path len이 0이라면?
        int here = path.get(path.size()-1);
        if (best<=currentLength+mstHeuristic(here,visited)) {
            System.out.println("pruning="+best+" "+(currentLength+mstHeuristic(here,visited)));
            return;
        }

        // base case : 모든 도시를 다 방문했을 때는
        // 0번 도시로 돌아가고 종료한다.
        if (path.size()==n){
            best = Math.min(best,currentLength+travel[here][0]);
            return;
        }
        //다음 방문할 도시를 전부 시도해본다. - 가까운 도시들을 우선적으로 방문한다.
        //최적해를 빠르게 찾는다면, 이 최적해보다 더 큰값들을 방문할 이유가없어진다.
        for (int i = 0; i <nearest[here].length ; i++) {
            int next = nearest[here][i];
            if (visited[next]) continue;
            path.add(next);
            visited[next] = true;
            search(path,visited,currentLength+travel[here][next]);
            visited[next] = false;
            path.remove(path.size()-1);
        }
    }
    //
    public static double sol(int n,double[][] travel){
        //init edges
        for (int i = 0; i < n; i++) {
            //이렇게 표현하면 자기자신을 넣지 않는다.
            for (int j = 0; j < i; j++) {
                edges.add(new Pair(travel[i][j],new Pair(i,j)));
            }
        }
        Collections.sort(edges);

        // n,n-1은 자기 자신을 제외하고 넣었다.
        nearest = new int[n][n-1];
        //init nearest
        for (int i = 0; i < n; i++) {
            List<Pair> order = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                //0인경우는 넣지 않는다. 즉, 자기자신은 넣지않는다.
                if (i!=j){
                    order.add(new Pair(travel[i][j],j));
                }
            }
            Collections.sort(order);
            for (int j = 0; j < n - 1; j++) {
                nearest[i][j] = order.get(j).vertex;
            }
        }
        //best를 큰 값으로 초기화
        best = INF;
        boolean[] visited = new boolean[n];
        List<Integer> path = new ArrayList<>();
        visited[0] = true;
        path.add(0);
        TspMstHeuristic.n = n;
        double currentLength = 0.0;
        search(path,visited,currentLength);
        return best;
    }

    public static void main(String[] args) {
        int n =4;
        double[][] travel= {
                {0.0000000000, 326.0008994586, 503.1066076077, 290.0250922998},
                {326.0008994586, 0.0000000000, 225.1785728436, 395.4019367384},
                {503.1066076077, 225.1785728436, 0.0000000000, 620.3945520632},
                {290.0250922998, 395.4019367384, 620.3945520632, 0.000000000}
        };
        System.out.println(sol(n,travel));
    }

}
