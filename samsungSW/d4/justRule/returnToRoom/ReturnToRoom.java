package samsungSW.d4.justRule.returnToRoom;

import java.util.*;

/**
 * 최단시간 to thr room
 * 400 room
 * 돌아가는 복도의 구간이 겹치면 안된다.
 * 위에있는 bfs로직 틀린로직.. gotoRoom이 맞는 로직이다.**
 */
public class ReturnToRoom {
    //인접 리스트가 좋을거같다.
    private static List<Integer>[] adj;
    //discover 된 곳만 남은 간선을 확인
    private static boolean[] discovered;
    //visited 된 곳의 간선은 확인하지 말 것.
    private static boolean[] visited;

    //여기에서 bfs 모두 돌리도록 할 것.
    public static int bfsAll(int[][] rooms){
        makeGraph(rooms);
        int n = rooms.length;
        discovered = new boolean[n];
        visited = new boolean[n];
        //모두 값을 나누어주고 +1을 해주어야한다.
        int shortestPath = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                shortestPath = Math.max(shortestPath,bfs(i,rooms));
            }
        }
        return shortestPath+1;
    }
    private static int bfs(int start,int[][] rooms){
        int n = rooms.length;
        visited[start] = true;
        discovered[start] =true;
        Queue<Integer> q = new LinkedList<>();
        int shortestPath = 0;
        boolean config = false;
        q.add(start);
        while(!q.isEmpty()){
            int here = q.poll();
            for (int i = 0; i < adj[here].size(); i++) {
                //간선이 하나 이상일 때 무조건 +1
                int there = adj[here].get(i);
                if (!visited[there]){
                    visited[here] = true;
                    config = true;
                }
                if (!discovered[there]){
                    discovered[there] = true;
                    q.add(there);
                }
            }
            if (config){
                shortestPath++;
                config = false;
            }
        }
        return shortestPath;
    }
    private static void makeGraph(int[][] rooms){
        int n = rooms.length;
        //n명의 학생. 0~n-1
        adj = new List[n];
        for (int i = 0; i < n; i++) {
            //초기화부터
            adj[i] = new ArrayList<>();
        }
        //각 path가 몇 개의 path를 포함하는지 나타내는 로직.
        for (int i = 0; i < n; i++) {
            int start = roomNum(rooms[i][0]);
            int end = roomNum(rooms[i][1]);
            for (int j = 0; j < n; j++) {
                if (i==j) continue;
                if (checkRange(rooms[j][0],rooms[j][1],start,end)){
                    //정점 번호 - index - undirected
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        Arrays.sort(adj, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return Integer.compare(o2.size(),o1.size());
            }
        });
    }
    public static int countingPath(int[][] rooms){
        int n = rooms.length;
        List<Student> students = new ArrayList<>(n);
        int[] includePath = new int[n];
        //각 path가 몇개의 path를 포함하는지 나타내는 로직.
        for (int i = 0; i < n; i++) {
            int start = roomNum(rooms[i][0]);
            int end = roomNum(rooms[i][1]);
            Student student = new Student(start,end,0);
            for (int j = 0; j < n; j++) {
                if (i==j) continue;
                if (checkRange(rooms[j][0],rooms[j][1],start,end)){
                    student.addPath();
                }
            }
            students.add(student);
        }
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Integer.compare(o2.getIncludePath(),o1.getIncludePath());
            }
        });

        // 가장 많은 path를 포함하는 것부터 처리.
        int counting = 1;//모든 경우가 포함하는 경우가 존재하지 않는다면, 1.
        for (int i = 0; i < n; i++) {
            int start = roomNum(students.get(i).getNowRoom());
            int end = roomNum(students.get(i).getMyRoom());
            System.out.println("i="+i+" start="+start+" "+end);
            for (int j = i+1; j < n; j++) {
                //단 하나라도 포함한다면 +1만 해주면 된다.
                if (checkRange(students.get(j).getNowRoom(),students.get(j).getMyRoom(),start,end)){
                    counting++;
                }
                //포함하지 않는 것은 아웃시켜줘야한다.
                else{

                }
            }
        }
        return counting;
    }

    private static boolean checkRange(int destS, int destE, int start, int end) {
        //도착지 S, E 가 포함되면, true.
        if (start<=destS&&destS<=end){
            return true;
        }else if (start<=destE&&destE<=end){
            return true;
        }
        return false;
    }

    //홀수일땐, - +1, 짝수일땐, -1
    private static int roomNum(int roomNum) {
        /*if (roomNum % 2 == 1) {
            return roomNum + 1;
        }
        //if (roomNum%2==0){
        else {
            return roomNum - 1;
        }*/
        if (roomNum%2==0){
            return roomNum-1;
        }
        return roomNum;
    }
    //위에있는 bfs로직 틀린로직.. gotoRoom이 맞는 로직이다.**
    // 방문할 수 있는 모든 방에 +1을 해주었다.
    // 중복되면 안되므로, 가장 큰 값이 답이 된다.
    // start 값이 end값보다 더 클 수 있으므로, 크다면, 바꾸어준다.
    public static int gotoRoom(int[][] rooms){
        int[] roomNum = new int[200];
        //start와 end를 홀수기준으로 2로 나눈 몫을 가지고 index로 사용.
        for (int i = 0; i < rooms.length; i++) {
            int s = rooms[i][0];
            int e = rooms[i][1];
            if (s>e){
                int tmp = s;
                s = e;
                e = tmp;
            }
            int start = roomNum(s)/2;
            int end = roomNum(e)/2;
            // 방문할수있는 모든 방번호에
            // start에서 end 사이의 값을 1씩 증가시켜준다.
            for (int j = start; j <= end; j++) {
                roomNum[j]++;
            }
        }
        int shortestPath = 0;
        int n = roomNum.length;
        for (int i = 0; i < n; i++) {
            shortestPath = Math.max(shortestPath,roomNum[i]);
        }
        return shortestPath;
    }
    public static void main(String[] args) {
        int n = 2;
        /*int[][] rooms = {
                {1,3},
                {2,200},
        };*/
        int[][] rooms = {
                /*{10,20},
                {30,40},
                {50,60},
                {70,80},*/
                {10,100},
                {20,80},
                {30,50},
                {60,70},
                {200,110},
                {120,130},
                {150,160},
                {300,310},
        };
//        System.out.println(bfsAll(rooms));
        System.out.println(gotoRoom(rooms));
    }
}
