package samsungSW.d4.graph.searchWay;

import java.util.ArrayList;
import java.util.List;

/**
 * 1219. [S/W 문제해결 기본] 4일차 - 길찾기
 * A -> B 가는길
 * 1~2개의 길 존재.
 * 일방통행, 위상정렬
 * A->B 가는길 존재하는지 확인.
 * 각 노드는 0~99
 *
 * dfs 로 풀었다.
 *
 */

public class SearchWay {
    //인접 list 로 표현하자. directed
    private static List<Integer>[] adj;
    private static boolean[] visited;
    private static final int DESTINATION = 99;
    private static void makeGraph(int[][] vertex){
        //정점이 몇개인지 알수없으므로, 정해진 정점 개수를 모두 저장 후
        //입력 받은 간선들로 계산한다.
        adj = new List[100];
        visited = new boolean[100];
        int n = adj.length;
        int m = vertex.length;
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            adj[vertex[i][0]].add(vertex[i][1]);
        }
    }
    //0부터 시작해서 경로가 존재하는지만 확인하면 된다.
    //1이면 경로 존재 0이면 no
    private static int dfs(int here){
        if (here==DESTINATION) return 1;
        visited[here] = true;
        for (int i = 0; i < adj[here].size(); i++) {
            int there = adj[here].get(i);
            if (!visited[there]){
                if (dfs(there)==1){
                    return 1;
                }
            }
        }
        return 0;
    }
    //0부터 시작해서 끝점까지 경로가 존재하는지만 확인해야한다.
    //즉, dfsAll을 쓰면안된다.
    public static int dfsAll(int[][] vertex){
        int n = adj.length;
        makeGraph(vertex);
        int ok = 0;
        for (int i = 0; i <n ; i++) {
            if (!visited[i]){
                ok = dfs(i);
                if (ok==1) return ok;
            }
        }
        return ok;
    }

    public static void main(String[] args) {
        int n = 16;
        String s = "0 1 0 2 1 4 1 3 4 8 4 3 2 9 2 5 5 6 5 7 7 99 7 9 9 8 9 10 6 10 3 7";
        int[][] vertex = new int[n][2];
        String[] ch = s.split(" ");
        int j =0;
        for (int i = 0; i < n; i++,j+=2) {
            vertex[i][0] =Integer.parseInt(ch[j]);
            vertex[i][1] = Integer.parseInt(ch[j+1]);
        }
        makeGraph(vertex);
        System.out.println(dfs(0));

    }
}
