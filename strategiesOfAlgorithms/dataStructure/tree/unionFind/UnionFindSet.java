package strategiesOfAlgorithms.dataStructure.tree.unionFind;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UnionFindSet {
    private int[] parent;
    private int[] rank;
    // 각 트리의 size
    // 트리가 합쳐질때마다 갱신. root에만 그 트리의 size를 저장
    // 즉, root =v 라면, size[v] += size[u], size[u]=0;
    private int[] size;
    private Set<Integer> otherGroup;
    //ACk 인 모든 경우를 +해주고, 둘다 ACK에 없는 경우에 비방하는 경우
    //처음 그룹에 포함되는 경우에는 처음 그룹에 포함되어있다고 표시를해줘야함.
    private Set<Integer> firstGroup;
    public UnionFindSet(int n){
        parent = new int[n];
        rank = new int[n];
        size=  new int[n];
        Arrays.fill(rank,1);
        Arrays.fill(size,1);
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        otherGroup = new HashSet<>();
        firstGroup = new HashSet<>();
    }
    public int otherGroupSize(){
        return otherGroup.size();
    }
    // find는 root를 찾아줘야한다.
    public int find(int v){
        // root
        if (v==parent[v]) return v;
        // v의 모든 parent들이 root를 가르키게 하면
        // root를 O(1)로 접근가능하다.
        return parent[v] = find(parent[v]);
    }
    //ACK 라고 가정. (이것부터 처리)
    // - 나중에 DIS는 ACk에 없는 것들로 채울 것.
    // a,b가 같은 그룹에 속해있는지 확인해주는 연산.
    // otherGroup에 v or u가 저장되어져있는지 확인.
    //DIS를 처리해주는 로직.
    public boolean isTheSameGroup(int v,int u){
        //u가 비방당하는 대상. -v,u둘다 아무 상태가 아닐때,
        //즉, 비방당하는 대상이 첫번째 그룹에 속해있지 않으면,
        //다른 그룹에 속해있는 그룹.
        v = find(v);
        u = find(u);
        // 총 4가지 상태 존재.
        if (size[v]>1&&size[u]>1){
            //같은 그룹에 속한 사람이 비방하는 것이므로,
            //즉, 값이 존재 x
            return true;
        }
        //v,u 모두 첫번째 그룹에 속하지 않은 상태
        else if (size[v]==1&&size[u]==1){
            //두번째 그룹에 v는 속하고 u는 안속한 경우.
            if (otherGroup.contains(v)&&!otherGroup.contains(u)){
                firstGroup.add(u);
            }
            //두번째 그룹에 둘다 속한 경우.
            else if (otherGroup.contains(v)&&otherGroup.contains(u)){
                return true;
            }
            //첫번째 그룹에 둘다 속한 경우
            else if (firstGroup.contains(v)&&firstGroup.contains(u)){
                return true;
            }
            //default 맨 첫번째 경우를 제외하고는 v를 첫번째그룹에 넣고,
            //u를 두번째 그룹에 넣는다.
            // u가 글쓴사람이므로, 비방당하는 사람을 다른 그룹에 넣는다.
            else{
                firstGroup.add(v);
                // set이기 때문에 그냥 넣으면 된다.
                otherGroup.add(u);
            }
        }
        //size[u]가 1보다 크면 무조건 v는 otherGroup인 사람이다.
        else if (size[u]>1){
            otherGroup.add(v);
        }
        //size[v]가 1보다 크면 무조건 u는 otherGroup인 사람이다.
        else if (size[v]>1){
            otherGroup.add(u);
        }
        //여기까지 내려왔다면 답 존재.
        return false;
    }

    // merge는 두 개를 합쳐주는 연산.
    public boolean merge(int v,int u){
        v = find(v);
        u = find(u);
        //같은 부모.
        if (v==u) return false;
        //작은거에 큰거 넣기.
        if (rank[v]<rank[u]){
            parent[v] = u;
            size[u] += size[v];
            size[v] = 0;
        }else{
            parent[u] = v;
            size[v] += size[u];
            size[u] = 0;
        }
        if (rank[v]==rank[u]) ++rank[v];
        //merge 성공.
        return true;
    }
}