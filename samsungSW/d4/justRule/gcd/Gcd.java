package samsungSW.d4.justRule.gcd;

/**
 * A<B 찾는 것
 * % 연산자 총 k 번
 *
 * % 연산자 k 번 수행 동안 나올수있는 숫자의 A,B의 최소값.
 * A>B를 만족하는
 * A B 를 출력
 */
public class Gcd {
    //%연산자가 몇번?
    //gcd(a,b)=gcd(b,a%b), gcd(a,0)=a 를 만족하는
    public static long[] getAb(int k){
        //해답
        //a=b, a+b=a 가 답이다.
        long a =2;
        long b =1;
        for (int i = 1; i < k; i++) {
            long tmp = a;
            //a에다가 a+b를 주면 된다.
            a = a+b;
            //b에다가 a를 주고
            b = tmp;

        }
        long[] ans = new long[2];
        ans[0] = a;
        ans[1] = b;
        return ans;
    }
}
