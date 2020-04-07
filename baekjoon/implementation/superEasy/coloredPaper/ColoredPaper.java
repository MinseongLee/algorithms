package baekjoon.implementation.superEasy.coloredPaper;

/**
 * 시간 복잡도는 O(n^2)
 */
public class ColoredPaper {
    public int sol(int n,int[][] xy){
        int cnt =0;
        boolean[][] visited = new boolean[101][101];
        for (int i = 0; i < n; i++) {
            applyColor(xy[i][1],xy[i][0],visited);
        }
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if (visited[i][j]) cnt++;
            }
        }
        return cnt;
    }
    //여기서 주의할 점은 정확히 10칸을 세어야한다는 것. 11칸을 세어서는 안된다.
    private void applyColor(int y, int x, boolean[][] visited) {
        for (int i = y; i < 10 + y; i++) {
            for (int j = x; j < 10 + x; j++) {
                visited[i][j] = true;
            }
        }
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] xy = {
                {3,7}, {15,7}, {5,2},
        };
        ColoredPaper coloredPaper  =new ColoredPaper();
        System.out.println(coloredPaper.sol(n,xy));
    }
}
