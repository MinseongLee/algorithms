package samsungSW.d4.graph.loveCounselor;

import java.util.*;

/**
 * 1494. 사랑의 카운슬러
 * bfs로 풀어보겠다.
 * ABCD를 하나의 start 상태로 보고,
 * <p>
 * 백터 -
 * V = (x,y)
 * |V|=|(x,y)|=x*x+y*y
 * 에서,, 백터 합의 크기의 최소값 즉,
 * (6,0),(3,3),(-7,2),(-4,-1) 이 있다면,
 * AB=(6-3,0-3),CD=(-7-(-4),2-(-1))
 * (3,-3),(-3,3) 이 나오고, 이 값의 합은 (0,0)이 나온다. 즉, 이 값이 최소값이 된다.
 * <p>
 * 후.. 문제 설명이 너무 부족하다. 그래도 I think I am continuing like this.
 */

/**
 * 내가 못 푼이유.. n=20일때, 여기서 반절만 선택하고
 * 나머지 반절은 신경쓰지 않는 코드를 만들지 못했다.
 * 즉, n/2인 경우만 처리하는 완벽한 알고리즘을 고안하지 못했다.
 *
 * 나는.. n/2 인 경우만 처리하는 dfs 코드를 만들지 못해서 못풀었다.
 * n=10이었다면 풀었겠지만..후.. 이문제 좋다.
 *
 * n=20 일때, 10개를 선택하는 것은 너무 크다. 하지만 i=here부터 시작한다면 가능하다.
 * 2명씩 짝지으는 경우이므로, 시간안에 들어오는 이유는 바로 i=here 때문이다.
 */
public class LoveCounselor {
    private static long min = Long.MAX_VALUE;
    private static int n;

    public static long dfsAll(int[][] xy) {
        //index를 저장하는 list
        List<Integer> ans = new ArrayList<>();
        n = xy.length;
        boolean[] visited = new boolean[n];
//        ans.add(0);
        min = Long.MAX_VALUE;
        return dfs(0, 0, visited, xy);
    }

    //순열+ dfs
    //2명씩 짝지으는경우.
    private static long dfs(int here, int cnt, boolean[] visited, int[][] xy) {
//        System.out.println("cnt="+cnt);
        //즉, cnt가 basecase 역할을 하는 것이다. cnt가 n/2가 되는 순간.
        //그렇다면, n이 10인 경우까지만 탐색 즉, 모든 경우 탐색이 가능하다.
        if (cnt==n/2){
            //선택된 경우만 신경쓰고 선택되지 않은 경우는 신경쓰지 않아도 된다.
            long x=0,y=0;
            for (int i = 0; i < n; i++) {
                if (visited[i]){
                    x += xy[i][0];
                    y += xy[i][1];
                }else{
                    x -= xy[i][0];
                    y -= xy[i][1];
                }
            }
            return x*x+y*y;
        }
//        long min = Long.MAX_VALUE;
        for (int i = here+1; i < n; i++) {
            if (!visited[i]) {
//                System.out.println("i="+i);
                visited[i] = true;
                min = Math.min(min,dfs(i, cnt + 1, visited, xy));
//                System.out.println("min="+min);
                visited[i] = false;
            }
        }
//        System.out.println(min);
        return min;
    }

    private static long calcPair(List<Integer> ans, int[][] xy) {
        long sum = 0, sumX = 0, sumY = 0;
        for (int i = 0; i < ans.size(); i += 2) {
            int x1 = xy[ans.get(i)][0];
            int x2 = xy[ans.get(i + 1)][0];
            int y1 = xy[ans.get(i)][1];
            int y2 = xy[ans.get(i + 1)][1];
            sumX += x2 - x1;
            sumY += y2 - y1;
        }
        sum += Math.abs(sumX * sumX + sumY * sumY);
//        System.out.println(ans + " " + sum);
        return sum;
    }

    private static long calcPair(List<Pair> start) {
        //|(x,y)| = x*x+y*y 가 아래처럼 표현된다.
        //Math.abs((x1+x1)*(x2+x2)+(y1+y1)*(y2+y2));
        long sum = 0, sumX = 0, sumY = 0;
        for (int i = 0; i < start.size(); i += 2) {
            int x1 = start.get(i).x;
            int y1 = start.get(i).y;
            int x2 = start.get(i + 1).x;
            int y2 = start.get(i + 1).y;
            sumX += x2 - x1;
            sumY += y2 - y1;
        }
        sum += Math.abs(sumX * sumX + sumY * sumY);
        return sum;
    }

    //queue가 없는 bfs
    public static long bfs(int[][] xy) {
        int n = xy.length;
        List<Pair> start = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            start.add(new Pair(xy[i][0], xy[i][1]));
        }
        Map<List<Pair>, Long> dist = new HashMap<>();
        long min = calcPair(start);
        dist.put(start, min);
        //크기가 너무 크고 양방향 탐색이 용이하지 않다.
        Queue<List<Pair>> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            List<Pair> here = q.poll();
            //이렇게 2부터 라고 하면, 2개 선택하는 거에 대해 모든 경우수를 확인할 수 있다.
            //중복x, 순서x 경우를.
            for (int i = 0; i < n; i += 2) {
                for (int j = i + 2; j < n; j++) {
                    //i와 j만 바꾸면 된다.
                    List<Pair> there = createThere(here, i, j);
                    if (dist.get(there) == null) {
                        long th = calcPair(there);
                        min = Math.min(min, th);
//                        System.out.println(min + " " + th);
                        dist.put(there, th);
                        q.add(there);
                    }
                }
            }
        }
        return min;
    }

    private static List<Pair> createThere(List<Pair> here, int i, int j) {
        List<Pair> there = new ArrayList<>(here);
        Pair tmp = there.get(i);
        there.set(i, there.get(j));
        there.set(j, tmp);
        return there;
    }

    public static void main(String[] args) {
        /*int n = 4;
        int[][] xy ={
                {6,0},
                {3,3},
                {-7,2},
                {-4,-1},
        };*/
        int n = 10;
        int[][] xy = {
                {-4, -5},
                {-71, -62},
                {-20, 93},
                {70, 39},
                {-26, -55},
                {-46, 81},
                {52, -56},
                {42, -22},
                {-24, -97},
                {-6, -79},
        };
//        System.out.println(bfs(xy));
        System.out.println(dfsAll(xy));
//        System.out.println("sol=" + sol(xy));
        long x1 = -100000;
        long y1 = 100000;
        long x2 = 100000;
        long y2 = -100000;
        //만약 내가 구한 식이 내 생각과 다르게 나오고 그런다면,
        //여러가지 시도를 해볼 것.
        //80000000000
        //20000000000
//        long sum = Math.abs(x1*x2)+Math.abs(y1*y2);
        long sum = Math.abs((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
//        System.out.println(sum);
        List<Pair> aa = new ArrayList<>();
        aa.add(new Pair(0, 0));
        aa.add(new Pair(0, 1));
        List<Pair> bb = new ArrayList<>(aa);
        Pair tmp = bb.get(0);
        bb.set(0, bb.get(1));
        bb.set(1, tmp);
//        System.out.println(aa.equals(bb));

    }
}
