package samsungSW.d5.playingDic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 3135. 홍준이의 사전놀이
 * <p>
 * 영어사전
 * 특정 문자열로 시작하는 단어의 총 개수
 * 단어가 insert 함수의 인자로 주어질 때에,
 * query 함수를 통해 스스로 던진 질문에 답을 하는 프로그램
 * 1<=n<=100,000
 * 1<=s.len<=10
 * p = 1 or 2
 * <p>
 * 로직은 아주 굿.
 * binary search 로만들었는데, 시간초과가 난다..
 * 45개 통과하고,,
 */

/**
 * 정적 trie로 만들어야 시간내에 풀 수 있다.
 */
public class PlayingDictionary {
    private Trie trie;

    public void init() {
        trie = new Trie();
    }

    public void insert(int buffer_size, String buf) {
        trie.insert(buf, trie.getHead());
    }

    public int query(int buffer_size, String buf) {
        int counting = 0;
        int ans = trie.find(buf, trie.getHead(), counting);
        System.out.println("ans=" + ans);
        return ans;
    }

    public static void main(String[] args) {
        int n = 9;
        int[] pList = {1, 1, 1, 2, 1, 1, 1, 2, 2};
        String[] sList = {"ab", "a", "amrhqubf", "a", "ibs", "ibsx", "i", "i", "ib"};
        PlayingDictionary pd = new PlayingDictionary();
        pd.init();
        for (int i = 0; i < n; i++) {
            int p = pList[i];
            String s = sList[i];
            if (p == 1) {
                pd.insert(0, s);
            } else {
                pd.query(0, s);
            }
        }
        StaticTrie st = new StaticTrie();
        st.init();
        for (int i = 0; i < n; i++) {
            int p = pList[i];
            String s = sList[i];
            if (p == 1) {
                st.insert(s.length(), s);
            } else {
                st.query(s.length(), s);
            }
        }
        //binary search tree로 풀었다.
        List<String> dic = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int p = pList[i];
            String s = sList[i];
            if (p == 1) {
                //dic.add(s);
                insert(dic, s);
            } else {
                int counting = query(dic, 0, dic.size() - 1, s);
                ans.add(counting);
            }
        }

        //답 출력.
        int test_case = 0;
        System.out.print("#");
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(" " + ans.get(i));
        }
        System.out.println();
    }

    // binary search
    // start, end는 둘다 index
    private static int query(List<String> dic, int start, int end, String target) {
        int mid = (start + end) / 2;
        //base case
        if (start > end || end >= dic.size()) return 0;
        if (target.length() <= dic.get(mid).length()) {
            if (target.equals(dic.get(mid).substring(0, target.length()))) {
                // target과 중복 가능하므로,
                // target에 도착시 좌우로 -1,+1를 해가며 같은 단어가 있는지 체크.
                return theSameWord(target, dic, mid);
            }
        }
        //무조건 같지 않은 경우.
        if (checkRange(target, dic.get(mid))) {
            //true라면, target이 더 작거나 같은 경우.
            return query(dic, start, mid - 1, target);
        }
        //false라면, target이 더 큰 경우.
        else {
            return query(dic, mid + 1, end, target);
        }
    }

    //문자열이 작은 경우, 큰 경우를 나누어서 경계 설정.
    private static boolean checkRange(String target, String s) {
        int len = Math.min(target.length(), s.length());
        for (int i = 0; i < len; i++) {
            if (target.charAt(i) < s.charAt(i)) {
                return true;
            } else if (target.charAt(i) > s.charAt(i)) {
                return false;
            }
        }
        // 만약 s.len이 더 작아서 같은 경우라면,
        // 무조건 앞쪽을 봐야하므로, false
        return false;
    }

    //같은 target을 가진 단어가 여러 개일 수있으므로, left와 right를 확인.
    private static int theSameWord(String target, List<String> dic, int mid) {
        int size = dic.size();
        int counting = 1;
        int targetLen = target.length();
        for (int left = mid - 1; left >= 0; left--) {
            if (targetLen <= dic.get(left).length()) {
                if (target.equals(dic.get(left).substring(0, targetLen))) {
                    counting++;
                } else break;
            }

        }
        for (int right = mid + 1; right < size; right++) {
            if (targetLen <= dic.get(right).length()) {
                if (target.equals(dic.get(right).substring(0, targetLen))) {
                    counting++;
                } else break;
            }
        }
        return counting;
    }

    private static void insert(List<String> dic, String s) {
        dic.add(s);
        Collections.sort(dic);
        //정렬을 이진탐색으로 해준다면? not good I think, cus its arrayList
//        dicSort(dic, s);

    }

    /**
     * 내가 알고있는 정보를 이용하면 된다. 알파벳은 26개라는 것을.
     * 각 단어 위치 확인 후 first, mid, end,로 검색하면 시간을 n/3
     * 줄일 수 있다.
     * 해보려고했는데.. 흠.. 힘들다.
     * 왜냐면 ArrayList<>() 에 삽입하는 시간이 크다면 O(n)이 걸리고,
     * 검색시간, 단어검색 O(mn)이 걸린다. 너무커..
     * 다음에 rethink
     */
    /*private static void dicSort(List<String> dic, String s) {
        if (dic.isEmpty()) dic.add(s);
        int size = dic.size();
        // 8,8,8 - 절대위치
        // 즉, 0~8,9~16,17~25
        // first/mid/end 로 상대위치로 나누어 계산
        int divThree = s.charAt(0) - 'a';
        //first
        if (0 <= divThree && divThree <= 8) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < s.length(); j++) {
                    if (s.charAt(j) < dic.get(i).charAt(j)) {
                    }
                    //같다면,
                    else{
                    }
                }
            }
        }
        //mid
        else if (9 <= divThree && divThree <= 16) {
        }
        //end
        else {
        }
    }*/
}
