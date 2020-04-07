package strategiesOfAlgorithms.graph.breadthFirstSearch.childrenDay;

import java.util.*;

/**
 * resolve it 3/3/2020
 * 아 이거구나.. n으로 나눈 나머지가 m이라는 것.. 이 것을 깨달았으면..
 * 금방 해결할 수 있었다.
 * 풀긴풀었다.. 그런데.. 참고를 해서,,
 *
 * n으로 나눈 나머지가 m이 된다는것을..
 * 즉, 이 점화식을 뽑아내는 것이 중요했어 나에게
 * n*몫+m=num : 이 식을 만족하는 num이 답이라는 것을.**
 * 나중에도 기억해야한다.
 * 두 수가 주어진다면, 이 두 수가 연관되어 있을거라 가정하고
 * 손으로 풀어봐야한다.
 */
public class ChildrenDay2 {
    // 만약 n,m s=n-m, 이걸 확인하는 과정을 간단하게 해낼 수 있다면,
    // 내 로직을 사용할 수 있다..
    // linear 시간 이상의 알고리즘이 없을뿐. - 이걸 출제자가 모를까?
    // 그렇다면.. 당연히 다른 접근방식을 택해야지 않을까?
    private static String IMPOSSIBLE = "IMPOSSIBLE";
    private static Set<String> state = new HashSet<>();
    private static int stopWatching = 0;
    // 주어지는 숫자는 0~9까지 이므로, 이 숫자를 계속 붙여나가며 값을 처리.
    public static String sol(int d,int n,int m){
        String digits = String.valueOf(d);
        //무조건 작은 값부터 q에 넣어서 먼저 검사를해야만한다.**
        //그래야 최소값부터 검사할 수 있다.
        char[] nums = new char[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
            nums[i] = digits.charAt(i);
        }
        Arrays.sort(nums);
        digits = String.copyValueOf(nums);
        //그냥 n명이라고 넘기면 더 편하지 않을까?
        //모든 상태를 Integer로 구할 수가 없다. String으로 구해야하고,
        //나누는 것을 모두 구해봐야해..
//        makeState(n,m);
        return bfs(digits,n,m);
    }

    private static void makeState(int n, int m) {
        int rest = n-m;
        int first = m*2+rest;
        for (int i = 0; i < 100000; i++) {
            String s = String.valueOf(first+(n*i));
//            System.out.println(s);
            state.add(s);
        }
    }
    // 상태를 미리 구하면, bfs를 구할 필요없이,
    // 저 상태에 있는 값이 존재하는지만 확인하면 된다.
    // n, m은 n에 포함되는 숫자. 인형은 +1개 더 가져간다.
    private static String bfs(String digits,int n,int m) {
        Queue<String> q = new LinkedList<>();
        //start에는 digits에 포함된 모든 숫자를 넣기.
        for (int i = 0; i < digits.length(); i++) {
            q.add(String.valueOf(digits.charAt(i)));
        }
        while(!q.isEmpty()){
            String here = q.poll();
            /*stopWatching++;
            if (state.size()==stopWatching) return IMPOSSIBLE;*/
            /*if (state.contains(here)){
                return here;
            }*/
            if (checkDigit(here,n,m)){
                return here;
            }
            for (int i = 0; i < digits.length(); i++) {
                q.add(here+digits.charAt(i));
            }
        }
        return null;
    }

    private static boolean checkDigit(String here, int n, int m) {
        int atLeast = (n-m)+m*2;
        long num = Long.parseLong(here);
        if (num%n==m) {
            if (atLeast<=num){
                return true;
            }
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        /*int d = 0;
        int n = 7;
        int m = 3;*/
        int d = 345;
        int n = 9997;
        int m = 3333;
        System.out.println(sol(d,n,m));
    }
}
