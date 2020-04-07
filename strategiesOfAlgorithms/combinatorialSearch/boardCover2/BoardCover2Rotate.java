package strategiesOfAlgorithms.combinatorialSearch.boardCover2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 이 문제를 풀 때, 관건은 도형을 90 -> 180 -> 270 회전처리였다.
 *
 * 또 하나 마지막에서 막아두고, recursive를 돌리고 풀어주는 것에서 해매었다..
 * 왜왜왜..단순한 기본적 내용을 자꾸 잊어버리는 거야...-
 * 모든 경우 탐색에서 가장 중요한 이 기본적인 공식을 머릿속에 항상 넣고.
 * 기억하자.
 * visited[x][y] = true;
 * serach(i+1);
 * visitied[x][y]=false;
 */

public class BoardCover2Rotate {
    private static List<Pair>[] cover;
    private static boolean[][] visited;
    private static int best;
    private static int blockSize;
    public static int sol(char[][] board,char[][] block){
        int[][] blockToInt = toInt(block);
        int[][] boardToInt = toInt(board);
        //make cover
        makeState(blockToInt);
        visited = new boolean[board.length][board[0].length];
        maxFillBlock(boardToInt,0);
        return best;
    }

    private static void maxFillBlock(int[][] board,int counting) {
        //최대값을 찾는 최적화 문제에서 낙관적인 휴리스틱들은 문제를
        //과대평가를 한다.
        int heuris = heuristic(board)+counting;
        if (best>=heuris){ //이 문장하나로.. 완탐이 시간안에 해결되었다..
//            System.out.println(best+" h="+ heuris);
            return;
        }
        int x= -1,y=-1;
        int n = board.length;
        int m = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j]==0&&!visited[i][j]){
                    x= i;
                    y = j;
                    break;
                }
            }
            if (x!=-1) break;
        }
        // base case;
        // 여기 base case를 만났을 때, 아무 도형도 찾지 못하는 곳을
        // true로 해준 곳을 기억해두었다가 여기에서 false로
        // 모두 일괄처리해줘야한다.
        if (x==-1) {
            best = Math.max(best,counting);
            return;
        }
        // 여기서 모든 값이 false가 되었을 때만
        // visited[x][y]=true로 놓아야만한다.
        for (int type = 0; type < 4; type++) {
            if (isFigure(board,x,y,type,true)){
                maxFillBlock(board,counting+1);
                isFigure(board,x,y,type,false);
            }
        }
        //이 칸을 덮지 않고 '막아'둔다.
        board[x][y] = 1;
        maxFillBlock(board,counting);
        board[x][y] = 0;
    }
    // board에 있는 값 중 모두 칸을 채우는 경우가
    // 항상 실제 답보다 크거나 같을 수 밖에 없다.
    // 그러므로 이 답을 상한으로 놓는다면, 많은 경우를 가지치키할 수 있다.
    private static int heuristic(int[][] board) {
        int cnt =0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j]==0&&!visited[i][j]){
                    cnt++;
                }
            }
        }
        // 남은 모든 흰색칸을 blockSize로 나눈 값은
        // 항상 실제 값보다 크거나 같다.
        return cnt/blockSize;
    }

    private static boolean isFigure(int[][] board, int x, int y, int type, boolean config) {
        int n = board.length;
        int m =board[0].length;
        int size = cover[type].size();
        //fulfill
        if (config){
            int[] xList = new int[size];
            int[] yList = new int[size];
            Arrays.fill(xList,-1);
            Arrays.fill(yList,-1);
            int count =0;
            for (int i = 0; i < size; i++) {
                int nx = x+cover[type].get(i).x;
                int ny=y+cover[type].get(i).y;
                if (nx>=0&&ny>=0&&nx<n&&ny<m){
                    if (board[nx][ny]==0&&!visited[nx][ny]){
                        count++;
                        xList[i] = nx;
                        yList[i] = ny;
                        visited[nx][ny] = true;
                    }
                }
            }
            //즉, 도형이 존재할 수 있는 경우에만 true!
            if (count==size){
                return true;
            }
            //만약 도형이 존재할 수 없으면 풀어줘야한다.
            else{
                //check xList and yList
                for (int i = 0; i < size; i++) {
                    if (xList[i]==-1) continue;
                    //도형 단위로 값을 카운트하기 위해 false로 놓아주었다.
                    visited[xList[i]][yList[i]] = false;
                }
            }
        }
        //remove !
        else{
            for (int i = 0; i < size; i++) {
                int nx = x+cover[type].get(i).x;
                int ny = y+cover[type].get(i).y;
                if (nx>=0&&ny>=0&&nx<n&&ny<m){
                    visited[nx][ny] = false;
                }
            }
        }
        return false;
    }

    private static void makeState(int[][] block){
        cover = new List[4];
        //init
        for (int i = 0; i < 4; i++) {
            cover[i] = new ArrayList<>();
        }
        for (int i = 0; i < 4; i++) {
            int x=-1,y=-1;
            for (int j = 0; j < block.length; j++) {
                for (int k = 0; k < block[0].length; k++) {
                    if (block[j][k]==1){
                        if (x==-1){
                            x = j;
                            y = k;
                        }
                        cover[i].add(new Pair(j-x,k-y));
                    }
                }
            }
            block = rotate(block);
        }
        blockSize = cover[0].size();
    }

    private static int[][] rotate(int[][] block){
        int n = block.length;
        int m =block[0].length;
        //[x][y] -> [y][x] 90도 회전했으므로,
        int[][] ret = new int[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                /**
                 * 12      531
                 * 34  ->  642
                 * 56
                 * 이렇게 변하므로,
                 */
                ret[j][i] = block[n-1-i][j];
            }
        }
        return ret;
    }
    // 0:white, 1:black 으로 변환해서 처리하자.
    private static int[][] toInt(char[][] board){
        int n = board.length;
        int m = board[0].length;
        int[][] toInt = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j]=='#'){
                    toInt[i][j] = 1;
                }else{
                    toInt[i][j] = 0;
                }
            }
        }
        return toInt;
    }

    public static void main(String[] args) {
        /*int n = 4;
        int m = 7;
        int r = 2;
        int c = 3;
        char[][] board = {
                {'#','#','.','#','#','.','.'},
                {'#','.','.','.','.','.','.'},
                {'#','.','.','.','.','#','#'},
                {'#','.','.','#','#','#','#'},
        };
        char[][] block = {
                {'#','#','#'},
                {'#','.','.'},
        };*/
        int n = 5;
        int m = 10;
        int r = 3;
        int c = 3;
        char[][] board = {
                {'.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.','.','.'},
        };
        char[][] block = {
                {'.','#','.'},
                {'#','#','#'},
                {'.','.','#'},
        };
        long start = System.currentTimeMillis();
        System.out.println(sol(board,block));
        long end = System.currentTimeMillis();
        System.out.println((end-start));
    }

}
