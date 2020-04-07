package baekjoon.implementation.superEasy.caclScore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CalcScore {
    public void sol(int[] scores){
        List<Pair> sc = new ArrayList<>();
        for (int i = 0; i < scores.length; i++) {
            sc.add(new Pair(scores[i],i+1));
        }
        Collections.sort(sc);
        int sum = 0;
        int[] ans = new int[5];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sum+= sc.get(i).score;
            ans[i] = sc.get(i).number;
        }
        Arrays.sort(ans);
        for (int i = 0; i < 5; i++) {
            if (sb.length()>0) sb.append(" ");
            sb.append(ans[i]);
        }
        System.out.println(sum);
        System.out.println(sb);
    }

    public static void main(String[] args) {
        int[] scores = {20,30,50,48,33,66,0,64};
        CalcScore calcScore = new CalcScore();
        calcScore.sol(scores);
    }
}
