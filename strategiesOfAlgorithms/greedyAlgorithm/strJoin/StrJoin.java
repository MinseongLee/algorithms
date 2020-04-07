package strategiesOfAlgorithms.greedyAlgorithm.strJoin;

import java.util.PriorityQueue;

/**
 * 문자열 합치기. :
 * 이론적 배경 : 허프만 코드(Huffman code) 알고리즘.
 * : 가변 길이 인코딩(variable-length encoding) 테이블을
 * 만드는 방법으로 여러 압축 알고리즘에 사용된다.
 *
 * 우선순위 큐에 넣어 가장 작은 수 2개를 꺼내 더하는 것을 반복하였다.
 * 가장 작은 수를 먼저 더하면 항상 작은 값이 되므로,
 * 시간복잡도는 O(nlogn)이 된다.
 * while문에서 n-1만큼 반복하고, PriorityQueue에서 pop하는데,
 * logn 만큼 시간이 들어가므로, O(nlogn)이다.
 */
public class StrJoin {
    public static int strJoin(int n, int[] strLen) {
        if (n == 1) return n;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(strLen[i]);
        }
        int sum = 0;
        while (pq.size() > 1) {
            int two = pq.poll() + pq.poll();
            pq.add(two);
            sum += two;
        }
        return sum;
    }

    public static void main(String[] args) {
//        int n = 3;
//        int[] strLen = {2,2,4};
//        int n = 5;
//        int[] strLen = {3,1,3,4,1};
        int n = 8;
        int[] strLen = {1,1,1,1,1,1,1,2};
        System.out.println(strJoin(n,strLen));
    }
}
