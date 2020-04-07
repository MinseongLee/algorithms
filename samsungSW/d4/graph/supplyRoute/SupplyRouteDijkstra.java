package samsungSW.d4.graph.supplyRoute;

import java.util.*;

/**
 * brute force algorithm을 dynamic programming 으로 사용하기 위해서,
 * 처음 top-down하는 길이 최적이어야만 한다. 그 걸어간 자취가 최적이어야만,
 * 그 전에 저장된 dp[x][y]에 있는 값을 꺼내와서 사용할 수 있는 것이다.
 * 이 값이 최적이 아니라면, 재사용을 할 수가 없다.
 * 만약 걸어간 길이 최적이라면, 사용 가능.
 * (즉, dp를 사용하기 위해 걸어간 자취를 최적으로 바꾸어줘야만 한다.)
 ***이건 dp로 절대 못푼다**
 *
 * 다익스트라 알고리즘으로 풀었다.
 */

public class SupplyRouteDijkstra {
    private static final int INF = Integer.MAX_VALUE;
    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, -1, 1, 0};
    private static int[][] dp;
    private static boolean[][] visited;

    public static int solution(int[][] supply) {
        int xSize = supply.length,ySize = supply[0].length;
        dp = new int[xSize][ySize];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
//        visited = new boolean[xSize][ySize];
        /*for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                System.out.print(" " + dp[i][j]);
            }
            System.out.println();
        }*/
//        return shortestPath(supply, 0, 0);
        return shortestPath(supply, 0, 0);
        //return shortestPathBottom(supply);
    }
    private static int dijkstra(int[][] supply){
        int xSize = supply.length,ySize=supply[0].length;
        int[][] dist = new int[xSize][ySize];
        //init
        PriorityQueue<Route> pq = new PriorityQueue<>(new Comparator<Route>() {
            @Override
            public int compare(Route o1, Route o2) {

//                return o1.depth-o2.depth;
                return Integer.compare(o1.depth,o2.depth);
            }
        });
        for (int i = 0; i < dist.length; i++) {
            Arrays.fill(dist[i],INF);
        }
        dist[0][0] = supply[0][0];
        pq.add(new Route(0,0,supply[0][0]));
        while(!pq.isEmpty()){
            Route route = pq.poll();
            //System.out.println("x="+route.x+" y="+route.y+" dist="+dist[route.x][route.y]+" route.depth="+route.depth);
            //만약 지금 꺼낸 것보다 더 짧은 경로를 알고 있다면 지금 꺼낸 것을 무시
            if (dist[route.x][route.y]<route.depth) continue;
            //인접한 정점 모두 검사
            for (int i = 0; i < dx.length; i++) {
                int nx = dx[i]+route.x;
                int ny = dy[i]+route.y;
                if (nx>=0 && ny>=0&&nx<xSize&&ny<ySize){
                    int nextDist = route.depth+supply[nx][ny];
                    //System.out.println("nextDist="+nextDist+" dist="+dist[nx][ny]+ " nx="+nx+" ny="+ny);
                    //더 짧은 경로를 발견하면 dist[]를 갱신하고 우선순위 큐에 넣는다.
                    if (dist[nx][ny]>nextDist){
                        dist[nx][ny] = nextDist;
                        pq.add(new Route(nx,ny,nextDist));
                    }
                }
            }
        }
        /*for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[i].length; j++) {
                System.out.print(dist[i][j]+" ");
            }
            System.out.println();
        }*/
        return dist[xSize-1][ySize-1];
    }

    private static int bfs(int[][] supply){
        int xSize = supply.length,ySize = supply[0].length;
        int[][] dist = new int[xSize][ySize];
        boolean[][] visited = new boolean[xSize][ySize];
        Queue<Pair> q = new LinkedList<>();
        visited[0][0] = true;
        dist[0][0] = supply[0][0];
        q.add(new Pair(0,0));
        List<Route> compare = new ArrayList<>();
        while(!q.isEmpty()){
            Pair here = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i]+here.x;
                int ny = dy[i]+here.y;
                if (nx>=0&&ny>=0&&nx<xSize&&ny<ySize){
                    if (!visited[nx][ny]){
                        visited[nx][ny] = true;
                        compare.add(new Route(nx,ny,supply[nx][ny]));
                    }
                }
            }
            if (!compare.isEmpty()){
                Collections.sort(compare, new Comparator<Route>() {
                    @Override
                    public int compare(Route o1, Route o2) {
                        return o1.depth-o2.depth;
                    }
                });
                for (int i = 0; i < compare.size(); i++) {
                    int x = compare.get(i).x;
                    int y = compare.get(i).y;
                    q.add(new Pair(x,y));
                    dist[x][y] = dist[here.x][here.y]+compare.get(i).depth;
                }
                compare.clear();
            }
        }
        System.out.println("dist");
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < ySize; j++) {
                System.out.print(" "+dist[i][j]);
            }
            System.out.println();
        }
        return dist[xSize-1][ySize-1];
    }
    //틀린 로직. 위와 오른쪽으로 가는 동선을 고려 안함.
    private static int shortestPathBottom(int[][] supply){
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i],0);
        }
        for (int i = 0; i < supply.length; i++) {
            for (int j = 0; j < supply[i].length; j++) {
                if (i-1<0 && j-1>=0){
                    if (dp[i][j-1]==INF) dp[i][j-1] = 0;
                    dp[i][j] = dp[i][j-1]+supply[i][j];
                }
                else if (j-1<0 && i-1>=0){
                    if (dp[i-1][j]==INF) dp[i-1][j] = 0;
                    dp[i][j] = dp[i-1][j]+supply[i][j];
                }
                //i-1>=0, j-1>=0
                else if (i-1>=0 &&j-1>=0){
                    if (dp[i][j-1]==INF && dp[i-1][j]==INF) {
                        dp[i][j-1] = 0;
                        dp[i-1][j] = 0;
                    }
                    dp[i][j] = Math.min(dp[i][j-1],dp[i-1][j])+supply[i][j];
                }
            }
        }
        return dp[supply.length-1][supply[0].length-1];
    }
    //brute force는 값이 정확하게 맞다.
    //brute force 로 하면, 값이 아주 잘나온다. 그런데 dp는.. 뭐가 문제지? (dp[x][y]의 값이 부분최적구조가 아니므로,)
    //이렇게 recursive 하게 로직을 짠다면 n이 아주 커질때, ex)100정도.
    //stack overflow가 나온다.
    private static int shortestPath(int[][] supply, int x, int y) {
//        System.out.println("x=" + x + " y=" + y);

        // base case last
        if (x == supply.length - 1 && y == supply[0].length - 1) {
//            System.out.println("x="+"y="+y);
            return supply[x][y];
        }
        /*if (x == 0 && y == 0) {
            System.out.println("x="+"y="+y);
            return 0;
        }*/

        //dp[x][y]에 저장된 값이 최적이 아니다. 그러므로, 값의 오류가 날수밖에 없는 것이다.
        // 문제점이 여기에있다.
        // cache
        /*if (dp[x][y] != -1) {
            System.out.println("visited");
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp.length; j++) {
                    System.out.print(" " + visited[i][j]);
                }
                System.out.println();
            }
            System.out.println("dp");
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp.length; j++) {
                    System.out.print(" " + dp[i][j]);
                }
                System.out.println();
            }
            System.out.println("x="+x+" y="+y+" dp[x][y]="+dp[x][y]);
            return dp[x][y];
        }*/

//        dp[x][y] = INF;
//        int ret = INF;
        for (int i = 0; i < dx.length; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            //base case border
            if (nx >= 0 && ny >= 0 && nx < supply.length && ny < supply[0].length) {

                //그리고, 0부터 내려가는 것이 아니라, 올라가니, 값이 정확하게 떨어진다.
                if (!visited[nx][ny]){
                    visited[x][y] = true;
//                    System.out.println("be dp[x][y]=" + dp[x][y]+" x="+x+" y="+y+" nx="+nx+" ny="+ny+" i="+i);
                    int ans = shortestPath(supply, nx, ny)+ supply[x][y];
                    if (dp[x][y]==-1||dp[x][y]>ans){
                        dp[x][y] = ans;
                    }
//                    dp[x][y] = Math.min(dp[x][y], shortestPath(supply, nx, ny)+ supply[x][y]);
//                    ret = Math.min(ret, shortestPath(supply, nx, ny) + supply[x][y]);
                    visited[x][y] = false;
//                    System.out.println("after dp[x][y]=" + dp[x][y]+" x="+x+" y="+y+" nx="+nx+" ny="+ny+" i="+i);
                }
            }
        }
//        System.out.println("x="+x+" y="+y+" dp[x][y]="+dp[x][y]);
        return dp[x][y];
//        return ret;
    }

    public static void main(String[] args) {
        /*int[][] supply = {
                {0, 1, 0, 2},
                {1, 1, 1, 0},
                {0, 0, 4, 1},
                {1, 0, 1, 0},
        };*/
        int[][] supply = {
                {0, 2,1},
                {1, 1,1},
                {2, 0,0},
        };
        /*int[][] supply = {
                {0, 5,0},
                {1, 8,0},
                {7, 0,0},
        };*/
        int[][] su = new int[100][100];
        int k =0;
        for (int i = 0; i < su.length; i++) {
            for (int j = 0; j < su[i].length; j++,k++) {
                su[i][j] = k;
                if (k==10) k =0;
            }
        }
        su[99][99] = 0;
        System.out.println(bfs(supply));
//        su[89][89] = 0;
        System.out.println(solution(supply));
        System.out.println("dijkstra(supply)="+dijkstra(supply));

        /*for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                System.out.print(" " + dp[i][j]);
            }
            System.out.println();
        }*/
//        System.out.println(shortestPathBottom(supply));
//        String b = "0119106499149727823509372941266299104330866247632843793434381778642504456145446940923864180615766475";
//        System.out.println(b.length());
    }
}
