package strategiesOfAlgorithms.combinatorialSearch;

import java.util.ArrayList;
import java.util.List;

public class TspPruning {
    private static final double INF = 987654321.0;
    //두 도시간의 거리를 저장하는 배열
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

    // path: 지금까지 만든 경로
    // visited: 각 도시의 방문여부
    // currentLength 지금까지만든 경로의 길이
    // 나머지 도시들을 모두 방문하는 경로들을
    // 만들어 보고 가능하면 최적해를 갱신.
    private static void search(List<Integer> path,boolean[] visited,double currentLength){
        System.out.println("above="+best+" "+currentLength);
        // 이 문장 하나로 최소한 현재 경로의 길이가 지금까지 가지고 있는
        // 최적해보다 커졌을 때는 탐색을 종료하므로,
        // n!개의 경로를 전부 만들어 보지는 않는다고 예상가능.
        //간단한 가지치기: 지금까지 찾은 가장 좋은 답 이상일 경우 중단.
        if (best<=currentLength) {
            System.out.println(best+" "+currentLength);
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
        TspPruning.n = n;
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
