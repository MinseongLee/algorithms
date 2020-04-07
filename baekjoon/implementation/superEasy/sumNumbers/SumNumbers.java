package baekjoon.implementation.superEasy.sumNumbers;

/**
 * 이 문제에서 주의할 점은. integer overflow이다.
 */
public class SumNumbers {
    public int sol(long s){
        long n = 0;
        long sum = 0;
        while(sum<s){
            n++;
            sum = n*(n+1)/2;
        }
        //sum이 s보다 크거나 같으면 종료.
        if (sum==s) return (int)n;
        else return (int)n-1;
    }

    public static void main(String[] args) {
        long s = 4294967295l;
        SumNumbers sumNumbers = new SumNumbers();
        System.out.println(sumNumbers.sol(s));

    }
}
