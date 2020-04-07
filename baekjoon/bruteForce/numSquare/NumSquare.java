package baekjoon.bruteForce.numSquare;

/**
 * 1051 숫자 정사각형
 *
 * x - last 사이에있는 길이를 넘겨서 쉽게 계산하였다.
 */
public class NumSquare {
    public int sol(int n,int m,int[][] rectangle){
        int maxArea =0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int last = Math.min(n-1-i,m-1-j);
                maxArea = Math.max(maxArea,getMaxArea(i,j,last,rectangle));
            }
        }
        return maxArea;
    }
    // 여기서 n,m의 크기를 따져야한다.
    private int getMaxArea(int x, int y, int last, int[][] rectangle) {
        // last+x를 해줘야 정확하게 길이를 계산 할 수 있다.
        for (int i = last+x; i > x; i--) {
            //x를 -해줘야 last+x 그리고 last+y 를 해줄 수 있다.
            //그래야 정확하게 같은 길이의 값으로 계산가능.
            //x는 x끼리 y는 y끼리.
            if (isValidSquare(x,y,i-x,rectangle)){
//                System.out.println(sqr(i+1-x));
                return sqr(i+1-x);
            }
        }
        return 1;
    }

    private boolean isValidSquare(int x, int y, int last, int[][] rectangle) {
//        System.out.println(x+" y="+y+" last="+last);
        return rectangle[x][y]==rectangle[last+x][y]&&
                rectangle[x][y]==rectangle[x][last+y]&&rectangle[x][y]==rectangle[last+x][last+y];
    }
    private int sqr(int x) {
        return x*x;
    }
    public static void main(String[] args) {
        /*int n =2,m=2;
        int[][] list={{1,2},{1,1}};*/
        int n =4,m=5;
        int[][] list = {
                {2,2,0,2,1},
                {2,2,1,0,0},
                {2,2,1,2,1},
                {2,2,1,2,1},
        };
        NumSquare numSquare = new NumSquare();
        System.out.println(numSquare.sol(n,m,list));
    }
}
