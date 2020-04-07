package baekjoon.bruteForce.resignation;

import java.util.Arrays;

/**
 * 시간복잡도 - O(n!),: (dp)O(n^2m),
 * O(2^n),: (dp)O(2nm),
 *
 * O(2^n) 선택 or not 로직도 만들어보고,
 * 모든 경우를 선택하는 n! 경우도 만들어보았다.
 * 굉장히 중요한문제.**
 * **dp와 best는 같이 사용할 수 없다.**cache에 부분값들이 들어가야는데,
 * 그 부분값들이 못들어가므로,**
 * **내가 틀린 부분
 * if (d+table[d][0]<table.length){
 *  maxProfitCache(d+table[d][0],p+table[d][1],table); }
 *  이렇게 넘기면 맨 마지막 값을 정확하게 체크할 수 없다. 왜냐하면 넘겨지는 d값이
 *  d== table.length-1 까지밖에 안되므로, 맨 마지막 값을 제외한 값만 받는다.
 * 그러므로,
 * if (d+table[d][0]<=table.length){
 *   maxProfitCache(d+table[d][0],p+table[d][1],table); }
 * 이렇게 넘긴다면, d==table.length까지 넘기므로, 즉, next값이 table.length까지이다.
 * 그렇다면 d==table.length-1까지 값을 체크하고 next값은 basecase에 걸리고,
 * 모든 값을 체크할 수 있다.
 * **이렇게 next를 넘기며 모든 값을 체크하는 로직은 경계값을 항상 경계해야한다.
 *
 * ----------------------------------o(n!)
 * table을 통째로 받아 (1,2) (2,1) 같은 중복을 방지하기 위해 visited도 같이 보내었다.
 * <p>
 * brute force 로는 시간초과가 나서, dp로 풀었다.
 * [d][p] 로 그 날짜에 들어갈 수있는 최대 profit을 넣었다.
 * <p>
 * brute force로 시간안에 풀려면?
 * 2가지 선택권. 그날짜를 선택한다 or not
 * 그렇다면 O(2^n)
 */
public class ResignationChooseOrNotAndBruteForceCache {
    private int[][] cache = new int[16][15000 + 1];
    private int best;

    public int sol(int n, int[][] table) {
        boolean[] visited = new boolean[n];
//        optimalProfit(table, visited, 0);
        for (int i = 0; i < 16; i++) {
            Arrays.fill(cache[i], -1);
        }
        //visited으로 순서 강제.
        int b = optimalProfitCache(0,0,table,visited);
        System.out.println("b="+b);
        for (int i = 0; i < 16; i++) {
            Arrays.fill(cache[i], -1);
        }
        int a = maxProfitCache(0,0,table);
        System.out.println(a);
        for (int i = 0; i < 16; i++) {
            Arrays.fill(cache[i], -1);
        }
        maxProfit2(0, 0, table);
        return best;
    }
    //n!개 만큼, 모든 경우의수를 선택하는 로직이다.
    private int optimalProfitCache(int d, int p, int[][] table, boolean[] visited) {
        if (d >= table.length) {
//            System.out.println(p);
            return p;
        }
        if (cache[d][p] > -1) return cache[d][p];
        cache[d][p] = 0;
        for (int i = d; i < table.length; i++) {
            if (!visited[i]) {
                //날짜안에 들어오는 경우만. 나는 0~n-1까지이다.
                if (table[i][0] + i <= table.length) {
                    //중복으로 처리되는 문제때문에 무한반복이 일어난다.
                    //isValid로 중복체크를 해주었다.
                    if (isValid(visited, i, table[i][0] + i)) {
                        for (int j = i; j < table[i][0] + i; j++) {
                            visited[j] = true;
                        }
                        /*for (int j = 0; j < visited.length; j++) {
                            System.out.print(visited[j] + " ");
                        }
                        System.out.println();*/
//                        ans = Math.max(ans, optimalProfit(table, visited, p + table[i][1]));
                        cache[d][p] = Math.max(cache[d][p], optimalProfitCache(i + table[i][0], p + table[i][1], table, visited));

                        for (int j = i; j < table[i][0] + i; j++) {
//                            System.out.println("t="+table[i][0]+" j="+j);
                            visited[j] = false;
                        }
                    }
                }
            }
        }
        //즉, 방문이 되었는데, cost에는 +를 안해주는 경우.
        visited[d] = true;
        cache[d][p] = Math.max(cache[d][p], optimalProfitCache(d + 1, p, table, visited));
        visited[d] = false;
        return cache[d][p];
    }
    private int maxProfitCache(int d, int p, int[][] table){
        if (d>=table.length){
            cache[d][p] = Math.max(cache[d][p],p);
            return cache[d][p];
        }
        if (cache[d][p]>-1) return cache[d][p];
        cache[d][p] = maxProfitCache(d+1,p,table);
        // d+table[d][0]을 넘기면 이 index부터 시작이다.
        // 즉, 마지막 index값을 넘기는 것이 아니므로,
        // d+table[d][0]이 경게값까지 넘겨줘야 맨 마지막 값까지 제대로 처리가 가능하다.
        if (d+table[d][0]<=table.length){
            cache[d][p] = Math.max(cache[d][p], maxProfitCache(d+table[d][0],p+table[d][1],table));
        }
        return cache[d][p];
    }
    //이렇게 사용할 수 없다. cache에 부분값들이 들어갈 수 없으므로,
    /*private void maxProfitCache2(int d, int p, int[][] table) {
        if (d >= table.length) {
            cache[d][p] = Math.max(cache[d][p], p);
            best = cache[d][p];
            return;
        }
        if (cache[d][p] > -1) return;
        //이렇게 cache를 0으로 초기화 해주지 않는다면, 최적값이 달라질 수 있다.
        //방문 된 곳이 또 방문이 되므로.
        cache[d][p] = 0;
        maxProfitCache2(d + 1, p, table);
        if (d + table[d][0] < table.length) {
            maxProfitCache2(d + table[d][0], p + table[d][1], table);
        }
    }*/
    //bruteforce O(2^n)
    //1~n까지만 n+1일날 퇴사이므로, 0~n-1 까지 되어야한다.
    private void maxProfit2(int d, int p, int[][] table) {
        //base case
        if (d >= table.length) {
            best = Math.max(best, p);
            return;
        }
        //먼저 선택을 안한 경우
        maxProfit2(d + 1, p, table);
        //선택을 한 경우.
        if (d + table[d][0] <= table.length) {
            maxProfit2(d + table[d][0], p + table[d][1], table);
        }
    }

    //int
    private void optimalProfit(int[][] table, boolean[] visited, int p) {
        int x = -1;
        for (int i = 0; i < table.length; i++) {
            if (!visited[i]) {
                x = i;
                break;
            }
        }
        if (x == -1) {
            best = Math.max(best, p);
//            System.out.println(best+" b=");
            return;
//            System.out.println(p);
//            return p;
        }
//        System.out.println("x=" + x);
//        int ans = 0;
        for (int i = x; i < table.length; i++) {
            if (!visited[i]) {
                //날짜안에 들어오는 경우만. 나는 0~n-1까지이다.
                if (table[i][0] + i <= table.length) {
                    //중복으로 처리되는 문제때문에 무한반복이 일어난다.
                    //isValid로 중복체크를 해주었다.
                    if (isValid(visited, i, table[i][0] + i)) {
                        for (int j = i; j < table[i][0] + i; j++) {
                            visited[j] = true;
                        }
                        /*for (int j = 0; j < visited.length; j++) {
                            System.out.print(visited[j] + " ");
                        }
                        System.out.println();*/
//                        ans = Math.max(ans, optimalProfit(table, visited, p + table[i][1]));
                        optimalProfit(table, visited, p + table[i][1]);
                        for (int j = i; j < table[i][0] + i; j++) {
//                            System.out.println("t="+table[i][0]+" j="+j);
                            visited[j] = false;
                        }
                    }
                }
            }
        }
        //즉, 방문이 되었는데, cost에는 +를 안해주는 경우.
        visited[x] = true;
        optimalProfit(table, visited, p);
        visited[x] = false;
//        System.out.println("x=" + x + " vi=" + visited[x]);
//        return ans;
    }


    private boolean isValid(boolean[] visited, int start, int size) {
        for (int i = start; i < size; i++) {
            if (visited[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 15;
        int[][] table = {
                {5, 50}, {4, 40}, {3, 30}, {2, 20}, {1, 10}, {1, 10},
                {2, 20}, {1, 30}, {2, 40}, {5, 50}, {4, 30}, {1, 20},
                {1, 50}, {1, 30}, {5, 50},
        };
        /*int n = 10;
        int[][] table = {
                {5, 50}, {4, 40}, {3, 30},
                {2, 20}, {1, 10}, {1, 10},
                {2, 20}, {3, 30}, {4, 40},
                {5, 50},
        };*/
        /*int n = 7;
        int[][] table = {
                {3, 10},
                {5, 20},
                {1, 10},
                {1, 20},
                {2, 15},
                {4, 40},
                {2, 200},
        };*/
        ResignationChooseOrNotAndBruteForceCache resignationChooseOrNotAndBruteForceCache = new ResignationChooseOrNotAndBruteForceCache();
        System.out.println(resignationChooseOrNotAndBruteForceCache.sol(n, table));


    }
}
