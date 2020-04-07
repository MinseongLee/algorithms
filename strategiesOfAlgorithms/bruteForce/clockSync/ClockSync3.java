package strategiesOfAlgorithms.bruteForce.clockSync;

import java.util.*;

/**
 *
 */
public class ClockSync3 {
    private static final int CLOCKS = 16;
    private static final int SWITCH = 10;
    //1이 연결, 0이 비연결.
    private static final int[][] linked = {
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1},
            {1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
    };

    public static int sol(int[] clock){
        List<Integer> start = new ArrayList<>();
        List<Integer> end = new ArrayList<>();
        for (int i = 0; i < CLOCKS; i++) {
            start.add(clock[i]);
            end.add(12);
        }
        return bfs(start,end);
    }

    private static int bfs(List<Integer> start, List<Integer> end) {
        Map<List<Integer>,Integer> dist = new HashMap<>();
        Queue<List<Integer>> q= new LinkedList<>();
        dist.put(start,1);
        dist.put(end,-1);
        q.add(start);
        q.add(end);
        int clock = 0;
        while(!q.isEmpty()){
            List<Integer> here = q.poll();
            if (clock>1100) return -1;
            for (int click = 0; click < SWITCH; click++) {
                int sign = dist.get(here)>0?1:-1;
                List<Integer> there = createThere(here,click,sign);
                if (dist.get(there)==null){
                    clock++;
                    dist.put(there,dist.get(here)+sign);
                    q.add(there);
                }
                else if ((dist.get(here)>0&&dist.get(there)<0)||
                        (dist.get(here)<0&&dist.get(there)>0)){
                    return Math.abs(dist.get(there))+Math.abs(dist.get(here))-2+1;
                }
            }
        }
        return -1;
    }
    // 부호에 따라 값이 달라진다.
    // +이면 정방향(시계방향), -이면 역방향(시계반대방향)
    private static List<Integer> createThere(List<Integer> here, int click, int sign) {
        List<Integer> there = new ArrayList<>(here);
        for (int i = 0; i < CLOCKS; i++) {
            if (linked[click][i]==1){
                if (sign>0){
                    there.set(i,there.get(i)+3);
                }else{
                    there.set(i,there.get(i)-3);
                }
                if (there.get(i)<0) there.set(i,12);
                else if (there.get(i)>12) there.set(i,0);
            }
        }
        return there;
    }

    public static void main(String[] args) {
                int[] clocks = {12, 6, 6, 6, 6, 6, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};
//        int[] clocks = {12, 6, 6, 6, 6, 6, 12, 12, 12, 12, 3, 12, 12, 12, 12, 12};
        System.out.println(sol(clocks));
    }
}
