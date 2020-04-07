package strategiesOfAlgorithms.greedyAlgorithm.allocateMeetingRoom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.acmicpc.net/problem/1931
 * greedy algorithm 의 대표적 문제.
 * 유용하게 사용되는 문제 중 유명한 예로
 * : 활동 선택 문제(activity selection problem)
 * - 회의실배정 - 366p
 * : 정당성 증명
 * - 가장 종료 시간이 빠른 회의를 포함하는 최적해가 반드시 존재.
 */
public class AllocateRoom {
    // 각 회의는 [begin,end] 구간 동안 회의실을 사용
    public static int schedule(int n,int[][] time){
        //일찍 끝나는 순서대로 정렬.
        List<Pair> order = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            order.add(new Pair(time[i][0],time[i][1]));
        }
        //end를 기준으로 정렬.
        Collections.sort(order);
        //earliest : 다음 회의가 시작할 수 있는 가장 빠른 시간
        //selected : 지금까지 선택한 회의의 수
        int earliest = 0,selected =0;
        for (int i = 0; i < order.size(); i++) {
            int meetingBegin = order.get(i).begin;
            int meetingEnd = order.get(i).end;
            if (earliest<=meetingBegin){
                earliest = meetingEnd;
                selected++;
            }
        }
        return selected;
    }
    public static void main(String[] args) {
        int n = 11;
        //ans=4
        int[][] time = {
                {1,4}, {3,5}, {0,6}, {5,7},
                {3,8},{5,9}, {6,10}, {8,11},
                {8,12}, {2,13}, {12,14},
        };
        System.out.println(schedule(n,time));
    }
}
