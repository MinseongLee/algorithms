package strategiesOfAlgorithms.greedyAlgorithm.matchOrder;

import java.util.Arrays;

public class MatchOrder {
    //이 문제에서 first는 의미가 없다.
    public static int rating(int n,int[] russian,int[] korean){
        int win=0,first=0,last=n-1;
        Arrays.sort(russian);
        Arrays.sort(korean);
        //만약.. 사람수가 맞지 않는다면, first의 존재도 굉장히 중요해진다.
        //그런데 사람수가 맞다면,, 그냥 가장 큰것들과 비교하여
        //하나씩 증가시키면 된다.
        for (int i = n-1; i >= 0; i--) {
            if (russian[i]<=korean[last]){
                last--;
                win++;
            }
            /*else if (russian[i]>korean[last]){
                first++;
            }*/

        }
        return win;
    }

    public static void main(String[] args) {
//        int n = 3;
//        int[] russian = {1,2,3};
//        int[] korean = {3,2,1};
        int n = 4;
        int[] russian = {2,3,4,5};
        int[] korean = {1,2,3,4};
        System.out.println(rating(n,russian,korean));
    }
}
