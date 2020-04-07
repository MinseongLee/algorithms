package baekjoon.implementation.superEasy.isPrime;

/**
 * 임의의 자연수 n에 대하여, n보다 크고, 2n보다 작거나 같은 소수는
 * 적어도 하나 존재한다는 내용을 담고 있다.
 * **이것은 증명이 된 내용.
 */
public class IsPrime {
    public int sol(int n){
        int cnt = 0;
        for (int i = n+1; i <= 2*n; i++) {
            if (isPrime(i)){
                cnt++;
            }
        }
        return cnt;
    }
    //o(루트n) 동작하는 소수 판별 알고리즘.
    //n이 소수인지 판별.
    private boolean isPrime(int n) {
        if (n<=1) return false;
        else if (n==2) return true;
        if (n%2==0) return false;
        int nsqrt = (int)Math.sqrt(n);
        for (int div = 3; div <= nsqrt; div+=2) {
            if (n%div==0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 0;
        IsPrime isPrime = new IsPrime();
        System.out.println(isPrime.sol(n));
    }
}
