package samsungSW.d5.oddExplore;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1
 * 23
 * 456
 * ..
 * 인접한 방만 이동. 두 방 사이에 접점이 존재해야함.
 * 1 단위 시간에 인접한 방으로 이동 가능 - bfs
 * 민지가 보물을 찾을때 걸리는 최소시간.
 * --
 * 민지의 위치와 보물 위치가 주어짐.
 * 1<=a,b<=10,000
 */
public class OddExplore {
    private static final int NUM_SIZE = 10000;
    private static int[] heights = new int[NUM_SIZE+2];
    //1만이므로, 1만 숫자에 대해 height를 다 구한 후
    // 이 높이를 바탕으로 문제를 해결하였다.

    // 6방향 체크
    // x-h,x-h+1,x-1,x+1,x+h,x+h+1
    // default 1,1,1
    private static void makeHeight(int start, int size, int height) {
        // base case
        if (size >= heights.length) {
            // 만약 start 부터 heights.len 까지 줄 수 있는 수가 남았다면,
            if (start<heights.length){
                for (int i = start; i < heights.length; i++) {
                    heights[i] = height;
                }
            }
            return;
        }
        for (int i = start; i <= size; i++) {
            heights[i] = height;
        }
        //+2,+3,+4,,, height를 +해주면 된다.
        makeHeight(size + 1, size + height + 1, height + 1);
    }

    public static int shortestPath(int start, int end) {
        makeHeight(1, 1, 1);
        /*for (int i = 0; i < heights.length; i++) {
            System.out.println("i="+i+" "+heights[i]);
        }*/
        return bfs(start, end);
    }

    // x-h,x-h+1,x-1,x+1,x+h,x+h+1
    private static int bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        int[] dist = new int[NUM_SIZE+2];
        Arrays.fill(dist,-1);
        q.add(start);
        dist[start] = 0;
        while (!q.isEmpty()) {
            if (addAroundHere(q,dist,end)){
                return dist[end];
            }
        }
        return -1;
    }

    private static boolean addAroundHere(Queue<Integer> q, int[] dist, int end) {
        int here = q.poll();
        int h = heights[here];
//        System.out.println(here+ " "+h);
        if (here==end){
            return true;
        }
        //형제노드는 같은 높이인경우만 추가.
        //left
        if (here-1>=0&&h == heights[here - 1]&&dist[here-1]==-1) {
            dist[here-1]=dist[here]+1;
            q.add(here - 1);

        }
        //right
        if (here+1<NUM_SIZE&&h == heights[here + 1]&&dist[here+1]==-1) {
            dist[here+1]=dist[here]+1;
            q.add(here + 1);
        }
        //top -left
        if (here-h>=0&&h==heights[here-h]+1&&dist[here-h]==-1){

            dist[here-h] = dist[here]+1;
            q.add(here-h);
        }
        //top - right
        if (here-h+1>=0&&h==heights[here-h+1]+1&&dist[here-h+1]==-1){
            dist[here-h+1] = dist[here]+1;
            q.add(here-h+1);
        }
        //bottom - left
        if (here+h<NUM_SIZE&&h==heights[here+h]-1&&dist[here+h]==-1){
            dist[here+h] = dist[here]+1;
            q.add(here+h);
        }
        //bottom - right
        if (here+h+1<NUM_SIZE&&h==heights[here+h+1]-1&&dist[here+h+1]==-1){
            dist[here+h+1] = dist[here]+1;
            q.add(here+h+1);
        }
        return false;
    }

    public static void main(String[] args) {
        int a = 10;
//        int b= 10000;
        int b= 9999;
        System.out.println(shortestPath(a,b));
    }
}
