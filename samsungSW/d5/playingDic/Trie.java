package samsungSW.d5.playingDic;

public class Trie {
    public static final int ALPHABETS = 26;
    private TrieNode head;

    public Trie() {
        head = new TrieNode();
    }

    public TrieNode insert(String key, TrieNode node) {
        if (key.length() == 0) {
            node.setTerminal(true);
        } else {
            int next = toNumber(key.charAt(0));
            //해당 자식 노드가 없다면 생성
            if (node.getChildren()[next] == null) {
                node.getChildren()[next] = new TrieNode();
                //index를 저장해서 검색을 O(1)로 해결,
                node.getIndexChildren().add(next);
            }
            //해당 자식 노드로 재귀호출
            node.getChildren()[next] = insert(key.substring(1), node.getChildren()[next]);
        }
        return node;
        /*TrieNode node = head;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            //index
            int index = toNumber(c);
            //추출한 알파벳을 가진 하위 노드가 존재하지 않으면,
            if (node.getChildren()[index]==null){
                TrieNode newNode = new TrieNode(c);
                //노드를 그 index 위치에 저장.
                node.setChild(newNode,index);


            }else{
                //만약 null이 아니면 그 아래 노드로 이동을 한다.
                node = node.getChild(index);
            }
        }
        node.setTerminal(true);*/
    }

    //이 노드를 루트로 하는 트라이에 문자열 key와 대응되는 노드를 찾는다.
    //없으면 0을 반환
    //key는 접두사이다.
    public int find(String key, TrieNode node, int counting) {
        if (key.length() == 0) {
            //terminal 노드인지 확인.
            if (node.isTerminal()) {
                counting++;
            }
            for (int i = 0; i < node.getIndexChildren().size(); i++) {
                counting = find(key,node.getChildren()[node.getIndexChildren().get(i)],counting);
            }
            //여기에 있는 모든 노드들을 검색해봐야한다.
            /*for (int i = 0; i < node.getChildren().length; i++) {
                if (node.getChildren()[i] != null) {
                    *//*if (node.getChildren()[i].isTerminal()){
                        counting++;
                    }*//*
                    counting = find(key, node.getChildren()[i], counting);
                }
            }*/
        } else {
            int next = toNumber(key.charAt(0));
            if (node.getChildren()[next] == null) {
                return counting;
            }
            return find(key.substring(1), node.getChildren()[next], counting);
        }

        return counting;
        /*TrieNode node = head;
        // 입력한 key의 길이만큼 반복.
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            int index = toNumber(ch);
            //알파벳을 가진 하위 노드가 존재하지 않으면 false 리턴.
            if (node.getChildren()[index]==null){
                return false;
            }
            //하위 노드로 이동.
            else{
                node = node.getChildren()[index];
            }
        }
        return true;*/
    }

    private int toNumber(char ch) {
        return ch - 'a';
    }

    public TrieNode getHead() {
        return head;
    }
}
