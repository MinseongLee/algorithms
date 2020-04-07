package samsungSW.d4.bruteforce.miro1;

import java.util.Arrays;

/**
 * s(1,1) -> e(13,13)
 * 갈 수 있는 길이 있는 지 boolean - 1 or 0 으로 표시
 * 1 : wall
 * 2 : start
 * 3 : end
 * 0 : path
 */
public class Miro1 {
    private static final int[] dx = {-1,0,0,1};
    private static final int[] dy = {0,-1,1,0};
    private static final int START = 2;
    private static final int END = 3;
    private static final int WALL = 1;
    private static final int WAY = 0;
    private static boolean[][] visited = new boolean[16][16];
    public static int findPath(int[][] miro,int x,int y){

        //base case - border
        if (x<0||y<0||x>=miro.length||y>=miro[0].length){
            return 0;
        }
        //base case - false
        if (miro[x][y]==WALL){
            return 0;
        }
        //base case - true
        if (miro[x][y]==END){
            return 1;
        }
        for (int i = 0; i < dx.length; i++) {
            int nx = dx[i]+x;
            int ny = dy[i]+y;
            if (!visited[nx][ny]){
                visited[x][y] = true;
                if (findPath(miro,nx,ny)==1){
                    return 1;
                }
                visited[x][y] = false;
            }

        }
        return 0;
    }

    public static void main(String[] args) {
        int[][] miro = {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,2,0,0,0,0,0,0,0,0,0,0,1,0,1,1},
                {1,0,1,1,1,1,1,1,1,1,1,0,1,0,1,1},
                {1,0,0,0,0,0,0,0,1,0,0,0,1,0,1,1},
                {1,1,1,1,1,1,1,0,1,0,1,1,1,0,1,1},
                {1,0,0,0,1,0,0,0,1,0,0,0,0,0,1,1},
                {1,0,1,1,1,0,1,1,1,1,1,1,1,0,1,1},
                {1,0,1,0,0,0,1,0,0,0,0,0,1,0,1,1},
                {1,0,1,0,1,1,1,0,1,1,1,0,1,0,1,1},
                {1,0,1,0,0,0,0,0,1,0,1,0,1,3,1,1},
                {1,0,1,1,1,1,1,1,1,0,1,0,1,1,1,1},
                {1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,1},
                {1,0,1,1,1,0,1,0,1,1,1,1,1,0,1,1},
                {1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        };
        System.out.println(findPath(miro,1,1));
        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i],false);
        }
    }
}
