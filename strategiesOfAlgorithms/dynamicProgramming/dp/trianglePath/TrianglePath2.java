package strategiesOfAlgorithms.dynamicProgramming.dp.trianglePath;

public class TrianglePath2 {
    //base case 는 항상 true 와 false 최소 두개는 있어야한다.
    // 혹은 true하나만이라도,
    private static int maxSum(int y,int x,int sum,int[][] tri){
        //base case - 이걸 한번에 확 구현을 못하고 두번에 걸쳐서 구현함.
        if (y==tri.length) return sum;
        //base case
        if (y<0||x<0||y>=tri.length||x>=tri[y].length){
            return 0;
        }
        return Math.max(maxSum(y+1,x,sum+tri[y][x],tri),maxSum(y+1,x+1,sum+tri[y][x],tri));
    }

    public static void main(String[] args) {
        int[][] triangle = {
                {6},
                {1,2},
                {3,7,4},
                {9,4,1,7},
                {2,7,5,9,4},
        };
        System.out.println(maxSum(0,0,0,triangle));
    }
}
