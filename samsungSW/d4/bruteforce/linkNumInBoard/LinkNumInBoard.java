package samsungSW.d4.bruteforce.linkNumInBoard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 0~9,
 * 4x4 6번 이동 - 7자리 수 리턴. (모든 경우 수)
 *
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV7I5fgqEogDFAXB&categoryId=AV7I5fgqEogDFAXB&categoryType=CODE
 *
 * 시간복잡도
 * 4^16 이다. 각 칸마다 4번씩.
 * solution
 * - 한 번 거쳤던 곳 다시 가능.
 * 0으로 시작 가능.
 * 만들 수 있는 서로 다른 7자리 수.(같은 수 중복 불가)
 * 6번 이동.
 * 설계
 * 1. dx, dy
 * 2. 4방향에 방문시 그 방향에서 다시 4방향 방문하며 6번 방문
 * 3. 7자리 수를 만드는데, 이 7자리 수를 저장.
 * Set에 저장하면 한번에 찾기 가능. - O(1)
 */

public class LinkNumInBoard {
    private static final int[] dx = {-1,0,0,1};
    private static final int[] dy = {0,-1,1,0};
    private static Set<String> answer = new HashSet<>();
    private static final int LIMIT = 7;
    private boolean[][] visited;
    public static void main(String[] args) {
        char q = '1';
        int aa = q-'0';
        System.out.println(aa);
        int[][] related = {
                {1,1,1,1},
                {1,1,1,2},
                {1,1,2,1},
                {1,1,1,1},
        };
        int[][] rel ={
                {2,1,1,1},
                {1,1,2,1},
                {1,1,1,2},
                {1,1,2,1},
        };
        String ss = "1 2 3";
        LinkNumInBoard lnib = new LinkNumInBoard();
        System.out.println(lnib.solution(related));
        System.out.println("answer.size()="+answer.size());
        answer.clear();
        System.out.println(lnib.solution(rel));
        System.out.println("answer.size()="+answer.size());
        List<Integer> a = new ArrayList<>();
        a.add(5);
        a.add(4);
        a.add(3);
        //내부적으로 indexof 가 동작하므로, 시간복잡도가 O(n)이다.
        System.out.println(a.contains(3));
        String b= "123";
        String c = "123";
        answer.add(b);
        //내부적으로 map.containsKey()를 사용. 시간복잡도 O(1)
        System.out.println(answer.contains(b));
    }
    public int solution(int[][] related){
        int sum = 0;
        StringBuilder sb = new StringBuilder();
        visited = new boolean[related.length][related[0].length];
        for (int i = 0; i < related.length; i++) {
            for (int j = 0; j < related[i].length; j++) {
                sb.append(related[i][j]);
                visited[i][j] =true;
//                System.out.println("i="+i+" j="+j+" "+sb+" rel="+related[i][j]);
                sum += searchSeven(related,sb,i,j);
                sb.setLength(0);
                visited[i][j] =false;
            }
        }
        return sum;
    }

    private int searchSeven(int[][] related,StringBuilder sb, int x, int y) {
        //base case - border
        //이것이 위에있으면 안된다. 이와같은 코드에는.
        /*if (x<0||y<0||x>=related.length||y>=related[0].length){
            return 0;
        }*/
        //base case - seven에 7개의 숫자가 다 찼을 경우.
        if (sb.length()==LIMIT){
            if (answer.contains(sb.toString())){
                return 0;
            }else{
//                System.out.println("x="+x+" y="+y+" sb="+sb);
                answer.add(sb.toString());
                return 1;
            }
        }
        int ans = 0;
        //6번이동하기 위해 방문 한번했던 곳은 다시 방문못하게 해줄 것.
        for (int i = 0; i < dx.length; i++) {
            int nx = dx[i]+x;
            int ny = dy[i]+y;
            if (nx>=0&&nx<related.length&&ny>=0&&ny<related[0].length){
                sb.append(related[nx][ny]);
                //visited
                visited[nx][ny] = true;
                ans += searchSeven(related,sb,nx,ny);
                visited[nx][ny] = false;
                sb.deleteCharAt(sb.length()-1);
            }
        }
        return ans;
    }
}
