package strategiesOfAlgorithms.dataStructure.tree;

public class Tree {
    //traversal을 이용해 트리의 높이를 구함.
    //root를 루트로 하는 트리의 높이를 구하기.**
    //트리의 높이를 구하는 기본적인 구조.
    private int height(TreeNode root){
        int h = 0;
        for (int i = 0; i < root.children.size(); i++) {
            h = Math.max(h,1+height(root.children.get(i)));
        }
        // 여기서는 h로 해도 된다.
        // 왜냐하면 h에는 height가 하나의 subtree에서만 들어가기 때문이다.
        return h;
    }

    //트리를 순회하며 모든 노드에 포함된 값을 출력.
    //주어진 트리의 각 노드에 저장된 값을 모두 출력
    private static void printLabel(TreeNode root){
        //root에 저장된 값을 출력
        System.out.println(root.value);
        //각 자손들을 루트로 하는 서브트리에 포함된 값들을 재귀적으로 출력
        for (int i = 0; i < root.children.size(); i++) {
            printLabel(root.children.get(i));
        }
    }
}
