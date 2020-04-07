package strategiesOfAlgorithms.dynamicProgramming.dpTechnic.morse;

/**
 * k번째 답 계산하기.
 * generate3() 을 제대로 익힐것.
 * 그 후 kth method 로직을 익히기.
 */
public class MorseCalcKth {
    //k의 최대값+100 overflow를 막기 위해 이보다 큰 값은 구하지 않는다.
    private static final int M = 1000000000+100;
    private static int[][] bino = new int[201][201];
    private static int skip;
    //필요한 모든 이항계수를 미리 계산해둔다.
    private static void calcBino(){
        for (int i = 0; i <=200; i++) {
            bino[i][0] = bino[i][i]=1;
            for (int j = 1; j < i; j++) {
                bino[i][j] = Math.min(M,bino[i-1][j-1]+bino[i-1][j]);
            }
        }
    }

    //skip개를 건너뛰고 출력
    private static void generate3(int n,int m,String s){
        //base case skip <0
        if (skip<0) return;
        //base case n=m=0
        if (n==0&&m==0){
            if (skip==0) System.out.println("code3="+s);
            skip--;
            return;
        }
        //pruning
        //만약 부분집합의 경우수 값이 skip(k)보다 작으면 가지를 쳐낼 것.
        if (bino[n+m][n]<=skip){
            skip-=bino[n+m][n];
            return;
        }
        if (n>0) generate3(n-1,m,s+"-");
        if (m>0) generate3(n,m-1,s+"o");
    }

    //pruning을 해주지 않아 시간내에 해결 불가능하다.
    //k번째 신호만 출력하기
    private static void generate2(int n,int m,String s){
        if (skip<0) return;
        if (n==0&&m==0){
//            System.out.println(s);
            if (skip==0) System.out.println("code2="+s);
            skip--;
            return;
        }
        if (n>0) generate2(n-1,m,s+"-");
        if (m>0) generate2(n,m-1,s+"o");
    }
    //모든 신호 만들기.
    private static void generate(int n,int m,String s){
        if (n==0&&m==0){
            System.out.println("code1="+s);
            return;
        }
        if (n>0) generate(n-1,m,s+"-");
        if (m>0) generate(n,m-1,s+"o");
    }

    // n개의 -,m개의 o로 구성된 신호 중 skip개를 건너뛰고
    // 만들어지는 신호를 반환
    private static String kth(int n,int m,int skip){
        // n==0인 경우 나머지 부분은 전부 o일 수밖에 없다.
        if (n==0) return makeM(m);
//        System.out.println(m+" bino[n+m-1][n-1]="+bino[n+m-1][n-1]+" skip="+skip);
        if (skip<bino[n+m-1][n-1]){
            return "-"+kth(n-1,m,skip);
        }
        return "o"+kth(n,m-1,skip-bino[n+m-1][n-1]);
    }
    private static String makeM(int m) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            sb.append('o');
        }
        return sb.toString();
    }

    public static String sol(int n,int m,int k){
        skip = k-1;
        calcBino();
        generate(n,m,"");
        generate2(n,m,"");
        skip = k-1;
        generate3(n,m,"");
        return kth(n,m,k-1);
    }

    public static void main(String[] args) {
        int n = 2;
        int m = 2;
        int k = 4;
        System.out.println(sol(n,m,k));
    }
}
