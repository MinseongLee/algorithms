package strategiesOfAlgorithms.dynamicProgramming.dp.asymTiling;

/**
 * 259p
 */
public class AsymTiling {
    public static void main(String[] args) {
        int n = 15;
        System.out.println(tiling(n));

        System.out.println(asymmetric(n));
    }
    //이 문제를 통해 기존에 만든,
    // (혹은 방금 만들었더라도 완벽하게 작동하는 완전탐색 로직을) 가지고
    // 그 로직에 width를 보내 경우의 수를 계산하고 비즈니스로직은 따로
    // asymmetric() 메서드에 만들어 처리한다.**********
    // 굉장히 중요한 패턴.
    public static int asymmetric(int width){
        //*2를 해선 안된다.
        //왜냐하면 대칭이기때문에, 한쪽만 count 해야 중복 없이 셀수 있다.
        //261p
        if (width %2==1){
            return tiling(width) - tiling((width-1)/2);
        }
        return tiling(width)-tiling((width-2)/2)-tiling(width/2);
    }
    private static int tiling(int n) {
        if (n==0) return 1;
        else if (n<=0) return 0;
        return tiling(n-1)+tiling(n-2);
    }

    /**
     * 좌우 대칭 x
     * 즉, 전체 경우의수 - 좌우 대칭 인경우
     * tiling(n) = tiling(n-1)+tiling(n-2) - 좌우 대칭 경우 수
     *
     * 1 n-2 1
     * 2 n-4 2
     * 이렇게 두 경우 존재 좌우 대칭
     */

}
