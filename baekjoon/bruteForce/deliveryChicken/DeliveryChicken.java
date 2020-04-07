package baekjoon.bruteForce.deliveryChicken;

import java.util.ArrayList;
import java.util.List;

public class DeliveryChicken {
    private static final int INF = 987654321;
    //치킨 거리의 최소값.
    private int best = INF;
    private List<Pair> chicken;
    private List<Pair> house;
//    private int count=0;
    //n*n = map[n][n]
    public int sol(int n,int m,int[][] map){
        //먼저 chicken과 house 부터 나눌 것.
        chicken = createChicken(map);
        house = createHouse(map);
        boolean[] visited = new boolean[chicken.size()];
        //-1부터 시작하면 모든 시작지점을 다 확인.
        //next=here+1로 주고, dfs(next)를 넘기면 된다.
        dfs(-1,m,visited);
        //0부터 시작한다면 next=here 이라고 주면 모든 시작지점을 다 확인.
        //dfs는 next+1 로 주고.
//        dfs(0,m,visited);
//        System.out.println(count);
        return best;
    }

    private void dfs(int here, int steps, boolean[] visited) {
        //여기서 모든 거리의 합을 구할 것.
        if (steps==0){
//            count++;
            //각 집이 visited true가 된 치킨 집에 방문.
            best = Math.min(best,getDist(visited));
            return;
        }
        //next=here 주는 것은 조합. visited처리는 순열.
//        for (int next = here; next < chicken.size(); next++) {
        //dfs(next+1,steps-1,visited);
        for (int next = here+1; next < chicken.size(); next++) {
            if (!visited[next]){
                visited[next] = true;
                dfs(next,steps-1,visited);
                visited[next] = false;
            }
        }
    }

    private int getDist(boolean[] visited) {
        int sum=0;
        for (int i = 0; i < house.size(); i++) {
            int minSub = INF;
            for (int j = 0; j < chicken.size(); j++) {
                if (visited[j]){
                    minSub = Math.min(minSub,dist(house.get(i).x,house.get(i).y,chicken.get(j).x,chicken.get(j).y));
                }
            }
            sum += minSub;
        }
        return sum;
    }

    private int dist(int x, int y, int x1, int y1) {
        return Math.abs(x-x1)+Math.abs(y-y1);
    }

    private List<Pair> createHouse(int[][] map) {
        List<Pair> house = new ArrayList<>();
        int n = map.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j]==1){
                    house.add(new Pair(i,j));
                }
            }
        }
        return house;
    }

    private List<Pair> createChicken(int[][] map) {
        List<Pair> chicken = new ArrayList<>();
        int n = map.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j]==2){
                    chicken.add(new Pair(i,j));
                }
            }
        }
        return chicken;
    }

    public static void main(String[] args) {
        int n = 5;
        int m =1;
        int[][] list = {
                {1,2,0,0,0},
                {1,2,0,0,0},
                {1,2,0,0,0},
                {1,2,0,0,0},
                {1,2,0,0,0},
        };
        DeliveryChicken deliveryChicken = new DeliveryChicken();
        System.out.println(deliveryChicken.sol(n,m,list));
    }
}
