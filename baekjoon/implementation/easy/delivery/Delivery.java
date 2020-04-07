package baekjoon.implementation.easy.delivery;

/**
 * 설탕배탈
 */
public class Delivery {
    public int sol(int n){
        int k = n/5;
        int re = n%5,sum=0;
        if (re==0) return k;
        while(k>=0){
            if ((re+sum)%3==0) return k+((re+sum)/3);
            k--;
            sum+=5;
        }
        return -1;
    }

    public static void main(String[] args) {
        int n = 11;
        Delivery delivery = new Delivery();
        System.out.println(delivery.sol(n));
    }
}
