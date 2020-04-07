package strategiesOfAlgorithms.dynamicProgramming.dp.joinedLIS;

import java.util.Arrays;

public class Jlis5 {
    private static int[][] cache;
    public static int sol(int[] A,int[] B){
        cache = new int[A.length+1][B.length+1];
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i],-1);
        }
        //-1부터 시작해 모든 정점을 다 돌아본다.
        return jlis(A,B,-1,-1)-2;
    }

    private static int jlis(int[] A, int[] B, int indexA, int indexB) {
        int i =indexA+1,j=indexB+1;
        if (cache[i][j]>-1) return cache[i][j];
        int a = indexA==-1?Integer.MIN_VALUE:A[indexA];
        int b = indexB==-1?Integer.MIN_VALUE:B[indexB];
        // indexA가 -일때, 최소값이 나오므로, max값을 택해줘야 제대로된 값이 나온다.
        // 하지만 만약 처음 시작값이 -1이 아니라면?
        // - min값을 줘야 제대로 된 값이 나올 수 있다.
        int maxElement = Math.max(a,b);
//        System.out.println(indexA+" in="+indexB+" max="+maxElement);
//        if (indexA!=-1&&indexB!=-1)
//            System.out.println(indexA+" A="+A[indexA]+" in="+indexB+" B="+B[indexB]+" max="+maxElement);
        cache[i][j] = 2;
        for (int nextA = indexA+1; nextA < A.length; nextA++) {
            if (maxElement<A[nextA]){
                cache[i][j] = Math.max(cache[i][j],jlis(A,B,nextA,indexB)+1);
            }
        }
        for (int nextB = indexB+1; nextB < B.length; nextB++) {
            if (maxElement<B[nextB]){
                cache[i][j] = Math.max(cache[i][j],jlis(A,B,indexA,nextB)+1);
            }
        }
        return cache[i][j];
    }

    public static void main(String[] args) {
        int[] A = {10,15,30,1,2,3};
        int[] B = {10,20,30};
        System.out.println(sol(A,B));
    }
}
