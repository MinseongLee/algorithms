package strategiesOfAlgorithms.dynamicProgramming.dp.donibal;

import java.util.Arrays;

public class Donibal5 {
    //[n][days]
    private double[][] cache = new double[51][101];
    private int[] degree;
    public String sol(int n,int d,int p,int[] q,int[][] villages){
        getDegree(villages);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q.length; i++) {
            initCache();
            if (sb.length()>0) sb.append(" ");
//            sb.append(getPercent(p,0,d,q[i],1,villages));
            sb.append(getPercent2(p,0,d,q[i],villages));
        }
        return sb.toString();
    }

    private void initCache() {
        for (int i = 0; i < 51; i++) {
            Arrays.fill(cache[i],-1);
        }
    }
    //this is dp.
    private double getPercent2(int here, int days, int d, int end, int[][] villages) {
        if (cache[here][days]>-1) return cache[here][days];
        if (days==d){
            if (here==end){
                return 1;
            }
            return 0;
        }
        cache[here][days] = 0;
        for (int next = 0; next < villages[here].length; next++) {
            int there = villages[here][next];
            if (there==1){
                cache[here][days] += getPercent2(next,days+1,d,end,villages)/degree[here];
            }
        }
        return cache[here][days];
    }

    //this is brute force
    private double getPercent(int here, int days, int d, int end, double dest, int[][] villages) {
        if (days==d){
//            System.out.println(here+ " "+end);
            if (here==end) {
//                System.out.println("dest="+dest);
                return dest;
            }
            return 0;
        }
        double ans = 0;
        for (int next = 0; next < villages[here].length; next++) {
            int there = villages[here][next];
            if (there==1){
//                System.out.println(degree[here]+" des="+(dest/degree[here]));
                ans += getPercent(next,days+1,d,end,dest/degree[here],villages);
            }
        }
        return ans;
    }

    //각 정점의 간선의 개수를 저장.
    private void getDegree(int[][] villages) {
        int n = villages.length;
        degree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (villages[i][j]==1){
                    degree[i]++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int d = 2;
        int p = 0;
        int[] q = {0,2,4};
        int[][] village = {
                {0 ,1 ,1 ,1 ,0},
                {1 ,0 ,0 ,0 ,1},
                {1 ,0 ,0 ,0 ,0},
                {1 ,0 ,0 ,0 ,0},
                {0 ,1 ,0 ,0 ,0},
        };
        Donibal5 donibal5 = new Donibal5();
        System.out.println(donibal5.sol(n,d,p,q,village));
    }
}
