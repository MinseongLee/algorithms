package baekjoon.graph.dfs.cabbage;

public class Cabbage {
    private static final int[] dx= {-1,0,0,1};
    private static final int[] dy= {0,-1,1,0};
    public int sol(int m,int n,int[][] edges){
        //m-가로, n-세로.
        int[][] adj = new int[n][m];
        makeGraph(adj,edges);
        return dfsAll(adj);
    }

    private int dfsAll(int[][] adj) {
        int componentCnt =0;
        int n = adj.length;
        int m = adj[0].length;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]&&adj[i][j]==1){
                    dfs(i,j,adj,visited);
                    componentCnt++;
                }
            }
        }
        return componentCnt;
    }

    private void dfs(int x, int y, int[][] adj,boolean[][] visited) {
        visited[x][y] = true;
        int n =adj.length;
        int m = adj[0].length;
        for (int i = 0; i < 4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if (nx>=0&&ny>=0&&nx<n&&ny<m){
                if (!visited[nx][ny]&&adj[nx][ny]==1){
                    dfs(nx,ny,adj,visited);
                }
            }
        }
    }

    private void makeGraph(int[][] adj, int[][] edges) {
        int n = edges.length;
        for (int i = 0; i < n; i++) {
            adj[edges[i][1]][edges[i][0]]=1;
        }
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 2;
        int k = 2;
        int[][] edges = {
                {0,0},
                {2,1},
        };
        Cabbage cabbage = new Cabbage();
        System.out.println(cabbage.sol(m,n,edges));
    }
}
