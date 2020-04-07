package baekjoon.bruteForce.sevenDwarf;

import java.util.Arrays;

/**
 * O(n^2)
 */
public class SevenDwarf {
    public void sol(int[] dwarfs){
        int sum =0;
        for (int i = 0; i < dwarfs.length; i++) {
            sum += dwarfs[i];
        }
        int[] ans = new int[7];
        for (int i = 0; i < dwarfs.length-1; i++) {
            int first = dwarfs[i];
            for (int j = i+1; j < dwarfs.length; j++) {
                int second = dwarfs[j];
                if (sum-(first+second)==100){
                    //답 찾음
                    for (int k = 0,g=0; k < dwarfs.length; k++) {
                        if (k==i||k==j) continue;
                        ans[g++] = dwarfs[k];
                    }
                }
            }
        }
        Arrays.sort(ans);
        for (int i = 0; i < ans.length; i++) {
            System.out.println(ans[i]);
        }
    }

    public static void main(String[] args) {
        int[] list = {20,7,23,19,10,15,25,8,13};
        SevenDwarf sevenDwarf = new SevenDwarf();
        sevenDwarf.sol(list);
    }
}
