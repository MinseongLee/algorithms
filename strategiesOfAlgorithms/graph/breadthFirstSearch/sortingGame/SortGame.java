package strategiesOfAlgorithms.graph.breadthFirstSearch.sortingGame;

import java.util.*;

/**
 * resolve it 3/3/2020
 *
 */
public class SortGame {
    private static Map<List<Integer>,Integer> dist;

    //그냥 모든 상태를 만드는 것인데.. 0~n-1까지의 번호로 만들면 된다.
    private static void makeState(List<Integer> sequence){
        int n = sequence.size();
        dist = new HashMap<>();
        Queue<List<Integer>> q = new LinkedList<>();
        dist.put(sequence,0);
        q.add(sequence);
        while(!q.isEmpty()){
            List<Integer> here = q.poll();
            for (int i = 0; i < n; i++) {
                for (int j = i+1; j < n; j++) {
                    //reverse 한 후 넘길것. - i~j까지 reverse
                    List<Integer> there = reverse(here,i,j);
                    if (dist.get(there)==null){
                        dist.put(there,dist.get(here)+1);
                        q.add(there);
                    }
                }
            }
        }
    }

    private static List<Integer> reverse(List<Integer> here, int start, int end) {
        List<Integer> there = new ArrayList<>(here);
        for (int i = start,j=end; i < j; i++,j--) {
            swap(there,i,j);
        }
        return there;
    }

    private static void swap(List<Integer> there, int i, int j) {
        int tmp = there.get(i);
        there.set(i,there.get(j));
        there.set(j,tmp);
    }

    public static int sol(int[] sequence){
        int n = sequence.length;
        List<Integer> se = new ArrayList<>();
        //첫 상태가 end값.
        for (int i = 0; i < n; i++) {
            se.add(i);
        }
        makeState(se);
        //여기서 만들어진 상태들을 가지고 sequence에 있는 내용을 구할 것.
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int number = 0;
            for (int j = 0; j < n; j++) {
                if (sequence[i]>sequence[j]){
                    number++;
                }
            }
            ans.add(number);
        }
        return dist.get(ans);
    }

    public static void main(String[] args) {
        int n = 8;
        int[] sequence = {1,2,3,4,8,7,6,5};
//        int[] sequence = {1,2,3};
        long start = System.currentTimeMillis();
        System.out.println(sol(sequence));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
