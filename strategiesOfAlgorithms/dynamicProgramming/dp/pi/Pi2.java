package strategiesOfAlgorithms.dynamicProgramming.dp.pi;

import java.util.Arrays;

/**
 * resolve it 2/29/2020
 */
public class Pi2 {
    private static final int INF = 987654321;
    private static final int[] spells = {3, 4, 5};
    private static int[] cache;
    //최소 난이도 합.
    public static int optimal(String s, int pos) {
        if (cache[pos]!=-1) return cache[pos];
        //base case - 즉, pos가 2개 이하 가르킬때,
        //최소값을 넘길때는 0을 넘겨야한다.
        if (pos > s.length() - 3) return 0;
//        int ans = INF;
        cache[pos] = INF;
        for (int i = 0; i < spells.length; i++) {
            if (pos + spells[i] - 1 < s.length()) {
                int level = 0;
                if (hard1(s, pos, spells[i])) {
                    level = 1;
                } else if (hard2(s, pos, spells[i])) {
                    level = 2;
                } else if (hard3(s,pos,spells[i])){
                    level = 4;
                } else if (hard4(s,pos,spells[i])){
                    level = 5;
                }else{
                    level =10;
                }
//                ans = Math.min(ans, optimal(s, pos + spells[i]) + level);
                cache[pos] = Math.min(cache[pos], optimal(s, pos + spells[i]) + level);
            }
        }
        return cache[pos];
    }
    //147 ,, 8642
    private static boolean hard4(String s, int pos, int spell) {
        int gap = Math.abs(s.charAt(pos)-s.charAt(pos+1));
        for (int i = 2; i < spell; i++) {
            if (gap!=Math.abs(s.charAt(i+pos)-s.charAt(pos+i-1))){
                return false;
            }
        }
        return true;
    }

    private static boolean hard3(String s, int pos, int spell) {
        //323 3232 32323
        char first = s.charAt(pos);
        char second = s.charAt(pos+1);
        for (int i = 2; i < spell; i++) {
            if (i%2==0 &&first!=s.charAt(pos+i)){
                return false;
            }else if (i%2==1&&second!=s.charAt(pos+i)){
                return false;
            }
        }
        return true;
    }

    //1증가 or 감소
    private static boolean hard2(String s, int pos, int spell) {
        char in = s.charAt(pos);
        char de = s.charAt(pos);
        int countIn=1,countDe=1;
        for (int i = 1; i < spell; i++) {
            if (s.charAt(pos+i)==in+i){
                countIn++;
            }
            else if (s.charAt(pos+i)==de-i){
                countDe++;
            }
        }
        if (countIn==spell || countDe==spell){
            return true;
        }
        return false;
    }

    private static boolean hard1(String s, int pos, int spell) {
        char same = s.charAt(pos);
        for (int i = 1; i < spell; i++) {
            if (same != s.charAt(pos + i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        String s  = "12673939";
        String s  = "22222222";
        cache = new int[s.length()+1];
        Arrays.fill(cache,-1);
        System.out.println(optimal(s,0));
    }
}
