package baekjoon.bruteForce.lotto;

import java.util.ArrayList;
import java.util.List;

public class Combination {
    public String sol(int n,int[] num){
        StringBuilder sb = new StringBuilder();
        List<Integer> ans = new ArrayList<>();
        combination(n,6,num,sb,ans);
        return sb.toString();
    }

    private void combination(int n, int r, int[] num, StringBuilder sb,List<Integer> ans) {
        if (r==0){
            if (sb.length()>0) sb.append("\n");
            sb.append(num[ans.get(0)]);
            for (int i = 1; i < ans.size(); i++) {
                sb.append(" ").append(num[ans.get(i)]);
            }
        }
        int smallest = ans.isEmpty()?0:ans.get(ans.size()-1)+1;
        for (int i = smallest; i < n; i++) {
            ans.add(i);
            combination(n,r-1,num,sb,ans);
            ans.remove(ans.size()-1);
        }
    }

    public static void main(String[] args) {
        int n  =8;
        int[] list = {1,2,3,5,8,13,21,34};
        Combination co = new Combination();
        System.out.println(co.sol(n,list));
        System.out.println();
    }
}
