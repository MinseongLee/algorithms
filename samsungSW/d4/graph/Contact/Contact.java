package samsungSW.d4.graph.Contact;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1238. [S/W 문제해결 기본] 10일차 - Contact
 * bfs 로 해결.
 */
public class Contact {
    private List<Integer>[] adj;
    public int sol(int n,int start,int[] edges){
        makeGraph(n,edges);
        return bfs(n,start);
    }

    private int bfs(int n, int start) {
        boolean[] visited = new boolean[n];
        int[] dist = new int[n];
        Queue<Integer> q= new LinkedList<>();
        dist[start] = 0;
        visited[start] = true;
        q.add(start);
        //모든 상태 방문.
        while(!q.isEmpty()){
            int here = q.poll();
            for (int i = 0; i < adj[here].size(); i++) {
                int there = adj[here].get(i);
                if (!visited[there]){
                    visited[there]= true;
                    dist[there] = dist[here]+1;
                    q.add(there);
                }
            }
        }
        //dist값이 가장 큰 값중 가장 뒤에 있는 index+1를 리턴.
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max,dist[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (max==dist[i]){
                ans = i;
            }
        }
        return ans;
    }

    //direct
    //edges[0] ->edges[1]
    private void makeGraph(int n, int[] edges) {
        adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        // 무조건 짝수.
        int size = edges.length;
        for (int i = 0; i < size; i+=2) {
            adj[edges[i]].add(edges[i+1]);
        }
    }
}
