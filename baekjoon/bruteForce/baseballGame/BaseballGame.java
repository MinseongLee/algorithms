package baekjoon.bruteForce.baseballGame;

/**
 * 이문제를 어떻게 풀렸지?
 * 모든 조건을 하나하나 만족하려고 했나?
 * 그렇게 하면 할 수록 복잡해지기만 했지.
 * 그냥 num하고 주어진 숫자하고 비교해서 strike인 경우와 ball인 경우를 ++해주고,
 * 주어진 strike와 ball을 비교하니, 아주 쉽게 풀렸다.
 *
 *  그런데 내가 못푼 이유는 문제를 확대해석한 것이 크다.
 *  나는 strike가 여러 경우가 된다고 생각했다. 하지만.. 문제에서는
 *  strike가 1개 라면, 실제로도 한개인것이다. 이것을 빠르게 깨달았으면,
 *  이렇게 쉽게 구현할 수 있었다.
 *  내가 못푼 이유는 문제해석을 제대로 못한 것에 있다.*
 */

public class BaseballGame {
    public int sol(String[] questions){
        int ans =0;
        for (int i = 111; i <= 999; i++) {
            String num = String.valueOf(i);
            if (num.charAt(1)=='0'||num.charAt(2)=='0') continue;
            //같은 숫자도 안됨.
            if (num.charAt(0)==num.charAt(1)||num.charAt(0)==num.charAt(2)||num.charAt(1)==num.charAt(2)) continue;
            if (isValidNum(num,questions)){
                System.out.println(num);
                ans++;
            }
        }
        return ans;
    }

    private boolean isValidNum(String num, String[] questions) {
        for (int i = 0; i < questions.length; i++) {
            int strikeCnt=0,ballCnt=0;
            String[] q = questions[i].split(" ");
            int strike = Integer.parseInt(q[1]);
            int ball = Integer.parseInt(q[2]);
            //여기서 strike와 ball의 개수를 cnt하고, 주어진 st,ball과 비교를한다.
            //개수가 무조건 똑같아야한다는 문제조건이 없었는데..
            //아.. "정직하게 답한다"는 표현이있다..
            // 즉,, strike가 3개면 3이라고 답하지 않겠는가? 라고 추측할 수 있다...
            //후.. 이렇게 문제를 정확하게 파악하는 것이 중요하다..
            //이것만 되면, 정말 문제들 다 풀수있다.
            //현재 겪고 있는 큰 문제가 바로 문제해석능력이다.**
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (j==k&&num.charAt(j)==q[0].charAt(k)){
                        strikeCnt++;
                    }
                    else if (j!=k&&num.charAt(j)==q[0].charAt(k)){
                        ballCnt++;
                    }
                }
            }
            //모든 조건을 만족해야하므로, 같지 않는 경우가 하나라도 존재하면 false
            if (strikeCnt!=strike||ballCnt!=ball){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
//        String[] list = {"123 1 1","356 0 1","327 0 1","489 0 1"};
        //123 456을 제외한 숫자 중 가능한 숫자 조합.
        String[] list = {"123 0 0"};
        BaseballGame baseballGame = new BaseballGame();
        System.out.println(baseballGame.sol(list));
    }
}
