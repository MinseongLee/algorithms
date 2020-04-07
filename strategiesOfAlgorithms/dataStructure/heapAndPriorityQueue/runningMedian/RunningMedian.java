package strategiesOfAlgorithms.dataStructure.heapAndPriorityQueue.runningMedian;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 */

public class RunningMedian {
    public static void main(String[] args) {
        /*int n = 10;
        int a = 1;
        int b = 1;*/
        int n = 10000;
        int a = 1273;
        int b = 4936;
        long[] A = new long[n];
        A[0] = 1983;
        for (int i = 1; i < A.length; i++) {
            A[i] = (A[i-1]*a+b)%20090711;
        }
        System.out.println("medianSum="+medianSum(A));
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i]+" ");
        }
        System.out.println();
    }
    /**
     * 수열의 중간값 - 가운데
     * 수열 길이가 짝수일 때, 가운데 있는 두 값보다 작은 값이 중간 값.
     * 텅빈 수열에서 시작해서 각 수가 추가 될때마다 중간값을 계산하는 프로그램.
     * 중간값의 합을 리턴.
     *
     * PriorityQueue - minheap 으로 가운데 있는 값을 쭉 더해 나가기.
     * odd - n/2, even - (n-1)/2;
     * 내 로직은 문제가 있다. pQ에 바로 접근할 수가 없다. 중간값에.
     *
     * sequence 를 반절로 나눈다면,
     * 그러므로, minheap과 maxheap으로 나누어서 접근을 해줘야한다.
     * 그러면 maxheap에는 항상 중간값이 들어온다.
     */
    public static long medianSum(long[] A){
        PriorityQueue<Long> minheap = new PriorityQueue<>();
        PriorityQueue<Long> maxheap = new PriorityQueue<>(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return (int) (o2-o1);
            }
        });
        long ret = 0;

        //반복문 불변식
        //1. maxHeap의 크기는 minHeap의 크기와 같거나 1 더 크다.
        //2. maxHeap.top() <= minHeap.top()
        for (int i = 0; i < A.length; i++) {
            // 이렇게 번갈아가며 넣는다면, maxheap에 항상 중간값이 들어갈 수 있다.
            //maxheap | minheap 이렇게 짜르면, maxheap에는 항상 중간값이 들어간다.
            //1번 불변식부터
            if (maxheap.size()==minheap.size()){
                maxheap.add(A[i]);
            }else{
                minheap.add(A[i]);
            }
            //2번 불변식이 깨졌을 경우 복구
            if (!minheap.isEmpty() && !maxheap.isEmpty() &&
            minheap.peek() < maxheap.peek()){
                long a = maxheap.peek(), b= minheap.peek();
                maxheap.poll();minheap.poll();
                maxheap.add(b);
                minheap.add(a);
            }
            ret = (ret+maxheap.peek()) % 20090711;
        }
        return ret;
    }
}
