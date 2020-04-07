package strategiesOfAlgorithms.graph.minimumSpanningTree.lan;

import importantInfo.tree.disjointSet.OptimizedDisjointSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 3/9/2020
 *
 * 모든 정점과 연결하는 모든 간선을 구한 후, edges
 * sort(edges)를 weight를 기준으로 해준 후,
 * 미리 연결된 간선들을 제외하고 : sets,
 * 추가된 간선들 중에서 최소값을 기준으로
 * 하나씩 크루스칼 알고리즘으로 해결하였다.
 */
public class Lan {
    private static List<Pair> edges = new ArrayList<>();
    // xy[i][0]=x,xy[i][1]=y; , linked는 간선의 모임.
    public static double kruskal(int n,int m,int[][] xy,int[][] linked){
        makeEdges(xy);
        // Arrays.sort()- weight를 기준으로 정렬.
        Collections.sort(edges);
        OptimizedDisjointSet sets = new OptimizedDisjointSet(n);
        //미리 연결되어 있는 곳들을 먼저 연결 시키기. and dist=0;
        for (int i = 0; i < m; i++) {
            int v = linked[i][0], u = linked[i][1];
            sets.merge(v,u);
        }
        double ans = 0;
        int size = edges.size();
        for (int i = 0; i < size; i++) {
            int v = (int)edges.get(i).pair.weight;
            int u = edges.get(i).pair.vertex;
            //같은 집합에 속해있다면 그 집합 안에 또 연결하면 안된다.
            if (sets.find(v)==sets.find(u)) continue;
            //같은 집합에 없다면 이 weight가 최소값이므로, merge가능.
            sets.merge(u,v);
            ans+= edges.get(i).weight;
        }
        return ans;
    }
    // edges 에 두 정점을 연결하는 모든 간선을 만들기.
    private static void makeEdges(int[][] xy){
        int n = xy.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                calcDist(xy,i,j);
            }
        }
    }

    private static void calcDist(int[][] xy,int vertex,int vertex2) {
        long x1=xy[vertex][0],y1=xy[vertex][1];
        long x2=xy[vertex2][0],y2=xy[vertex2][1];
        //대각선 구하기.
        double dist = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        edges.add(new Pair(dist,new Pair(vertex,vertex2)));
    }

    public static void main(String[] args) {
        /*int n = 3;
        int m = 1;
        int[][] xy = {
                {0,0},
                {0,1},
                {1,2}
        };
        int[][] linked = {
                {0,1},
        };*/
        int n = 10;
        int m = 5;
        int[][] xy = {
                {-7,6},
                {-7,8},
                {10,-5},
                {-4,3},
                {10,-4},
                {-4,6},
                {-5,-10},
                {0,4},
                {-10,-7},
                {-6,10},
        };

        int[][] linked = {
                {9,7},
                {7,3},
                {9,7},
                {5,0},
                {8,6},
        };
        System.out.println(String.format("%.10f",kruskal(n,m,xy,linked)));
    }
}
