package samsungSW.swTest.connectProcessor;

/**
 * 1767. [SW Test 샘플문제] 프로세서 연결하기
 *
 * base case 에서 모든 코어를 방문했을 때,
 * else if (maxCore==linkedCore){ 를 해주지 않아
 * 제대로 된 처리가 안되었었다.
 * 또한, 여기서 중요하게 볼 부분은,
 * 방문이 안되는 곳을 처리하는 코드이다.
 * 선택되지 못하는 경우도 처리.-**
 * visited[x][y] = true;
 * dfs(cells,n);
 * visited[x][y] = false;
 *
 *
 */
public class ConnectProcessor {
    private static final int[] dx = {-1,0,0,1};
    private static final int[] dy = {0,-1,1,0};
    private static final String[] direction = {"UP", "LEFT", "RIGHT", "DOWN"};
    private int linkedCore;
    private int countEdge;
    private int maxCore;
    private int best = 987654321;
    private boolean[][] visited;

    private void dfs(int[][] cells, int n) {
        int x = -1, y = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j] == 1 && !visited[i][j]) {
                    x = i;
                    y = j;
                    break;
                }
            }
            if (x != -1) break;
        }
        // 모든 core를 방문했으므로, 이때의 값을 비교.
        if (x == -1) {
//            System.out.println(maxCore+" "+linkedCore+" "+best+" "+countEdge);
            if (maxCore < linkedCore) {
                maxCore = linkedCore;
                best = countEdge;
            } else if (maxCore==linkedCore){
                best = Math.min(best, countEdge);
            }
            return;
        }
        // 4방향 전부 체크
        for (int i = 0; i < 4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if (nx>=0&&ny>=0&&nx<n&&ny<n){
                if (!visited[nx][ny]&&cells[nx][ny]!=1){
                    //선택 된 경우 처리.
                    if (isConnect(cells, nx,ny,direction[i], true)) {
                        visited[x][y] = true;
                        linkedCore++;
                        dfs(cells,n);
                        visited[x][y] = false;
                        linkedCore--;
                        //해제작업.
                        isConnect(cells,nx,ny,direction[i],false);
//                isConnect(cells, direction[i], false);
                    }
                }
            }
        }
        //선택되지 못하는 경우도 처리.-**
        visited[x][y] = true;
        dfs(cells,n);
        visited[x][y] = false;
    }
    //일단 cells[nx][ny]는 1이 아니고, 방문된적이 없다.
    // 즉, 가는 길 중간에 막히지만 안으면 된다.
    // String direct
    private boolean isConnect(int[][] cells,int nx,int ny,String direct,boolean config) {
        // 상 좌 우 하 모두 체크 후,
        // visited, linkedCore,countEdge 증가 시킬 것.
        //visited을 true로,
        if (config){
            if (doDirection(cells,nx,ny,direct)){
                return true;
            }
        }
        // visited을 false 로, linkedCore,countEdge 감소 시킬 것.
        else{
            removeDirection(nx,ny,direct);
        }
        return false;
    }

    private void removeDirection(int nx, int ny, String direct) {
        //top
        if (direct.equals(direction[0])){
            for (int i = 0; i <=nx ; i++) {
                visited[i][ny] = false;
                countEdge--;
            }
        }
        //left
        else if (direct.equals(direction[1])){
            for (int i = 0; i <=ny ; i++) {
                visited[nx][i] = false;
                countEdge--;
            }
        }
        //right
        else if (direct.equals(direction[2])){
            for (int i = ny; i < visited[0].length; i++) {
                visited[nx][i] = false;
                countEdge--;
            }
        }
        //bottom
        else if (direct.equals(direction[3])){
            for (int i = nx; i < visited.length; i++) {
                visited[i][ny] = false;
                countEdge--;
            }
        }
    }

    private boolean doDirection(int[][] cells, int nx, int ny, String direct) {
        //top
        if (direct.equals(direction[0])){
            if (toTop(cells,nx,ny)){
                return true;
            }
        }
        //left
        else if (direct.equals(direction[1])){
            if (toLeft(cells,nx,ny)){
                return true;
            }
        }
        //right
        else if (direct.equals(direction[2])){
            if (toRight(cells,nx,ny)){
                return true;
            }
        }
        //bottom
        else if (direct.equals(direction[3])){
            if (toBottom(cells,nx,ny)){
                return true;
            }
        }
        return false;
    }

    private boolean toBottom(int[][] cells, int nx, int ny) {
        boolean ok = false;
        int last = 0;
        for (int i = nx; i < cells.length; i++) {
            if (!visited[i][ny]&&cells[i][ny]==0){
                visited[i][ny] = true;
                countEdge++;
            }else{
                ok = true;
                last = i;
                break;
            }
        }
        if (ok){
            for (int i = nx; i < last; i++) {
                visited[i][ny] = false;
                countEdge--;
            }
            return false;
        }else{

            return true;
        }
    }

    private boolean toRight(int[][] cells, int nx, int ny) {
        boolean ok = false;
        int last = 0;
        for (int i = ny; i < cells[0].length; i++) {
            if (!visited[nx][i]&&cells[nx][i]==0){
                visited[nx][i] = true;
                countEdge++;
            }else{
                ok = true;
                last = i;
                break;
            }
        }
        if (ok){
            for (int i = ny; i < last; i++) {
                visited[nx][i] = false;
                countEdge--;
            }
            return false;
        }else{
            return true;

        }
    }

    private boolean toLeft(int[][] cells, int nx, int ny) {
        boolean ok = false;
        int last = 0;
        for (int i = ny; i >=0; i--) {
            if (!visited[nx][i]&&cells[nx][i]==0){
                visited[nx][i] = true;
                countEdge++;
            }else{
                ok = true;
                last = i;
                break;
            }
        }
        //만약 끝까지 도달 못한다면, 다시 풀어줄 것.
        if (ok){
            for (int i = ny; i >last ; i--) {
                visited[nx][i] = false;
                countEdge--;
            }
            return false;
        }else{
            return true;
        }
    }

    private boolean toTop(int[][] cells, int nx, int ny) {
        boolean ok = false;
        int last = 0;
        for (int i =nx; i >=0; i--) {
            //cells[i][y]
            if (!visited[i][ny]&&cells[i][ny]==0){
                visited[i][ny] = true;
                countEdge++;
            }
            else{
                ok = true;
                last = i;
                break;
            }
        }
        //만약 끝까지 도달 못한다면, 다시 풀어줄 것.
        if (ok){
            for (int i = nx; i >last ; i--) {
                visited[i][ny] = false;
                countEdge--;
            }
            return false;
        }else{
            return true;
        }
    }

    private void addCore(int[][] cells, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i==0||j==0||i==n-1||j==n-1){
                    if (cells[i][j]==1){
                        visited[i][j] = true;
                        linkedCore++;
                        maxCore++;
                        best =0;
                    }
                }
            }
        }
    }
    //미리 visited을 true로 해놓을 것은 미리 해놓을 것.
    public int sol(int n, int[][] cells) {
        visited = new boolean[n][n];
        addCore(cells,n);
        dfs(cells, n);
        return best;
    }
    public static void main(String[] args) {
        int n = 7;
        int[][] cells = {
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 1, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
        };
        ConnectProcessor cp = new ConnectProcessor();
        System.out.println(cp.sol(n,cells));
    }
}
