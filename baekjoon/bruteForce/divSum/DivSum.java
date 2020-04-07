package baekjoon.bruteForce.divSum;

/**
 * 시간복잡도
 * O(nm);
 * //m은 숫자의 길이.
 *
 * 이 로직을 넣어서 한 번 틀렸다. 왜냐하면 m이 n보다 커졌을 때,
 * 나중에 더 작은 값이 올 수있는 경우가 존재하므로.
 * else if (m>n) return 0;
 */
public class DivSum {
    public int sol(int n){
        // 여기 for문에 답이 존재하지 않다면, 0
        for (int i = 1; i <= n; i++) {
//            System.out.println(i);
            int m = i;
            String s = String.valueOf(i);
            for (int j = 0; j < s.length(); j++) {
                m += s.charAt(j)-'0';
            }
//            System.out.println("m="+m);
            if (m==n) return i;
//            else if (m>n) return 0;
        }
        return 0;
    }
    public static void main(String[] args) {
        int n = 100;
        DivSum divSum = new DivSum();
        System.out.println(divSum.sol(n));

    }
}
