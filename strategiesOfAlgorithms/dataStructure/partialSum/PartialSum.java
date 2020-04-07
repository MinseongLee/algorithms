package strategiesOfAlgorithms.dataStructure.partialSum;

/**
 * 구간합 기본 로직을 여기서 표현.
 */
public class PartialSum {
    private static final int INF = 987654321;
    // 1차원 배열 - 구간합,
    // 2차원 배열 구간합(직사각형 모양에 있는 숫자들의 합)

    //1차원 배열 모든 원소의 합 표현
    public static int[] allSum(int[] sequence){
        int n = sequence.length;
        int[] divSum = new int[n];
        divSum[0] = sequence[0];
        for (int i = 1; i < n; i++) {
            divSum[i] = divSum[i-1]+sequence[i];
        }
        return divSum;
    }
    //1차원 배열의 start-end까지의 합 리턴
    public static int rangeSum(int[] divSum,int start,int end){
        if (start==0){
            return divSum[end];
        }
        //start 전 원소의 합을 빼줘야 start~end까지의 합을 구할 수 있다.
        else{
            return divSum[end]-divSum[start-1];
        }
    }
    //2차원 배열의 직사각형 합
    public static int[][] allSum2(int[][] sequence){
        int n = sequence.length;
        int m = sequence[0].length;
        int[][] divSum = new int[n][m];
        divSum[0][0] = sequence[0][0];
        for (int i = 1; i < m; i++) {
            divSum[0][i] = divSum[0][i-1]+sequence[0][i];
        }
        //exception처리가 없는 깔끔한 코드를 위해 경계값을 위에서 처리하였다.
        for (int i = 1; i < n; i++) {
            divSum[i][0] = divSum[i-1][0]+sequence[i][0];
            for (int j = 1; j < m; j++) {
                //양옆을 더해주고 중복되는 부분은 하나 빼준다.
                divSum[i][j] = divSum[i-1][j]+divSum[i][j-1]+sequence[i][j]-divSum[i-1][j-1];
            }
        }
        return divSum;
    }
    public static int gridSumInBook(int[][] divSum,int x1,int y1,int x2,int y2){
        int ret = divSum[x2][y2];
        if (y1>0) ret-= divSum[x2][y1-1];
        if (x1>0) ret-= divSum[x1-1][y2];
        if (x1>0&&y1>0) ret+= divSum[x1-1][y1-1];
        return ret;
    }
    public static int gridSum(int[][] divSum,int x1,int y1,int x2,int y2){
        //x1=0,y1=0,x2=0,y2=0;
        if (x1==0&&y1==0){
            return divSum[x2][y2];
        }
        //x1이 0일때, x2=0인 경우까지 처리해줄 수 있다.
        //x1=0,x2=0
        else if (x1==0){ //x1이 0이란 의미는 y1은 0이 아니란 의미이다.
            return divSum[x2][y2]-divSum[x2][y1-1];
        }
        //y1=0,y2=0
        else if (y1==0){ //y1==0이란 말은 x1은 0이 될 수 없다.
            return divSum[x2][y2]-divSum[x1-1][y2];
        }
        //일반적인 상황에서는
        else{ //x1,y1 둘다 0이 아니란 의미.
            return divSum[x2][y2]-divSum[x1-1][y2]
                    -divSum[x2][y1-1]+divSum[x1-1][y1-1];
        }
    }

    public static void main(String[] args) {
        //2차원배열 부분합
        int[][] sequence = new int[4][4];
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                sequence[i-1][j-1] = j*i;
            }
        }
        int[][] divSum = allSum2(sequence);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(" "+sequence[i][j]);
            }
            System.out.println();
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(" "+divSum[i][j]);
            }
            System.out.println();
        }
        System.out.println(gridSum(divSum,1,0,2,2));
        System.out.println(gridSumInBook(divSum,1,0,2,2));
        //1차원 배열 부분합.
        int[] sequence2 = new int[10];
        for (int i = 1; i <= 10; i++) {
            sequence2[i-1] = i;
        }
        int[] divSum2 = allSum(sequence2);
        for (int i = 0; i < sequence2.length; i++) {
            System.out.print(sequence2[i]+" ");
        }
        System.out.println();
        for (int i = 0; i < divSum2.length; i++) {
            System.out.print(divSum2[i]+" ");
        }
        System.out.println();
        System.out.println(rangeSum(divSum2,6,9));
        // 합이 0에 가장 가까운 구간 구하기.
//        int[] A = {-14,7,2,3,-8,4,-6,8,9,11};
        int[] A = new int[100000];
        for (int i = 0; i < A.length; i++) {
            A[i] = i+1;
        }
        int[] divSum3 = allSum(A);
        int ans = INF;
        int aLen = A.length;
//        int[][] ansList = new int[aLen][aLen];
        /*for (int i = 0; i < aLen; i++) {
            Arrays.fill(ansList[i],INF);
        }*/
        long start = System.currentTimeMillis();
        for (int i = 0; i < aLen; i++) {
            for (int j = i+1; j < aLen; j++) {
//                ansList[i][j] = rangeSum(divSum3,i,j);
                ans = Math.min(ans,rangeSum(divSum3,i,j));
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("time="+(end-start));
        int min = INF,x=0,y=0,ret=0;
        /*for (int i = 0; i < aLen; i++) {
            for (int j = 0; j < aLen; j++) {
                int abs = Math.abs(ansList[i][j]);
                if (min>abs){
                    //-가 0에 가까운 가장 작은 값일 수도 있으므로,
                    //-인경우는 ret에 저장을 해두고, 비교값에는 abs값을 저장해서,
                    //정확한 값을 리턴하도록한다.
                    ret = ansList[i][j];
                    min = abs;
                    x = i;
                    y = j;
                }
            }
        }*/
        System.out.println("x="+x+" y="+y+" min="+ret);

    }
}
