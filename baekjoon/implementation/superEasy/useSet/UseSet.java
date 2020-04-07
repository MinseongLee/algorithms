package baekjoon.implementation.superEasy.useSet;

import java.util.*;

public class UseSet {
    public String sol(int n,int m,String[] nList,String[] mList){
        int size = Math.min(n,m);
        Set<String> set = new HashSet<>();
        List<String> ans = new ArrayList<>();
        if (size==n){
            getAns(nList,mList,set,ans);

        }else{
            getAns(mList,nList,set,ans);
        }
        Collections.sort(ans);
        StringBuilder sb = new StringBuilder();
        sb.append(ans.size());
        for (int i = 0; i < ans.size(); i++) {
            sb.append("\n").append(ans.get(i));
        }
        return sb.toString();
    }

    private void getAns(String[] nList, String[] mList, Set<String> set, List<String> ans) {
        int n = nList.length;
        int m = mList.length;
        for (int i = 0; i < n; i++) {
            set.add(nList[i]);
        }
        for (int i = 0; i < m; i++) {
            if (set.contains(mList[i])){
                ans.add(mList[i]);
            }
        }
    }

    public static void main(String[] args) {
        int n = 3;
        int m = 4;
        String[] nList = {"ohhenrie", "charlie", "baesangwook"};
        String[] mList = {"obama", "baesangwook", "ohhenrie","clinton"};
        UseSet useSet = new UseSet();
        System.out.println(useSet.sol(n,m,nList,mList));
    }
}
