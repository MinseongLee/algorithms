package samsungSW.d3.landMine;

public class LandMine {
    private static final int INF = 987654321;
    private static final int[] dx = {-1,-1,-1,0,0,1,1,1};
    private static final int[] dy = {-1,0,1,-1,1,-1,0,1};
    /**
     * 지뢰 없는 칸에 주변 8칸에 지뢰 개수를 적기.
     * 0이라면, 그 주변도 숫자 표시를 해줄 것.
     *
     * 지뢰 - 1,
     * 나머지 0으로 초기화. 만약 주변 8에 1이 있으면 +1,
     * 없으면 +0
     */
    public static int searchMine(char[][] mine){
        int n = mine.length;
        int[][] bomb = createBomb(mine);
        int smallest = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //먼저 0일때 방문해서 연결된 노드들을 다 처리.
                if (bomb[i][j]==0){
                    smallest += bfs(i,j,bomb);
                }
            }
        }
        smallest += restVertex(bomb);
        return smallest;
    }

    private static int restVertex(int[][] bomb) {
        int count = 0;
        int n = bomb.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (bomb[i][j]==INF){
                    count++;
                }
            }
        }
        return count;
    }

    //0처리해주는 로직. 무조건 1만 리턴.
    private static int bfs(int x,int y,int[][] bomb) {
        int n = bomb.length;
        bomb[x][y]++;
        for (int i = 0; i < dx.length; i++) {
            int nx = dx[i]+x;
            int ny = dy[i]+y;
            if (nx>=0&&ny>=0&&nx<n&&ny<n){
                //근접한 0이 된 값까지 전부 같이 처리.
                if (bomb[nx][ny]==0){
                    bfs(nx,ny,bomb);
                }
                else if (bomb[nx][ny]!=-1){
                    bomb[nx][ny]++;
                }
            }
        }
        return 1;
    }

    private static int[][] createBomb(char[][] mine) {
        int n = mine.length;
        int[][] bomb = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //.*
                if (mine[i][j]=='*'){
                    bomb[i][j] = -1;
                }else{
                    bomb[i][j] = searchNotMine(mine,i,j);
                }
            }
        }
        return bomb;
    }

    private static int searchNotMine(char[][] mine, int x, int y) {
        int n = mine.length;
        //초기화 할때, 주변 8곳을 검색한 후, 지뢰가 없는 곳이면 0으로 놓기.
        for (int k = 0; k < dx.length; k++) {
            int nx = dx[k]+x;
            int ny = dy[k]+y;
            if (nx>=0&&ny>=0&&nx<n&&ny<n){
                if (mine[nx][ny]=='*'){
                    return INF;
                }
            }
        }
        //8군데 어디에도 지뢰가 없다면 0
        return 0;
    }

    public static void main(String[] args) {
        //bfs로 풀기 가능.
        char[][] mine = {
                {'.','.','*'},
                {'.','.','*'},
                {'*','*','.'},
        };
        System.out.println(searchMine(mine));
        //..*..
        //..*..
        //.*..*
        //.*...
        //.*...
    }
}
