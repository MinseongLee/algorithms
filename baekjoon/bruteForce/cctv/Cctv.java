package baekjoon.bruteForce.cctv;

import java.util.ArrayList;
import java.util.List;

public class Cctv {
    private static final char[][] cctv={
        {'u','l','r','d'},
            {'u','l'},
        {'u','l','r','d'},
        {'u','l','r','d'},
    };
    private int best =Integer.MAX_VALUE;
    // 6은 벽 1~5cctv, 7번 광선.
    public int sol(int n,int m,int[][] map){
        List<Pair> camera = createCamera(map);
        // 5번은 항상 같으므로, 미리 처리해놓기.
        for (int i = 0; i < camera.size(); i++) {
            if (camera.get(i).number==5){
                doFiveCctv(map,camera.get(i).x,camera.get(i).y);
                //원소를 삭제하고 한칸 땡겨지므로, i를 마이너스 해줘야
                //모든 원소를 방문 가능하다.
                camera.remove(i);
                i--;
            }
        }

//        System.out.println(camera);
        List<Pair> list = new ArrayList<>();
        dfs(-1,0,camera,map,list);
        return best;
    }

    private void dfs(int here, int steps, List<Pair> camera, int[][] map, List<Pair> list) {
        if (steps==camera.size()){
            //그 방향에 맞게 visited 처리를 해주고, 남은 0값을 ++;
            int n = map.length;
            int m = map[0].length;
            int[][] visited = new int[n][m];
            for (int i = 0; i < list.size(); i++) {
                doCctv(list.get(i),map,visited);
            }
            //이제 남은 0의 개수세기.
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j]==0&&visited[i][j]==0){
                        ans++;
                    }
                }
            }
            best = Math.min(best,ans);
            return;
        }
        for (int next = here+1; next < camera.size(); next++) {
            int cctvIndex = camera.get(next).number - 1;
//            System.out.println(cctvIndex);
            for (int i = 0; i < cctv[cctvIndex].length; i++) {
                list.add(new Pair(camera.get(next),cctv[cctvIndex][i]));
                dfs(next,steps+1,camera,map,list);
                list.remove(list.size()-1);
            }
        }
    }

    private void doCctv(Pair cctv, int[][] map, int[][] visited) {
        int x = cctv.pair.x,y = cctv.pair.y;
        int num = cctv.pair.number;
        int direction = cctv.direction;
        //각 cctv 번호에 맞춰서 정리.
        //1번
        if (num==1){
            //4가지 방향.
            if (direction=='u'){
                getTop(x,y,visited,map);
            }else if (direction=='d'){
                getDown(x,y,visited,map);
            }else if (direction=='l'){
                getLeft(x,y,visited,map);
            }else if (direction=='r'){
                getRight(x,y,visited,map);
            }
        }
        //2번
        if (num==2){
            //up and down
            if (direction=='u'){
                getTop(x,y,visited,map);
                getDown(x,y,visited,map);
            }
            //left and right
            if (direction=='l'){
                getLeft(x,y,visited,map);
                getRight(x,y,visited,map);
            }
        }
        //3
        if (num==3){
            //up ,right
            if (direction=='u'){
                getTop(x,y,visited,map);
                getRight(x,y,visited,map);
            }
            //right,down
            else if (direction=='r'){
                getRight(x,y,visited,map);
                getDown(x,y,visited,map);
            }
            //left and down
            else if (direction=='d'){
                getLeft(x,y,visited,map);
                getDown(x,y,visited,map);
            }
            //up , left
            else if (direction=='l'){
                getTop(x,y,visited,map);
                getLeft(x,y,visited,map);
            }
        }
        //4
        if (num==4){
            //up,left,right
            if (direction=='u'){
                getTop(x,y,visited,map);
                getLeft(x,y,visited,map);
                getRight(x,y,visited,map);
            }
            //up,right,down
            if (direction=='r'){
                getTop(x,y,visited,map);
                getDown(x,y,visited,map);
                getRight(x,y,visited,map);
            }
            //left,down,right
            if (direction=='d'){
                getDown(x,y,visited,map);
                getLeft(x,y,visited,map);
                getRight(x,y,visited,map);
            }
            //up,left,down
            if (direction=='l'){
                getTop(x,y,visited,map);
                getLeft(x,y,visited,map);
                getDown(x,y,visited,map);
            }
        }
    }

    private void getDown(int x, int y, int[][] visited, int[][] map) {
        for (int i = x+1; i < map.length; i++) {
            if (map[i][y]==6) break;
            visited[i][y]=1;
        }
    }

    private void getTop(int x, int y, int[][] visited, int[][] map) {
        for (int i = x-1; i >=0; i--) {
            if (map[i][y]==6) break;
            visited[i][y]=1;
        }
    }

    private void getLeft(int x, int y, int[][] visited, int[][] map) {
        for (int j = y-1; j >=0 ; j--) {
            if (map[x][j]==6) break;
            visited[x][j]=1;
        }
    }

    private void getRight(int x, int y, int[][] visited,int[][] map) {
        for (int j = y+1; j <map[0].length ; j++) {
            if (map[x][j]==6) break;
            visited[x][j]=1;
        }
    }

    private void doFiveCctv(int[][] map, int x, int y) {
        int up = x-1, down=x+1,left=y-1,right=y+1;
        while((up>=0&&map[up][y]!=6)||(down<map.length&&map[down][y]!=6)||
                (left>=0&&map[x][left]!=6)||(right<map[0].length&&map[x][right]!=6)){
            if (up>=0&&map[up][y]!=6){
                map[up][y] =7;
                up--;
            }
            if (down<map.length&&map[down][y]!=6){
                map[down][y]=7;
                down++;
            }
            if (left>=0&&map[x][left]!=6){
                map[x][left]=7;
                left--;
            }
            if (right<map[0].length&&map[x][right]!=6){
                map[x][right]=7;
                right++;
            }
        }
        /*//up
        for (int i = x-1; i >=0; i--) {
            if (map[i][y]==6) break;
            map[i][y] = 7;
        }
        //left
        for (int j = y-1; j >=0; j--) {
            if (map[x][j]==6) break;
            map[x][j] = 7;
        }*/
    }

    private List<Pair> createCamera(int[][] map) {
        List<Pair> camera = new ArrayList<>();
        int n = map.length;
        int m = map[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j]>=1&&map[i][j]<=5){
                    camera.add(new Pair(i,j,map[i][j]));
                }
            }
        }
        return camera;
    }

    public static void main(String[] args) {
        /*int n =3;
        int m =7;
        int[][] list ={
                {4,0,0,0,0,0,0},
                {0,0,0,2,0,0,0},
                {0,0,0,0,0,0,4},
        };*/
        int n =1;
        int m =7;
        int[][] list ={
                {0,1,2,3,4,5,6},
        };
        Cctv cctv = new Cctv();
        System.out.println(cctv.sol(n,m,list));

        //for remove test.
        List<Integer> ans =new ArrayList<>();
        ans.add(5);
        ans.add(5);
        ans.add(2);
        ans.add(3);
        ans.add(5);
        int size = ans.size();
        for (int i = 0; i < ans.size(); i++) {
            System.out.println(ans.get(i));
            if (ans.get(i)==5){
                ans.remove(i);
                i--;
            }
        }
        System.out.println(ans);
    }
}
