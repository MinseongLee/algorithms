package baekjoon.bruteForce.reapplyChess;

/**
 * O(8^2nm)
 * 앞에 상수는 무시하고 nm이 시간복잡도.
 */
public class ReapplyChess {
    private static final int INF= 987654321;
    //1=B, 0=W
    private static final int[][] chess1={
            {1,0,1,0,1,0,1,0}, {0,1,0,1,0,1,0,1}, {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1}, {1,0,1,0,1,0,1,0}, {0,1,0,1,0,1,0,1},
            {1,0,1,0,1,0,1,0}, {0,1,0,1,0,1,0,1},
    };
    private static final int[][] chess2={
            {0,1,0,1,0,1,0,1}, {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1}, {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1}, {1,0,1,0,1,0,1,0},
            {0,1,0,1,0,1,0,1}, {1,0,1,0,1,0,1,0},
    };
    public int sol(int n,int m,String[] list){
        int[][] board = createBoard(list);
        int ans = INF;
        for (int i = 0; i < n-7; i++) {
            for (int j = 0; j < m-7; j++) {
                ans = Math.min(ans,Math.min(reapply(board,i,j,chess1),reapply(board,i,j,chess2)));
            }
        }
        return ans;
    }
    //i,j를 start 지점으로 확인해줄 것. 8칸씩.
    private int reapply(int[][] board, int x, int y,int[][] chess) {
        //총 두번 확인해야한다. 그중 작은 값을 넣을 것.
        int ans =0;
        for (int i = x,k=0; i < 8 + x; i++,k++) {
            for (int j = y,g=0; j < 8 + y; j++,g++) {
                //같지 않은경우 다시 칠해야하는 경우.
                if (board[i][j]!=chess[k][g]){
                    ans++;
                }
            }
        }
        return ans;
    }

    private int[][] createBoard(String[] list) {
        int n = list.length;
        int m = list[0].length();
        int[][] board = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (list[i].charAt(j)=='W'){
                    board[i][j] = 0;
                }else{
                    board[i][j]=1;
                }
            }
        }
        return board;
    }

    public static void main(String[] args) {
        ReapplyChess reapplyChess = new ReapplyChess();
//        System.out.println(reapplyChess.sol(n,m,list));
    }
}
