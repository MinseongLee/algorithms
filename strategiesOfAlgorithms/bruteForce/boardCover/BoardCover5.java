package strategiesOfAlgorithms.bruteForce.boardCover;

public class BoardCover5 {
    private static final int[][][] cover={
            {{0,0},{1,0},{1,1}},
            {{0,0},{1,0},{0,1}},
            {{0,0},{0,1},{1,1}},
            {{0,0},{1,0},{1,-1}},
    };
    private int best;
    public int sol(String[][] map){
        int[][] board = createBoard(map);
        if (isNotValid(board)){
            return 0;
        }
        return coverBlocks(board);
//        return best;
    }

    private int coverBlocks(int[][] board) {
        int h = board.length,w=  board[0].length;
        int x =-1,y=-1;
        for (int i = 0; i <h ; i++) {
            for (int j = 0; j < w; j++) {
                if (board[i][j]==0){
                    x=i;
                    y=j;
                    break;
                }
            }
            if (x!=-1) break;
        }
        if (x==-1) {
            best++;
            return 1;
        }
        int ans = 0;
        for (int type = 0; type < 4; type++) {
            if (setBlock(x,y,type,board,1)){
                ans+=coverBlocks(board);
            }
            setBlock(x,y,type,board,-1);
        }
        return ans;
    }

    private boolean setBlock(int x, int y, int type, int[][] board, int delta) {
        boolean ok = true;
        for (int i = 0; i < 3; i++) {
            int nx = cover[type][i][0]+x;
            int ny = cover[type][i][1]+y;
            if (nx<0||ny<0||nx>=board.length||ny>=board[0].length){
                ok =false;
            }
            // 2가 된다는 것은 검은색에 흰색이 놓이거나
            // 흰색에 두개가 놓인 경우이므로,
            //false를 해준 후 바로 풀어줘야한다.
            //그리고 이 로직이 이 program의 핵심이다.
            else if ((board[nx][ny]+=delta)>1){
                ok = false;
            }
        }
        return ok;
    }

    private boolean isNotValid(int[][] board) {
        int h = board.length;
        int w=  board[0].length;
        int whiteCnt =0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (board[i][j]==0){
                    whiteCnt++;
                }
            }
        }
        return whiteCnt % 3 != 0;
    }

    private int[][] createBoard(String[][] map) {
        int h = map.length;
        int w=  map[0].length;
        int[][] board = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j].charAt(0)=='#'){
                    board[i][j] =1;
                }else{
                    board[i][j]=0;
                }
            }
        }
        return board;
    }

    public static void main(String[] args) {
        String[][] board = {
                {"#", "#", "#", "#", "#", "#", "#","#","#","#"},
                {"#", ".", ".", ".", ".", ".",".",".",".", "#"},
                {"#", ".", ".", ".", ".", ".",".",".",".", "#"},
                {"#", ".", ".", ".", ".", ".",".",".",".", "#"},
                {"#", ".", ".", ".", ".", ".",".",".",".", "#"},
                {"#", ".", ".", ".", ".", ".",".",".",".", "#"},
                {"#", ".", ".", ".", ".", ".",".",".",".", "#"},
                {"#", "#", "#", "#", "#", "#", "#","#","#","#"},
        };
        /*String[][] board = {
                {"#", ".", ".", "."},
                {"#", ".", ".", "."},
                {"#", "#", "#", "#"}
        };*/
        BoardCover5 boardCover5 = new BoardCover5();
        System.out.println(boardCover5.sol(board));
    }
}
