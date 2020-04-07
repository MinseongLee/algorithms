package strategiesOfAlgorithms.combinatorialSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * simple heuristic의 핵심은 minEdge[]이다.
 * 각 정점이 연결하고 있는 간선들 중 가장 작은 weight에만 연결.
 * 그래서 각 선들이 제대로 연결이 안될 확률이 높아 현실성은 많이 떨어짐.
 */

/**
 * 휴리스틱을 이용해 답의 남은 부분을 어림짐작하는 가지치기.
 * -이 부분에서는 최적해가 나올 수 없다는 것을 가능한 일찍 알아내는 것이 유리.
 * 어림짐작-
 * 반환 값은 항상 정확한 답일 필요도 없고 그럴 수도 없다.
 * return 값은 항상 남은 최단 경로의 길이 보다 작거나 같아야 한다.
 */
public class TspSimpleHeuristic {
    // 각 도시에 인접한 간선 중 가장 짧은 것을 미리 찾아 둔다.
    private static double[] minEdge;
    private static final double INF = 987654321.0;
    // 두 도시간의 거리를 저장하는 배열
    private static final double[][] travel= {
            {0.0000000000, 326.0008994586, 503.1066076077, 290.0250922998},
            {326.0008994586, 0.0000000000, 225.1785728436, 395.4019367384},
            {503.1066076077, 225.1785728436, 0.0000000000, 620.3945520632},
            {290.0250922998, 395.4019367384, 620.3945520632, 0.000000000}
    };
    // 전역 변수에 지금까지 찾은 최적해의 길이를 저장해 두면 탐색 중에
    // 더 최적해를 발견할 가능성이 없는 가지를 쳐내기 쉬워진다.
    private static double best;
    // 도시의 수.
    private static int n;
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
        //이렇게 따로 변수로만든건 print를 찍어보기 위해서
        double heuristic = simpleHeuristic(visited);
        System.out.println("above="+best+" "+currentLength+" "+heuristic);
        System.out.println("sum="+(currentLength+heuristic));
        // currentLength가 끝까지 간 상태가 아니라도,
        // heuristic으로 미리 가본 값들을 +해줘서
        // (가장 짧은 간선의 길이만 더했으므로 실제 최단 경로 이하의 값을 만든 후)
        // 항상 currentLength가 마지막 경로까지 간 길이를 리턴하여 최단경로와 비교를한다.**
        // 그렇다면 수행시간이 완탐에 비해 100배정도 더 빠른 효과를 낼 수 있다.
        if (best<=currentLength+simpleHeuristic(visited)) {
            System.out.println("pruning="+best+" "+(currentLength+heuristic));
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
        //다음 방문할 도시를 전부 시도해본다.
        for (int next = 0; next < n; next++) {
            if (visited[next]) continue;
            path.add(next);
            visited[next] = true;
            //나머지 경로를 재귀 호출을 통해 완성
            search(path,visited,currentLength+travel[here][next]);
            visited[next] = false;
            path.remove(path.size()-1);
        }
    }
    //
    public static double sol(int n,double[][] travel){
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
        TspSimpleHeuristic.n = n;
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
