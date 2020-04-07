package baekjoon.bruteForce.baseballGame;

import java.util.ArrayList;
import java.util.List;

public class BaseballGame2 {
    public int sol(String[] q){
        List<State> questions = createQuestions(q);
        int ans =0;
        //123~987 의 숫자중 모든 경우의수.
        for (int num = 123; num <= 987; num++) {
            String s = String.valueOf(num);
            if (s.charAt(0)==s.charAt(1)||s.charAt(1)==s.charAt(2)||s.charAt(0)==s.charAt(2)) continue;
            if (s.charAt(1)=='0'||s.charAt(2)=='0') continue;
            if (isValidNum(s,questions)){
                ans++;
            }
        }
        return ans;
    }
    //각 퀘스천에 조건을 만족하는지만 확인.
    private boolean isValidNum(String num, List<State> questions) {
        for (int i = 0; i < questions.size(); i++) {
            String s = String.valueOf(questions.get(i).num);
            //strike확인.
            int strike = getStrike(num,s);
            int ball = getBall(num,s);
            if (strike!=questions.get(i).strike||ball!=questions.get(i).ball){
                return false;
            }
        }
        return true;
    }

    private int getBall(String num, String s) {
        int ball =0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i==j) continue;
                if (num.charAt(i)==s.charAt(j)) {
                    ball++;
                }
            }
        }
        return ball;
    }

    private int getStrike(String num, String s) {
        int strike = 0;
        for (int i = 0; i < 3; i++) {
            if (num.charAt(i)==s.charAt(i)){
                strike++;
            }
        }
        return strike;
    }

    private List<State> createQuestions(String[] q) {
        List<State> questions = new ArrayList<>();
        for (int i = 0; i < q.length; i++) {
            String[] s = q[i].split(" ");
            questions.add(new State(Integer.parseInt(s[0]),Integer.parseInt(s[1]),Integer.parseInt(s[2])));
        }
        return questions;
    }

    public static void main(String[] args) {
                String[] list = {"123 1 1","356 1 0","327 2 0","489 0 1"};
        //123 456을 제외한 숫자 중 가능한 숫자 조합.
//        String[] list = {"123 0 0"};
        BaseballGame2 baseballGame2 = new BaseballGame2();
        System.out.println(baseballGame2.sol(list));
    }
}
