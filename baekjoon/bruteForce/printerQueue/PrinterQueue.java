package baekjoon.bruteForce.printerQueue;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PrinterQueue {
    private int best;
    public int sol(int n,int m,int[] document){
        PriorityQueue<State> pq = new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State o1, State o2) {
                return Integer.compare(o2.value,o1.value);
            }
        });
        Queue<State> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            State state = new State(i,document[i]);
            pq.add(state);
            q.add(state);
        }
        print(m,pq,q);
        return best;
    }

    private void print(int m, PriorityQueue<State> pq, Queue<State> q) {
        if (pq.isEmpty()) return;
        //base case 는 m을 만났거나, q가 비었을 때,
        int max = pq.peek().value;
        while(!pq.isEmpty()){
            State here = q.poll();
            if (max==here.value){
                //가장 큰값을 만났으므로, return.
                pq.poll();
                best++;
                //base case 그 값을 만났다면 루프 끝낼 것.
                if (here.index==m) {
                    //남은 pq를 전부 없앨 것.
                    while(!pq.isEmpty()) pq.poll();
                    return;
                }
                print(m,pq,q);
            }
            q.add(here);
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int m =0;
        int[] list = {1,1,9,1,1,1};
        PrinterQueue printerQueue = new PrinterQueue();
        System.out.println(printerQueue.sol(n,m,list));
    }
}
