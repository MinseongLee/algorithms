package strategiesOfAlgorithms.divideAndConquer.fence;

/**
 * 2/28/2020 - resolve it
 * 시간이 어디에서 오래걸렸나?
 * - 설계 , 정확한 설계가 오래걸렸다.
 * 구현도 오래걸림.
 */
public class Fence2 {
    //총 3분류, left-Max, right-Max, mid-Max
    private static int areaMax(int[] fence, int start, int end) {
        //base case
        if (start == end) return fence[start];
        int mid = (start + end) / 2;
        //left-Max, right-Max 인 경우
        int ans = Math.max(areaMax(fence, start, mid), areaMax(fence, mid + 1, end));
        //mid-Max일때 처리,
        //가운데 있는 경계값중 가장 큰값부터 처리,
        //10*1 ->7*2->6*3->4*4; 이런 식으로.

        //mid값은 항상 왼쪽에 있다.
        //홀수인경우, 짝수인 경우 left,right로 퍼져나가며 검색.
        int height = fence[mid];
        //mid값이 항상 중심이 되어서 계산해야한다.
        //mid에서 양 옆 중 가장 큰값부터 방문.
        //가장 작은값을 제일 나중에 방문 후 while문을 종료하면 된다.**
        int left = mid - 1;
        int right = mid + 1;
        //count가 아니라, right-left+1 이 개수로 구할 수 있다.
        //하지만 right-left+1 로 구할 땐, right,left 증감을 잘 선택해야만 한다.
        //참고 Fence class
        int count = 1;
        //left right 둘 중 더 큰값으로 한칸씩이동하면서 max값 구하기.
        while (left >= start || right <= end) {
//            System.out.println(left+" "+right+" "+start+" e="+end);
            if (right <= end && (left < start || fence[left] < fence[right])) {
//                System.out.println(" r="+fence[right]);
                count++;
                height = Math.min(height, fence[right]);
                ans = Math.max(ans, count * height);
                right++;
            }
            else if (left >= start && (right > end || fence[left] >= fence[right])) {
//                System.out.println(" l="+fence[left]);
                //즉, 왼쪽이 더 크거나 같으면, 왼쪽으로
                count++;
                height = Math.min(height, fence[left]);
                ans = Math.max(ans, count * height);
                left--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 7;
//        int[] board = {1 ,4, 4, 4, 4, 1, 1};
        int[] board = {7, 1, 5, 9, 6, 7, 3};
        System.out.println(areaMax(board, 0, n - 1));
    }
}
