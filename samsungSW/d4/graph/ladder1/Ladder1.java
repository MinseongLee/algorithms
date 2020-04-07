package samsungSW.d4.graph.ladder1;

import samsungSW.d4.graph.ladder2.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1210. [S/W 문제해결 기본] 2일차 - Ladder1
 *
 * bfs
 * 좌우 방향 이동,
 * 다시 아래 방향으로만. 즉, 좌우가 나타나면 아래방향보다 우선권을 가짐.
 *
 * 도착지점이 2로 표시가 되어있다.
 */
public class Ladder1 {
    //양옆이 우선권이 있으므로, 양옆부터.
    private static final int[] dy = {0,0};
    private static final int[] dx = {-1,1};

    //암시적 그래프.
    public static int gotoNum2(int[][] ladder){
        int n = ladder.length;
        for (int i = 0; i < n; i++) {
            //여기서는 마지막에 ladder[n][i]가 2인지점에 도착
            if (ladder[0][i]==1){
                if (bfs(ladder,i)){
                    return i;
                }
            }
        }
        //없다는 소리.
        return -1;
    }

    private static boolean bfs(int[][] ladder, int start) {
        int n = ladder.length;
        Queue<Pair> q= new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        q.add(new Pair(0,start));
        while(!q.isEmpty()){
            Pair here = q.poll();
//            System.out.println(q.size());
            boolean checkSide = true;
            //양옆 먼저 확인 후
            for (int i = 0; i < 2; i++) {
                int ny = dy[i]+here.getY();
                int nx = dx[i]+here.getX();
                if (ny>=0&&nx>=0&&ny<n&&nx<n){
                    if (ladder[ny][nx]==1&&!visited[ny][nx]){
                        visited[ny][nx] = true;
                        q.add(new Pair(ny,nx));
                        checkSide =false;
                    }
                }
            }
            //무조건 아래로 (양 옆이 없을 경우만 아래로 갈수있다.)
            if (checkSide){
                int ny = here.getY()+1;
                int nx = here.getX();
                if (ny<n){
                    if (ladder[ny][nx]==1&&!visited[ny][nx]){
                        visited[ny][nx] = true;
                        q.add(new Pair(ny,nx));
                    }
                    if (ladder[ny][nx]==2){
                        return true;
                    }
                }
            }
        }
        //맨 마지막에 도달했을 때가 정확한 값.
        //그 중 최소값.
        return false;
    }

    public static void main(String[] args) {
        int[][] ladder = {
                {1,0,1,0,1},
                {1,0,1,0,1},
                {1,0,1,1,1},
                {1,0,1,0,1},
                {1,0,2,0,1},
        };
        System.out.println(gotoNum2(ladder));
    }
}
