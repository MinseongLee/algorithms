package samsungSW.d3.exam;

public class Exam {
    /**
     * nextInt 가 nextLine보다 시간 엄청 오래걸린다.
     * nextLine()을 써야한다.**
     */
    /**
     * N명
     * 1~n 번호
     * T개 문제
     * solve or not
     * 각 문제는 그 문제를 풀지 못한 사람들의 수가 점수.
     * N T P
     * P 번
     */
    public static int[] score(int n,int t,int p,int[][] test){
        int[] answer = new int[2];
        //점수 -
        //p번째, 맞은 걸루
        //근데 미리 계산해놓는 것이 좋다. 즉,
        //index를 문제 번호로하는 배열을 만들어 거기에 점수를 저장.
        int[] problems = new int[t];
        //이렇게 queue를 사용하면 더 느리다.
//        Queue<Xy> q = new LinkedList<>();
        //문제 푼 개수와 점수 - 각 참가자의 (배열로 저장)
        //[i][0] - points, [i][1] - 개수
        int[][] participants = new int[n][2];
        for (int j = 0; j < t; j++) {
            for (int i = 0; i < n; i++) {
                if (test[i][j]==0){
                    problems[j]++;
                }
                /*else if (test[i][j]==1){
                    q.add(new Xy(i,j));
                }*/
            }
        }
        /*while(!q.isEmpty()){
            Xy xy = q.poll();
            int x= xy.getX();
            int y = xy.getY();
            participants[x][1]++;
            participants[x][0] += problems[y];
        }*/
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < t; j++) {
                if (test[i][j]==1){
                    participants[i][1]++;
                    participants[i][0] += problems[j];
                }
            }
        }
        answer[0] = participants[p-1][0];
        int myPoint = answer[0],myPointNum=participants[p-1][1];
        int myLevel = 1;
        //많은 점수 획득한 참가자수
        for (int i = 0; i < n; i++) {
            if (i==p-1) continue;
            int otherPoint =participants[i][0];
            int otherPointNum = participants[i][1];
            if (myPoint<otherPoint){
                myLevel++;
            }
            else if (myPoint==otherPoint &&myPointNum<otherPointNum){
                myLevel++;
            }else if (myPoint==otherPoint&&myPointNum==otherPointNum&&
            p>(i+1)){
                myLevel++;
            }
        }
        answer[1] = myLevel;
        return answer;
    }

    public static void main(String[] args) {
        int n = 5;
        int t = 4;
        int p = 4;
        //1 0 1 1
        //0 0 1 1
        //0 1 1 0
        //0 1 1 0
        //1 1 1 1
        int[][] test = {
                {1,0,1,1},
                {0,0,1,1},
                {0,1,1,0},
                {0,1,1,0},
                {1,1,1,1},
        };
        int[] score = score(n, t, p, test);
        System.out.println("sc="+score[0]+" "+score[1]);
        String b = "00001";
        int c = Integer.parseInt(b);
        System.out.println(c);
        int[][] a = {
                {1,3}
        };
        int[][] d = a.clone();
        System.out.println(d[0][1]);
    }
}
