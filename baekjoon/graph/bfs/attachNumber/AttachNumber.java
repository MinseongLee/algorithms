package baekjoon.graph.bfs.attachNumber;

import java.util.*;

public class AttachNumber {
    private static final int[] dx ={-1,0,0,1};
    private static final int[] dy ={0,-1,1,0};
    public void sol(int n,int[][] map){
        List<Integer> ansList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j]==1){
                    bfs(new Pair(i,j),map,ansList);
                }
            }
        }
        Collections.sort(ansList);
        System.out.println(ansList.size());
        for (int i = 0; i < ansList.size(); i++) {
            System.out.println(ansList.get(i));
        }
    }

    private void bfs(Pair start, int[][] map, List<Integer> ansList) {
        int n = map.length;
        int counting =1;
        map[start.x][start.y] = 2;
        Queue<Pair> q =new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            Pair here = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = here.x+dx[i];
                int ny = here.y+dy[i];
                if (nx>=0&&ny>=0&&nx<n&&ny<n){
                    if (map[nx][ny]==1){
                        map[nx][ny] =2;
                        counting++;
                        q.add(new Pair(nx,ny));
                    }
                }
            }
        }
        ansList.add(counting);
    }

    public static void main(String[] args) {
        int n = 7;
        int[][] list = {
                {0,1,1,0,1,0,0},
                {0,1,1,0,1,0,1},
                {1,1,1,0,1,0,1},
                {0,0,0,0,1,1,1},
                {0,1,0,0,0,0,0},
                {0,1,1,1,1,1,0},
                {0,1,1,1,0,0,0},
        };
        AttachNumber attachNumber = new AttachNumber();
        attachNumber.sol(n,list);
    }
}
