package baekjoon.simulation.easy.robotCleaner;

/**
 * 간단한 내용을 난 왜이리 어렵게 생각했을 까..
 * <p>
 * 난 또.. 이 문제가 너무 설명이 부족하다고 생각한다.
 * 또한 문제의 제약조건을 느슨하게 생각하여 큰 그림을 그리는 것도 중요했다.
 */

public class RobotCleaner2 {
    //0-북,1-동,2-남,3-서 이 인덱스에 맞춰서 움직이는 값들.
    private static final int[] dx = {0, -1, 0, 1};
    private static final int[] dy = {-1, 0, 1, 0};
    /*private static final int[][] dx ={
            {0,1,0,-1},//북
            {-1,0,1,0},//동
            {0,-1,0,1},//남
            {1,0,-1,0},//서
    };
    private static final int[][] dy ={
            {-1,0,1,0},//북
            {0,-1,0,1},//동
            {1,0,-1,0},//남
            {0,1,0,-1},//서
    };*/
    private static final int[] direction = {3, 0, 1, 2};
    private static final int[] xBack = {1, 0, -1, 0};
    private static final int[] yBack = {0, -1, 0, 1};
    private int best;

    public int sol(int n, int m, int x, int y, int d, int[][] room) {
        clean(x, y, d, room);
        return best;
    }

    private void clean(int x, int y, int d, int[][] room) {
        // exception 처리하는 내용이다.
        if (room[x][y] == 1) return;
        if (room[x][y] == 0) {
            room[x][y] = 2;
            best++;
        }
        for (int i = 0; i < 4; i++) {
            int nx = dx[d] + x;
            int ny = dy[d] + y;
            if (room[nx][ny] == 0) {
                clean(nx, ny, direction[d], room);
                //내가 참고한 내용은 이 return;과.
                return;
            } else {
                d = direction[d];
            }
        }
        //검사끝.
        clean(x + xBack[d], y + yBack[d], d, room);
    }

    public static void main(String[] args) {
        int n = 11, m = 10, x = 7, y = 4, d = 0;
        int[][] list = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 1, 1, 1, 0, 1},
                {1, 0, 0, 1, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };
        RobotCleaner2 robotCleaner2 = new RobotCleaner2();
        System.out.println(robotCleaner2.sol(n, m, x, y, d, list));
    }
}
