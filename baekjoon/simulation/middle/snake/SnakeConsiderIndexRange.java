package baekjoon.simulation.middle.snake;

import java.util.LinkedList;
import java.util.Queue;


/**
 * index가 1부터 시작하는 것으로 문제가 주워졌다.
 * 이 문제는.. 이걸 알아차릴 수 있나 없나로 갈린다.
 * 만약에 시험볼 때, 에러가 난다면, 이걸 의심해보자.
 * 주어진 (x-1,y-1) 로!
 *
 * how to solve it
 * 또한.. tail을 queue 를 활용해 짤라냈다. FIFO,
 * 첫 start 값을 4로 주어 exception에 걸리지 않게하였다.
 * queue.size()로 start값을 유지할 수 있게하였다.
 * (x,y) 값을 계속 recursive 로 돌리며, (u,l,r,d)일 때, + or -를 주어서
 * exception 처리를 basecase 로 함수 앞에서 처리하였다.
 */
public class SnakeConsiderIndexRange {
    private static final char[] direction = {'u','l','r','d'};
    //direction에 매칭하는 index를 넣기.
    private static final int[] left ={1,3,0,2};
    private static final int[] right ={2,0,3,1};
    private int second;
    private int index;
    public int sol(int n,int k,int[][] xy,int l,int[] seconds,char[] lr){
        int[][] board = createBoard(n,xy);
        Queue<Pair> snake =new LinkedList<>();
        moveSnake(0,0,2,snake,board,seconds,lr);
        return second;
    }

    private void moveSnake(int x, int y, int d, Queue<Pair> snake, int[][] board, int[] seconds, char[] lr) {
        if (index<seconds.length&&second==seconds[index]){
            if (lr[index]=='L'){
                d = left[d];
            }else{
                d = right[d];
            }
            index++;
        }
        //wall or myself 에 마주치면 game over
        if (x<0||y<0||x>=board.length||y>=board[0].length||board[x][y]==3) {
            return;
        }
        int apple = board[x][y];
        board[x][y] = 3;
        snake.add(new Pair(x,y));
//        System.out.println("befo="+snake+" best="+second);
        if (apple!=2){
            if (snake.size()>1){
                Pair here = snake.poll();
                board[here.x][here.y]=0;
            }
        }
        System.out.println("second="+second);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
//        System.out.println("after="+snake);
        //up
        if (direction[d]=='u'){
            second++;
            moveSnake(x-1,y,d,snake,board,seconds,lr);
        }
        else if (direction[d]=='l'){
            second++;
            moveSnake(x,y-1,d,snake,board,seconds,lr);
        }
        else if (direction[d]=='r'){
            second++;
            moveSnake(x,y+1,d,snake,board,seconds,lr);
        }
        else if (direction[d]=='d'){
            second++;
            moveSnake(x+1,y,d,snake,board,seconds,lr);
        }
    }

    //wall=1,move=0,apple=2,snake=3;
    private int[][] createBoard(int n, int[][] xy) {
        int[][] board = new int[n][n];
        //for start
        board[0][0] = 4;
        for (int i = 0; i < xy.length; i++) {
            board[xy[i][0]-1][xy[i][1]-1] = 2;
        }
        return board;
    }

    public static void main(String[] args) {
        int n=10,k=5,l=4;
        int[][] xy= {
                {1,5},
                {1,3},
                {1,2},
                {1,6},
                {1,7},
        };
        int[] seconds={8,10,11,13};
        char[] s = {'D','D','D','L'};
        /*int n=10,k=4,l=4;
        int[][] xy={
                {1,2},
                {1,3},
                {1,4},
                {1,5},
        };
        int[] seconds={8,10,11,13};
        char[] s = {'D','D','D','L'};*/
        /*int n=6,k=3,l=3;
        int[][] xy={
                {3,4},
                {2,5},
                {5,3},
        };
        int[] seconds={3,15,17};
        char[] s = {'D','L','D'};*/
        SnakeConsiderIndexRange snakeConsiderIndexRange = new SnakeConsiderIndexRange();
        System.out.println(snakeConsiderIndexRange.sol(n,k,xy,l,seconds,s));
    }
}
