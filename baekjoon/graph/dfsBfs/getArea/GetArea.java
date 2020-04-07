package baekjoon.graph.dfsBfs.getArea;

import java.util.*;

public class GetArea {
    private static final int[] dx ={-1,0,0,1};
    private static final int[] dy ={0,-1,1,0};
    //m*n
    public void sol(int m,int n,int k,int[][] list){
        int[][] map = createMap(list,m,n);
        /*for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }*/

        //dfs
        List<Integer> ans = dfsAll(map);
        Collections.sort(ans);
        System.out.println(ans.size());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.size(); i++) {
            if (sb.length()>0) sb.append(" ");
            sb.append(ans.get(i));
        }
        System.out.println(sb);
        //init - bfs도 돌리기위한.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j]==2) map[i][j]=0;
            }
        }
        //bfs
        List<Integer> ans2 = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j]==0){
                    ans2.add(bfs(i,j,map));
                }
            }
        }
        Collections.sort(ans2);
        System.out.println(ans2.size());
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < ans2.size(); i++) {
            if (sb2.length()>0) sb2.append(" ");
            sb2.append(ans2.get(i));
        }
        System.out.println(sb2);
    }

    private int bfs(int x, int y, int[][] map) {
        int counting = 1;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(x,y));
        map[x][y] = 2;
        while(!q.isEmpty()){
            Pair here = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = here.x+dx[i];
                int ny = here.y+dy[i];
                if (nx>=0&&ny>=0&&nx<map.length&&ny<map[0].length){
                    if (map[nx][ny]==0){
                        counting++;
                        map[nx][ny]=2;
                        q.add(new Pair(nx,ny));
                    }
                }
            }
        }
        return counting;
    }

    private List<Integer> dfsAll(int[][] map) {
        List<Integer> ans = new ArrayList<>();
        int m = map.length;
        int n = map[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j]==0){
                    ans.add(dfs(i,j,map));
                }
            }
        }
        return ans;
    }

    private int dfs(int x, int y, int[][] map) {
        //방문했단 소리.
        map[x][y] = 2;
        int ans = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x+dx[i];
            int ny = y+dy[i];
            if (nx>=0&&ny>=0&&nx<map.length&&ny<map[0].length){
                if (map[nx][ny]==0){
                    //위에서 방문처리를 했으니 당연히 ans도 위에서 ++를 해줘야한다.
                    ans += dfs(nx,ny,map);
                }
            }
        }
        return ans;
    }

    private int[][] createMap(int[][] list,int m,int n) {
        int[][] map = new int[m][n];
        for (int i = 0; i < list.length; i++) {
            makeRectangle(list[i][1],list[i][0],list[i][3],list[i][2],map);
        }
        return map;
    }
    //직사각형을 장애물 1로 map에 표현.
    private void makeRectangle(int x1, int y1, int x2, int y2, int[][] map) {
        for (int i = x1; i <x2 ; i++) {
            for (int j = y1; j < y2; j++) {
                map[i][j] = 1;
            }
        }
    }

    public static void main(String[] args) {
        int m =5;
        int n = 7;
        int k=3;
        int[][] list= {
                {0,2,4,4},
                {1,1,2,5},
                {4,0,6,2},
        };
        GetArea getArea = new GetArea();
        getArea.sol(m,n,k,list);
    }
}
