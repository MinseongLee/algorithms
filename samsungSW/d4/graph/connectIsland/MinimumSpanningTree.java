package samsungSW.d4.graph.connectIsland;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1251. [S/W 문제해결 응용] 4일차 - 하나로
 * 최소 스패닝 트리 문제.
 */
public class MinimumSpanningTree {
    private List<Pair> edges = new ArrayList<>();
    public long sol(int n,int[] xList,int[] yList,double taxRate){
        makeEdges(xList,yList,taxRate);
        //스패닝 트리 구하기.
        //먼저 최소값을 맨 앞으로 정렬.
        Collections.sort(edges);
        OptimizedDisjointSet sets =new OptimizedDisjointSet(n);
        double ans = 0;
        int size = edges.size();
        for (int i = 0; i < size; i++) {
            int v = (int)edges.get(i).pair.weight;
            int u = edges.get(i).pair.vertex;
            //같은 집합에 속해있다면 continue;
            if (sets.find(v)==sets.find(u)) continue;
            //같은 집합에 없다면 weight가 최소값이므로, merge가능.
            sets.merge(v,u);
            ans += edges.get(i).weight;
        }
        return Math.round(ans);
    }

    //모든 간선 만들기.
    private void makeEdges(int[] xList, int[] yList,double taxRate) {
        int n = xList.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                calcDist(xList,yList,i,j,taxRate);
            }
        }
    }
    //소수 첫째자리에서 반올림.
    private void calcDist(int[] xList, int[] yList, int v, int u,double taxRate) {
        long x1 = xList[v],x2=xList[u];
        long y1=yList[v],y2=yList[u];
        System.out.println("(y2-y1)="+(y2-y1));
        System.out.println((x2-x1)*(x2-x1));
        System.out.println((y2-y1)*(y2-y1));
        //루트를 안씌어두 된다. 왜냐하면 제곱하므로,
        double dist = ((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1))*taxRate;
        System.out.println("dist="+dist);
        edges.add(new Pair(dist,new Pair(v,u)));
    }

    public static void main(String[] args) {
        int n =9;
        int[] xList = {567, 5, 45674, 24, 797, 29, 0, 0, 0};
        int[] yList = {345352, 5464, 145346, 54764, 5875, 0, 3453, 4545, 123};
        double taxRate = 0.0005;
        MinimumSpanningTree mst = new MinimumSpanningTree();
        System.out.println(mst.sol(n,xList,yList,taxRate));

        double a = 1.4;
        System.out.println(Math.round(a));
    }
}
