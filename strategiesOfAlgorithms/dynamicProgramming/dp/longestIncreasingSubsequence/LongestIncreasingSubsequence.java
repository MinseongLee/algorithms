package strategiesOfAlgorithms.dynamicProgramming.dp.longestIncreasingSubsequence;

import java.util.ArrayList;
import java.util.List;

/**
 * 나는.. 처음인데,
 * 내가 만든 로직이 더 훌륭하다고 생각한다.
 */

public class LongestIncreasingSubsequence {
    private static int[] dp;
    private static int[] d = new int[100];
    public static void main(String[] args) {
//        int[] sequence = {1,5,2,4,7,5,6};
        int[] sequence = {30,10,10,12,5};
        System.out.println(solution(sequence));
        for (int i = 0; i < 5; i++) {
            System.out.println("d[i]="+d[i]);
        }
    }
    public static int solution(int[] sequence){
        dp = new int[sequence.length+1];
        //return longestSubsequence(sequence);
        //여기서 j 값은 -1 이나 0을 넣어줘도된다.
        //return longestRecursion(sequence,0,-1);

        //-1이라는 가상값을 추가했으므로 -1을 해줘야한다.
        //복잡하다..흠.. 내 방법이 더 좋다고 생각한다.
        return lis2(sequence,-1)-1;
    }
    private static int longestRecursion(int[] sequence,int i,int j){
        //end
        if (i==sequence.length){
            //System.out.println("getMax");
            return getMax();
        }
        //base case
        if (j<0){
            //System.out.println("i="+i+" dp[i]="+dp[i]);
            if (dp[i]<=0){
                //아무것도 없더라도 최소 1은 가진다 각 부분 수열이.
                dp[i] =1;
            }
            return longestRecursion(sequence,i+1,i);
        }
        else if (dp[i]>0) return dp[i];
        else{
            //j>=0
            if (sequence[i]>sequence[j]){
                dp[i] = dp[j]+1;
                System.out.println("i="+i+" j="+j+" dp[i]="+dp[i]);
                //j=0 이 break 코드이다.
                j=0;
            }
        }
        return longestRecursion(sequence,i,--j);
    }
    // i 가 가르키는 값보다 작은 값 중 가장 큰값에다가 +1을 해주면
    // 계속 최적부분구조가 유지될 수 있다.
    // 이런 형태는 확실히.. for문이 더 편하다.
    // first of all, for
    private static int longestSubsequence(int[] sequence) {
        for (int i = 0; i < sequence.length; i++) {
            dp[i] = 1;
            for (int j = i-1; j >= 0; j--) {
                if (sequence[i]>sequence[j]){
                    dp[i] = dp[j]+1;
                    break;
                }
            }
        }
        int max = getMax();
        return max;
    }

    private static int getMax() {
        int max =0;
        for (int i = 0; i < dp.length; i++) {
            if (max<dp[i]){
                max = dp[i];
            }
        }
        return max;
    }
    //lis2(start) = S[start]에서 시작하는 최대 증가 부분 수열의 길이
    // S[start]보다 뒤에 있고 큰 수들 중 하나를 다음 숫자로 결정한 뒤,
    // 여기서 시작하는 부분 증가 수열의 최대치를 구함.
    public static int lis2(int[] sequence,int start){
        System.out.println("start="+start+ " d[start+1]="+d[start+1]);
        if (d[start+1]!=0) return d[start+1];
        // always S[start]는 있으므로 default = 1;
        d[start+1] = 1;
        for (int next = start+1; next < sequence.length; next++) {
            if (start == -1 || sequence[start]<sequence[next]){
                d[start+1] = Math.max(d[start+1],lis2(sequence,next)+1);
            }
        }
        return d[start+1];
    }
    //231p - brute force
    //A[i+1] 이후의 부분 수열에서 A[i]보다 큰 숫자들만 고른 부분 수열 B를 만들고
    //B의 lis를 재귀호출로 계산한다.
    public static int lis(List<Integer> sequence){
        //base case : sequence is empty
        if (sequence.isEmpty()) return 0;
        int ans = 0;
        for (int i = 0; i < sequence.size(); i++) {
            List<Integer> subsequence = new ArrayList<>();
            for (int j = i+1; j < sequence.size(); j++) {
                if (sequence.get(i)<sequence.get(j)){
                    subsequence.add(sequence.get(j));
                }
            }
            ans = Math.max(ans,1+lis(subsequence));
        }
        return ans;
    }
}
