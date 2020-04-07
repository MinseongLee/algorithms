package strategiesOfAlgorithms.greedyAlgorithm.lunchBox;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LunchBox2 {
    public int sol(int n,int[] M,int[] E){
        List<Pair> lunch = createLunch(M,E);
        Collections.sort(lunch,Collections.reverseOrder());
        return getMinimumEatingTime(lunch);
    }

    private int getMinimumEatingTime(List<Pair> lunch) {
        int n = lunch.size(),time=0,eating=0,ans=0;
        //내가 짠 로직은 아주 직관적.
        for (int i = 0; i < n; i++) {
            time += lunch.get(i).wait;
            eating-=lunch.get(i).wait;
            if (eating<0) eating=0;
            eating = Math.max(eating,lunch.get(i).eat);

            //in book
            //즉, time에 맨 마지막 eat을 더한 값이 가장 큰값이 답이다.
            //왜냐하면 eat은 계속 먹어가므로, 병렬이므로,
            ans = Math.max(ans,time+lunch.get(i).eat);
        }
        if (eating>0) time+= eating;
        return time;
    }

    private List<Pair> createLunch(int[] M, int[] E) {
        int n =M.length;
        List<Pair> lunch = new ArrayList<>();
        for (int i = 0; i <n ; i++) {
            lunch.add(new Pair(M[i],E[i]));
        }
        return lunch;
    }

    public static void main(String[] args) {
        int n = 3;
        int[] M = {2,5,6};
        int[] E = {1,8,2};
        LunchBox2 lunchBox2 = new LunchBox2();
        System.out.println(lunchBox2.sol(n,M,E));
    }
}
