package strategiesOfAlgorithms.dataStructure.partialSum.christmas;

import java.util.Arrays;

/**
 * 문제를 세심하고 꼼꼼하게.. 읽는거 진짜 너무 중요.
 *
 * 문제 해결전략.
 * 1. pSum[T]=pSum[H-1] 을 이용해야한다. 그래야
 * 모든 경우의 수를 확인하지 않고 처음 구한 부분합을 가지고 처리할 수 있다.
 * pSum[j]=pSum[j-1]%k , 원소는 0~k-1 이 저장.
 * 2. 구한 pSum에서 최대 n개 중 2개를 선택하는 경우의 수이므로,
 * 이건 1~n-1까지의 합이다. 그러므로, n(n-1)/2 로 모든 경우의 수를 구할 수 있다.
 * 물론 이때, count를 해주는 함수가 있어서 count를 해준 후,
 * (count>=2)인 경우에만 처리할 수 있도록 해주어야한다.
 */

/**
 * 문제를 잘못이해했다.. 정확히 같은 수..즉, 한개 이상이란소리..남는인형이없다..
 * 즉, k의 배수란 의미.
 *
 * 즉, 이 문제는 특정 role을 찾는 것이 매우 중요하다.
 * 시간 안에 풀기 위해. - 부분합을 만드는 구조도 굉장히 중요.
 * H~T번까지 구매 가능.
 * (divSum[T]-divSum[H-1])%k=0; : 정답 조건.
 * 그리고
 * k의 배수,
 * divSum[T]%k=divSum[H-1]%k : 정답 조건.
 *
 */

/**
 * 문제 해설은 이해했다.
 * 그런데.. 왜 부분합을 이중 for문 돌리지 않아도 되는 것일까?
 * 딱 한번만 구해도 모든 경우의 수가 처리가능한 것일까?
 * 숫자 하나인 경우를 divSum[-1]=0 인 경우를 맨 앞에다가 추가를 했다.
 * 그래서 첫 번째 상자부터 주문하는 경우까지 고려하였다.(다 counting)
 * (이건.. 내일 다시 생각해보자. 이제 그 다음 챕터로 넘어가자.)
 * ---다시 풀어볼 것.-3/1/2020
 */

public class Christmas {
    private static final int MOD = 20091101;
    //첫번째
    //D[]의 부분 합 배열 divSum[]과 k가 주어질 때,
    // 몇 가지 방법으로 살 수 있는지 반환한다.
    //divSum[]의 첫 번째 원소 전에 0을 삽입했다고 가정한다.
    public static int waysToBuy(int[] divSum,int k){
        long ret = 0;
        //divSum[]의 각 값을 몇 번이나 본 적 있는지 기록
        long[] count = new long[k];
        int n = divSum.length;
        for (int i = 0; i < n; i++) {
            count[divSum[i]]++;
        }
        // 두 번 이상 본 적 있다면 이 값 중
        // 두 개를 선택하는 방법의 수를 더한다.
        for (int i = 0; i < k; i++) {
            if (count[i]>=2){
                ret = (ret+((count[i]*(count[i]-1))/2))%MOD;
            }
        }
        return (int)ret;
    }
    //D[]의 부분 합 배열 divSum과 k가 주어질 때,
    // 겹치지 않게 몇 번이나 살 수 있는지 반환한다.
    //divSum[]의 첫 번째 원소 전에 0을 삽입했다고 가정한다.
    public static int maxBuys(int[] divSum,int k){
        //ret[i]=첫 번째 상자부터 i번째 상자까지 고려했을 때
        // 살 수 있는 최대 횟수
        int[] ret = new int[divSum.length];
        // prev[s]=divSum[]이 s였던 마지막 위치
        int[] prev = new int[k]; //cache 이다.
        Arrays.fill(prev,-1);
        for (int i = 0; i < divSum.length; i++) {
            //i번째 상자를 아예 고려하지 않은 경우
            if (i>0){
                ret[i] = ret[i-1];
            }
            else{
                ret[i] = 0;
            }
            // divSum[i]를 전에도 본 적이 있으면,
            // prev[divSum[i]]+1부터 여기까지 쭉 사 본다.
            int loc = prev[divSum[i]];
            if (loc!=-1) {
                ret[i] = Math.max(ret[i],ret[loc]+1);
            }
            // prev[]에 현재 위치를 기록한다.
            prev[divSum[i]] = i;
        }
        return ret[ret.length-1];
    }
    //divSum[i] = sum[i-1]%k;
    public static int[] allSum(int[] sequence,int k){
        int n = sequence.length;
        //divSum[-1] =0 을 추가. 그래야 한개인 경우도 샐 수 있으므로,
        int[] divSum = new int[n+1];
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += sequence[i-1];
            divSum[i] = sum%k;
        }
        return divSum;
    }

    public static void main(String[] args) {
        int n = 6;
        int k = 4;
//        int[] d = {1,2,3,4,5,6};
        int[] d = {4,4,4,4};
        int[] divSum = allSum(d,k);
        System.out.println(waysToBuy(divSum,k));
        System.out.println(maxBuys(divSum,k));
    }
}
