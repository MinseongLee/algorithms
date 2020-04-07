package baekjoon.bruteForce.lab;

import java.util.ArrayList;
import java.util.List;

/**
 * 이문제 아주 좋다. 500C3도 금방 뽑는다는 것을 이해할 수 있어.
 * 즉, 64C3도 금방 뽑는다.
 * 이문제를 풀면서 배운것은 500C3도 금방 푼다는 것.***
 * 조합 관련은 n의 크기가 커도 금방금방 풀수있다는 것을 이해하고 있자.
 */
public class LabCombination {
    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, -1, 1, 0};
    private int best;
    private int m;
    // 생각보다 조합 수가 작다는 것을 이해하고 있어야한다. 500C3도 금방뽑아.
    public int sol(int n, int m, int[][] lab) {
        List<Integer> ans = new ArrayList<>();
        this.m = m;
        // 그냥 모든 조합 수를 다 구하면 된다. 이건 조합의수.. 크기가 굉장히 작다.
        combination(n*m,3,ans,lab);
        System.out.println(best);
        best = 0;
        //block 이 로직은 2^n 너무 크다..
        block(0, 0, lab, 0);
//        하나더 확인할 것이. 2와 1이 근처에 없는 경우.4군데 모서리를 전부 확인 후
        //3개를 뽑아낼 수 있는지를 체크.
        //직각 삼각형이 되는 6칸 모두를 확인해줄 것.
        return best;
    }
    //n은 n*m
    private void combination(int n, int r, List<Integer> ans, int[][] lab) {
        if (r==0){
            for (int i = 0; i < ans.size(); i++) {
                //[x][y]는 m으로 나눈 몫과 나머지가 된다. 여기서 n은 n*m이므로,
                if (lab[ans.get(i)/m][ans.get(i)%m]==1||lab[ans.get(i)/m][ans.get(i)%m]==2){
                    return;
                }
            }
            //모두 0일 때 처리.
            for (int i = 0; i < ans.size(); i++) {
                lab[ans.get(i)/m][ans.get(i)%m] = 1;
            }
            best = Math.max(best,bfs(lab));
            for (int i = 0; i < ans.size(); i++) {
                lab[ans.get(i)/m][ans.get(i)%m] = 0;
            }
            return;
        }
        int smallest = ans.isEmpty()?0:ans.get(ans.size()-1)+1;
        for (int i = smallest; i < n; i++) {
            ans.add(i);
            combination(n,r-1,ans,lab);
            ans.remove(ans.size()-1);
        }
    }
    //이 로직은 선택 하는지 안하는지 2^n 이다. 너무 크다.
    private void block(int x, int y, int[][] lab, int cnt) {
        if (x == lab.length) {
            return;
        }
        if (cnt == 3) {
            best = Math.max(best, bfs(lab));
            return;
        }
        //선택 안하는 경우.
        if (y < lab[0].length) {
            block(x, y + 1, lab, cnt);
        } else if (y==lab[0].length){
            block(x + 1, 0, lab, cnt);
        }
        //pruning을 한다면, 8타일에서 2 or 1이 있는 경우만 돌리기.
        //선택하는 경우, cnt < 3
        if (y < lab[0].length && lab[x][y] == 0) {
            //5번은 막는 값.
            lab[x][y] = 1;
            block(x, y + 1, lab, cnt + 1);
            lab[x][y] = 0;
        }
        //여기는 검색해야할 칸을 바꾸는 용도.
        else if (y == lab[0].length) {
            block(x + 1, 0, lab, cnt);
        }
    }

    //virus가 있는 지점에서 4방향으로 가기.
    private int bfs(int[][] lab) {
        int n = lab.length;
        int m = lab[0].length;
        int[][] dist = createLab(lab);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dist[i][j] == 2) {
                    dfs(i, j, dist);
                }
            }
        }
        //남은 0의 개수 세기/
        int counting = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dist[i][j] == 0) {
                    counting++;
                }
            }
        }
        return counting;
    }

    private void dfs(int x, int y, int[][] dist) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < dist.length && ny < dist[0].length) {
                if (dist[nx][ny] == 0) {
                    dist[nx][ny] = 3;
                    dfs(nx, ny, dist);
                }
            }
        }
    }

    private int[][] createLab(int[][] lab) {
        int n = lab.length;
        int m = lab[0].length;
        int[][] dist = new int[n][m];
        //이중 for문을 이렇게 대체함. -2중for문이나 저거나 시간복잡도는 똑같다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dist[i][j] = lab[i][j];
            }
//            System.arraycopy(lab[i], 0, dist[i], 0, m);
        }
        return dist;
    }

    public static void main(String[] args) {
        int n = 7;
        int m = 7;
        int[][] lab2 = {
                {2, 0, 0, 0, 1, 1, 0},
                {0, 0, 1, 0, 1, 2, 0},
                {0, 1, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1},
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0},
        };
        /*int n = 8;
        int m = 8;
        int[][] lab2 = {
                {2, 0, 0, 0, 0, 0, 0, 2},
                {2, 0, 0, 0, 0, 0, 0, 2},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 1},
        };*/
        /*int n = 4;
        int m = 6;
        int[][] lab2 = {
                {0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 2},
                {1, 1, 1, 0, 0, 2},
                {0, 0, 0, 0, 0, 2},
        };*/
        /*int n = 3;
        int m = 3;
        int[][] lab2 = {
                {2,2,0},
                {0,0,0},
                {0,2,2},
        };*/
        long start = System.currentTimeMillis();
        LabCombination labCombination = new LabCombination();
        System.out.println(labCombination.sol(n, m, lab2));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
