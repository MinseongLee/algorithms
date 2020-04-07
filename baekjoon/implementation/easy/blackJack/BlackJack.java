package baekjoon.implementation.easy.blackJack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 이문제를 cache를 활용해 dp로도 풀고,
 * 완탐으로도 풀어보았다.
 */
public class BlackJack {
    private int[][] cache;
    public int sol(int n,int m, int[] cards){
        cache = new int[n+1][m+1];
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(cache[i],-1);
        }
        List<Integer> three = new ArrayList<>();
        int a= jack(n,3,m,cards,three);
        System.out.println("a="+a);
        int b= jackDp(-1,0,cards,m,0);
        return b;
    }
    //그렇다면, dynamic programming 식 재귀는?
    private int jackDp(int here,int sum,int[] cards,int m,int picked){
        int i = here+1;
        if (cache[i][sum]>-1) return cache[i][sum];
        if (picked==3){
            return sum;
        }
        cache[i][sum]=0;
        //here+1을 해주는 것.. 모든 경우를 따지기 위해.
        for (int next = here+1; next < cards.length; next++) {
            if (sum+cards[next]<=m){
                cache[i][sum] = Math.max(cache[i][sum],jackDp(next,sum+cards[next],cards,m,picked+1));
            }
        }
        return cache[i][sum];
    }

    //이것이 완탐.
    private int jack(int n, int picked, int m, int[] cards,List<Integer> three) {
        if (picked==0){
            int sum = 0;
            for (int i = 0; i < three.size(); i++) {
                sum += cards[three.get(i)];
            }
            return sum;
        }
        int smallest = three.isEmpty()?0:three.get(three.size()-1)+1;
        int ans =0;
        for (int i = smallest; i < n; i++) {
            three.add(i);
            int sum = jack(n,picked-1,m,cards,three);
            if (sum<=m){
                int min1 = m-sum,min2=m-ans;
                int sub = Math.min(min1,min2);
                if (sub==min1) ans = sum;
            }
            three.remove(three.size()-1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 10;
        int m = 500;
        int[] cards = {93, 181, 245, 214, 315, 36, 185, 138, 216, 295};
//        int n = 5;
//        int m = 21;
//        int[] cards = {5,6,7,8,9};
        BlackJack blackJack = new BlackJack();
        System.out.println(blackJack.sol(n,m,cards));
    }
}
