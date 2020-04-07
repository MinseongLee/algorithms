package baekjoon.graph.dfs.componentCnt;

import java.util.ArrayList;
import java.util.List;

public class ComponentCnt {
    private List<Integer>[] adj;
    private int best;
    public int sol(int n,int m,int[][] list){
        makeGraph(list,n);
        dfsAll();
        return best;
    }

    private void dfsAll() {
        boolean[] visited = new boolean[adj.length];
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]){
                dfs(i,visited);
                best++;
            }
        }
    }

    private void dfs(int here, boolean[] visited) {
        visited[here] = true;
        for (int i = 0; i < adj[here].size(); i++) {
            int there = adj[here].get(i);
            if (!visited[there]){
                dfs(there,visited);
            }
        }
    }

    private void makeGraph(int[][] list,int n) {
        adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < list.length; i++) {
            adj[list[i][0]-1].add(list[i][1]-1);
            adj[list[i][1]-1].add(list[i][0]-1);
        }
    }

    public static void main(String[] args) {
        int n =6;
        int m =5;
        int[][] list={
                {1,2},
                {2,5},
                {5,1},
                {3,4},
                {4,6},
        };
        ComponentCnt componentCnt = new ComponentCnt();
        System.out.println(componentCnt.sol(n,m,list));
    }
}
