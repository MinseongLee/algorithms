package strategiesOfAlgorithms.bruteForce.boggleGame;

public class Boggle {
    private static final int[] dx = {-1,-1,-1,0,0,1,1,1};
    private static final int[] dy = {-1,0,1,-1,1,-1,0,1};

    public static void main(String[] args) {
        String[][] board = {
                {"U","R","L","P","M"},
                {"X","P","R","E","T"},
                {"G","I","A","E","T"},
                {"X","T","N","Z","Y"},
                {"X","O","Q","R","S"},};
        String[] words = {"PRETTY","GIRL","REPEAT"};
        boolean[] game = game(board, words);
        for (int i = 0; i < game.length; i++) {
            System.out.print(" "+game[i]);
        }
        System.out.println();

    }
    /**
     * 단어 words의 존재 여부 확인.
     * 상하좌우 대각선으로 단어를 이어 갈 수 있음.
     *
     * how to solve
     * 1. 8방향 탐색
     * 2. 단어하나 넘겨 board에서 모든 경우를 탐색
     * 3. 스펠링을 하나씩 지워가며 탐색.
     * 4. 같은 단어를 지나갈 수 있으므로 visited 처리를 해주면 안된다.
     */
    public static boolean[] game(String[][] board, String[] words){
        boolean[] ans = new boolean[words.length];
        for (int i = 0; i < words.length; i++) {
            if (isWord(words[i],board)){
                System.out.println(words[i]);
                ans[i] = true;
            }
        }
        return ans;
    }

    private static boolean isWord(String word, String[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (word.charAt(0)==board[i][j].charAt(0)){
                    if (hasWord(word,i,j,board)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean hasWord(String word, int x, int y, String[][] board) {
        //check range
        if (x<0||y<0 ||x>=board.length||y>=board[0].length) return false;
        //check false
        if (word.charAt(0)!=board[x][y].charAt(0)) return false;
        //check true
        if (word.length()==1) return true;


        for (int k = 0; k < 8; k++) {
            int nx = dx[k]+x;
            int ny = dy[k]+y;
            if (hasWord(word.substring(1),nx,ny,board)){
                return true;
            }
        }
        return false;
    }
}
