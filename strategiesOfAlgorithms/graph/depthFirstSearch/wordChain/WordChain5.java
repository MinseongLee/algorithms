package strategiesOfAlgorithms.graph.depthFirstSearch.wordChain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 오일러 경로 문제.
 *
 * getEulerTrailOrCircuit()
 * getEulerCircuit()
 * 이 두 method가 오일러 경로 구하는 문제이다.
 */
public class WordChain5 {
    private static final int ALPHABET_SIZE=26;
    private int[][] adj = new int[ALPHABET_SIZE][ALPHABET_SIZE];
    private List<String>[][] graph = new List[ALPHABET_SIZE][ALPHABET_SIZE];
    private int[] outdegree = new int[ALPHABET_SIZE];
    private int[] indegree = new int[ALPHABET_SIZE];
    public String sol(String[] words){
        makeGraph(words);
        if (!isValidGraph()){
            return "IMPOSSIBLE";
        }
        //오일러 서킷이나 트레일 찾기.
        List<Integer> circuit = getEulerTrailOrCircuit();
        //모든 간선을 방문하지 못했으면 실패
        if (circuit.size()!=words.length+1) return "IMPOSSIBLE";
        Collections.reverse(circuit);
        return getWords(circuit);
    }

    private String getWords(List<Integer> circuit) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < circuit.size(); i++) {
            int a = circuit.get(i-1);
            int b= circuit.get(i);
            if (sb.length()>0) sb.append(" ");
            sb.append(graph[a][b].get(graph[a][b].size()-1));
            //같은 단어가 출력되면 안되므로 추가된것은 제거.
            graph[a][b].remove(graph[a][b].size()-1);
        }
        return sb.toString();
    }
    private List<Integer> getEulerTrailOrCircuit() {
        List<Integer> circuit = new ArrayList<>();
        //우선 trail 찾기. 시작점 존재.
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            //시작점 존재한단 의미.
            if (indegree[i]==outdegree[i]+1){
                getEulerCircuit(i,circuit);
                return circuit;
            }
        }
        //or circuit이니 아무곳에서 시작해도 된다.
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (outdegree[i]>0){
                getEulerCircuit(i,circuit);
                return circuit;
            }
        }
        //모두 실패한 경우 빈배열 반환
        return circuit;
    }

    private void getEulerCircuit(int here, List<Integer> circuit) {
        for (int there = 0; there < adj[here].length; there++) {
            while(adj[here][there]>0){
                adj[here][there]--;
                getEulerCircuit(there,circuit);
            }
        }
        circuit.add(here);
    }

    //in-out =0, or 1,-1 하나씩만 존재.
    private boolean isValidGraph() {
        int inCnt=0,outCnt=0;
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            int a = indegree[i]-outdegree[i];
            if (a>1||a<-1) return false;
            if (a==1) inCnt++;
            else if (a==-1) outCnt++;
        }
        if ((inCnt==1&&outCnt==1)||(inCnt==0&&outCnt==0)) return true;
        return false;
    }

    private void makeGraph(String[] words) {
        //init
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            for (int j = 0; j < ALPHABET_SIZE; j++) {
                graph[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < words.length; i++) {
            int first = words[i].charAt(0)-'a';
            int last = words[i].charAt(words[i].length()-1)-'a';
            adj[first][last]++;
            graph[first][last].add(words[i]);
            outdegree[first]++;
            indegree[last]++;
        }
    }

    public static void main(String[] args) {
        String[] words = {"dog", "god", "dragon", "need"};
        WordChain5 wordChain5 = new WordChain5();
        System.out.println(wordChain5.sol(words));
    }
}
