package baekjoon.graph.dfs.searchPath;

/**
 * base case를 잘못지정해서 틀렸었다.
 * i==j가 같은 지점으로 놓으면, 0,0에 방문할 수 없더라도 방문을 하게된다.
 * 그러므로, base case 를 놓지 않고,
 * start와 here을 넘겨, here이 방문하는 곳(j)은 start도 방문할 수 있으므로,
 * start와 here을 방문처리해준다. start->j(there), here->j(there)
 */
public class SearchPath {
    public void sol(int n,int[][] adj){
        //i->j 경로 있는지 확인.
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ans[i][j]==0){
                    boolean[][] visited = new boolean[n][n];
                    dfs(i,i,visited,ans,adj);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (j>0) sb.append(" ");
                sb.append(ans[i][j]);
            }
            System.out.println(sb.toString());
        }
    }

    private void dfs(int here,int start, boolean[][] visited, int[][] ans,int[][] adj) {
        //잘못된 base case. i==j 가 같은 경우에 무조건 1처리를 해주므로, 잘못된 값을 반환.
//        if (here==end) {
//            ans[start][end] = 1;
//            return;
//        }
        for (int there = 0; there < adj[here].length; there++) {
            if (!visited[here][there]&&adj[here][there]==1){
                ans[here][there] = 1;
                ans[start][there] = 1;
                visited[here][there] = true;
                visited[start][there] = true;
                dfs(there,start,visited,ans,adj);
            }
        }
    }

    public static void main(String[] args) {
        int n =7;
        int[][] adj = {
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0},
                {1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0},
        };
        SearchPath searchPath = new SearchPath();
        searchPath.sol(n,adj);
    }
}
