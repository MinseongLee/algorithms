package strategiesOfAlgorithms.combinatorialSearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1. 인접한 두 도시 순서 바꾸는 가지치기
 * 2. 부분 경로 뒤집는 가지치기
 */

public class TspReversePruning {
    private static int[][] nearest;
    private static double[] minEdge;
    private static final double INF = 987654321.0;
    private static final double[][] travel= {
            {0.0000000000, 326.0008994586, 503.1066076077, 290.0250922998},
            {326.0008994586, 0.0000000000, 225.1785728436, 395.4019367384},
            {503.1066076077, 225.1785728436, 0.0000000000, 620.3945520632},
            {290.0250922998, 395.4019367384, 620.3945520632, 0.000000000}
    };
    private static double best;
    private static int n;

    //path : 지금까지 만든 경로.
    // path의 마지막 네 개의 도시 중 가운데 있는 두 도시의 순서를 바꿨을 때
    // 경로가 더 짧아지는지 여부를 반환.
    //,,,p,a,b,q,,,here - 여기서 a와 b의 순서를 바꾸었을 때,
    private static boolean pathSwapPruning(List<Integer> path){
        if (path.size()<4) return false;
        int p = path.get(path.size()-4);
        int a = path.get(path.size()-3);
        int b = path.get(path.size()-2);
        int q = path.get(path.size()-1);
        //여기서 travel[a][b]==travel[b][a] 는 같은 값이므로, 포함하지 않아도 된다.
        //즉, 바꾼값이 더 작으면 true - 그래서 탐색을 종료한다.
        return travel[p][a]+travel[b][q] >travel[p][b]+travel[a][q];
    }
    //현재 이전 도시에서 끝나는 경로들을 뒤집는 경우만을 고려한다.
    //시작 도시와 현재 도시를 제외한 path의 부분 경로를
    //뒤집어 보고 더 짧아지는지 확인.
    private static boolean pathReversePruning(List<Integer> path){
        if (path.size()<4) return false;
        int b = path.get(path.size()-2);
        int q = path.get(path.size()-1);
        for (int i = 0; i+3 < path.size(); i++) {
            int p = path.get(i);
            int a = path.get(i+1);
            //,,p,a,,,b,q를 ,,p,b,,a,q로 바꾸어본다.
            //양 사이드만 변할 수 있으므로,p와 a를 전진하며,
            //이 구간의 모든 경우 수를 비교해본다.
            //비교를 해보며, reverse한 부분이 더 작은 경우가 존재하면,
            //더 깊이 들어갈 이유가 없다.
            if (travel[p][a]+travel[b][q]>travel[p][b]+travel[a][q]){
                return true;
            }
        }
        //현재가 최적해면 false.
        return false;
    }

    // minEdge는 인접 간선 중 가장 짧은 간선의 길이만 모음.
    private static void makeMinEdge(int n){
        minEdge = new double[n];
        for (int i = 0; i < travel.length; i++) {
            double min = 0;
            if (i==0){
                min = travel[i][1];
            }else{
                min = travel[i][0];
            }
            for (int j = 0; j < travel[i].length; j++) {
                if (i==j) continue;
                min = Math.min(min,travel[i][j]);
            }
//            System.out.println(i+" "+min);
            minEdge[i] = min;
        }

    }

    private static double simpleHeuristic(boolean[] visited){
        //마지막에 시작점으로 돌아갈 때 사용할 간선
        double ret=  minEdge[0];
        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                ret += minEdge[i];
            }
        }
        return ret;
    }

    // path: 지금까지 만든 경로
    // visited: 각 도시의 방문여부
    // currentLength 지금까지만든 경로의 길이
    // 나머지 도시들을 모두 방문하는 경로들을
    // 만들어 보고 가능하면 최적해를 갱신.
    private static void search(List<Integer> path, boolean[] visited, double currentLength){
        //당연한 이야기지만 여기 아래 2개 중 하나만 적용해놓을 것.
        //pathSwapPruning() vs pathReversePruning()
        //reversePruning 이 성능이 2~100배 이상 더 좋다.
        // (n이 커질 수록 성능이 더 좋음)
        if (pathSwapPruning(path)){
            System.out.println("pathSwapPruning="+path+" best="+best+" len="+(currentLength+simpleHeuristic(visited)));
            return;
        }
        if (pathReversePruning(path)){
            System.out.println("reversePruning="+path+" best="+best+" len="+(currentLength+simpleHeuristic(visited)));
            return;
        }
        if (best<=currentLength+simpleHeuristic(visited)) {
            System.out.println("pruning="+best+" "+(currentLength+simpleHeuristic(visited)));
            return;
        }

        // path len이 0이라면?
        int here = path.get(path.size()-1);

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
        //init minEdge
        makeMinEdge(n);
        //best를 큰 값으로 초기화
        best = INF;
        boolean[] visited = new boolean[n];
        List<Integer> path = new ArrayList<>();
        //start 지점을 0으로 고정시키고 돌려도 된다. 왜냐하면
        //S->A->B->C->D 나, D->S->A->B->C 나 C->D->S->A->B 나 등등
        //모두 같은 경우이기때문이다. 사이클이 있으므로, 그래서
        //항상 0부터 출발한다고 가정하면 (n-1)! 만 확인하면 되므로,
        //최적화를 할 수 있다.
        visited[0] = true;
        path.add(0);
        TspReversePruning.n = n;
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
