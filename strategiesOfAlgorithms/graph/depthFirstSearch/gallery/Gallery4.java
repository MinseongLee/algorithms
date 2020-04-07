package strategiesOfAlgorithms.graph.depthFirstSearch.gallery;

import java.util.ArrayList;
import java.util.List;

public class Gallery4 {
    private static List<Integer>[] adj;
    private static boolean[] visited;
    private static final int UNWATCHED =0;
    private static final int WATCHED =1;
    private static final int INSTALLED =2;
    private static int installed;

    public static int installCamera(int g,int[][] galleries){
        makeGraph(g,galleries);
        installed = 0;
        visited = new boolean[g];
        for (int i = 0; i < g; i++) {
            if (!visited[i]&&dfs(i)==UNWATCHED){
                installed++;
            }
        }
        return installed;
    }

    private static int dfs(int here) {
        visited[here] = true;
        //here과 연결된 노드들의 상태를 나타내기 위한 배열
        int[] children = {0,0,0};
        for (int i = 0; i < adj[here].size(); i++) {
            int there = adj[here].get(i);
            if (!visited[there]){
                ++children[dfs(there)];
            }
        }
        if (children[UNWATCHED]>0){
            installed++;
            return INSTALLED;
        }
        if (children[WATCHED]>0){
            return WATCHED;
        }
        return UNWATCHED;
    }

    private static void makeGraph(int g,int[][] galleries){
        adj = new List[g];
        for (int i = 0; i < g; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < galleries.length; i++) {
            adj[galleries[i][0]].add(galleries[i][1]);
            adj[galleries[i][1]].add(galleries[i][0]);
        }
    }

    public static void main(String[] args) {
        int n = 1000;
        int m = 1;
        int[][] bridge = {
                {0, 1},
        };
        System.out.println(installCamera(n,bridge));
    }
}
