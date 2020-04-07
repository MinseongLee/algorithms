package samsungSW.d4.dp.testScore;


import java.util.ArrayList;
import java.util.List;

/**
 * ****대표적인 조합문제. 중복x, 순서x
 * 뒤에 숫자를 하나씩 추가해주면서 푸는 문제이다.
 * ex)2,3,5라면,
 * 0
 * 0,0+2
 * 0,0+3,0+2+3
 * 0,0+5,0+2+5,0+3+5,0+2+3+5
 * 이렇게 뒤에 숫자를 하나씩 추가해주면서 중복, 순서없이 풀어나갈 수 있다.
 */


public class TestScoreCombinationDp {
    //이풀이로는.. n이 23만 넘어가도 힘들다..
    public static int scoreCount(int[] points) {
        int[] ans = new int[100 * 100 + 1];
        //0점과 원소 1개인 경우 처리
        ans[0] = 1;
        for (int score = 1; score < ans.length; score++) {
            for (int j = 0; j < points.length; j++) {
                // score가 같은 수가 하나라도 있다면,
                // 바로 다음 스코어를 확인하면 된다.
                if (score == points[j]) {
                    ans[score]++;
                    break;
                }
            }
        }
        //원소2개인 경우부터 완탐 시작.
        for (int i = 1; i < points.length; i++) {
            List<Integer> list = new ArrayList<>();
            combination(points.length, i + 1, list, ans, points);
        }
        int count = 0;
        for (int i = 0; i < ans.length; i++) {
            count += ans[i];
        }
        return count;
    }

    private static void combination(int n, int pick, List<Integer> list, int[] ans, int[] points) {
        if (pick == 0) {
            int sum = 0;
            for (int i = 0; i < list.size(); i++) {
                sum += points[list.get(i)];
            }
            ans[sum] = 1;
            return;
        }
        int smallest = list.isEmpty() ? 0 : list.get(list.size() - 1) + 1;
        for (int i = smallest; i < n; i++) {
            list.add(i);
            combination(n, pick - 1, list, ans, points);
            list.remove(list.size() - 1);
        }
    }

    //dp 스럽게 푼 문제.
    //순서x, 중복x, 조합문제를 이렇게 풀수도있다.
    public static int solve(int[] points) {
        for (int i = 0; i < points.length; i++) {
            System.out.println("i=" + i + " " + points[i]);
        }
        int sum = 0;
        for (int i = 0; i < points.length; i++) {
            sum += points[i];
        }
        int[] ans = new int[sum + 1];
        ans[0] = 1;
        //뒤에 숫자를 하나씩 추가해주면서 푸는 문제이다.
        //ex)2,3,5라면,
        //0
        //0,0+2
        //0,0+3,0+2+3
        //0,0+5,0+2+5,0+3+5,0+2+3+5
        //이렇게 뒤에 숫자를 하나씩 추가해주면서 중복, 순서없이 풀어나갈 수 있다.
        sum = 0;
        for (int i = 0; i < points.length; i++) {
            sum += points[i];
            //2,3,43,1,54
            for (int j = sum; j >= 0; j--) {
                if (ans[j] == 1) {
                    System.out.println("i=" + i + " j=" + j + " j+p[i]=" + (j + points[i]));
                    ans[j + points[i]] = 1;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == 1)
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
//        int[] points = {5,3,2};
//        int[] points = {1,1,1,1,1,1,1,1,1,1};
//        int[] points = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,11,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
//        int[] points = {1,1,1,1,1,2,2,2,3,3,3};
        //good examples
        int[] points = {2,2,3,3,6,43,2,4,7,9,10,4,2,1,54,1,1,2,1,1,5,100};
//        int[] points = {2,3,43,1,54};
//        int[] points = {2, 3, 43, 54};
//        Arrays.sort(points);
        System.out.println(solve(points));
        System.out.println(scoreCount(points));
//        System.out.println(count(points));
        /*int[] noSameNum = reduceSameNum(points);
        for (int i = 0; i < noSameNum.length; i++) {
            System.out.print(noSameNum[i]+" ");
        }
        System.out.println();*/
    }
}
