package strategiesOfAlgorithms.dataStructure.tree.traversal;

public class Traversal3 {

    public static void postorder(int[] preorder,int[] inorder){
        //base case
        if (preorder.length==0){
            return;
        }
        int root = preorder[0];
        int left = leftSubtree(inorder,root);
        //left+root+the first of right subtree.
        int right = left+1+1;
        //left
        postorder(slice(preorder,1,left+1),slice(inorder,0,left));
        //right
        postorder(slice(preorder,right,preorder.length-1),slice(inorder,right,inorder.length-1));
        //root
        System.out.print(root+" ");
    }

    private static int[] slice(int[] order, int start, int end) {
        int size = end-start+1;
        int[] subTree = new int[size];
        for (int i = start,j=0; i <= end; i++,j++) {
            subTree[j] = order[i];
        }
        return subTree;
    }

    private static int leftSubtree(int[] inorder, int root) {
        for (int i = 0; i < inorder.length; i++) {
            if (root==inorder[i]){
                //i는 root 그렇다면 i-1은 left
                //inorder - left -> root -> right
                return i-1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] preorder = {27, 16, 9, 12, 54, 36, 72};
        int[] inorder = {9, 12, 16, 27, 36, 54, 72};
        postorder(preorder,inorder);
        System.out.println();
    }
}
