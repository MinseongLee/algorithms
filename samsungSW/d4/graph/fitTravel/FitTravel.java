package samsungSW.d4.graph.fitTravel;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 7699. 수지의 수지 맞는 여행
 * bfs로 안되어서(a lot of memory, and the speed)
 * 그래서 bfs로 풀땐, 최대한 적은 메모리를 사용하기 위해 노력해야한다.
 * bruteforce로 해결하였다..
 *
 * -문제를 풀때.. 어떤 방식이 빠르고 쉽게 해결될 수 있는지 고민해봐야한다.
 * brute force, bfs, dfs 등 문제에 맞게 푸는게 좋은거같다.
 * 이렇게 3가지 방식을 확실하게 일단 알자.
 * 사실..just roll도 brute force야. dp도 brute force의 연장선상이다.
 * 즉,, brute force를 제대로 해야해. 이게 가장 중요해.
 * 어떤 문제건 간에. brute force 부터 생각한 후, 이걸로 안될 때,
 * bfs, dfs등을 생각해야해.
 *
 *
 */
public class FitTravel {
    private static final int[] dx={-1,0,0,1};
    private static final int[] dy={0,-1,1,0};
    private int best = 1;
    //brute force, dfs,, 결국 같은말이야.
    // dfs가 뭔데, 깊이 쭉 들어가는거잖아.
    // brute force는 뭔데.. 모든 경우수를 찾는 거잖아.
    // 그렇담.. bfs로 풀리지 않는다면, dfs(brute force)로 풀 수 있는 것이다.
    //brute force
    //x , y 반복되는 상태
    // island[][]는 이동한 값을 추가하며 길을 넓혀간다.
    private void dfs(int x, int y, String[][] island){
        int r = island.length;
        int c = island[0].length;
        for (int i = 0; i < 4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if (nx>=0&&nx<r&&ny>=0&&ny<c){
                //여기서 here에 nx ny의 값이 포함되었는지 확인.
                if (!isTheSameLand(island[x][y],island[nx][ny].charAt(island[nx][ny].length()-1))){
//                    System.out.println(island[x][y]+" "+island[nx][ny]);
                    // [nx][ny]를 뒤에 다가 붙이고 있으므로, 뒤에 한 단어를 붙여줘야한다.
                    // 같은 랜드가 없을 시.
                    String there = island[x][y]+island[nx][ny].charAt(island[nx][ny].length()-1);
//                    System.out.println("there="+there+" len="+there.length());
                    best = Math.max(best,there.length());
                    island[nx][ny] = there;
                    dfs(nx,ny,island);
                }
            }
        }
    }

    // 최대값을 기억해두고 이값보다 작은 값으로 탐색해가면?
    private int bfs(char[][] island){
        int r = island.length;
        int c = island[0].length;
        int[][] dist = new int[r][c];
        Queue<Pair> q = new LinkedList<>();
        String state = String.valueOf(island[0][0]);
        dist[0][0] = 1;
        q.add(new Pair(0,0,state));
        while(!q.isEmpty()){
            Pair here = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = here.x+dx[i];
                int ny = here.y+dy[i];
                if (nx>=0&&nx<r&&ny>=0&&ny<c){
                    //여기서 here에 nx ny의 값이 포함되었는지 확인.
                    if (!isTheSameLand(here.state,island[nx][ny])){
                        //같은 랜드가 없을 시.
                        String there = here.state+island[nx][ny];
                        dist[nx][ny] = dist[here.x][here.y]+1;
                        q.add(new Pair(nx,ny,there));
                    }
                }
            }
        }
        int max= 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (max<dist[i][j]){
                    max = dist[i][j];
                }
            }
        }
        return max;
    }

    private boolean isTheSameLand(String state, char land) {
        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i)==land){
                return true;
            }
        }
        return false;
    }

    public int sol(char[][] island){
        int[][] land = toInt(island);
        String[][] land2 = toString(island);
//        return bfs(island);
        dfs(0,0,land2);
        System.out.println("best="+best);
        return best;
    }

    private String[][] toString(char[][] island) {
        int r = island.length;
        int c = island[0].length;
        String[][] land = new String[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                land[i][j] = String.valueOf(island[i][j]);
            }
        }
        return land;
    }

    private int[][] toInt(char[][] island) {
        int r = island.length;
        int c = island[0].length;
        int[][] land = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int v = island[i][j]-'A';
                land[i][j] = v;
            }
        }
        return land;
    }

    public static void main(String[] args) {
        int r = 2;
        int c= 4;
        char[][] island = {
                {'A'}
        };
        /*char[][] island = {
                {'I','E','F','C','J'},
                {'F','H','F','K','C'},
                {'F','F','A','L','F'},
                {'H','F','G','C','F'},
                {'H','M','C','H','H'},

        };*/
        FitTravel fitTravel =new FitTravel();
        System.out.println(fitTravel.sol(island));
    }
}
