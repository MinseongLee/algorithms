package baekjoon.bruteForce.urekaTheory;

public class UrekaTheory {
    public int sol(int k){
        int[] t = new int[k+1];
        for (int i = 1; i <= k; i++) {
            t[i] = i*(i+1)/2;
        }
        if (dfs(1,0,t,k,0)){
            return 1;
        }
        return 0;
    }
    //이렇게 짜면 중복조합.
    private boolean dfs(int here, int steps, int[] t, int k, int dest) {
        if (steps==3){
            if (dest==k) return true;
            return false;
        }
        //i=here을 넣고 i를 보냈으니, 중복되는 경우수를 세겠다는 의미.
        //만약 i+1이라면 중복되는 수를 세지 않겠단 의미.
        for (int i = here; i <= k; i++) {
            if (dfs(i,steps+1,t,k,dest+t[i])){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int k = 1000;
        UrekaTheory urekaTheory = new UrekaTheory();
        System.out.println(urekaTheory.sol(k));
    }
}
