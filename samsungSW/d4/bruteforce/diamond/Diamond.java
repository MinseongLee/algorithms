package samsungSW.d4.bruteforce.diamond;

import java.util.Arrays;

/**
 * 고른 다이아몬드 크기 차가 k 이하인 것들을 묶음으로 가져간다.
 * (단 묶음은 여러 개일 수도 있다.)
 * 고를 수 있는 다이아몬드의 최대 개수.
 * 1<=N<=1,000
 * 0<=K<=10,000
 *
 *  최소 값은 1,
 *  dp로 풀수있다.
 *  k 이전 값은 + k 이후 값은 - 왜냐하면 k 이상 차이나는 것은 더하지 않으므로,
 */

/**
 * 1. 문제에는 안나와있지만 연속된 수라는 것을 깨닫는것이 매우 중요.
 * 2. 2개의 숫자만 비교하고,
 * 3. sort가 해결책. 즉, {a,b,c} 에서 c-a<=k 면, c-b<=k는 항상 만족을 이용.
 * 위의 내용을 깨닫는 순간 문제가 엄청 쉬워졌다.
 *
 * -이렇게, 제일 먼저 brute force 부터 생각하고,
 * 그다음 dp, bfs,dfs등을 생각할것.
 */
public class Diamond {
    //이 로직은 정확히 brute force 로직이다.
    public static int maxDiamond(int n,int k,int[] diaSize){
        Arrays.sort(diaSize);
        int ans=0;
        for (int i = 0; i < n; i++) {
            int dia = diaSize[i];
            //자기 자신
            int max=1;
            for (int j = i+1; j < n; j++) {
                if (diaSize[j]-dia<=k){
                    max++;
                }else break;
            }
            ans = Math.max(ans,max);
        }
        return ans;
    }
    //틀린 로직.
    //괜히 지래 겁먹어서 dp로 풀려고했다.
    public static int maxDiamondDp(int n,int k,int[] diaSize){
        int maxV = diaSize[0];
        for (int i = 1; i < n; i++) {
            if (maxV<diaSize[i]){
                maxV = diaSize[i];
            }
        }
        //n은 두 다이아몬드 크기 차.
        int[] dp = new int[maxV+1];
        for (int i = 0; i < n; i++) {
            int pick = diaSize[i];
            for (int j = i+1; j < n; j++) {
                dp[Math.abs(pick-diaSize[j])]++;
            }
        }
        int ans = 0;
        for (int i = 0; i <= maxV; i++) {
            if (i<=k){
                ans+= dp[i];
            }else{
                ans -= dp[i];
            }
        }
        //자기 자신 +1
        return ans+1;
    }
    public static void main(String[] args) {
        /*int n =2;
        int k = 0;
        int[] diaSize={1,1};*/
        int n =6;
        int k = 5;
        int[] diaSize={1,5,7,10,15,2};
        System.out.println(maxDiamond(n,k,diaSize));
    }
}
