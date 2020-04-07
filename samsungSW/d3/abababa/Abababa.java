package samsungSW.d3.abababa;

/**
 * 8840. 아바바바
 * abababa ,,,
 * 홀수 L이 주어질 때, ‘a’와 ‘b’가 번갈아 나오는 길이 L인 문자열에서,
 *
 * 길이가 2이상인 연속한 부분문자열이
 * 회문(앞으로 읽어도 뒤로 읽어도 같은 문자열)인 것의 개수
 */
public class Abababa {
    //숫자가 ab 라 패턴이 정해져있다.
    public static long ababa(int n){
        //1+3+5+7+9+,,이렇게 홀수들의 합이다.
        int last = n-2;
        int first = 1;
        long sum = last+first;
        int count = (n/2)/2;
        int reminder = (n/2)%2;
        sum *=count;
        if (reminder==1){
            sum += (last+first)/2;
        }
        return sum;
    }
    public static void main(String[] args) {
//        int n = 1000000000;
        int n = 11;
        System.out.println(ababa(n));
    }
}
