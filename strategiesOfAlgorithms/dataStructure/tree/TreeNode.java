package strategiesOfAlgorithms.dataStructure.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    int value;
    TreeNode parent;
    TreeNode left;
    TreeNode right;
    //children을 이렇게표현 or 이진트리라면,
    List<TreeNode> children = new ArrayList<>();
}
