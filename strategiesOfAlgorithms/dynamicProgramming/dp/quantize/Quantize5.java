package strategiesOfAlgorithms.dynamicProgramming.dp.quantize;

import java.util.Arrays;

/**
 * 쭉 적고. 한번 틀렸다. 그 이유는 base case의 순서때문이다.
 * if (parts==0) return INF;
 * if (first==num.length) return 0;
 * 이렇게 parts를 위에 올렸을 때, 틀렸었다. 왜냐하면 parts를 다 쓴 상태에서는
 * 계속 INF가 먼저 나갈 것이기 때문이다. 그러므로, first가 num.length에 도달했을 때,
 * 0이 먼저 리턴될수 있도록 앞에 놓아주어야한다.
 */
public class Quantize5 {
    private static final int INF = 987654321;
    private static int[][] cache;
    private static int quantize(int first, int parts, int[] num) {
        if (cache[first][parts]>-1) return cache[first][parts];
        if (first==num.length) {
            return 0;
        }
        if (parts==0) return INF;
        cache[first][parts] = INF;
        for (int div = first+1; div <= num.length; div++) {
            cache[first][parts] = Math.min(cache[first][parts],quantize(div,parts-1,num)+
                    errorCalc(first,div-1,num));
        }
        return cache[first][parts];
    }

    private static int errorCalc(int first, int end, int[] num) {
        int minElement =INF;
        for (int i = num[first]; i <=num[end] ; i++) {
            int subSum = 0;
            for (int j = first; j <= end; j++) {
                subSum += sqr(num[j]-i);
            }
            minElement = Math.min(minElement,subSum);
        }
        return minElement;
    }

    private static int sqr(int x) {
        return x*x;
    }
    public static int sol(int n,int s,int[] num){
        cache = new int[n+1][s+1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(cache[i],-1);
        }
        Arrays.sort(num);
        return quantize(0,s,num);
    }

    public static void main(String[] args) {
        int n = 9;
        int s = 3;
        int[] num = {1, 744, 755, 4, 897, 902, 890, 6, 777};
//        int[] A = {1 ,744, 55, 4, 297, 502, 890, 6, 777};
        System.out.println(sol(n,s,num));
    }
}
