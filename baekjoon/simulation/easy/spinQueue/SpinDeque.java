package baekjoon.simulation.easy.spinQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 두 개의 조건 중 하나만 선택하므로, 그에 맞는 deque를 계속 갱신하였다.
 */
public class SpinDeque {
    public int sol(int n, int m, int[] a) {
        // 먼저 n개 수만큼 deque를 만들 것.
        Deque<Integer> dq = createDeque(n);
        int ans = 0;
        for (int i = 0; i < a.length; i++) {
            // 1번쨰부터.
            int here = dq.removeFirst();
            if (a[i]==here) {
                continue;
            }
            else dq.addFirst(here);
            //두 경우 중 최소값을 ans에 넣기.
            Deque<Integer> dq2 = new LinkedList<>(dq);
            Deque<Integer> dq3 = new LinkedList<>(dq);
            int subMin2 = matchingNum2(a[i],dq2);
            int subMin3 = matchingNum3(a[i],dq3);
//            System.out.println("dq="+dq+" sub2="+subMin2+" sub3="+subMin3);
            if (subMin2<=subMin3){
                ans += subMin2;
                dq = dq2;
            }else {
                ans+=subMin3;
                dq = dq3;
            }
//            ans += Math.min(matchingNum2(a[i], dq),matchingNum3(a[i],dq));
        }
        return ans;
    }
    //3번째에서 끝이므로, 여기에서는 완전히 제거.
    private int matchingNum3(int k, Deque<Integer> dq) {
        //이건 1부터 시작해야함. 왜냐하면 remove한 값을 first에 넣고
        //그다음에 1번 연산을 할 수 있으므로,
        int ans =1;
        // 3번째
        while(!dq.isEmpty()){
            // 먼저 위에 마지막에 add했던 here을 remove해주고,
            int here = dq.removeLast();
            if (here==k) return ans;
            // 첫번째에 add해준다.
            dq.addFirst(here);
            ans++;
        }
        return ans;
    }

    //3번째에도 써야하므로, 2번째에서는 복사본으로 처리.
    private int matchingNum2(int k, Deque<Integer> dq) {
//        Deque<Integer> dq2 = new LinkedList<>(dq);
        int ans = 0;
        //2번째
        while(!dq.isEmpty()){
            int here = dq.removeFirst();
            if (k==here) {
                return ans;
            }
            dq.addLast(here);
            ans++;
        }
        return ans;
    }

    private Deque<Integer> createDeque(int n) {
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            dq.addLast(i);
        }
        return dq;
    }

    public static void main(String[] args) {
//        int n = 32, m = 6;
//        int[] list = {27, 16, 30,11,6,23};
//        int n = 10, m = 3;
//        int[] list = {2, 9, 5};
//        int n =10,m=3;
//        int[] list = {1,2,3};
        int n =10,m=10;
        int[] list = {1,6,3,2,7,9,8,4,10,5};
        SpinDeque spinDeque = new SpinDeque();
        System.out.println(spinDeque.sol(n, m, list));

    }
}
