package baekjoon.bruteForce.equalDifference;

public class EqualDifference {
    //1~9, 10~99 까지는 무조건 등차수열.
    public int sol(int n){
        //정확하게는 122까지 99이다.
        if (n<=99) return n;
        int cnt = 99;
        for (int num = 100; num <= n; num++) {
            boolean ok = true;
            String s = String.valueOf(num);
            int first = s.charAt(0)-s.charAt(1);
            for (int i = 2; i < s.length(); i++) {
                int second = s.charAt(i-1)-s.charAt(i);
                if (first!=second) {
                    ok = false;
                    break;
                }
            }
            if (ok) cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        int n = 1000;
        EqualDifference equalDifference = new EqualDifference();
        System.out.println(equalDifference.sol(n));
    }
}
