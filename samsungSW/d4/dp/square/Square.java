package samsungSW.d4.dp.square;

import java.util.LinkedList;
import java.util.Queue;

//bfs는 시간초과나서 못푼다.. dp..
//그래서 dp로 풀었다.
public class Square {
    private static final int[] dx= {-1,0,0,1};
    private static final int[] dy= {0,-1,1,0};
    private static int[][] dp;
    public static int[] plusOneDp(int[][] square){
        int n = square.length;
        dp = new int[n][n];
        int max = 0;
        int pos = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //max값을 찾는 동시에 그 max값을 가지고 있는 pos값도 같이 찾기.
                int tmp = searchBiggest(square,i,j);
                int store = max;
                max = Math.max(max,tmp);
                //무조건 max값에 있는 pos값 넣기.
                if (store<tmp){
                    pos = square[i][j];
                }
                //max값이 같다면 더 작은 pos값을 넣기.
                else if (store==tmp){
                    pos = Math.min(pos,square[i][j]);
                }
            }
        }
        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }*/
        int[] ans =new int[2];
        ans[0] = pos;
        ans[1] = max;
        return ans;
    }

    private static int searchBiggest(int[][] square, int x, int y) {
        if (dp[x][y]>0) return dp[x][y];
        //default가 -1일 수도 있으므로, 1로 주고 시작할 수도 있다.
        dp[x][y]=1;
        for (int i = 0; i < 4; i++) {
            int nx = dx[i]+x;
            int ny = dy[i]+y;
            if (nx>=0&&ny>=0&&nx<square.length&&ny<square.length){
                if (square[x][y]+1==square[nx][ny]){
//                    System.out.println("nx="+nx+" ny="+ny);
//                    dp[x][y] = Math.max(dp[x][y],searchBiggest(square,nx,ny)+1);
                    dp[x][y] += searchBiggest(square,nx,ny);
//                    System.out.println("x="+x+" y="+y+" "+dp[x][y]);
                }
            }
        }
        //여기서 그냥 1이 아니라 dp에 1을 주고 리턴을 해야지.
//        dp[x][y]++;
        return dp[x][y];
    }

    /**
     * N^2,
     * 1<=Aij<=N^2
     * 이동하려면 +1 상하좌우
     * 처음 어떤 수가 적힌 방에서 있어야 가장 많은 개수의 방을 이동할 수 있는지
     * bfs 문제.
     */
    public static int[] gotoPlusOne(int[][] square){
        // 여러번 방문해야하므로, 꼭 초기화해줘야한다.
        int n = square.length;
        int max = 0;
        int pos = Integer.MAX_VALUE;
        int[][] maxList = new int[n][n];
        //모든 방에 대해서 bfs를 시행한다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                maxList[i][j] = bfs(square,i,j);
                //max = Math.max(max,maxList[i][j]);
                if (checkSearch(square,i,j)){
                    int tmp = bfs(square,i,j);
                    int store = max;
                    max = Math.max(max,tmp);
                    if (store<tmp){
                        pos = square[i][j];
                    }
                    else if (store==tmp){
                        pos = Math.min(pos,square[i][j]);
                    }
                }
            }
        }

        //max값과 같은 가장 작은 수 찾기.
        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (max==maxList[i][j]){
                    pos = Math.min(pos,square[i][j]);
                }
            }
        }*/
        int[] ans = new int[2];
        ans[0] = pos;
        ans[1] = max;
        return ans;
    }

    private static boolean checkSearch(int[][] square, int x, int y) {
        int n = square.length;
        for (int i = 0; i < 4; i++) {
            int nx = dx[i]+x;
            int ny = dy[i]+y;
            if (nx>=0&&ny>=0&&nx<n&&ny<n){
                return true;
            }
        }
        return false;
    }

    private static int bfs(int[][] square, int x, int y) {
        int n = square.length;
        boolean[][] visited = new boolean[n][n];
        int[][] dist = new int[n][n];
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x,y));
        dist[x][y] = 1;
        visited[x][y] = true;
        int max = dist[x][y];
        while(!q.isEmpty()){
            Pair here = q.poll();
            for (int i = 0; i < dx.length; i++) {
                int nx = dx[i]+here.getX();
                int ny = dy[i]+here.getY();
                if (nx>=0&&ny>=0&&nx<n&&ny<n){
                    if (!visited[nx][ny] && square[here.getX()][here.getY()]+1==square[nx][ny]){
                        visited[nx][ny] = true;
                        q.add(new Pair(nx,ny));
                        dist[nx][ny] = dist[here.getX()][here.getY()]+1;
                        max = Math.max(max,dist[nx][ny]);
                    }
                }
            }
        }
        return max;
    }
    // graph를 안 만들고 암시적 그래프로 풀자.
    public static void main(String[] args) {
        int n =8; //<=1,000
        int[][] square = {
                {38, 39, 40, 41, 42, 43, 44, 45 },
                {46, 47, 48, 49, 50, 51, 52, 53 },
                {54, 55, 56, 57, 58, 59, 60, 61 },
                {62, 63, 64, 1, 2, 3, 4, 5 },
                {6, 7, 8, 9, 10, 11, 12, 13 },
                {14, 15, 16, 17, 18, 19, 20, 21 },
                {22, 23, 24, 25,26, 27, 28, 29 },
                {30, 31, 32, 33, 34, 35, 36, 37 },
        };
        /*int[][] square = {
                {9,3,4},
                {6,1,5},
                {7,8,2},
        };*/
        int[] ans = gotoPlusOne(square);
        System.out.println(ans[0]+" "+ans[1]);
        int[] ans2 = plusOneDp(square);
        System.out.println(ans2[0]+" "+ans2[1]);
    }
}
