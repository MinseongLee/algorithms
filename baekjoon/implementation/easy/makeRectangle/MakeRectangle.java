package baekjoon.implementation.easy.makeRectangle;

public class MakeRectangle {
    public int sol(int n){
        int sum =0;
        for (int i = 1; i <= n; i++) {
            sum++;
            for (int j = 2;; j++) {
                if (j*j<=i){
                    if (i%j==0) sum++;
                }else break;
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        int n =6;
        MakeRectangle makeRectangle = new MakeRectangle();
        System.out.println(makeRectangle.sol(n));
    }
}
