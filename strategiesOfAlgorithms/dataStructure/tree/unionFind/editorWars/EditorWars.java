package strategiesOfAlgorithms.dataStructure.tree.unionFind.editorWars;

import strategiesOfAlgorithms.dataStructure.tree.unionFind.UnionFindSet;

public class EditorWars {
    private static final String ACK = "ACK";
    public static String sol(int n,int m,String[] comments){
        UnionFindSet unionFindSet = new UnionFindSet(n);
        int[][] dis = new int[m][3];
        int size = 0;
        //처음에 ACK에 있는 값들을 모두 저장.
        for (int i = 0; i < m; i++) {
            String[] divComment = comments[i].split(" ");
            //ACK 인 경우부터 다 처리.
            //다 merge를 해주면 된다.
            if (divComment[0].equals(ACK)){
                int v = Integer.parseInt(divComment[1]);
                int u = Integer.parseInt(divComment[2]);
                unionFindSet.merge(v,u);
            }
            else{
                dis[size][0] = Integer.parseInt(divComment[1]);
                dis[size][1] = Integer.parseInt(divComment[2]);
                //모순이 처음 발생하는 값의 번호.
                dis[size][2] = i+1;
                size++;
            }
        }
        //이제 DIS인 경우를 처리.
        for (int i = 0; i < size; i++) {
            //true이면, 값이 존재하지 않는다.
            if (unionFindSet.isTheSameGroup(dis[i][0],dis[i][1])){
                return "CONTRADICTION AT "+dis[i][2];
            }
        }
        //-1이 없으면, 값 존재.
        int number =n-unionFindSet.otherGroupSize();
        //둘 중 더 큰값을 리턴.
        return "MAX PARTY SIZE IS "+Math.max(number,unionFindSet.otherGroupSize());
    }

    public static void main(String[] args) {
//        int n = 4;
//        int m = 4;
//        String[] comments = {"ACK 0 1","ACK 1 2","DIS 1 3","ACK 2 0"};
        int n = 8;
        int m = 6;
//        String[] comments = {"ACK 0 1","ACK 1 2","DIS 2 0"};
        String[] comments = {"DIS 0 1","ACK 1 2","ACK 1 4","DIS 4 3","DIS 5 6","ACK 5 7"};
        System.out.println(sol(n,m,comments));
    }
}
