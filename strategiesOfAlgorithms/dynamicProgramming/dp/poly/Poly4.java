package strategiesOfAlgorithms.dynamicProgramming.dp.poly;


import java.util.Arrays;

/**
 * 틀린 이유:
 * cache[n][first] += add*poly(n-first,second);
 * 여기에서 += 을 해주지 않았다.
 * 나오는 값을 모두 +=해서 모든 경우수를 구하는 것이므로,
 */
public class Poly4 {
    private static int[][] cache;
    public static int sol(int n){
        cache = new int[n+1][n+1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(cache[i],-1);
        }
        int sum =0;
        for (int first = 1; first <= n; first++) {
            sum += poly(n,first);
        }
        return sum;
    }

    private static int poly(int n, int first) {
        if (cache[n][first]>-1) return cache[n][first];
        if (first==n) return 1;
        cache[n][first] =0;
        for (int second = 1; second+first <= n; second++) {
            int add = first+second-1;
            cache[n][first] += add*poly(n-first,second);
        }
        return cache[n][first];
    }

    public static void main(String[] args) {
        int n =4;
        System.out.println(sol(n));
    }
}
