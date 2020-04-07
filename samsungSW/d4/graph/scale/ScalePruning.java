package samsungSW.d4.graph.scale;

/**
 * 3234. 준환이의 양팔저울
 *
 * 문제를 풀긴풀었는데, 약간의 시간초과가 뜬다.. 음..
 * 어떻게 가지치기를 할지.. 고민 해보고 다시 풀어보자.
 * -문제는 정확하게 풀었다. 하지만.. 가지치기가 부족해 시간초과가뜬다.
 * 나중에 다시 풀어보자.
 * 대회 공식 풀이를 보니 휴리스틱을 추가한 재귀로 설명하네요
 * -휴리스틱을 공부한 후 다시 풀어보자.**
 *
 * 해결했다. 가지치기 참고 해서 해결.
 * 가지치기.. 진짜 중요.
 */
public class ScalePruning {
    private boolean[][] visited;
    private int N;
    private int best;
    private int count;
    private int allSum;
//    private static final int[] fact = {0,1,2,6,24,120,720,5040,40320,362880};
//    private static final int[] pow = {1,2,4,8,16,32,64,128,256,512};

    //무게가 0인경우 존재하지 않음.
    public int sol(int n,int[] scale){
        visited = new boolean[2][n];
        for (int i = 0; i < scale.length; i++) {
            allSum += scale[i];
        }
        N = n;
        dfs(0,scale,0,0);
        return best;
    }
    //visited을 매개변수로 주니 조금 더 빨라지긴 했다..
    //와.. 이게 진짜 heuristic..
    // 왜냐하면 left에 있는 경우는 무조건 더 커.
    private void dfs(int steps, int[] scale, int left, int right) {
        //pruning
        if (left<right) {
            count++;
//            System.out.println("left="+left+" right="+right);
            return;
        }
        //base case
        if (N==steps){
            //왼쪽총합>=오른쪽총합. - 경우의 수 구하기.
            //무조건 left가 right보다 크거나 같은거야.
            best++;
            return;
        }
        //이 코드를 적용하니, 가지치기가 되었다.
        //근데 왜? 그리고 이건 참고해서 넣은거다.
        //나머지 코드는 다 내가 짬.
        if (left>=allSum-left){// 이게 바로 가지치기..
            //즉, left가 모든 합에서 left를 빼었을때도 left가 더 크다면,
            //더이상 탐색할 이유가 없다.
            //이렇다면, 더 right를 구할 이유가 없다.
            //그러므로, 지금까지 진행된 steps를 전체 크기 N에서 빼준값을
            //best에 더해준다. 2^(n-steps)*n!을.
//            best += pow[N-steps]*fact[N-steps];
            best += (int)Math.pow(2,N-steps)*factorial(N-steps);
            return;
        }
        //i=0 left, i=1 right,
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < scale.length; j++) {
                if (!visited[i][j]){
                    visited[i][j] = true;
                    // 둘다 true로 놓아줘야 중복되는 경우가 존재하지 않는다.
                    if (i==0) {
                        visited[i+1][j]=true;
                    }
                    else visited[i-1][j] = true;
                    //i==1이면, 그냥 left, i==0이면, left에 +scale[j]
                    //left 합을 계속 넘겨준다 pruning을 위해.
                    //그냥 +를 해야지 left+=scale[j]로 해주면 안된다.
                    // left에다가 그냥 집어넣으면 그 값자체가 left에 저장이 되므로, 안된다.
                    dfs(steps+1,scale,i==1?left:(left+scale[j]),i==0?right:(right+scale[j]));
                    visited[i][j] = false;
                    if (i==0) visited[i+1][j]=false;
                    else visited[i-1][j] = false;
                }
            }
        }
    }

    private int factorial(int n) {
        if (n<=1) return 1;
        return n*factorial(n-1);
    }

    public static void main(String[] args) {
        int a = (int) Math.pow(2,9);
        int sum= 1;
        for (int i = 9; i >=2 ; i--) {
            sum *=i;
        }
        //1억 8천.. 가지치기 좀 하면 가능할듯..
        //185,794,560
//        System.out.println(a*sum);
        int n = 9;
//        int[] scal = {1,2,3,4,5,6,7,8,9};
        int[] scal = {9,8,7,6,5,4,3,2,1};
        /*int n = 3;
        int[] scal = {1,2,4};*/
        long start = System.currentTimeMillis();
        ScalePruning scalePruning = new ScalePruning();
        System.out.println(scalePruning.sol(n,scal));
        System.out.println("count="+ scalePruning.count);
        long end = System.currentTimeMillis();
        System.out.println("time="+(end-start));

    }
}
