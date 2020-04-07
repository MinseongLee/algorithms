package strategiesOfAlgorithms.dataStructure.tree.fortress;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fortress3 {
    private static int best;

    public static int sol(int n,int[][] castle){
        //0번을 root로 하는 tree를 생성.
        FortNode root = getTree(0,castle);
        int h = height(root);
        return Math.max(h,best);
    }
    //이것이 tree의 height를 구하는 기본적 구조.
    private static int h(FortNode root){
        int h =0;
        for (int i = 0; i < root.children.size(); i++) {
            h = Math.max(h,h(root.children.get(i))+1);
        }
        return h;
    }
    private static int height(FortNode root) {
        //subtree의 높이들.
        List<Integer> heights = new ArrayList<>();
        for (int i = 0; i < root.children.size(); i++) {
            heights.add(height(root.children.get(i)));
        }
        if (heights.size()==0) return 0;
        Collections.sort(heights,Collections.reverseOrder());
        if (heights.size()>=2){
            //높이가 가장 큰 2개 를 넣어주기.
            best = Math.max(best,2+heights.get(0)+heights.get(1));
        }
        //한 서브트리의 높이를 반환.
        return heights.get(0)+1;
    }

    private static FortNode getTree(int root, int[][] castle) {
        FortNode node = new FortNode();
        for (int child = 0; child < castle.length; child++) {
            if (isChild(root,child,castle)){
                //전형적인 tree 구조.
                node.children.add(getTree(child,castle));
            }
        }
        return node;
    }

    private static boolean isChild(int parent, int child,int[][] castle) {
        //만약 parent보다 child가 더 크면 false
        if (!enclosed(parent,child,castle)){
            return false;
        }
        //만약 parent에 i가 포함되고, 그 i가 child를 포함하고 있으면,
        //자손이므로 false
        for (int i = 0; i < castle.length; i++) {
            if (i!=parent&&i!=child&&
            enclosed(parent,i,castle)&&enclosed(i,child,castle)){
                return false;
            }
        }
        return true;
    }

    private static boolean enclosed(int parent, int child, int[][] castle) {
        return castle[parent][2]>castle[child][2]&&
                sqr(castle[parent][2])>sqr(castle[child][2])+sqrDist(castle[parent][0],castle[parent][1],castle[child][0],castle[child][1]);
    }

    private static int sqrDist(int x1, int y1, int x2, int y2) {
        return sqr(x2-x1)+sqr(y2-y1);
    }

    private static int sqr(int x) {
        return x*x;
    }

    public static void main(String[] args) {
        int n = 8;
        int[][] castle ={
                {21 ,15, 20},
                {15, 15 ,10},
                {13, 12, 5},
                {12 ,12 ,3},
                {19 ,19, 2},
                {30 ,24, 5},
                {32 ,10, 7},
                {32, 9 ,4}
        };
        System.out.println(sol(n,castle));
    }
}
