package samsungSW.d4.justRule.oddMid;

/**
 * N층의 홀수 피라미드
 * 2N-1개, 2N-3개,,,,1개
 *
 */
public class OddMidPyramid {
    //결론은 x=1, x=2n-1 일때를 제외하고는 모든 경우수 가능.
    //중간값이므로,
    public static int oddMid(int n,int x){
        //0 은 불가능,
        if (x==1 ||x==2*n-1) return 0;
        //1은 가능.
        return 1;
    }
    public static void main(String[] args) {

    }

}
