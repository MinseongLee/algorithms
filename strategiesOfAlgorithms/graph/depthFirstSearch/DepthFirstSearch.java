package strategiesOfAlgorithms.graph.depthFirstSearch;

public class DepthFirstSearch {
    //그래프의 인접 리스트 표현
    private static final int[][] adj = {
            {1,3,4},
            {2,4},
            {1,4},
            {1},
            {2},
    };
    public static void main(String[] args) {
        dfsAll();
    }
    public static void dfs(int here,boolean[] visited){
        System.out.println("DFS visits "+here);
        visited[here] = true;
        //모든 인접 정점을 순회하면서
        for (int i = 0; i < adj[here].length; i++) {
            int there = adj[here][i];
            //아직 방문한 적 없다면 방문한다.
            if (!visited[there]){
                dfs(there,visited);
            }
        }
        //더이상 방문할 정점이 없으니, 재귀 호출을 종료하고 이전 정점으로 돌아간다.
    }
    //visit every vertex
    public static void dfsAll(){
        //visited를 모두 false로 초기화한다.
        boolean[] visited = new boolean[adj.length];
        //모든 정점을 순회하면서, 아직 방문한 적 없으면 방문한다.
        for (int i = 0; i < adj.length; i++) {
            if (!visited[i]){
                dfs(i,visited);
            }
        }
    }

}
