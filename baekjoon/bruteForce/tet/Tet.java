package baekjoon.bruteForce.tet;

/**
 * 너무 쉬운문제인데,, 회전하는 경우만 생각하면 13가지이다.
 * 대칭까지 생각하면, 19가지가 나온다.
 */
public class Tet {
    //총 19가지 상태를 미리 만들 것.
    private int[][][] cover = {
            {{0, 0},
                    {1, 0},
                    {2, 0},
                    {3, 0}},
            {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
            {{0, 0}, {0, 1},
                    {1, 0}, {1, 1}},
            {{0, 0},
                    {1, 0},
                    {2, 0}, {2, 1}},
            {{0, 0}, {0, 1}, {0, 2},
                    {1, 2}},
            {{0, 0}, {0, 1},
                    {1, 1},
                    {2, 1}},
            {{0, 0},
                    {1, 0}, {1, 1}, {1, 2}},
            {{0, 0},
                    {1, 0}, {1, 1},
                    {2, 1}},
            {{0, 0}, {0, 1},
                    {1, -1}, {1, 0}},
            {{0, 0}, {0, 1}, {0, 2},
                    {1, 1}},
            {{0, 0},
                    {1, -1}, {1, 0}, {1, 1}},
            {{0, 0},
                    {1, 0}, {1, 1},
                    {2, 0}},
            {{0, 0},
                    {1, -1}, {1, 0},
                    {2, 0}},
            {{0, 0}, {1, 0}, {2, 0}, {2, -1}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 0}},
            {{0, 0}, {0, 1}, {1, 0}, {2, 0}},
            {{0, 0}, {1, 0}, {1, -1}, {1, -2}},
            {{0, 0}, {1, 0}, {1, -1}, {2, -1}},
            {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
    };
    public int sol(int n,int m,int[][] board){
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans,set(board,i,j));
            }
        }
        return ans;
    }

    private int set(int[][] board, int x, int y) {
        int maxElement = 0;
        for (int type = 0; type < cover.length; type++) {
            int counting =0,sum=0;
            for (int i = 0; i < 4; i++) {
                int nx = x+cover[type][i][0];
                int ny = y+cover[type][i][1];
                if (nx>=0&&ny>=0&&nx<board.length&&ny<board[0].length){
                    counting++;
                    sum+=board[nx][ny];
                }
            }
            if (counting==4&&maxElement<sum){
                maxElement = sum;
            }
        }
        return maxElement;
    }

    public static void main(String[] args) {
        int n = 5;
        int m =5;
        int[][] list ={
                {1,2,3,4,5},
                {5,4,3,2,1},
                {2,3,4,5,6},
                {6,5,4,3,2},
                {1,2,1,2,1},
        };
        /*int n = 4;
        int m =10;
        int[][] list ={
                {1, 2, 1, 2, 1, 2, 1, 2, 1, 2},
                {2, 1, 2, 1, 2, 1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1, 2, 1, 2, 1, 2},
                {2, 1, 2, 1, 2, 1, 2, 1, 2, 1},
        };*/
        /*int n = 4;
        int m =5;
        int[][] list ={
                {1,2,3,4,5},
                {1,2,3,4,5},
                {1,2,3,4,5},
                {1,2,3,4,5},
        };*/
        Tet tet = new Tet();
        System.out.println(tet.sol(n,m,list));
    }
}
