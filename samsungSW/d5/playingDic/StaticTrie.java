package samsungSW.d5.playingDic;

/**
 * 이게 정적 Trie .
 *
 */
public class StaticTrie {
    // static trie는 이렇게 배열의 크기를 먼저 지정해주고 시작한다.
    //n은 새로운 트리가 생성될때마다 N크기를 증가시킨다.
    //a->b, a->c 등등
    private static final int N = 210000;
    //M은 내가 넣고자 하는 단어 or 숫자의 크기.
    private static final int M = 26;
    private int[][] trie;
    private int[] cnt;
    private int trie_cnt;
    public void init(){
        trie = new int[N][M];
        cnt = new int[N];
        trie_cnt = 0;
    }
    //insert 에 그 알파벳이 존재하는 곳에 ++를 해줄 것.
    public void insert(int buffer_size, String buf) {
        //n은 0부터, 즉, 루트부터 시작.
        int n =0;
        for (int i = 0; i < buffer_size; i++) {
            int m = buf.charAt(i)-'a';
            //즉, 하위 노드가 존재하지 않는 경우
            if (trie[n][m]==0) trie[n][m] = ++trie_cnt;
            n = trie[n][m];
            cnt[n]++;
        }
    }
    public int query(int buffer_size, String buf) {
        int n = 0;
        for (int i = 0; i < buffer_size; i++) {
            int m = buf.charAt(i)-'a';
            n = trie[n][m];
        }
        System.out.println(cnt[n]);
        return cnt[n];
    }
}
