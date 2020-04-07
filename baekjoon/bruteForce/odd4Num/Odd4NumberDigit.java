package baekjoon.bruteForce.odd4Num;

/**
 * getTwelveSum(), getSixteenSum() 에서 original 진법 구하는 알고리즘이 있다.
 */
public class Odd4NumberDigit {
    private static final char[] twelve = {'0','1','2','3','4','5','6','7','8','9','A','B'};
    private static final char[] sixteen = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    public void sol(){
        //각 자리수 합.
        for (int num = 1000; num <= 9999; num++) {
            int ten = getTenSum(num);
            int twelve = getTwelveSum(num);
            int sixteen = getSixteenSum(num);
            if (ten==twelve&&ten==sixteen){
                System.out.println(num);
            }
        }
    }

    private int getSixteenSum(int num) {
        String s = "";
        while(num>0){
            s = sixteen[num%16]+s;
            num /=16;
        }
//        System.out.println(s);
        int sum =0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='A') {
                sum+= 10;
            }else if (s.charAt(i)=='B'){
                sum+= 11;
            }else if (s.charAt(i)=='C'){
                sum+=12;
            }else if (s.charAt(i)=='D'){
                sum+=13;
            }else if (s.charAt(i)=='E'){
                sum+=14;
            }else if (s.charAt(i)=='F'){
                sum+=15;
            } else{
                sum += (s.charAt(i)-'0');
            }
        }
        return sum;
    }

    private int getTwelveSum(int num) {
        //4진법, 3진법 12진법 등.. 모든 진법의 original 문법.
        String s = "";
        while(num>0){
            s = twelve[num%12]+s;
            num /= 12;
        }
//        System.out.println(s);
        int sum =0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='A') {
                sum+= 10;
            }else if (s.charAt(i)=='B'){
                sum+= 11;
            }else{
                sum += (s.charAt(i)-'0');
            }
        }
        return sum;
    }

    private int getTenSum(int num) {
        int sum =0;
        String s = String.valueOf(num);
        for (int i = 0; i < s.length(); i++) {
            sum += (s.charAt(i)-'0');
        }
        return sum;
    }

    public static void main(String[] args) {
        Odd4NumberDigit odd4NumberDigit = new Odd4NumberDigit();
        odd4NumberDigit.sol();
    }
}
