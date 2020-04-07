package baekjoon.graph.bfs.decreaseNum;

import java.util.*;

/**
 * 산술 오버플로 항상 생각해줘야한다.**
 * 산술 오버플로가 발생시. 그냥 String으로 리턴해버리는 방법도 존재.
 * 혹은 int -> long
 *
 * 이문제 풀이법.
 * : 나는 bfs로 각 start 0~9 까지의 숫자중 조건을 만족하는 각 숫자를
 * 하나씩 붙여가며 해결하였다.
 *
 */

public class DecreaseNum {
    //이렇게 String으로 리턴.
    public String sol(int n){
        List<String> start = createStart();
        return bfs(start,n);
    }
    //아니면 이렇게 long형으로 리턴.
    public long solution(int n){
        List<String> start = createStart();
        return bfs2(start,n);
    }
    private long bfs2(List<String> start, int n) {
        Map<String,Integer> numMap = new HashMap<>();
        Queue<String> q = new LinkedList<>();
        //init
        int k =getStart(numMap,q,start);
//        System.out.println(k);
        while(!q.isEmpty()){
            String here = q.poll();
//            System.out.println(numMap.get(here)+" here="+here);
            if (numMap.get(here)==n) return Long.parseLong(here);
            for (int num = 0; num < 9; num++) {
                char ch = (char)(num+'0');
                //here의 가장 마지막 값과 비교한다.
                if (here.charAt(here.length()-1)>ch){
                    String there = here+ch;
                    if (numMap.get(there)==null){
                        //가장 작은 숫자부터 비교해 나가므로, 순서를 하나씩++
                        //해주어도 된다.
                        numMap.put(there,k++);
                        q.add(there);
                    }
                }else{
                    break;
                }
            }
        }
        return -1;
    }
    private String bfs(List<String> start, int n) {
        Map<String,Integer> numMap = new HashMap<>();
        Queue<String> q = new LinkedList<>();
        //init
        int k =getStart(numMap,q,start);
//        System.out.println(k);
        while(!q.isEmpty()){
            String here = q.poll();
//            System.out.println(numMap.get(here)+" here="+here);
            if (numMap.get(here)==n) return here;
            for (int num = 0; num < 9; num++) {
                char ch = (char)(num+'0');
                //here의 가장 마지막 값과 비교한다.
                if (here.charAt(here.length()-1)>ch){
                    String there = here+ch;
                    if (numMap.get(there)==null){
                        //가장 작은 숫자부터 비교해 나가므로, 순서를 하나씩++
                        //해주어도 된다.
                        numMap.put(there,k++);
                        q.add(there);
                    }
                }else{
                    break;
                }
            }
        }
        return String.valueOf(-1);
    }

    private int getStart(Map<String, Integer> numMap, Queue<String> q, List<String> start) {
        int k=0;
        for (int i = 0; i < start.size(); i++) {
            numMap.put(start.get(i),k++);
            q.add(start.get(i));
        }
        return k;
    }

    private List<String> createStart() {
        List<String> start = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            start.add(String.valueOf(i));
        }
        return start;
    }

    public static void main(String[] args) {
//        int n =18;
        int n =100000;
        DecreaseNum decreaseNum = new DecreaseNum();
        System.out.println(decreaseNum.sol(n));
        System.out.println(decreaseNum.solution(n));
    }
}
