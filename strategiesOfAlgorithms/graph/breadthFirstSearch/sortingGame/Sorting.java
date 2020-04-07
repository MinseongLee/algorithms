package strategiesOfAlgorithms.graph.breadthFirstSearch.sortingGame;

import java.util.*;


public class Sorting {
    private static Map<List<Integer>,Integer> toSort = new HashMap<>();
    //n만 받으면 된다. 실제 수열이 어떻게 이루어졌는지 확인을 굳이 안해도 된다.
    //왜하면 0~n-1까지의 변경된 위치에 따라 변경 최소값을 저장할 것이기 떄문
    public static void bfs(int n){
        List<Integer> perm = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            perm.add(i);
        }
        Queue<List<Integer>> q = new LinkedList<>();
        q.add(perm);
        toSort.put(perm,0);
        while (!q.isEmpty()) {
            List<Integer> here = q.poll();
            int cost = toSort.get(here);
            for (int i = 0; i < n; i++) {
                for (int j = i+2; j <= n; j++) {
                    List<Integer> there = reverse(here,i,j);
                    //즉, 미방문된 노드에 방문.
                    if (toSort.get(there)==null){
                        toSort.put(there,cost+1);
                        q.add(there);
                    }
                }
            }
        }
    }

    private static List<Integer> reverse(List<Integer> here, int i, int size) {
        //deep copy를 해서 swap을 하면, arrayList는 swap 비용이 더 비싸다.
        List<Integer> there = new ArrayList<>();
        //for문을 3개로 나누어 써서 길어졌지만, O(n) 시간만큼 딱 떨어진다.
        for (int j = 0; j < i; j++) {
            there.add(here.get(j));
        }
        for (int j = size-1; j >= i; j--) {
            there.add(here.get(j));
        }
        for (int j = size; j < here.size(); j++) {
            there.add(here.get(j));
        }
        return there;
    }
}
