package baekjoon.graph.bfs.breakWall;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 2206. 벽 부수고 이동하기
 * 여기서 핵심은 먼가 변수를 추가할 떄, 하나의 객체에만 추가하는 것이 아니라,
 * 이 상태와 연결된 모든 객체, 자료구조등을 다 같이 변화시켜야한다.
 * ex) 이 문제에서,
 * 벽을 지나는 경우 or 안지나는 경우 두가지로 나누었다 처음에,
 * 그런데 나는 Queue만 변화시켰고, dist에는 적용하지 않았다.
 * 이 상태가 변화하는 것을 다른 객체도 그 변화에 맞춰서 값이 제대로 들어갈 수 있게
 * 방을 더 추가해줘야한다.**
 * ex) dist[x][y][방문or안방문]
 */
public class BreakWallBfsTwoChoice {
    private static final int INF = 987654321;
    private static final int[] dx = {-1,0,0,1};
    private static final int[] dy = {0,-1,1,0};
    //0 =way, 1=wall : 1은 최대 1개 부술수있음.
    public int sol(int n,int m,int[][] map){
        //벽을 부수지 않고 bfs
        return bfs(new Pair(0,0,0),map);
//        return bfsMap(new Pair(0,0,0),map);
    }
    //이렇게 풀수도 있지만.. 시간초과가 뜬다..
    private int bfsMap(Pair start,int[][] map){
        int n = map.length;
        int m =map[0].length;
        //map을 쓸때에는, visited을 쓰면안돼....
        //왜냐하면 각 상태들을 dist로 확인해야하거든.
        Map<Pair,Integer> dist = new HashMap<>();
        Queue<Pair> q= new LinkedList<>();
        dist.put(start,1);
        q.add(start);
        while (!q.isEmpty()) {
            Pair here = q.poll();
            if (here.x==n-1&&here.y==m-1) {
                return dist.get(here);
            }
            for (int i = 0; i < 4; i++) {
                int nx = here.x+dx[i];
                int ny = here.y+dy[i];
                if (nx>=0&&nx<n&&ny>=0&&ny<m){
                    Pair there = new Pair(nx,ny,here.broken);
                    if (dist.get(there)==null&&map[nx][ny]==0){
                        dist.put(there,dist.get(here)+1);
                        q.add(there);
                    }else if (dist.get(there)==null&&map[nx][ny]==1&&here.broken==0){
                        there.broken++;
                        dist.put(there,dist.get(here)+1);
                        q.add(there);
                    }
                }
            }
        }
        return -1;
    }
    // 양방향 탐색으로 구현.
    private int bfs(Pair start, int[][] map) {
        int n = map.length;
        int m =map[0].length;
        //벽에 방문 한 경우 or 안한 경우.
        //2개중 하나만 고르는 문제.
        int[][][] dist = new int[n][m][2];
        Queue<Pair> q= new LinkedList<>();
        dist[0][0][0] = 1;
        q.add(start);
//        int min =INF;
        while(!q.isEmpty()){
            Pair here = q.poll();
            if (here.x==n-1&&here.y==m-1) {
                //마지막에는 한가지 상태만 들어가므로, 이렇게 표현안해도 된다.
//                min = Math.min(min,dist[here.x][here.y][here.broken]);
                return dist[here.x][here.y][here.broken];
            }
            for (int i = 0; i < 4; i++) {
                int nx = here.x+dx[i];
                int ny = here.y+dy[i];
                if (nx>=0&&nx<n&&ny>=0&&ny<m){
                    // 0인경우 방문.
                    if (map[nx][ny]==0&&dist[nx][ny][here.broken]==0){
                        dist[nx][ny][here.broken] = dist[here.x][here.y][here.broken]+1;
                        q.add(new Pair(nx,ny,here.broken));
                    }
                    else if (map[nx][ny]==1&&dist[nx][ny][here.broken]==0&&here.broken==0){
                        dist[nx][ny][here.broken+1] = dist[here.x][here.y][here.broken]+1;
                        q.add(new Pair(nx,ny,here.broken+1));
                    }
                }
            }
        }
//        if (min==INF) return -1;
//        return min;
        return -1;
    }
    //x의 부호를 반환.
    private int sgn(int x) {
        return x>0?1:-1;
    }

    private int incr(int x) {
        if (x<0) return x-1;
        return x+1;
    }

    public static void main(String[] args) {
        int n = 6;
        int m = 4;
        int[][] map ={
                {0,1,0,0},
                {1,1,1,0},
                {1,0,0,0},
                {0,0,0,0},
                {0,1,1,1},
                {0,0,0,0},
        };
        BreakWallBfsTwoChoice breakWallBfsTwoChoice = new BreakWallBfsTwoChoice();
        System.out.println(breakWallBfsTwoChoice.sol(n,m,map));
    }
}
