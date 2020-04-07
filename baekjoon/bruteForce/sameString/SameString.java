package baekjoon.bruteForce.sameString;

/**
 * 쉬운문제를.. 너무 어렵게 풀어서 시간만 낭비했다..
 * 진짜 조심하자.
 */
public class SameString {
    private static final int INF = 987654321;
    private static final int ALPHA_SIZE = 26;
    private int best=INF;
    private char[] alphabets = new char[ALPHA_SIZE];
    public int sol(String A,String B){
        int minElement = INF;
        for (int i = 0; i < B.length()-A.length()+1; i++) {
            String sub = B.substring(i, A.length() + i);
            int subMin = 0;
            for (int j = 0; j < A.length(); j++) {
                if (sub.charAt(j) != A.charAt(j)) {
                    subMin++;
                }
            }
            minElement = Math.min(minElement, subMin);
        }
        return minElement;
//        makeAlphabets();
//        addAElement(A,B);
//        return best;
    }

    private void makeAlphabets() {
        for (int i = 0; i < ALPHA_SIZE; i++) {
            alphabets[i] = (char)('a'+i);
//            System.out.println(alphabets[i]);
        }
    }
    //이 로직은.. 시간복잡도가 진짜.. 어마어마 하다..
    private void addAElement(String A, String B) {
//        System.out.println(A+" "+B);
        //두 String이 다른 값을 정리.
        if (A.length()==B.length()){
            best = Math.min(best,getNotSame(A,B));
//            System.out.println(best);
            return;
        }
        //뒤에 붙이는 경우
        for (int i = 0; i < ALPHA_SIZE; i++) {
            addAElement(A+alphabets[i],B);
        }
        //앞에 붙이는 경우.
        for (int i = 0; i < ALPHA_SIZE; i++) {
            addAElement(alphabets[i]+A,B);
        }
    }

    private int getNotSame(String A, String B) {
        int ans = 0;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i)!=B.charAt(i)){
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String A = "aza";
        String B = "dsazad";
        SameString sameString = new SameString();
        System.out.println(sameString.sol(A,B));
    }
}
