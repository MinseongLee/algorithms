package baekjoon.implementation.superEasy.pickLastName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PickLastName {
    public String sol(int n,String[] players){
        int[] alphabets = new int[26];
        for (int i = 0; i < n; i++) {
            int al = players[i].charAt(0)-'a';
            System.out.println(al);
            alphabets[al]++;
        }
        List<Character> ans = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (alphabets[i]>=5){
                ans.add((char) (i+'a'));
            }
        }
        if (ans.size()==0) return "PREDAJA";
        Collections.sort(ans);
        StringBuilder sb =new StringBuilder();
        for (int i = 0; i < ans.size(); i++) {
            sb.append(ans.get(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int n  = 7;
        String[] players = {"babic","beslic",
                "keksic",
                "boric","bukic",
                "sarmic",
                "balic"};
        PickLastName pickLastName = new PickLastName();
        System.out.println(pickLastName.sol(n,players));
    }
}
