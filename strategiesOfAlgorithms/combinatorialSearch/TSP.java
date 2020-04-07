package strategiesOfAlgorithms.combinatorialSearch;

import java.util.ArrayList;
import java.util.List;

public class TSP {
    private int count = 0;
    public static void main(String[] args) {
        int n = 4; // 도시의 수
        // 두 도시 간의 거리를 저장하는 배열.
        //즉, A 가 B를 선택하면 i=1을 맞춰야만한다. 만약 A가 D를 선택하면 i=3을 맞춰야한다.
        double dist[][] = {
                {0.0000000000, 326.0008994586, 503.1066076077, 290.0250922998},
                {326.0008994586, 0.0000000000, 225.1785728436, 395.4019367384},
                {503.1066076077, 225.1785728436, 0.0000000000, 620.3945520632},
                {290.0250922998, 395.4019367384, 620.3945520632, 0.000000000}
        };
        TSP tsp = new TSP();
        System.out.println(tsp.solution(n,dist));
        //false 로 초기화.
        boolean[] visited = new boolean[5];
        System.out.println(visited[3]);
    }
    //돌아가는 경우까지 합하면 최소값이 맞다.
    // for 를 여러개 보단.. 일단 하나로 제대로 처리할 수 있는지부터
    // 확인을 해줘야한다.
    public double solution(int n, double[][] dist){
        // path 지금까지 만든 경로
        // visited 각 도시의 방문 여부
        // currentLength 지금까지 만든 경로의 길이
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[n];
        double currentLength = 0.000;
        //나머지 도시들을 모두 방문하는 경로들 중 가장 짧은 것의 길이를 반환한다.
        return shortestPath(path,visited,currentLength,n,dist);
    }

    private double shortestPath(List<Integer> path, boolean[] visited, double currentLength, int n, double[][] dist) {
        //basecase : 모든 도시를 다 방문했을 때는 시작 도시로 돌아가고 종료한다.
        System.out.println("currentLength = "+currentLength);
        //총 4개지만, 하나는 0이 포함된다는 가정이 있으므로, 3개의 합이 들어온다.
        if (path.size() == n) {
            count++;
            System.out.println("count = "+count);
            System.out.println("final currentLength = "+(currentLength + dist[path.get(0)][path.get(path.size()-1)]));
            // 만약에 돌아가는 경로를 추가하지 않으려면
            // dist[path.get(0)][path.get(path.size()-1)]를 제외하면 된다.*

            //********순열 로직이다. here은 path index의 전 index를 가지고 와서,
            //그 인덱스의 도시에 방문을 하고, (즉, 마지막 도시 - 전 도시)
            //여기에서 다시 되돌아가는 거리를 currentLength에 plus 해주는 것이다.
            //ex) here = 1, path={0,2,1,3} 이면 - currentLength+dist[here][i]
            //저 로직이 더해지고, recursion 에 의해, 이 함수에 다시 들어오고,
            //path.size()==n 이므로, 이 if 절에 들어와 출발 도시로
            // 다시 되돌아 갈 수 있는 것이다.
            return currentLength + dist[path.get(0)][path.get(path.size()-1)];
        }
        double ret = 993429348923.1231231; // 큰값으로 초기화
        //다음 방문할 도시를 전부 시도해 본다.
        //i는 next 이다.
        for (int i = 0; i < n; i++) {
            //true이면 방문을 했었던 장소이다.
            if (visited[i]) continue;
            // here 초기값 0을 주고, path.size()가 0이 아닐 시
            // path.get(i)의 최신값을 준다.
            int here = 0;
            if (path.size()!=0){
                System.out.println("i="+i+" path.size-1="+(path.size()-1));
                //i 와 path.size-1 과 다르다. why?
                //here은 i 에 방문하기 전의 값이므로 다를 수밖에 없다.
                here = path.get(path.size()-1);
            }
            path.add(i);
            System.out.println("here = "+here+" "+"path = "+path);

            visited[i] = true;
            double cand = shortestPath(path,visited,currentLength+dist[here][i],n,dist);
            //항상 최소값을 구하기 위해, recursive로 구해진 값을 지속적으로 비교한다.
            ret = Math.min(ret,cand);
            visited[i] = false;
            path.remove(Integer.valueOf(i));
        }
        return ret;
    }

}
