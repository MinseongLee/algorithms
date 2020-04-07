package strategiesOfAlgorithms.graph.breadthFirstSearch.sortingGame;

import java.util.*;

/**
 * 대부분의 경우, linkedList보다 arrayList가 더 빠르다.
 */

public class SortingGame2 {
    public int sol(int n,int[] sequence){
        List<Integer> start = createSequence(n);
        Map<List<Integer>,Integer> map = new HashMap<>();
        cntSort(start,map);
        List<Integer> ans = getOrder(sequence);
        return map.get(ans);
    }

    private List<Integer> getOrder(int[] sequence) {
        List<Integer> ans = new LinkedList<>();
        int[] se = new int[sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            for (int j = 0; j < sequence.length; j++) {
                if (i==j) continue;
                if (sequence[i]>sequence[j]) se[i]++;
            }
        }
        for (int i = 0; i < se.length; i++) {
            ans.add(se[i]);
        }
        return ans;
    }

    private void cntSort(List<Integer> start, Map<List<Integer>, Integer> map) {
        int n = start.size();
        Queue<List<Integer>> q = new LinkedList<>();
        map.put(start,0);
        q.add(start);
        while(!q.isEmpty()){
            List<Integer> here = q.poll();
            for (int i = 0; i <n ; i++) {
                for (int j = i+1; j < n; j++) {
                    List<Integer> there = reverse(here,i,j);
                    if (map.get(there)==null){
                        map.put(there,map.get(here)+1);
                        q.add(there);
                    }
                }
            }
        }
    }

    private List<Integer> reverse(List<Integer> here, int start, int end) {
        List<Integer> there = new ArrayList<>(here);
        for (int i = start,j=end; i < j; i++,j--) {
            swap(there,i,j);
        }
        return there;
    }

    private void swap(List<Integer> there, int a, int b) {
        int tmp  =there.get(a);
        there.set(a,there.get(b));
        there.set(b,tmp);
    }

    private List<Integer> createSequence(int n) {
        List<Integer> start = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            start.add(i);
        }
        return start;
    }

    public static void main(String[] args) {
        int n = 8;
        int[] sequence = {1,2,3,4,8,7,6,5};
        long start = System.currentTimeMillis();
        SortingGame2 sortingGame2 = new SortingGame2();
        System.out.println(sortingGame2.sol(n,sequence));
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
