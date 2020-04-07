package samsungSW.d4.graph.validationProgram;

/**
 * 1824. 혁진이의 프로그램 검증
 * exception 처리에 대한 고찰.
 * 이게.. exception 처리를 해줄 수 있는 범위를 넘어서는 문제이다..
 * 그렇다면 exception이 의미가 있나..?*******
 * 만약에.. exception으로 처리해줄 수 있는 범위가 넘어간다면,
 * exception 처리 하라는 문제가 아니다.
 * 모든 상태를 방문하라는 문제인것이다.
 * 모든 상태를 방문하는 것이 더 중요하지 않나요?
 * 그렇습니다.. 모든 상태를 방문하는 것이 제일 중요해요.
 * 그렇다면.. bfs가 제격이죠. 하지만 visited[][][][] 배열이 있다면,
 * 지금 만든 dfs로도 가능합니다.
 *
 * - hint를 얻고 푼 문제. visited[][][][] 을 사용하라는.
 * (이것만 얻었다.)
 */
public class ValidationProgram {
    //?, ., @, +, -
    private static final char[] MOVE = {'<', '>', '^', 'v'};
    //                              좌,우,상,하
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};
    private static final char[] CHANGE = {'_', '|'};
    private static final char RANDOM = '?';
    private static final char NOTHING = '.';
    private static final char STOP = '@';
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    //memory 안의 default = 0;
    private int memory = 0;
    //char direction = '>'; : default
    //int로 index를 기억하게 하는 것이 더 좋은 선택.
    private int direction = 1;
    //[x][y][memory][direction]
    private boolean[][][][] visited;

    // 이제 NO가 될 포인트를 찾아야한다.
    // NO가 될 포인트만 찾아서 exception 처리해주면 끝.

    /**
     * NO가 될 exception handling
     * 1.
     * 무한루프를 어떻게 해결해줘야할까..?
     * - no! 모든 상태를 방문하는 것. 이것이 해답이다.
     * exception 처리를 다 해줄 수 없을때, 문제로부터,
     * 그렇다면, 모든 상태를 방문하는거야.
     */
    public String sol(int r,int c,char[][] program){
        visited = new boolean[r][c][16][4];
        visited[0][0][memory][direction] = true;
        if (dfs(program,0,0)){
            return "YES";
        }
        return "NO";
    }
    /**
     * 목표 상태 도달할 수 없는지 어떻게 알 수 있을까..?
     * 1. @의 존재 유무.
     * 2. ?는 visited으로 처리했다.
     * 3. direction이 @ 쪽으로 향하지 않는 경우
     * 4. _, | 이 존재할 시, ---(메모리가 0이 될 가능성이 존재하지 않아,)
     * 두 방향 중 한 방향으로 갈 수 있는 경우,
     * 그 방향은 @ 쪽으로 갈 가능성이 없는 경우
     */
    /*public static String ids(char[][] program,int growth){
        for (int limit = 4;; limit+=growth) {
            best = limit+1;
            dfs(program,0,0,0);
            if (best<=limit) return "YES";
            else if (best==-1) return "NO";
        }

    }*/
    //값이 존재하지 않을 시, best=-1 을 넣어줄 것.
    private boolean dfs(char[][] program,int x,int y){
        boolean checkRandom = false;
        //@ 처리.
        if (program[x][y]==STOP) {
            return true;
        }
        // 여기부터는 program[x][y]가 무엇인지에 따라 행동이 달라진다.
        if (checkNum(program[x][y])){
            memory = program[x][y]-'0';
        }
        // 이 direction 방향에 따라 x,y를 넘겨야한다.
        else if (checkMove(program[x][y])){
            for (int i = 0; i < 4; i++) {
                if (program[x][y]==MOVE[i]){
                    direction = i;
                }
            }
//            direction = program[x][y];
        }
        // _ | 체크
        else if (checkChangeMove(program[x][y])) {
            //_
            if (memory == 0 && program[x][y] == CHANGE[0]) {
                //오른쪽
//                direction = MOVE[1];
                direction = 1;
            } else if (memory != 0 && program[x][y] == CHANGE[0]) {
                //left
//                direction = MOVE[0];
                direction = 0;
            }
            //|
            else if (memory == 0 && program[x][y] == CHANGE[1]) {
                //아래
//                direction = MOVE[3];
                direction = 3;
            } else if (memory != 0 && program[x][y] == CHANGE[1]) {
                //top
//                direction = MOVE[2];
                direction = 2;
            }
        }
        // '?'
        else if (program[x][y]==RANDOM){
            checkRandom = true;
        }
        else if (program[x][y]==PLUS){
            memory++;
            if (memory>15) memory= 0;
        }
        else if (program[x][y]==MINUS){
            memory--;
            if (memory<0) memory=15;
        }
        //그래프의 정점을 방문하는 것이 아니다. 그러므로,
        //방문되는 장소 위치를 true로 놓아줘야 무한 루프에 빠지지 않는다.
//        visited[x][y][memory][direction] = true;
//        System.out.println("x="+x+" y="+y+" me="+memory+" di="+direction);
        //checkRandom이 true가 되면, 4방향을 전부 방문한다.
        if (checkRandom){
            // 좌,우,상,하
            for (int i = 0; i < dx.length; i++) {
                //MOVE 역시 좌,우,상,bottom
//                direction = MOVE[i];
                int nx = getNx(x,dx[i],program);
                int ny = getNy(y,dy[i],program);
                if (!visited[nx][ny][memory][direction]){
                    visited[nx][ny][memory][direction] = true;
                    direction = i;
                    if (dfs(program,nx,ny)){
                        return true;
                    }
                }
            }
        }
        // '.' 아무 것도 하지 않는다. - 즉, 위에 설정된 값 그대로 보낸다.
        else{
            //여기서는 direction이 향하는 방향으로 간다.
            /*int index = 0;
            for (int i = 0; i < 4; i++) {
                if (direction==MOVE[i]){
                    index = i;
                    break;
                }
            }*/
            int nx = getNx(x,dx[direction],program);
            int ny = getNy(y,dy[direction],program);
            if (!visited[nx][ny][memory][direction]){
                visited[nx][ny][memory][direction] = true;
                if (dfs(program,nx,ny)){
                    return true;
                }
            }
        }
        return false;
    }

    private int getNy(int y, int dy, char[][] program) {
        int ny = y+dy;
        if (ny<0) ny = program[0].length-1;
        else if (ny>=program[0].length) ny = 0;
        return ny;
    }

    private int getNx(int x, int dx,char[][] program) {
        int nx = x+dx;
        if (nx<0) nx = program.length-1;
        else if (nx>=program.length) nx = 0;
        return nx;
    }

    private boolean checkChangeMove(char change) {
        if (change == CHANGE[0] || change == CHANGE[1]) {
            return true;
        }
        return false;
    }

    private boolean checkMove(char move) {
        //좌우상하
        if (move == MOVE[0] || move == MOVE[1] || move == MOVE[2] || move == MOVE[3]) {
            return true;
        }
        return false;
    }

    private boolean checkNum(char num) {
        if (num >= '0' && num <= '9') {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        /*int n = 4;
        int m = 4;*/
        /*int n = 14;
        int m = 8;*/
        String a = "6>--v. .^--_@";
        String b = ".>--v. .^--?@";
        int n = 2;
        int m =6;
        String c = "5>--v. .^--_@";
        String d = "v4<>0718 2+33^^v? <@5933-- <<^5<2@5 4.867531 +_8639>8 >4+377^+ 881^-2v3 78._>943 v+27_.6? >1-v9-2> 9.??3v-+ 30?_.20v 81_7539<";
        String e = "...v @... .... ^..<";
        String[] s = c.split(" ");
        char[][] program = new char[n][m];
        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length(); j++) {
//                System.out.println("i="+i+" j="+j);
                program[i][j] = s[i].charAt(j);
            }
        }
        ValidationProgram vp = new ValidationProgram();
        System.out.println(vp.sol(n,m,program));

    }

}
