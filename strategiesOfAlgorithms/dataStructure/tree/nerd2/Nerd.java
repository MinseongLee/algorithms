package strategiesOfAlgorithms.dataStructure.tree.nerd2;

import java.util.*;

/**
 * 702p
 * TreeMap<> 을 활용해서 풀어보자.
 * 일단, 이진탐색만 활용한다면 내가 어떻게 풀지는 결정할 수 있다.
 *
 * 책은 좌표를 이용해서 풀음.
 *
 * 나중에 다시 구현. - 2/13/2020
 */

public class Nerd {
    private static final int[][] participants = {
            {72,50},
            {57,67},
            {74,55},
            {64,60},
    };
    public static void main(String[] args) {
        Map<Integer,Integer> problemNum = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        Map<Integer,Integer> plateNum = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        problemNum.put(72,50);
        problemNum.put(57,67);
        problemNum.put(74,55);
        problemNum.put(64,60);
        plateNum.put(50,72);
        plateNum.put(67,57);
        plateNum.put(55,74);
        plateNum.put(60,64);

        for (int key :
                problemNum.keySet()) {
            System.out.println("key="+key+" problem="+problemNum.get(key));
        }
        for (int key:
             plateNum.keySet()) {
            System.out.println("key="+key+" plate="+plateNum.get(key));
        }
    }
    /**
     * 두 사람의 문제 수나 라면 그룻 수는 같은 경우가 존재하지 않는다.
     * 그렇다면,, 같은 경우가 없으므로, map을 두 개만든다면?
     */
    public static int solution(int[][] people){
        Map<Integer,Integer> participants = new TreeMap<>();
        int sum = 0;
        for (int i = 0; i < people.length; i++) {
            sum += registered(people[i][0],people[i][1],participants);
        }
        return 0;
    }

    // 새 점 (x,y)가 추가되었을 때 participants를 갱신하고,
    // 다른 점에 지배당하지 않는 점들의 개수를 반환한다.
    private static int registered(int x, int y, Map<Integer, Integer> participants) {
        //(x,y)가 이미 지배당하는 경우에는 그냥 (x,y)를 버린다.
        if (isDominated(x,y,participants)) return participants.size();
        // 기존에 있던 점 중 (x,y)에 지배당하는 점들을 지운다.
        removeDominated(x,y,participants);
        participants.put(x,y);
        return participants.size();
    }

    private static void removeDominated(int x, int y,Map<Integer, Integer> participants) {

    }

    //새로운 점 x,y가 기존의 다른 점들에 지배당하는지 확인
    private static boolean isDominated(int x, int y,Map<Integer, Integer> participants) {
        // x보다 오른쪽에 있는 점 중 가장 왼쪽에 있는 점을 찾는다.
        Iterator<Integer> it = participants.keySet().iterator();
        //그런 점이 없으면 (x,y)는 지배당하지 않는다.
        if (participants.size()==1) return false;
        // 이 점은 x보다 오른쪽에 있는 점 중 가장 위에 있는 점이므로,
        // (x,y)가 어느 점에 지배되려면 이 점에도 지배되어야 한다.
        return y < it.next();
    }
}
