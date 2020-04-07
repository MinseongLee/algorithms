package samsungSW.d4.justRule.hagiGuess;

/**
 * 8993. 하지 추측
 * 자연수 n, 이 프로그램이 종료하는지 판별.
 * 1<=n<=10^14
 * 종료되면 YES, 안되면 NO
 */
public class HagiGuess {

    public static void main(String[] args) {
        //범위가 넘어서면 무한루프,
        //답은 무조건 1이다.
        //왜? 무조건 짝수만 나와야 무한루프에 빠지지 않을 수 있다.
        //즉, 2의 승수만 가능.2,4,8,16,32,64,,
        long n = 64;

        while(n>1){
            System.out.println(n);
            if (n%2==0) {
                n /= 2;
            }
            else{
                n *=3+3;
            }
        }
        System.out.println("n="+n);
    }
}
