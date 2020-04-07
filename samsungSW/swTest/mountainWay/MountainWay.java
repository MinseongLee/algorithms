package samsungSW.swTest.mountainWay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1949. [모의 SW 역량테스트] 등산로 조성
 * : 풀었다.
 * 이문제 시간초과 나오는 이유..
 * C++과 같은 시간을 주었다.. python은 15초를 주고,
 * C++ 3초, java 3초.. 내 로직엔 문제없다.
 * 그래도.. 3초안에 넘기려고 하자. 이렇게 놓았다는 것은 가능하단 것을 의미하니.
 *
 *-------------pruning을 통해 시간안에 해결하였다.
 * - 모든 상태를 방문할 필욘 없으므로,
 * 같은 상태에 있다면, continue를 통해 해결하였다.:
 * 1이면, 더 크다, 0이면 작은경우, -1이면 방문 못한경우 or 같은경우.
 *  int[] isDirection = makeIsDirection(way,i,j);
 *  way[i][j]--;
 *  만약 isDirection과 같은 상태라면, continue; 아니라면 체크.
 *  if (isDirect(isDirection,way,i,j)) continue;
 */
public class MountainWay {
    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, -1, 1, 0};
    //각 start point에서 최고 길이 저장.
    private int best;
    //각 start point에서 최고 길이 원소 저장.
    private List<List<Pair>> bestLen = new ArrayList<>();

    public int sol(int n, int k, int[][] way) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (max < way[i][j]) {
                    max = way[i][j];
                }
            }
        }
        // 각 start 지점에서 나온 best와 비교해 가장 긴 길이를 저장.
        int longest = 0;
        // 각 start 지점에서 나온 bestLen과 비교해
        // 가장 긴 길이의 원소들을 저장.
//        List<List<Pair>> allLen = new ArrayList<>();
        // 4방향 중 한번 탐색 된 곳에서의 길이를 리턴.
        List<Pair> len = new ArrayList<>();
        List<Pair> startList = new ArrayList<>();
        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (way[i][j]==max){
                    startList.add(new Pair(i,j,way[i][j]));
                }
            }
        }*/
        //각 start지점에서 lower 와 upper 값 구하기.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 이 start지점에서 나올 수 있는 최고값을 리턴.
                if (way[i][j] == max) {
                    startList.add(new Pair(i,j,way[i][j]));
                    len.add(new Pair(i, j, way[i][j]));
                    lowerDfs(i, j, way, len);
                    // len에 있는 내용이 다 지워진 상태이므로,
                    // bestLen에 있는 내용을 준다.
//                    len = new ArrayList<>(bestLen);
//                    upperDfs(i,j,way,len);
                    for (int l = 0; l < bestLen.size(); l++) {
//                        System.out.println(bestLen.get(l).size());
//                        len = new ArrayList<>(bestLen.get(l));
//                        System.out.println("len="+len);
                        upperDfs(i, j, way, bestLen.get(l));
                    }
                    //bestLen에는 그 start지점에 최고값이 들어있다.
                    //longestLen에 갱신할 것.
//                    best = Math.max(best,bestLen.size());

//                    System.out.println("bestLen.size()="+bestLen.size());
//                    allLen.add(new ArrayList<>(bestLen));
                    for (int l = 0; l < bestLen.size(); l++) {
                        bestLen.get(l).clear();
                    }
                    len.clear();
                }
            }
        }
//        System.out.println(best);
        //최종 length가 longest, longestLen에 저장
        //k개 만큼 longestLen에 저장된 변수들을 1씩 감소시키며
        //lowerDfs와 upperDfs를 돌리기.
        /*List<List<Pair>> longestLen = new ArrayList<>();
        for (int i = 0; i < allLen.size(); i++) {
            if (best==allLen.get(i).size()){
                longestLen.add(new ArrayList<>(allLen.get(i)));
            }
        }*/
//        System.out.println(longestLen.size());
//        Collections.sort(longestLen);
//        System.out.println("longestLen="+longestLen);
//        System.out.println(longestLen.size()+" "+len.size()+" best="+bestLen.size());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //깊이는 한곳에만 들어갈 수 있으므로, 다시 원상복구를 시켜준다.
                int origin = way[i][j];
                for (int l = 0; l < k; l++) {
                    //1이면, 더 크다, 0이면 작은경우, -1이면 방문 못한경우 or 같은경우.
                    int[] isDirection = makeIsDirection(way,i,j);
                    way[i][j]--;
                    //만약 isDirection과 같은 상태라면, continue; 아니라면 체크.
                    if (isDirect(isDirection,way,i,j)) continue;
                    // 즉, --를 해줬는데, 4방향 대소관계가 같다면, continue를해줄 것.
                    // 대소관계가 같지 않아지는 숫자들만 recursion
                    if (way[i][j] < 0) break;
                    for (int a = 0; a < startList.size(); a++) {
                        int x = startList.get(a).x,y=startList.get(a).y;
                        len.add(new Pair(x, y, way[x][y]));
                        lowerDfs(x, y, way, len);
                        for (int m = 0; m < bestLen.size(); m++) {
                            upperDfs(x, y, way, bestLen.get(m));
                        }
                        for (int m = 0; m < bestLen.size(); m++) {
                            bestLen.get(m).clear();
                        }
                        len.clear();
                    }
                }
                way[i][j] = origin;
            }
        }
        return best;
    }

    private boolean isDirect(int[] isDirection, int[][] way, int x, int y) {
        int[] newOne = makeIsDirection(way,x,y);
        for (int i = 0; i < 4; i++) {
            if (isDirection[i]!=newOne[i]){
                return false;
            }
        }
        return true;
    }

    private int[] makeIsDirection(int[][] way, int x, int y) {
        int[] isDirection = new int[4];
        Arrays.fill(isDirection,-1);
        for (int i = 0; i < 4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if (nx>=0&&ny>=0&&nx<way.length&&ny<way[0].length){
                if (way[x][y]>way[nx][ny]){
                    isDirection[i] = 1;
                }else if (way[x][y]<way[nx][ny]){
                    isDirection[i] = 0;
                }
            }
        }
        return isDirection;
    }

    private void upperDfs(int x, int y, int[][] way, List<Pair> len) {
        if (best>=len.size()) return;
        int n = way.length;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                if (way[x][y] < way[nx][ny]) {
                    len.add(new Pair(nx, ny, way[nx][ny]));
                    upperDfs(nx, ny, way, len);
                    len.remove(len.size() - 1);
                }
            }
        }

        //base case
        //4방향을 다 돌아도 없는 경우.
        if (best <= len.size()) {
//            System.out.println(len);
//            System.out.println(" size=" + len.size());
//            allLen.add(new ArrayList<>(len));
            best = Math.max(best, len.size());
        }
        /*if (bestLen.size()<len.size()){
            bestLen = new ArrayList<>(len);
        }*/
    }

    private void lowerDfs(int x, int y, int[][] way, List<Pair> len) {
        int n = way.length;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                if (way[x][y] > way[nx][ny]) {
                    len.add(new Pair(nx, ny, way[nx][ny]));
                    lowerDfs(nx, ny, way, len);
                    len.remove(len.size() - 1);
                }
            }
        }
        //base case
        //4방향을 다 돌아도 없는 경우.
//        System.out.println(len);
//        System.out.println(" size="+len.size());
        if (best<=len.size()){
            bestLen.add(new ArrayList<>(len));
            best = Math.max(best, len.size());
        }

        /*if (bestLen.size()<len.size()){
            bestLen = new ArrayList<>(len);
        }*/
    }

    public static void main(String[] args) {
        int n = 8;
        int k = 5;
        int[][] way = {
                {9, 3, 2, 3, 2,12,6,7},
                {9, 3, 2, 3, 2,12,6,7},
                {6, 3, 1, 7, 5,12,6,7},
                {6, 3, 1, 7, 5,12,6,7},
                {3, 4, 8, 9, 9,12,6,7},
                {2, 3, 7, 7, 7,12,6,7},
                {2, 3, 7, 7, 7,12,6,7},
                {7, 6, 5, 5, 8,12,6,7},
        };
        /*int n = 5;
        int k = 1;
        int[][] way = {
                {9, 3, 2, 3, 2},
                {6, 3, 1, 7, 5},
                {3, 4, 8, 9, 9},
                {2, 3, 7, 7, 7},
                {7, 6, 5, 5, 8},
        };*/
        MountainWay mountainWay = new MountainWay();
        System.out.println(mountainWay.sol(n, k, way));
    }
}
