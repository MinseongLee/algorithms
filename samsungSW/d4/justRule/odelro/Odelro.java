package samsungSW.d4.justRule.odelro;

/**
 * 6731. 홍익이의 오델로 게임
 * NxN
 * white or black,
 * 뒤집기 규칙
 * (1,2)에 있는 바둑알을 뒤집으면, white는 black으로 black은 white로
 * 십자가 모양으로 뒤집힌다.
 *
 * 보여지는 바둑판을 만드려면 올 white에서 몇번 작업. 최소값.
 * N은 even : 1<=N<=1,000
 *
 * 몇 번 뒤집.
 *
 * 흠.. 문제 질이 딱히.. 규칙을 찾아야만 풀 수 있는 문제이다..
 *  참고 https://whereisusb.tistory.com/278
 *  board[][] 를 만들지 않고, garo, sero 로 만들어 풀이를 진행.
 */
public class Odelro {
    public static int counting(char[][] board){
        int n = board.length;
        boolean[][] arr= new boolean[n][n];
        int[] garo = new int[n];
        int[] sero = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j]=='B'){
                    arr[i][j] = true;
                    garo[i]++;
                    sero[j]++;
                }
            }
        }
        int result = 0;
        //즉, 가로,세로 의 검은색 바둑알의 합이 홀수일때만 바꾼 흔적이 있단 의미이다.
        //모든 바둑판을 순회하면서,
        //이러한 규칙을 찾아내면 풀수있는문제.
        //문제에 무조건 n이 짝수라고 했다. 그렇다면, 홀수로 풀수있지 않을까?란 생각은 할 수 있지만,
        //그냥 단순한 규칙을 찾아내는 문제는 쉽지 않은거같다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int t = garo[i]+sero[j];
                //검은색일 경우 -1 왜냐면 가로 세로 가운데 중복되는 경우 하나 제거.
                if (arr[i][j]){
                    if (--t%2!=0){
                        result++;
                    }
                }
                else{
                    if (t%2!=0){
                        result++;
                    }
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        int n = 4;
        char[][] b = {
                {'W','B','W','B'},
                {'W','B','W','B'},
                {'W','B','W','B'},
                {'W','B','W','B'},
        };
        System.out.println(counting(b));
        char[] c = new char[5];
        c[0]++;
        System.out.println(" "+c[0]);
    }

}
