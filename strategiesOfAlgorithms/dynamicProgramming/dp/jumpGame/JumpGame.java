package strategiesOfAlgorithms.dynamicProgramming.dp.jumpGame;


import java.util.Arrays;

/**
 * I think it is not hard
 *
 * 이 문제는 완탐 문제를 dp로 변형한 문제.
 */

public class JumpGame {
    // dp를 적용할 때는 true or false 라도 0, 1로 표현하는 것이 더 좋다.
    private static int[][] dp;
    public static void main(String[] args) {
        int n = 4;
        int[][] jumpBoard = {
                {2,3,3,1},
                {1,2,1,3},
                {1,2,3,1},
                {3,1,1,0}
        };
        dp = new int[n+1][n+1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i],-1);
        }
        System.out.println(jump(jumpBoard,0,0));
        System.out.println(jumpDp(jumpBoard,0,0));
        System.out.println(solution(jumpBoard));
    }
    public static boolean solution(int[][] board){
        int ans = jumpDp(board,0,0);
        boolean ret = jump(board,0,0);
        if (ans>=1) return true;
        else return false;
    }
    public static int jumpDp(int[][] board,int x,int y){
        // base case 경계
        if (x<0 || y<0 || x>=board.length || y>=board[0].length){
            return 0;
        }
        // base case true
        if (x==board.length-1&&y==board[0].length-1){
            return 1;
        }
        if (dp[x][y]!=-1) return dp[x][y];
        //갈 수 있는 총 경우 수를 나타내는 로직. 즉, 1 이상이면 무조건 트루.
        dp[x][y] = jumpDp(board,x+board[x][y],y)+jumpDp(board,x,y+board[x][y]);
        //dp[x][y] = jumpDp(board,x,y+board[x][y]);
        return dp[x][y];
    }
    /**
     * 정수 게임판 nxn
     * 0,0 -> n-1,n-1 에 도착가능 or false
     * 각 칸에 적혀 있는 숫자만큼 아래쪽이나 오른쪽으로 이동 가능.
     * n<=100 2^100
     *
     *
     */
    public static boolean jump(int[][] board,int x,int y){
        //base case 경계
        if (x<0 || y<0 || x>=board.length || y>=board[0].length){
            return false;
        }
        //base case true
        if (x==board.length-1&&y==board[0].length-1){
            return true;
        }
        //이렇게가 더 깔끔하다.
        return jump(board,x+board[x][y],y) || jump(board,x,y+board[x][y]);
        //이렇게는 내가 한 표현
        /*boolean ok =  jump(board,x+board[x][y],y);
        if (!ok){
            ok = jump(board,x,y+board[x][y]);
        }
        return ok;*/
    }
}
