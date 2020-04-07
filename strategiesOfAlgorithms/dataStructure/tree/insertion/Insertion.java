package strategiesOfAlgorithms.dataStructure.tree.insertion;

import java.util.LinkedList;
import java.util.List;

/**
 * LinkedList 를 활용해서 O(n)에 해결하였다.
 */

public class Insertion {
    public static void main(String[] args) {
//        int n = 5;
//        int[] order = {0,1,1,2,3};
        int n = 4;
        int[] order = {0 ,1 ,2 ,3};
        int[] list = beforeList(order);
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i]+" ");
        }
        System.out.println();
    }
    /**
     * LinkedList 를 활용해서 풀면 O(n)에 insert, delete를 O(1)에 할 수 있다.
     * 1<=n<=50,000
     * order list 안에 있는 숫자만큼 left!
     */
    public static int[] beforeList(int[] order){
        List<Integer> linkedList = new LinkedList<>();
        int[] ans = new int[order.length];
        for (int i = 1; i <= order.length; i++) {
            linkedList.add(i);
        }
        for (int i = 0; i < order.length; i++) {
            //order[i] 값 만큼 왼쪽으로 이동.
            int move = i-order[i]; //move는 index
            //add는 무조건 left쪽이므로, remove하는 장소는 무조건 뒤다.
            linkedList.add(move,i+1);
            linkedList.remove(i+1);
        }
        for (int i = 0; i < order.length; i++) {
            ans[linkedList.get(i)-1] = i+1;
        }
        return ans;
    }


}
