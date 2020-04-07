package samsungSW.d4.graph.miro2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1,1 -> 13,13
 * 1 or 0
 */
public class Miro2 {
    private static final int[] dx = {-1,0,0,1};
    private static final int[] dy = {0,-1,1,0};
    private static final int WALL = 1;
    private static final int WAY = 0;
    private static final int START = 2;
    private static final int END = 3;
    //bfs로 그냥 모든 0을 방문하면 된다.
    public static int sol(int[][] miro){
        for (int i = 0; i < miro.length; i++) {
            for (int j = 0; j < miro[i].length; j++) {
                if (miro[i][j]==START){
                    if (bfs(new Pair(i,j),miro)){
                        return 1;
                    }else{
                        return 0;
                    }
                }
            }
        }
        return 0;
    }

    private static boolean bfs(Pair start, int[][] miro) {
        int n = miro.length;
        int m = miro[0].length;
        boolean[][] visited = new boolean[n][m];
        Queue<Pair> q= new LinkedList<>();
        visited[start.x][start.y] = true;
        q.add(start);
        while(!q.isEmpty()){
            Pair here = q.poll();
            if (miro[here.x][here.y]==END) return true;
            for (int i = 0; i < 4; i++) {
                int nx = here.x+dx[i];
                int ny = here.y+dy[i];
                if (nx>=0&&ny>=0&&nx<n&&ny<m){
                    if (!visited[nx][ny]&&miro[nx][ny]!=WALL){
                        visited[nx][ny] = true;
                        q.add(new Pair(nx,ny));
                    }
                }
            }
        }
        //도달할 수 없어.
        return false;
    }
}
