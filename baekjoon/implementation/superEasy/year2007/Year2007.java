package baekjoon.implementation.superEasy.year2007;

/**
 * 시간복잡도 O(1)
 * 부분합 개념을 이용하여 시간복잡도를 줄였다.
 */
public class Year2007 {
    private static final String[] date ={"SUN","MON","TUE","WED","THU","FRI","SAT"};
    public String sol(int x,int y){

        //1~12 까지
        int[] subSum = createSubSum();
        int days = subSum[x-1]+y;
        return date[days%7];
    }
    private int[] createSubSum() {
        int[] subSum = new int[13];
        for (int i = 1; i <= 12; i++) {
            int days = 0;
            if (i==2) days = 28;
            else if (i==4||i==6||i==9||i==11) days=30;
            else days = 31;
            subSum[i] = subSum[i-1]+days;
        }
        return subSum;
    }

    public static void main(String[] args) {
        int x = 12;
        int y = 25;
        Year2007 year2007 = new Year2007();
        System.out.println(year2007.sol(x,y));

    }
}
