package strategiesOfAlgorithms.dynamicProgramming.dp.wildcard;

import java.util.Arrays;

public class Wildcard6 {
    //O(s.len*w.len*s.len) 즉, 최대 100*100*100 이므로, 충분.
    //[s.len][w.len]
    private int[][] cache = new int[101][101];
    public String sol(String w,String[] words){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            //init
            initCache();
            if (isWord(0,0,words[i],w)){
                if (sb.length()>0) sb.append("\n");
                sb.append(words[i]);
            }
        }
        return sb.toString();
    }

    private void initCache() {
        for (int i = 0; i < 101; i++) {
            Arrays.fill(cache[i],-1);
        }
    }

    //brute force, dp = 1 is true, 0 is false, -1 is not visited.
    private boolean isWord(int sPos, int wPos, String s, String w) {
        if (cache[sPos][wPos]>-1) {
            if (cache[sPos][wPos]==1) return true;
            return false;
        }
        while((sPos<s.length()&&wPos<w.length())&&
                (w.charAt(wPos)==s.charAt(sPos)||w.charAt(wPos)=='?')){
            wPos++;
            sPos++;
        }
        //base case
        if (w.length()==wPos){
            if (sPos==s.length()) {
                cache[sPos][wPos] = 1;
                return true;
            }
            cache[sPos][wPos] = 0;
            return false;
        }
        //visited 했다는 처리를 해줄 것.
        cache[sPos][wPos] = 0;
        if (w.charAt(wPos)=='*'){
            for (int next = sPos; next <= s.length(); next++) {
                if (isWord(next,wPos+1,s,w)){

                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String w = "*p*";
        String s = "help";
        String[] sList = {"help", "papa", "hello"};
        Wildcard6 wildcard6 = new Wildcard6();
        System.out.println(wildcard6.sol(w,sList));
    }
}
