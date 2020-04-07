package samsungSW.d4.graph.ladder2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1211. [S/W 문제해결 기본] 2일차 - Ladder2
 *
 * default : 아래로 ,
 * 왼오가 나오면 방향 전환 무조건.
 *
 * 가장 짧은 이동거리 갖는 x좌표. -복수인 경우 가장 큰 x좌표
 * 0은 이동불가, 1로만 이동가능. 2는 도착
 * 100x100
 * 맨 아래 도착하면 2로 표시.
 *
 * bfs로 맨 처음 x좌표0,0 - 0,99 까지 다 방문.
 * 좌우 아래로만 갈 수 있다.
 */
public class Ladder2 {
    private static final int INF = 987654321;
    //양옆이 우선권이 있으므로, 양옆부터.
    private static final int[] dy = {0,0};
    private static final int[] dx = {-1,1};

    //암시적 그래프.
    public static int shortestPath(int[][] ladder){
        int n = ladder.length;
        int small = INF;
        int x = 0;
        for (int i = 0; i < n; i++) {
            if (ladder[0][i]==1){
                int tmp = bfs(ladder,i);
                int store = small;
                small = Math.min(small,tmp);
                //tmp와 비교를해야한다. small값이 아닌.
                if (store>tmp){
                    x = i;
                }
                else if (store==tmp && tmp!=INF){
                    x = Math.max(x,i);
                }
            }
        }
        return x;
    }

    private static int bfs(int[][] ladder, int start) {
        int n = ladder.length;
        int[][] dist = new int[n][n];
        //visited을 없애고 dist로 해결
        for (int i = 0; i < dist.length; i++) {
            Arrays.fill(dist[i],-1);
        }
        Queue<Pair> q= new LinkedList<>();
        q.add(new Pair(0,start));
        dist[0][start] = 1;
        while(!q.isEmpty()){
            Pair here = q.poll();
            boolean checkSide = true;
            //양옆 먼저 확인 후
            for (int i = 0; i < 2; i++) {
                int ny = dy[i]+here.getY();
                int nx = dx[i]+here.getX();
                if (ny>=0&&nx>=0&&ny<n&&nx<n){
                    if (ladder[ny][nx]==1&&dist[ny][nx]==-1){
                        dist[ny][nx]=dist[here.getY()][here.getX()]+1;
                        q.add(new Pair(ny,nx));
                        checkSide =false;
                    }
                }
            }
            //무조건 아래로 (양 옆이 없을 경우)
            if (checkSide){
                int ny = here.getY()+1;
                int nx = here.getX();
                if (ny<n){
                    if (ladder[ny][nx]==1&&dist[ny][nx]==-1){
                        dist[ny][nx] = dist[here.getY()][here.getX()]+1;
                        q.add(new Pair(ny,nx));
                    }
                }
            }
        }
        //맨 마지막에 도달했을 때가 정확한 값.
        //그 중 최소값.
        int min = INF;
        for (int i = 0; i < n; i++) {
            //0인경우와 -1인경우를 제외.
            if (dist[n-1][i]>0){
                System.out.println("i="+i+" "+dist[n-1][i]);
                min = Math.min(min,dist[n-1][i]);
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int[][] ladder = {
                {1,0,1,0,1},
                {1,0,1,0,1},
                {1,0,1,1,1},
                {1,0,1,0,1},
                {1,0,1,0,1},
        };
        System.out.println(shortestPath(ladder));
    }
}
