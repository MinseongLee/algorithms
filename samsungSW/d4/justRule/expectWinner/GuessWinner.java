package samsungSW.d4.justRule.expectWinner;

/**
 * Alick Bob
 * 양의 정수 N, 1로 초기화 된 x,
 * 게임은 Alice가 먼저 시작하며 서로 번갈아 가면서
 * 자신의 차례에 아래의 작업을 한번씩 한다.
 * long x = 1;
 * x = 2x or x = 2x+1
 * x>N : 이때 이 작업을 한 사람이 패배
 * ex) N=1, Alice는 2x or 2x+1 다 1초과하므로, Bob 승리
 * N=5, Alice가 2x+1 선택 x=3 되면 Bob은 x=2*3, x=2*3+1,
 * 둘다 5초과이므로 Alice 승리
 *
 * N이 주어질 때, 두 사람이 최선을 다해 게임을 한다면
 * 어떤 사람이 이기게 되는지 출력하는 프로그램을 작성하라.
 *
 * 1<=N<=10^18
 * solution
 * : 선택 가지수 2경우, 하지만 어느 경우도 승리할 수 없는 상황을 만들어야만한다.
 * 100% 승리로. - 즉, 두 사람이 최선을 다해서 게임한다는 가정.
 *
 * full binary tree 로 표현 가능.
 * 각 레벨을 쭉 이으면 1,2,3,4,5,6,7,,,,로
 * 맨 왼쪽 2^i, 맨 오른쪽 2^n+1 -1 로 표현가능.
 *
 * 규칙
 * n이 어느 라인에 속해있냐에 따라서,
 * 1. B line 에서는 A는 큰 값, B는 작은값 선택
 * 2. A line 에서는 A는 작은값, B는 큰 값 선택.
 *
 * 먼저 내가 찾은 n이 어디에 속하는지 부터 확인.
 * A - 2^1 ~ 2^2 -1
 * B - 2^2 ~ 2^3 -1
 * A - 2^3 ~ 2^4 -1
 * B - 2^4 ~ 2^5 -1
 */

/**
 * 이 문제는 이진트리로 구성해 각 level에 속한 Alice와 Bob을 구분하고,
 * 어떠한 규칙을 찾아내 그 규칙을 이용해 해결하였다.
 */
public class GuessWinner {
    private static final String ALICE = "Alice";
    private static final String BOB = "Bob";

    public String winner(long n){
        //exception - n=1 : Bob
        if (n==1) return BOB;
        int height = findLevel(n);
        //pow가 홀수이면, ALICE, 짝수이면 BOB
        String where = "";
        if (height%2==1){
            where = ALICE;
        }else{
            where = BOB;
        }
//        System.out.println(height+" "+where);
        long choice = 1;
        for (int i = 1; i <= height; i++) {
            choice = getChoice(where,i,choice);
        }
//        System.out.println("choice="+choice);
        if (choice<=n){
            return where;
        }else{
            if (!where.equals(ALICE)){
                return ALICE;
            }else{
                return BOB;
            }
        }
    }

    private long getChoice(String where, int height,long choice) {
        //            A는 작은값, B는 큰 값 선택.
        if (where.equals(ALICE)){
            if (height%2==1){
                choice = 2*choice;
            }else{
                choice = 2*choice+1;
            }
        }
//            A는 큰 값, B는 작은값 선택
        else if (where.equals(BOB)){
            if (height%2==1){
                choice = 2*choice +1;
            }else{
                choice = 2*choice;
            }
        }
        return choice;
    }

    //tree의 height를 구한다.
    private int findLevel(long n) {
        int pow = 0;
        int count = 0;
        while((long)Math.pow(2,pow)<=n && n>((long)Math.pow(2,pow+1)-1)){
            pow++;
        }
        return pow;
    }

    public static void main(String[] args) {
        long n = 123456789123456789l;

//        long n = 10;
        //long n = (long)Math.pow(10,18);
        GuessWinner gw = new GuessWinner();
        System.out.println(gw.winner(n));
    }
}
