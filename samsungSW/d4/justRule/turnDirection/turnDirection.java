package samsungSW.d4.justRule.turnDirection;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 8382. 방향 전환
 *
 * bfs 문제
 * 처음 4개
 * 그담 2군데씩 방문.
 * 그리고 답이 나오면 끝.
 * bfs로는 크기가 너무 커진다. 2^200 만큼 걸려..
 *
 * 그래서 role 대로 풀어야한다. 그 룰은.
 * 1. 첫 시작은 같지 않은 값쪽 혹은 더 큰값쪽부터 움직인다.
 * 2. sX와 eX, sY와 eY 를 계속 비교해가며,
 * (이걸 반복하면, 결국 end값으로 간다.)
 * ++, -- 를 해주며, end값에 가까워진다.
 *
 * //에러가 났던 부분은 첫 시작때 더 큰 값쪽부터 움직이는 것을 조금 늦게 catch.
 * but 그리 오래는 아니고 금방 catch 함.
 * enum Move로 어디를 방문했는지 체크했다.
 */
public class turnDirection {
    //role로 풀어야하는 문제.
    //start -> end 가는 길이 (-,+),(+,+),(-,-),(+,-) 4군데로 나누어진다.
    //이 네군데를 처리하면된다.
    public static int counting(int[] coordinate){
        int startX = coordinate[0];
        int startY = coordinate[1];
        int endX = coordinate[2];
        int endY = coordinate[3];
        int counting = 0;
        Move move = Move.X;
        int distX = Math.abs(startX-endX);
        int distY = Math.abs(startY-endY);
//        if (startX==endX || distX<distY) move = Move.X;
        //처음에는 큰값부터 움직임.
        //처음에만 둘이 같은 경우, 같지 않은 경우로 움직이는 것이 좋다.
        if (startY==endY || distX>distY) move = Move.Y;

        while(startX!=endX||startY!=endY) {
            if (startX==endX&&move== Move.X){
                //startY가 작은지 큰지 체크해야한다.
                if (startY>endY) startY--;
                else startY++;
                move = Move.Y;
            }else if (startY==endY&&move== Move.Y){
                if (startX>endX) startX--;
                else startX++;
                move = Move.X;
            }
            else if (move== Move.X){
                if (startY>endY) startY--;
                else startY++;
                move = Move.Y;
            }
            else if (move== Move.Y){
                //startX가 endX보다 작은지 큰지 체크.
                if (startX>endX) startX--;
                else startX++;
                move= Move.X;
            }
            counting++;
        }
        System.out.println(startX+" "+startY+" "+endX+" "+endY);
        return counting;
    }

    //bfs로는 x,y 크기가 너무 커서 찾을 수 없다.
    public static int shortestCounting(int[] coordinate){
        //(x,y)
        Pair start = new Pair(coordinate[0],coordinate[1]);
        Pair end = new Pair(coordinate[2],coordinate[3]);
        return bfs(start,end);

    }

    private static int bfs(Pair start, Pair end) {
        if (start.equals(end)){
            return 1;
        }
        Map<Pair,Integer> dist = new HashMap<>();
        Queue<Pair> q = new LinkedList<>();
        dist.put(start,0);
        // start 때 처음만 4번.
        createX(q,start,dist);
        createY(q,start,dist);

//        q.add(start);
        while(!q.isEmpty()){
            Pair here = q.poll();
            if (end.equals(here)) return dist.get(here);
            if (here.getMove()== Move.X){
                createY(q,here,dist);
            }
            else if (here.getMove()== Move.Y){
                createX(q,here,dist);
            }
        }
        //만약 존재하지 않는다면,
        return -1;
    }

    private static void createY(Queue<Pair> q, Pair here,Map<Pair,Integer> dist) {
        if (here.getY()-1>=-100&&here.getY()-1<=100){
            q.add(new Pair(here.getX(),here.getY()-1, Move.Y));
            dist.put(new Pair(here.getX(),here.getY()-1, Move.Y),dist.get(here)+1);
        }
        if (here.getY()+1>=-100&&here.getY()+1<=100){
            q.add(new Pair(here.getX(),here.getY()+1, Move.Y));
            dist.put(new Pair(here.getX(),here.getY()+1, Move.Y),dist.get(here)+1);
        }
    }

    private static void createX(Queue<Pair> q, Pair here,Map<Pair,Integer> dist) {
        if (here.getX()-1>=-100&&here.getX()-1<=100){
            q.add(new Pair(here.getX()-1,here.getY(), Move.X));
            dist.put(new Pair(here.getX()-1,here.getY(), Move.X),dist.get(here)+1);
//            System.out.println((dist.get(here)+1)+" ");
        }
        if (here.getX()+1>=-100&&here.getX()+1<=100){
            q.add(new Pair(here.getX()+1,here.getY(), Move.X));
            dist.put(new Pair(here.getX()+1,here.getY(), Move.X),dist.get(here)+1);
        }
    }

    public static void main(String[] args) {
        Pair p = new Pair(0,0);
        Pair a = new Pair(0,0);
//        int[] coordinate = {-100,-100,100,100};
        int[] coordinate = {-1,0,0,0};
//        System.out.println(shortestCounting(coordinate));
        System.out.println(counting(coordinate));
    }
}
