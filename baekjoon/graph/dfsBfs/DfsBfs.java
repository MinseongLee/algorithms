package baekjoon.graph.dfsBfs;

import java.util.*;

/**
 *
 */
public class DfsBfs {
    private List<Integer>[] adj;
    public void sol(int n,int m,int[][] edges,int start){
        makeGraph(n+1,edges);
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        dfsAll(start,n+1,sb1);
        bfs(start,n+1,sb2);
        sb1.deleteCharAt(sb1.length()-1);
        System.out.println(sb1.toString());
        System.out.println(sb2.toString());
    }

    private void bfs(int start, int n,StringBuilder sb2) {
        boolean[] visited = new boolean[n];
        Queue<Integer> q= new LinkedList<>();
        visited[start] = true;
        q.add(start);
        sb2.append(start);
        while(!q.isEmpty()){
            int here = q.poll();
            for (int i = 0; i < adj[here].size(); i++) {
                int there = adj[here].get(i);
                if (!visited[there]){
                    visited[there] = true;
                    q.add(there);
                    sb2.append(" ").append(there);
                }
            }
        }
    }

    private void dfsAll(int start,int n,StringBuilder sb1) {
        boolean[] visited = new boolean[n];
        dfs(start,visited,sb1);
    }

    private void dfs(int here, boolean[] visited,StringBuilder sb1) {
        sb1.append(here).append(" ");
        visited[here] = true;
        for (int i = 0; i < adj[here].size(); i++) {
            int there = adj[here].get(i);
            if (!visited[there]){
                dfs(there,visited,sb1);
            }
        }
    }

    private void makeGraph(int n, int[][] edges) {
        adj = new List[n];
        //init
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        int m = edges.length;
        for (int i = 0; i < m; i++) {
            adj[edges[i][0]].add(edges[i][1]);
            adj[edges[i][1]].add(edges[i][0]);
        }
        //sort.
        for (int i = 0; i < n; i++) {
            Collections.sort(adj[i]);
        }
    }

    public static void main(String[] args) {
        //1000 1 1000
        //999 1000
        int n = 1000;
        int m = 1;
        int start =1000;
        int[][] edges = {{999,1000}};
        DfsBfs dfsBfs = new DfsBfs();
        dfsBfs.sol(n,m,edges,start);
    }
}
