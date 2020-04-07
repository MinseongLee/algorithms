package baekjoon.implementation.superEasy.clock;

public class Clock {
    public String sol(int h,int m){
        m -= 45;
        if (m<0){
            h--;
            if (h<0) h = 23;
            m += 60;
        }
        return h+" "+m;
    }

    public static void main(String[] args) {
        Clock clock = new Clock();
//        System.out.println(clock.sol(h,m));
    }
}
