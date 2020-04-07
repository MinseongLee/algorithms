package samsungSW.d5.playingDic;

import java.util.ArrayList;
import java.util.List;

/**
 * 트라이의 한 노드를 표현하는 객체는 자손 노드들을 가리키는 포인터 목록과,
 * 이 노드가 종료 노드인지를 나타내는 불린 값 변수로 구성된다.
 *
 * 루트 노드에는 null이 들어간다.
 */
public class TrieNode {
//    private char ch;
    //index가 곧 String으로 표현.
    private TrieNode[] children = new TrieNode[Trie.ALPHABETS];
    private List<Integer> indexChildren = new ArrayList<>();
    private boolean terminal;

    public TrieNode() {
    }

    /*public TrieNode(char ch) {
        this.ch = ch;
        children = ;
        terminal = false;
    }*/

    /*public TrieNode getChild(int index) {
        return children[index];
    }

    public void setChild(TrieNode newNode, int index) {
        children[index] = newNode;
    }*/

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public List<Integer> getIndexChildren() {
        return indexChildren;
    }

    public TrieNode[] getChildren() {
        return children;
    }
}
