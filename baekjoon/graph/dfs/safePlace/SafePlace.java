package baekjoon.graph.dfs.safePlace;

/**
 * 무난했지만, exception 처리 h=0 부터 시작인것을 해주지 않아 한번 실패.
 */
public class SafePlace {
    private static final int[] dx= {-1,0,0,1};
    private static final int[] dy= {0,-1,1,0};
    private int best;
    public int sol(int n,int[][] map){
        int finish =0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j]>finish) finish = map[i][j];
            }
        }
        //base case 를 생각해서 0부터 시작.
        maxSpace(0,map,finish);
        return best;
    }
    //O(n^3)
    private void maxSpace(int h, int[][] map,int finish) {
        if (h>finish) return;
        int n = map.length;
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j]<=h){
                    visited[i][j] = true;
                }
            }
        }
        /*System.out.println("this=");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(visited[i][j]+" ");
            }
            System.out.println();
        }*/
        best = Math.max(best,dfsAll(visited));
        maxSpace(h+1,map,finish);
    }

    private int dfsAll(boolean[][] visited) {
        int ans =0;
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited.length; j++) {
                if (!visited[i][j]){
                    dfs(i,j,visited);
                    ans++;
                }
            }
        }
        return ans;
    }

    private void dfs(int i, int j, boolean[][] visited) {
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int nx= i+dx[k];
            int ny =j+dy[k];
            if (nx>=0&&ny>=0&&nx<visited.length&&ny<visited.length){
                if (!visited[nx][ny]){
                    dfs(nx,ny,visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        /*int n =5;
        int[][] map ={
                {6, 8, 2, 6, 2},
                {3, 2, 3, 4, 6},
                {6, 7, 3, 3, 2},
                {7, 2, 5, 3, 6},
                {8, 9, 5, 2, 7},
        };*/
        int n =2;
        int[][] map ={
                {1,1},
                {1,1},
        };
        SafePlace safePlace = new SafePlace();
        System.out.println(safePlace.sol(n,map));
    }
}
