package strategiesOfAlgorithms.graph.breadthFirstSearch;

import java.util.*;

/**
 * bfs는 대개 시작점으로부터 다른 정점들까지의 거리를 구하기 위해 사용된다.
 */

public class BreadthFirstSearch {
    //graph의 인접 리스트 표현
    private final int[][] adj = {
            {1,3,4},
            {0,2,3,4},
            {1,4},
            {0,1},
            {0,1,2},
    };

    /**
     * start에서 시작해 그래프를 너비 우선 탐색하고 시작점부터 각 정점까지의
     * 최단 거리와 너비 우선 탐색 스패닝 트리를 계산
     * distance[i]=start부터 i까지의 최단 거리
     * parent[i]=너비 우선 탐색 스패닝 트리에서 i의 부모의 번호.
     * 루트인 경우 자신의 번호
     */
    public void bfs2(int start,int[] distance,int[] parent){

        Queue<Integer> q= new LinkedList<>();
        distance[start] = 0;
        parent[start] = start;
        q.add(start);
        while(!q.isEmpty()){
            int here = q.poll();
            //here의 모든 인접한 정점을 검사
            for (int i = 0; i < adj[here].length; i++) {
                int there = adj[here][i];
                //처음 보는 정점이면 방문 목록에 넣는다.
                if (distance[there]==-1){
                    q.add(there);
                    distance[there] = distance[here]+1;
                    parent[there] = here;
                }
            }
        }
    }
    //v로부터 시작점까지의 최단 경로를 계산
    public List<Integer> shortestPath(int v,int[] parent){
        List<Integer> path = new ArrayList<>();
        path.add(v);
        while(parent[v]!=v){
            v = parent[v];
            path.add(v);
        }
        reverse(path);
        return path;
    }

    private void reverse(List<Integer> path) {
        List<Integer> reverse = new ArrayList<>();
        for (int i = path.size()-1; i >=0 ; i--) {
            reverse.add(path.get(i));
        }
        path = reverse;
    }


    //start에서 시작해 그래프를 너비 우선 탐색하고 각 정점의 방문 순서를 반환
    public List<Integer> bfs(int start){
        //각 정점의 방문여부
        boolean[] discovered = new boolean[adj.length];
        //방문할 정점 목록을 유지하는 큐
        Queue<Integer> q = new LinkedList<>();
        //정점의 방문 순서
        List<Integer> order = new ArrayList<>();
        discovered[start] = true;
        q.add(start);
        while (!q.isEmpty()) {
            int here = q.poll();
            //here을 방문
            order.add(here);
            // 모든 인접한 정점을 검사
            for (int i = 0; i < adj[here].length; i++) {
                int there = adj[here][i];
                //처음 보는 정점이면 방문 목록에 집어넣는다.
                if (!discovered[there]){
                    q.add(there);
                    discovered[there] = true;
                }
            }
        }
        return order;
    }

    public static void main(String[] args) {
        BreadthFirstSearch bfs = new BreadthFirstSearch();
        System.out.println(bfs.bfs(0));
        int[] distance = new int[5];
        int[] parent = new int[5];
        Arrays.fill(distance,-1);
        Arrays.fill(parent,-1);
        bfs.bfs2(0,distance,parent);
        int v = 2;
        System.out.println(bfs.shortestPath(v,parent));
    }
}
