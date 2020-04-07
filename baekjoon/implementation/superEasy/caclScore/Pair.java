package baekjoon.implementation.superEasy.caclScore;

public class Pair implements Comparable<Pair>{
    int score;
    int number;

    public Pair(int score, int number) {
        this.score = score;
        this.number = number;
    }

    @Override
    public int compareTo(Pair o) {
        //큰숫자가 앞에 올 수 있도록.
        return Integer.compare(o.score,score);

    }
}
