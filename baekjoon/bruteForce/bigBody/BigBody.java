package baekjoon.bruteForce.bigBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 정렬론 안돼.. 처리할게 많아서 안된다.
 */

public class BigBody {
    public String sol(int n,int[][] list){
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            int level = 1;
            for (int j = 0; j < n; j++) {
                if (i==j) continue;
                if (list[i][0]<list[j][0]&&list[i][1]<list[j][1]){
                    level++;
                }
            }
            rank[i] =level;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (sb.length()>0) sb.append(" ");
            sb.append(rank[i]);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        int n = 5;
        int[][] li = {
                {55,185},
                {58,183},
                {88,186},
                {60,175},
                {46,155},
        };
        BigBody bigBody = new BigBody();
        System.out.println(bigBody.sol(n,li));
        List<StateWrongSort> list = new ArrayList<>();
        list.add(new StateWrongSort(55,185,0));
        list.add(new StateWrongSort(58,183,1));
        list.add(new StateWrongSort(88,186,2));
        list.add(new StateWrongSort(60,175,3));
        list.add(new StateWrongSort(46,155,4));
        list.add(new StateWrongSort(55,155,4));
        list.add(new StateWrongSort(55,183,4));
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
    }
}
