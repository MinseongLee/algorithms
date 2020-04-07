package strategiesOfAlgorithms.graph.breadthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 두 정점 사이의 최단 경로를 찾을 때 사용할 수 있는 굉장히 간단하면서도 유용한 테크닉
 * 시작 정점에서 - 정방향 탐색
 * 목표 정점에서 - 역방향 탐색 : 동시 진행.
 * 둘이 가운데서 만나면 종료.
 *
 * 양방향 탐색 완벽하게 다 쓰진 않았다. 나중에 필요할 때, 보자.
 */

public class Bfs_bidirectionalSearch {
    private static final int[][] adj = {
            {1,2,3},
            {0,3},
            {0,3},
            {0,1,2},
    };
    //x의 부호를 반환한다.
    private static int sgn(int x){
        //0이 아닌 모든값 true, 0을 거짓
        if (x==0){
            return 0;
        }
        return x>0?1:-1;
    }
    //x의 절대값을 1 증가시킨다.
    private static int incr(int x){
        if (x<0){
            return x-1;
        }
        return x+1;
    }
    // start에서 finish까지 가는 최단 경로의 길이를 반환한다.
    public static int bidirectional(int start,int end){
        //각 정점까지의 최단 경로의 길이를 저장
        int[] dist = new int[adj.length];
        //앞으로 방문할 정점들
        Queue<Integer> q = new LinkedList<>();
        //시작 상태와 목표 상태가 같은 경우는 예외로 처리
        if (start==end) return 0;
        q.add(start);
        //0부터 시작하면 안된다. :정방향 양수: 역방향 음수
        dist[start] = 1;
        q.add(end);
        dist[end] = -1;
        //bfs
        while(!q.isEmpty()){
            int here = q.poll();
            //인접한 상태들 검사
            for (int i = 0; i < adj[here].length; i++) {

            }
        }
        return 0;
    }
    public static void main(String[] args) {

    }
}
