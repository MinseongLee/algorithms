package baekjoon.implementation.easy.cokeCnt;

public class CokeCnt {
    public int sol(int e,int f,int c){
        int bottle = e+f;
        return getCoke(bottle,c);
    }

    private int getCoke(int bottle, int c) {
        if (bottle<c) return 0;
        int ans = bottle/c;
        ans += getCoke(ans+bottle%c,c);
        return ans;
    }

    public static void main(String[] args) {
        int e= 9;
        int f =2;
        int c=3;
        CokeCnt cokeCnt = new CokeCnt();
        System.out.println(cokeCnt.sol(e,f,c));
    }
}
