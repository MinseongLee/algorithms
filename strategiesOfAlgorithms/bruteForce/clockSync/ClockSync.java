package strategiesOfAlgorithms.bruteForce.clockSync;

/**
 * 완전탐색을 풀기 위해서,
 * 이 문제가 순열,중복순열,조합,중복조합 문제인지부터 알아야한다.
 * 보통 순열,조합,중복순열 문제라고 생각하자.
 * 중복과 순서 체크를 하고
 * 문제가 원하는 바에 맞는 완전탐색을 진행할 것.
 *
 * 1.	Brute force
 * 1)	완전탐색 문제풀이 순서(how to think)
 * (1)	문제를 완전탐색으로 풀 수 있는지부터 확인
 * (2)	중복과 순서 체크
 * (3)	순열,중복순열,조합,중복조합 문제인지 파악.
 * -	중복조합이라도 중복순열로 풀 수 있는지 확인**
 * (4)	문제 의도 파악
 * (5)	설계
 * (6)	구현
 */


/**
 * 스위치를 누르는 횟수 0~3
 * 왜냐하면 4번 누르면 자기 자신.
 */
public class ClockSync {
    private static final int INF = 987654321;
    private static final int SWITCHES = 10;
    private static final int CLOCKS = 16;
    //linked[i][j]=1 i번 스위치와 j번 시계와 연결
    //linked[i][j]=0 i번 스위치와 j번 시계와 연결되어있지 않다.
    private static final int[][] linked = {
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1},
            {1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
    };
    public static void main(String[] args) {
        int[] clocks = {12, 9, 3, 12, 6, 6, 9, 3, 12, 9, 12, 9, 12, 12, 6, 6};
        //int[] clocks = {12, 6, 6, 6, 6, 6, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};
//        int[] clocks = {12, 6, 6, 6, 6, 6, 12, 12, 12, 12, 3, 12, 12, 12, 12, 12};
        long start = System.currentTimeMillis();
        System.out.println(solve(clocks, 0));
        long end = System.currentTimeMillis();
        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
    }

    /**
     * 순서x
     * 0~3 정수.
     *
     */
    private static int solve(int[] clocks, int swtch) {
        //base case
        if (swtch == SWITCHES) {
            return areAligned(clocks) ? 0 : INF;
        }
        // 이 스위치를 0번부터 3번 누르는 경우까지 모두 확인
        int ret = INF;
        // 이 로직이 순서o 중복o 인 로직이다. - 중복순열로직이야.
        //O(4^n)
        //중복을 허용해야하므로 0부터 계속 불러올 수 있게 하였다.
        for (int cnt = 0; cnt < 4; cnt++) {
            //System.out.println("before ret="+ret);
            ret = Math.min(ret, cnt + solve(clocks, swtch + 1));
            //System.out.println("after ret="+ret);
            push(clocks,swtch);
        }
        // push(clocks,swtch) 로 인해, 4번 호출되었으니
        // clocks는 원래와 같은 상태가 된다.
        return ret;
    }
    //swtch번 스위치를 누른다.
    private static void push(int[] clocks, int swtch) {
        for (int clock = 0; clock < CLOCKS; clock++) {
            if (linked[swtch][clock]==1){
                clocks[clock] += 3;
                if (clocks[clock]==15) clocks[clock] = 3;
            }
        }
    }

    //모든 시계가 12시를 가리키고 있는지 확인.
    private static boolean areAligned(int[] clocks) {
        for (int i = 0; i < clocks.length; i++) {
            if (clocks[i]!=12){
                return false;
            }
        }
        return true;
    }
    /**
     * 시계 모두 12시를 가리키도록
     * 시계 조작 방법 : 열개의 스위치
     * 각 스위치 - 최소 3개의 시계 ~ 5개의 시계를 조작가능.
     * 한 스위치를 누름 - 이 스위치와 연결된 시계들의 시간은 3시간씩 앞으로 움직임.
     *
     * 모든 시계를 12시로 돌리기 위해 스위치를 몇 번 눌러야 하는지
     * 12시로 돌리기 위한 최소횟수를 리턴. 불가능할 경우 -1
     */
}
