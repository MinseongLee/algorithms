package baekjoon.implementation.superEasy.crossWord;

/**
 * if (sb.length()>0) sb.append("\n");
 * 이문장은 좀 위험한가..?
 * 이문장을 넣으니 틀리고,
 * sout으로 print하니 맞았다.. 흠.
 */
public class CrossWord {
    public void sol(String A,String B){
        getWord(A,B);
    }

    private void getWord(String A, String B) {
        //A에서 등장하는 글자에 우선권이 있다.
        int x =-1,y=-1;
        for (int i = 0; i < A.length(); i++) {
            for (int j = 0; j < B.length(); j++) {
                if (A.charAt(i)==B.charAt(j)){
                    x=j;
                    y=i;
                    break;
                }
            }
            if (x!=-1) break;
        }
        for (int i = 0; i < B.length(); i++) {
            StringBuilder sb = new StringBuilder();
//            if (sb.length()>0) sb.append("\n");
            for (int j = 0; j < A.length(); j++) {
                if (i==x){
                    sb.append(A.charAt(j));
                }else if (j==y){
                    sb.append(B.charAt(i));
                }else{
                    sb.append(".");
                }
            }
            System.out.println(sb.toString());
        }
//        return sb.toString();
    }

    public static void main(String[] args) {
//        String A = "BANANA";
        String A = "AAAAAAAAABBBBBBBBBBBB";
//        String B = "PIDZAMA";
        String B = "BBBBBBBBBBBBBBBBBBBBBBBBBA";
        CrossWord crossWord  =new CrossWord();
        crossWord.sol(A,B);
//        System.out.println();
    }
}
