package strategiesOfAlgorithms.graph.depthFirstSearch.dictionary;

/**
 * 이로직에서 조심할 점은 makeGraph를 만들때, break를 해줘야한다는 것이다.
 */
public class Dictionary3 {
    private static final int ALPHABET_SIZE=26;
    private int[][] adj = new int[ALPHABET_SIZE][ALPHABET_SIZE];
    public String sol(String[] words){
        makeGraph(words);
        //여기서 오류 체크. 만약 역방향 간선이 있다면,
        if (!isValidGraph()){
            return "INVALID HYPOTHESIS";
        }
        String s = dfsAll();
        return s;
    }
    //역방향 간선 체크.
    private boolean isValidGraph() {
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            for (int j = 0; j < ALPHABET_SIZE; j++) {
                if (adj[i][j]==1&&adj[j][i]==1){
                    return false;
                }
            }
        }
        return true;
    }

    private String dfsAll() {
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[ALPHABET_SIZE];
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (!visited[i]){
                dfs(i,visited,sb);
            }
        }
        //여기서 reverse 까지
        return sb.reverse().toString();
    }

    private void dfs(int here, boolean[] visited, StringBuilder sb) {
        visited[here] = true;
        for (int there = 0; there < adj[here].length; there++) {
            if (!visited[there]&&adj[here][there]==1){
                dfs(there,visited,sb);
            }
        }
        sb.append((char)(here+'a'));
    }

    //size가 1일수도 있다. 1인경우는.. 그냥 a~z 까지 출력하면 되잖아?
    private void makeGraph(String[] words) {
        for (int i = 1; i < words.length; i++) {
            int len = Math.min(words[i-1].length(),words[i].length());
            for (int j = 0; j < len; j++) {
                //directed graph
                int a = words[i-1].charAt(j)-'a';
                int b= words[i].charAt(j)-'a';
                if (a!=b){
                    adj[a][b]=1;
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] words = {"gg","kia","lotte","lg","hachwa","han"};
        Dictionary3 dictionary3 = new Dictionary3();
        System.out.println(dictionary3.sol(words));
    }
}
