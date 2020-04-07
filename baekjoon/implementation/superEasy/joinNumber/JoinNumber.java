package baekjoon.implementation.superEasy.joinNumber;

/**
 * 1부터 N까지의 수를 이어서 쓰면 다음과 같이 새로운 하나의 수를 얻을 수 있다.
 *
 * 1234567891011121314151617181920212223...
 *
 * 이렇게 만들어진 새로운 수는 몇 자리 수일까? 이 수의 자릿수를 구하는 프로그램을 작성하시오.
 *
 * 자리수 별로 나누어서 전체 자리수를 구하는 점화식을 만들었다.
 * 자리 수 k
 * 점화식 : (end-start+1)*k;
 * end는 9,,,9 (k자리수만큼의)
 * start는 1,,,0 (k자리수만큼의)
 */
public class JoinNumber {
    public long sol(int n){
        String num = String.valueOf(n);
        int k = num.length();
        //k==1인경우를 처리.
        if (k==1) return n;
        long sum =0;
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        start.append("1");
        end.append("9");
        sum += 9;
        for (int i = 2; i <= k-1; i++) {
            start.append("0");
            end.append("9");
            sum += (Long.parseLong(end.toString())-Long.parseLong(start.toString())+1)*i;
        }
        //마지막인 경우 처리.
        start.append("0");
        sum += (n-Long.parseLong(start.toString())+1)*k;
        return sum;
    }

    public static void main(String[] args) {
        int n = 120;
        JoinNumber joinNumber = new JoinNumber();
        System.out.println(joinNumber.sol(n));
    }
}
