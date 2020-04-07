package samsungSW.d4.justRule.palindrome;

/**
 * a,b,c 세문자로만 이루어진 문자열.
 * 길이 2이상의 팰린드롬이 없도록 하는 것이 가능한지 판단.
 * "YES","NO"
 * ** 아.. 문제를 정말 잘 읽어야만한다.
 * 즉, 문자열 pal을 섞어서 팰린드가 나올 수 없는 경우가 하나라도 있으면 YES,
 * 아무리 섞어도 나올수있으면 NO***
 */
//즉, 모든 경우의수에 팰린드룸이 등장해야 NO
public class Palindrome {
    public static String palind(String pal){
        int aCnt=0,bCnt=0,cCnt=0;
        for (int i = 0; i < pal.length(); i++) {
            //aa 인 경우를 못하게 하는 경우는 b,c가 하나씩 있는경우이다.
            //즉, x(가장 개수가 많은 수)의 개수가
            // y(가장 개수가 적은 수)의 개수보다 2이상 많으면
            //팰린드룸은 무조건 등장한다.
            //이것 이외의 것들은 무조건 YES이다.

            if (pal.charAt(i)=='a') aCnt++;
            else if (pal.charAt(i)=='b') bCnt++;
            else cCnt++;
        }
        int max = Math.max(Math.max(aCnt,bCnt),cCnt);
        int min = Math.min(Math.min(aCnt,bCnt),cCnt);
        if (max-min>1) return "NO";
        return "YES";
    }


    //아래 로직은 의미가없다.
    //mid를 중심으로 left, right 한칸씩 전진.
    private static boolean checkEven(String pal, int mid) {
        int n = pal.length();
        int left = mid;
        int right = mid+1;
        if (left>=0&&right<n){
            if (pal.charAt(left)==pal.charAt(right)){
                return true;
            }
        }
        //범위가 벗어났으므로 false
        return false;
    }
    //딱 하나만 체크하면된다.
    private static boolean checkOdd(String pal, int mid) {
        int left = mid-1;
        int right = mid+1;
        if (left>=0&&right<pal.length()){
            if (pal.charAt(left)==pal.charAt(right)){
                return true;
            }
        }
        return false;
    }

    private static boolean check(String pal) {
        int end = pal.length()-1;
        for (int start = 0; start <= end; start++,end--) {
            if (pal.charAt(start)!=pal.charAt(end)){
                return false;
            }
        }
        return true;
    }
}
