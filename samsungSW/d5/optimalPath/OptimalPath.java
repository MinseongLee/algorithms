package samsungSW.d5.optimalPath;

import java.util.ArrayList;
import java.util.List;

//최적 경로

/**
 * N명
 * 회사, 집, 각 고객의 위치, 0<=(x,y)<=100
 * |x1-x2|+|y1-y2|
 * 회사, 집, 고객 위치 중복 x
 * 회사 -> 고객 다 방문 -> 집 : 최단 경로
 * 2<=N<=10
 * 이동거리만.
 *
 * 회사좌표,집좌표,고객좌표 순
 * N의 크기가 작아서, brute force로 풀 수 있다.
 * O(n!)
 */
public class OptimalPath {
    private static final int INF = 987654321;
    //final을 붙여서 만드는 것이 좋다. company와 home은
    private static Pair company;
    private static Pair home;
    public static int shortestPath(int n,int[] lo){
        List<Pair> customer = new ArrayList<>();
        company = new Pair(lo[0],lo[1]);
        home = new Pair(lo[2],lo[3]);
        //lo의 개수는 무조건 짝수
        for (int i = 4; i < lo.length; i+=2) {
            customer.add(new Pair(lo[i],lo[i+1]));
        }
//        System.out.println(customer.size());
        //index넣는 곳.
        List<Integer> indexList = new ArrayList<>();
        boolean[] checkDupl = new boolean[n];
        return permutation(n,indexList,checkDupl,customer);
    }

    private static int permutation(int n, List<Integer> indexList,boolean[] checkdupl ,List<Pair> customer) {
        int size = customer.size();
        //이때, 들어온 값들 처리
        if (n==0){
            //company->customer->home
            return getPath(indexList,customer);
        }
        int shortest = INF;
        for (int i = 0; i < size; i++) {
            if (!checkdupl[i]){
                checkdupl[i] = true;
                indexList.add(i);
                shortest = Math.min(shortest,permutation(n-1,indexList,checkdupl,customer));
//                System.out.println("shortest="+shortest);
                checkdupl[i] = false;
                indexList.remove(Integer.valueOf(i));
            }
        }
        return shortest;
    }

    private static int getPath(List<Integer> indexList, List<Pair> customer) {
        int size = customer.size();
        int path = calcDist(company,customer.get(indexList.get(0)));
        for (int i = 0; i < size; i++) {
            if (i==size-1){
                path += calcDist(customer.get(indexList.get(i)),home);
            }else{
                path += calcDist(customer.get(indexList.get(i)),customer.get(indexList.get(i+1)));
            }
        }
        return path;
    }
    //|x1-x2|+|y1-y2|
    private static int calcDist(Pair start, Pair end) {
        return Math.abs(start.getX()-end.getX())+Math.abs(start.getY()-end.getY());
    }

    public static void main(String[] args) {
        int n = 5;
        int[] lo = {0, 0, 100, 100, 70, 40, 30, 10, 10, 5, 90, 70, 50, 20};
        System.out.println(shortestPath(n,lo));
    }
}
