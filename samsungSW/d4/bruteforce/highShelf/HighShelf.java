package samsungSW.d4.bruteforce.highShelf;

import java.util.ArrayList;
import java.util.List;

public class HighShelf {
    private static final int INF = 987654321;
    /**
     * height :b
     * N명
     * Hi
     * N<=20
     *B<=sum(Hi의 합)
     * 1<=Hi<=10,000
     *
     * 탑의 높이와 B의 차이가 가장 작은 것.
     * -조합으로 완탐 가능.
     */
    public static int lowerHeight(int[] heights,int b){
        int n = heights.length;
        List<Integer> combi = new ArrayList<>();
        int min = INF;
        for (int i = 1; i <= n; i++) {
            //여기에서 Math.min을 안해줘서 큰일날뻔.
            min = Math.min(min,getMinHeight(n,i,combi,heights,b));
        }
        return min;
    }

    private static int getMinHeight(int n, int r, List<Integer> combi, int[] heights, int b) {
        if (r==0){
            int sum = 0;
            for (int i = 0; i < combi.size(); i++) {
                sum += heights[combi.get(i)];
            }
            sum -= b;
            if (sum<0) return INF;
            else return sum;
        }
        int ret = INF;
        int smallest = combi.isEmpty()?0:combi.get(combi.size()-1)+1;
        for (int i = smallest; i < n; i++) {
            combi.add(i);
            ret = Math.min(ret,getMinHeight(n,r-1,combi,heights,b));
            combi.remove(Integer.valueOf(i));
        }
        return ret;
    }

    public static void main(String[] args) {
        int n = 10;
        int b = 2780;
        int[] height = {268, 61, 201, 535, 464, 168, 491, 275, 578, 153};
        System.out.println(lowerHeight(height,b));
    }
}
