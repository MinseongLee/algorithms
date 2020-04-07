package strategiesOfAlgorithms.dataStructure.linearStructure.josephus;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Josephus {
    public static void main(String[] args) {
        /*int n = 6;
        int k = 3;*/
        int n = 40;
        int k = 3;
        int[] live = live(n, k);
        for (int i = 0; i < live.length; i++) {
            System.out.print(live[i]+" ");
        }
        System.out.println();
    }
    /**
     * n명의 사람들이 원형 시계방향으로 한명씩 자살
     * 시계방향으로 k번째 살아 있는 사람이 자살.
     *
     * 조세푸스가 나머지 2명남으려면 첫 번째 병사로부터 몇 자리 떨어져야 하는지?
     * 3<=N<=1,000
     * 1<=K<=1,000
     *
     */
    public static int[] live(int n,int k){
        int[] ans = new int[2];
        List<Integer> people = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            people.add(i);
        }
        int index = 0;
        //remove 연산이 많이 들어가므로 LinkedList 로 값처리를 해주어야한다.
        while(people.size()!=2){
            people.remove(index);
            //System.out.println(people);
            index+=k-1;
            //exception
            if (index>=people.size()){
                index -= people.size();
            }
        }
        ans[0] = people.get(0);
        ans[1] = people.get(1);
        Arrays.sort(ans);
        return ans;
    }
}
