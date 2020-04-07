package baekjoon.implementation.superEasy.numCnt;

public class NumCnt {
    public void sol(int a,int b,int c){
        int mul = a*b*c;
        String s = String.valueOf(mul);
        int[] num = new int[10];
        for (int i = 0; i < s.length(); i++) {
            int number = s.charAt(i)-'0';
            num[number]++;
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(num[i]);
        }
    }

    public static void main(String[] args) {
        NumCnt numCnt = new NumCnt();
//        numCnt.sol(a,b,c);
    }
}
