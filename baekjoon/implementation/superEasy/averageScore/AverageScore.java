package baekjoon.implementation.superEasy.averageScore;

public class AverageScore {
    public int sol(int[] scores){
        int sum = 0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i]<40) scores[i] =40;
            sum += scores[i];
        }
        return sum/scores.length;
    }

    public static void main(String[] args) {
        AverageScore averageScore = new AverageScore();
//        System.out.println(averageScore.sol(list));
    }
}
