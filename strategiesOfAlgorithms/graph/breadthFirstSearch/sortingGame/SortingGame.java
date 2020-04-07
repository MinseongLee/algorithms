package strategiesOfAlgorithms.graph.breadthFirstSearch.sortingGame;

import java.util.*;


/**
 * 이 문제를 brute force 로 풀려면 n!*n! 만큼의 시간복잡도가 있다.
 * 그러므로, 상태공간에서 최소값을 찾는 문제는 제일 먼저 bfs를 생각해볼 것.
 */

//배열은 인자로 사용할 때,
//int[] 같은 형보단, List, 즉, Colleciton interface 를 사용하는 것이 좋다.
//List, Set, Map, 등의 interface 들이 더 안전하다.

public class SortingGame {
    //1~8 까지의
    // n의 상태를 모두 저장하는 map
    private Map<List<Integer>,Integer> toSort = new HashMap<>();
    //**이렇게 상태를 미리 저장해 놓는 것. 정말 중요할 거같다.**
    //[0,,n-1]의 모든 순열에 대해 toSort[]를 계산해 저장
    //이렇게 상태를 미리 저장하고 값을 구하면 중복되는 여러 count 계산들을 피할 수 있다.
    private void precalc(int n){
        List<Integer> perm = new ArrayList<>();
        //값이 똑같을 필요가 없다. 상태를 저장하는 것이므로,
        //즉, 1,3,2,5 나 10,30,20,40 이나 같은 상태란 소리이다.
        for (int i = 0; i < n; i++) {
            perm.add(i);
        }
        Queue<List<Integer>> q = new LinkedList<>();
        q.add(perm);
        toSort.put(perm,0);
        while (!q.isEmpty()) {
            List<Integer> here = q.poll();
            int cost = 0;
            if (toSort.get(here)!=null){
                cost= toSort.get(here);
            }
            for (int i = 0; i < n; i++) {
                //i+2를 준것은 +1은 미포함이다. len를 의미.
                for (int j = i+2; j<=  n; j++) {
                    //여기서 int[] 를 사용하면, Map에 저장된 list값의 비교를 할 수가없다.
                    //즉, 일일이 다 비교를 해줘야만한다.
                    // so, Collection interface 를 사용하는 것이 좋다.
                    List<Integer> there = reverse(here, i, j);
                    /*System.out.println("after=");
                    for (int k = 0; k < here.size(); k++) {
                        System.out.print(there.get(k)+" ");
                    }
                    System.out.println();*/
                    if (toSort.get(there)==null){
//                        System.out.println("q="+q+" ");
                        toSort.put(there,cost+1);
                        q.add(there);
                    }
                }
            }
        }
    }
    public int solve(int[] perm){
        //perm 을 0,,,n-1의 순열로 변환
        int n = perm.length;
        List<Integer> fixed = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int smaller = 0;
            // 각 원소가 가지는 상대적 위치를 구해,
            // toSort에 저장된 list와 비교한다.
            for (int j = 0; j < n; j++) {
                if (perm[j]<perm[i]){
                    smaller++;
                }
            }
            fixed.add(smaller);
        }
        precalc(n);
        return toSort.get(fixed);
    }

    //이 로직은 다 좋은데 시간이 너무 오래걸린다.
    //배열을 각 정점으로 사용했다.
    //perm을 정렬하기 위해 필요한 최소 뒤집기 연산의 수를 계산
    public int bfs(int[] perm){
        int n = perm.length;
        //목표 정점을 미리 계산
        //deep copy를 위해, clone이나 copyOf로 가능하지만,
        // 일차원배열만 지원해준다.
        //perm.clone(); Arrays.copyOf(perm,perm.length);
        List<Integer> sorted = new ArrayList<>();
        List<Integer> permutation  =new ArrayList<>();
        for (int i = 0; i < perm.length; i++) {
            sorted.add(perm[i]);
            permutation.add(perm[i]);
        }
        Collections.sort(sorted);
        //방문 queue와 시작점부터 각 정점까지의 거리
        Queue<List<Integer>> q = new LinkedList<>();
        //맵으로 구현한 이유는, 그 배열에 대해 접근을 했는지 여부를 나타내기 위해.
        //key는 뒤집힌 수열, value는 최소변경횟수.
        Map<List<Integer>,Integer> distance = new HashMap<>();
        //시작점을 큐에 넣기.
        distance.put(permutation,0);
        q.add(permutation);
        while (!q.isEmpty()) {
            List<Integer> here = q.poll();
            //목표정점을 발견했으면 곧장 종료
            if (here.equals(sorted)) return distance.get(here);
            int cost =0;
            if (distance.get(here)!=null){
                cost = distance.get(here);
            }
            //가능한 모든 부분 구간을 뒤집어 본다.
            //**for문과 queue의 결합으로 이렇게 확인할 수도 있다.
            for (int i = 0; i < n; i++) {
                //여기서 j는 size를 넘기는 것이다.
                for (int j = i+2; j <= n; j++) {
                    /*System.out.println("before=");
                    for (int k = 0; k < here.length; k++) {
                        System.out.print(here[k]+" ");
                    }
                    System.out.println();*/
                    List<Integer> there = reverse(here,i,j);
                    System.out.println("after=");
                    for (int k = 0; k < here.size(); k++) {
                        System.out.print(there.get(k)+" ");
                    }
                    System.out.println();
                    System.out.println("distance.get(here)="+distance.get(here));
                    if (distance.get(there)==null){
                        System.out.println("somebody?");
                        distance.put(there,cost+1);
                        q.add(there);
                    }
                    //원래대로 복구.
                    //reverse(here,i,j);
                    /*System.out.println("bottom---");
                    for (int k = 0; k < here.length; k++) {
                        System.out.print(here[k]+" ");
                    }
                    System.out.println();*/
                }
            }
        }
        return -1;
    }

    private List<Integer> reverse(List<Integer> here,int start, int end) {
//        List<Integer> there = Arrays.copyOf(here,here.size());
        List<Integer> there = new ArrayList<>(here);
        int len = (end-start)/2;
        int last = end-1;
        for (int k = start; k < len+start; k++,last--) {
            //근데 이로직은 swap하는 비용이 크다고 생각.
            swap(there,k,last);
        }
        return there;
    }
    //내 생각에 ArrayList는 swap 비용이 더 비싸다.
    private void swap(List<Integer> there,int start, int end) {
        int tmp = there.get(start);
        there.set(start,there.get(end));
        there.set(end,tmp);
    }
    public static void main(String[] args) {
        int n = 4;
        int[] perm = {3,9999,1,2};
//        int[] perm = {1,2,3};
//        int[] perm = {1,2,3,4,8,7,6,5};
        SortingGame sg = new SortingGame();
        System.out.println(sg.bfs(perm));
        System.out.println(sg.solve(perm));
        //Collection 에서 자료구조를 키나 중요 데이터로 사용할 때에는,
        //안전한 타입으로 사용할것. 배열 타입은 너무 위험.
        Map<List<Integer>,Integer> map = new HashMap<>();
        List<Integer> b = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        b.add(2);
        c.add(2);
        b.add(3);
        c.add(3);
        map.put(b,0);
        int[] a = {1,2,3};
        //값 비교 필요.
        if (map.get(c)==null){
            System.out.println("null");
        }

    }
}
