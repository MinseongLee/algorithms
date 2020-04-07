package baekjoon.implementation.easy.commonNum;

import java.util.ArrayList;
import java.util.List;

/**
 * 공약수 개수 구하기.
 * 이 수 집합의 최대공약수를 구한 다음 이 최대공약수를 1부터 나누어 떨어지는
 * 모든 수를 구했다.
 * 1억이라 할지라도 그냥 for문 돌리면 0.5초 정도 걸린다.
 */
public class CommonNum {
    public void sol(int[] numList) {
        //최대공약수부터 구하기.
        int gcd = gcd(numList[0],numList[1]);
        if (numList.length==3){
            gcd = gcd(gcd,numList[2]);
        }
        //세 수의 최대공약수를 구한 후,
        for (int num = 1; num <= gcd; num++) {
            if (gcd%num==0) {
                System.out.println(num);
            }
        }
    }
    private int gcd(int m, int n) {
        if (n == 0) return m;
        //%를 해주면 만약 m이 n보다 작다면 두 값이 체인지가 된다.
        //그래서 m>=n 이란 조건을 따로 안써도 되는 것이다.
        //m>=n이 m%n 에 포함되므로.
        return gcd(n, m % n);
    }
    public static void main(String[] args) {
        int[] list = {100000000,100000000,100000000};
        CommonNum commonNum = new CommonNum();
        long start = System.currentTimeMillis();
        commonNum.sol(list);
        int num = 100000000;
        List<Integer> a = new ArrayList<>();

        for (int i = 1; i <= num; i++) {
            if (num%i==0) a.add(i);
        }
        System.out.println(a);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
