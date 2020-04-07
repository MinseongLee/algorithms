package baekjoon.bruteForce.baseballGame;

public class State {
    //하나의 숫자가 될 수 있는 strike와 ball의 개수.
    int num;
    int strike;
    int ball;

    public State(int num, int strike, int ball) {
        this.num = num;
        this.strike = strike;
        this.ball = ball;
    }
}
