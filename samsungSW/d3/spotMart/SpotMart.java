package samsungSW.d3.spotMart;

/**
 * N개의 과자
 * ai그램 무게
 * 과자 두 봉지의 무게(최대값) <M
 * 2<=N<=1000
 * 두 개 들고갈 방법 x -1
 */
public class SpotMart {
    public static int takeTwoStack(int[] snacks,int m){
        int n = snacks.length;
        int max = -1;
        for (int i = 0; i < n; i++) {
            int sum = snacks[i];
            for (int j = i+1; j < n; j++) {
                sum+= snacks[j];
                if (sum<=m){
                    max = Math.max(max,sum);
                }
                sum-=snacks[j];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int n = 3;
        int m = 45;
        int[] snack;
    }
}
