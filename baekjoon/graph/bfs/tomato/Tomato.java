package baekjoon.graph.bfs.tomato;

import java.util.*;

public class Tomato {
    private static final int[] dx ={-1,0,0,1};
    private static final int[] dy ={0,-1,1,0};
    private static final int[] dh = {-1,1};
    public int sol(int n,int m,int t,int[][][] box){
        //처음부터 다 익은 경우.
        boolean ok = false;
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (box[i][j][k]==0){
                        ok = true;
                        break;
                    }
                }
                if (ok) break;
            }
            if (ok) break;
        }
        //0이 없단 의미 이므로, 0
        if (!ok) return 0;
        //start지점 부터 만들 것.
        List<Pair> start = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (box[i][j][k]==1){
                        start.add(new Pair(i,j,k));
                    }
                }
            }
        }
        return bfs(start,box);
    }

    private int bfs(List<Pair> start, int[][][] box) {
        int h = box.length;
        int n = box[0].length;
        int m =box[0][0].length;
        //크기가 너무 크므로, visited을 제외하고 하자.
//        boolean[][][] visited = new boolean[h][n][m];
        int[][][] dist = new int[h][n][m];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dist[i][j],-1);
            }
        }
        Queue<Pair> q = new LinkedList<>();
        for (int i = 0; i < start.size(); i++) {
            q.add(new Pair(start.get(i).h,start.get(i).x,start.get(i).y));
            dist[start.get(i).h][start.get(i).x][start.get(i).y] = 0;
        }
        while(!q.isEmpty()){
            Pair here = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = here.x+dx[i];
                int ny =here.y+dy[i];
                if (nx>=0&&ny>=0&&nx<n&&ny<m){
                    if (dist[here.h][nx][ny]==-1&&box[here.h][nx][ny]==0){
                        dist[here.h][nx][ny] = dist[here.h][here.x][here.y]+1;
                        q.add(new Pair(here.h,nx,ny));
                    }
                }
            }
            for (int i = 0; i < 2; i++) {
                int nh = here.h+dh[i];
                if (nh>=0&&nh<h){
                    if (dist[nh][here.x][here.y]==-1&&box[nh][here.x][here.y]==0){
                        dist[nh][here.x][here.y] = dist[here.h][here.x][here.y]+1;
                        q.add(new Pair(nh,here.x,here.y));
                    }
                }
            }
        }
        //다 처리 후, 모두 익지 않은 경우 -1,
        // 모두 익은 상태 max
        int maxTomato = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (maxTomato<dist[i][j][k]){
                        maxTomato = dist[i][j][k];
                    }
                    else if (dist[i][j][k]==-1&&box[i][j][k]==0){
                        return -1;
                    }
                }
            }
        }
        return maxTomato;
    }

    public static void main(String[] args) {
        int n = 3;
        int m =5;
        int t = 2;
        int[][][] list ={
                {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0},},
                {{0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 0, 0},},
        };
        Tomato tomato = new Tomato();
        System.out.println(tomato.sol(n,m,t,list));

    }
}
