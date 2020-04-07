package baekjoon.bruteForce.sumOfSubsequence;

import java.util.ArrayList;
import java.util.List;

public class SumOfSubsequence {
    private int best;
    public int sol(int n,int s,int[] num){
        List<Integer> list = new ArrayList<>();
        for (int pick = 1; pick <= n; pick++) {
            toPick(n,pick,s,num,list);
        }
        return best;
    }

    private void toPick(int n, int pick,int s, int[] num, List<Integer> list) {
        if (pick==0){
            int sum =0;
            for (int i = 0; i < list.size(); i++) {
                sum += num[list.get(i)];
            }
            if (sum==s) best++;
            return;
        }
        int smallest = list.isEmpty()?0:list.get(list.size()-1)+1;
        for (int i = smallest; i < n; i++) {
            list.add(i);
            toPick(n,pick-1,s,num,list);
            list.remove(list.size()-1);
        }
    }

    public static void main(String[] args) {
        int n =5;
        int s = 0;
        int[] num ={-7,-3,-2,5,8};
        SumOfSubsequence sumOfSubsequence = new SumOfSubsequence();
        System.out.println(sumOfSubsequence.sol(n,s,num));
    }
}
