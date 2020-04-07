package strategiesOfAlgorithms.dynamicProgramming.dp.snail;

import java.util.Arrays;

public class Snail3 {
    //[days][meter]
    private double[][] cache;
    public double sol(int n,int m){
        cache = new double[n+1][m+1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(cache[i],-1);
        }
        return climb(0,0,n,m);
        //이렇게 해서 parameter의 개수를 줄일 수 있다.
//        return climb2(n,m);
    }

    private double climb2(int n, int m) {
        if (m==0){
            if (n<=0) {
                return 1;
            }
            return 0;
        }
        //이것을 아래에다가 놔둔다면 이렇게 설계하는 것도 가능하다.
        if (cache[n][m]>-1) return cache[n][m];
        cache[n][m] = 0.75*climb2(n-2,m-1)+0.25*climb2(n-1,m-1);
        return cache[n][m];
//        return 0.75*climb2(n-2,m-1)+0.25*climb2(n-1,m-1);
    }

    private double climb(int meter, int days, int n, int m) {
        //base case
        if (days==m){
            if (meter>=n) return 1;
            return 0;
        }
        if (cache[meter][days]>-1) return cache[meter][days];
        cache[meter][days] = 0.75*climb(meter+2,days+1,n,m)+0.25*climb(meter+1,days+1,n,m);
        return cache[meter][days];
//        return 0.75*climb(meter+2,days+1,n,m)+0.25*climb(meter+1,days+1,n,m);
    }

    public static void main(String[] args) {
        int n =5;
        int m =3;
        Snail3 snail3 = new Snail3();
        System.out.println(snail3.sol(n,m));
    }
}
