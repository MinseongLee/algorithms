package samsungSW.swTest.treasurePwd;

import java.util.*;

/**
 * 5658. [모의 SW 역량테스트] 보물상자 비밀번호
 */
public class TreasurePwd {
    private static final int SIZE = 4;
    public int sol(int n,int k,String s){
        return getPwd(n,k,s);
    }

    private int getPwd(int n, int k, String s) {
        int quo = n/4;
        Set<String> allPwd = new HashSet<>();
        String[] treasure = createTreasure(s,quo);
        for (int i = 0; i < SIZE; i++) {
            allPwd.add(treasure[i]);
        }
//        System.out.println(allPwd);
        //3이라면, 2회전만.
        for (int i = 1; i <= quo - 1; i++) {
            treasure = createNewPwd(treasure);
            for (int j = 0; j < SIZE; j++) {
                allPwd.add(treasure[j]);
            }
//            System.out.println(allPwd);
        }
        List<String> ansList = new ArrayList<>(allPwd);
        Collections.sort(ansList,Collections.reverseOrder());
        String hex = ansList.get(k-1);
        int ret = Integer.parseInt(hex,16);
        return ret;
    }

    private String[] createNewPwd(String[] treasure) {
        String[] newPwd = new String[SIZE];
        //맨 마지막것을 처음꺼에 붙이기.
        for (int i = 1; i < SIZE; i++) {
            char ch = treasure[i-1].charAt(treasure[i-1].length()-1);
            String s = ch+treasure[i].substring(0,treasure[i].length()-1);
//            System.out.println(s);
            newPwd[i] = s;
        }
        char c = treasure[SIZE-1].charAt(treasure[SIZE-1].length()-1);
        String s = c+treasure[0].substring(0,treasure[0].length()-1);
//        System.out.println(s);
        newPwd[0] = s;
        return newPwd;
    }

    private String[] createTreasure(String s, int quo) {
        String[] treasure = new String[SIZE];
        int plus = 0;
        for (int i = 0; i < SIZE; i++) {
            treasure[i] = s.substring(plus,quo+plus);
            plus += quo;
        }
        return treasure;
    }

    public static void main(String[] args) {
        int n = 12;
        int k =10;
        String s = "1B3B3B81F75E";
        TreasurePwd treasurePwd = new TreasurePwd();
//        System.out.println(treasurePwd.sol(n,k,s));
        //parameter를 하나만 놓으면 start가 되어버린다.
        //두개를 놓아줘야 start,end로 된다.
        System.out.println(s.substring(0,s.length()-1));
    }
}
