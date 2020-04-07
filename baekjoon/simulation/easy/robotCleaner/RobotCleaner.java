package baekjoon.simulation.easy.robotCleaner;

/**
 * 틀린 로직..
 */

public class RobotCleaner {
    //0-북,1-동,2-남,3-서 이 인덱스에 맞춰서 움직이는 값들.
    private static final int[] dx = {0, -1, 0, 1};
    private static final int[] dy = {-1, 0, 1, 0};
    //항상 왼쪽으로 간다.
    private static final int[] direction = {3, 0, 1, 2};
    private static final int[] xBack = {1, 0, -1, 0};
    private static final int[] yBack = {0, -1, 0, 1};
    private int best = 1;

    public int sol(int n, int m, int x, int y, int d, int[][] room) {
        int[][] visited = new int[n][m];
        int a = cntCleanRoom(x, y, d, visited, room);
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] > 0) cnt++;
            }
        }
        System.out.println(cnt);
        System.out.println(best);
        return a;
    }

    private int cntCleanRoom(int x, int y, int d, int[][] visited, int[][] room) {
        int ans = 0;
        if (visited[x][y]==0&&room[x][y]==0){
            visited[x][y] = 1;
            ans++;
            best++;
        }

        for (int i = 0; i < 4; i++) {
            //            System.out.println(x+" "+y);
            int nx = dx[d] + x;
            int ny = dy[d] + y;
            //청소할 수 있는 공간이면 그쭉으로 이동하며 +1
            if (visited[nx][ny] == 0 && room[nx][ny] == 0) {

//                    System.out.println(ans);
                ans += cntCleanRoom(nx, ny, direction[d], visited, room);
            }
            //청소공간이 아니라면,
            else {
                //방향을 바꾼다.
                d = direction[d];
            }
        }
        //그 정점 방문 할때마다 4방향 체크. 모두 방문되었는지.
        //true이면 다 방문했단의미.
        if (isAllVisited(x, y, visited, room)) {
            //true이면    뒤로 한칸 갈수있단 의미.
            if (isBack(x, y, d, room, visited)) {//그후 4방향만 체크.
                x += xBack[d];
                y += yBack[d];
                ans += cntCleanRoom(x,y,d,visited,room);
            }
            //갈수없으므로, return
            else {
                System.out.println("ans="+ans);
                return ans;
//                    break;
            }
        }
        /*for (int i = 0; i < 4; i++) {
            int nx= dx[d]+x;
            int ny=dy[d]+y;
            if (nx>=0&&ny>=0&&nx<room.length&&ny<room[0].length){
                //청소할 수 있는 공간이면 그쭉으로 이동하며 +1
                if (visited[nx][ny]==0&&room[nx][ny]==0){
//                    System.out.println(ans);
                    ans = cntCleanRoom(nx,ny,direction[d],visited,room)+1;
                }
                //청소공간이 아니라면,
                else{
                    //방향을 바꾼다.
                    d = direction[d];
                }
            }
            //4군데 다 돌았는데 막혀있다면, 뒤로 후진후 2번반복.
            if (i==3){
                nx = x+xBack[d];
                ny = y+yBack[d];
                if (nx>=0&&ny>=0&&nx<room.length&&ny<room[0].length){
                    if (room[nx][ny]==0){
                        x = nx;
                        y = ny;
                        i=-1;
                    }else{
                        //만약 바로 뒤가 1이라면 작동을 멈출 것.
                        return ans;
                    }
                }
            }
        }*/
        return ans;
    }

    private boolean isBack(int x, int y, int d, int[][] room, int[][] visited) {
        int nx = x + xBack[d];
        int ny = y + yBack[d];
        //후진하는 칸은 항상 빈칸이어야만한다.
        if (room[nx][ny] == 0) {
//            if (visited[nx][ny] >= 2) return false;
//            visited[nx][ny]++;
            return true;
        }
        //만약 바로 뒤가 1이라면 작동을 멈출 것.
        return false;
    }

    private boolean isAllVisited(int x, int y, int[][] visited, int[][] room) {
        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if (visited[nx][ny] == 0 && room[nx][ny] == 0) return false;
        }
        return true;
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
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (list[i][j] == 0) cnt++;
            }
        }
//        System.out.println(cnt);
        /*int n=3,m=3,x=1,y=1,d=0;
        int[][] list = {
                {0,0,0},
                {0,0,1},
                {1,0,0},
        };*/
        RobotCleaner robotCleaner = new RobotCleaner();
        System.out.println(robotCleaner.sol(n, m, x, y, d, list));
    }
}
